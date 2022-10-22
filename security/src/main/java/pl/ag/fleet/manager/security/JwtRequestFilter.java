package pl.ag.fleet.manager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Value("${secret}")
  private String secret;
  @Autowired
  private UserRepository repository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    final String token = header.substring(7);
    final String username = validateTokenAndGetUsername(token);
    if (username == null) {
      // validation failed or token expired
      filterChain.doFilter(request, response);
      return;
    }

    // set user details on spring security context
    val user = repository.findByUsername(new Username(username)).orElseThrow();
    val userDetails = new User(user.getUserId().getId(), user.getPassword().getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority("USER")));
    final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // continue with authenticated user
    filterChain.doFilter(request, response);
  }

  public String validateTokenAndGetUsername(final String token) {
    try {
      return JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getSubject();
    } catch (final JWTVerificationException verificationEx) {
      log.warn("token invalid: {}", verificationEx.getMessage());
      return null;
    }
  }
}

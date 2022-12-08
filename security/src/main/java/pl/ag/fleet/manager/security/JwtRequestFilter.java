package pl.ag.fleet.manager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import java.io.IOException;
import java.util.Map;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private UserRepository repository;

  @Value("${secret}")
  private String secret;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    val payload = validateTokenAndGetPayload(header);
    if (payload == null) {
      // validation failed or token expired
      filterChain.doFilter(request, response);
      return;
    }

    // set user details on spring security context
    val user = repository.findBy(new Username(payload.get("username").asString())).orElseThrow();
    val authentication = new AuthenticatedUser(
        new PrincipalData(user.getUserId().getId(), user.getUsername().getUsername(),
            user.getCompanyId().getCompanyId(),
            user.getRole()), null);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    // continue with authenticated user
    filterChain.doFilter(request, response);
  }

  public Map<String, Claim> validateTokenAndGetPayload(final String header) {
    try {
      final String token = header.substring(7);
      return JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getClaims();
    } catch (final JWTVerificationException verificationEx) {
      log.warn("token invalid: {}", verificationEx.getMessage());
      return null;
    }
  }


}

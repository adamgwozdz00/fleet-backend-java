package pl.ag.fleet.manager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TokenService {

  private final UserRepository repository;
  @Value("${expiresAfter}")
  private long expiresAfter;
  @Value("${secret}")
  private String secret;

  Token generateToken(Username username) {
    val user = repository.findBy(username).orElseThrow();
    return doGenerateToken(user);
  }

  private Token doGenerateToken(User user) {
    long currentTime = System.currentTimeMillis();
    long expiresAt = currentTime + expiresAfter;
    val token = JWT.create()
        .withClaim("userId", user.getUserId().getId())
        .withClaim("username", user.getUsername().getUsername())
        .withClaim("role", user.getRole().roleName)
        .withClaim("companyId", user.getCompanyId().getCompanyId())
        .withIssuedAt(new Date(currentTime))
        .withExpiresAt(new Date(expiresAt))
        .sign(Algorithm.HMAC256(secret));
    return new Token(token, expiresAt);
  }
}

package pl.ag.fleet.manager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import java.util.Map;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTUserDecoderService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Value("${secret}")
  private String secret;

  public Map<String, Claim> validateTokenAndGetPayload(final String header) {
    try {
      final String token = header.substring(7);
      return JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getClaims();
    } catch (final JWTVerificationException verificationEx) {
      log.warn("token invalid: {}", verificationEx.getMessage());
      return null;
    }
  }

  public JWTDecodedUser decode(final String token) {
    val claims = validateTokenAndGetPayload(token);
    return new JWTDecodedUser(claims.get("userId").asString(), claims.get("role").asString(),
        claims.get("companyId").asLong());
  }

}

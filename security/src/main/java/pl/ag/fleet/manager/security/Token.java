package pl.ag.fleet.manager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import lombok.Value;

@Value
class Token {

  private String token;

  static Token createToken(String username, String secret, long expiresAfter) {
    return new Token(JWT.create()
        .withSubject(username)
        .withExpiresAt(new Date(System.currentTimeMillis() + expiresAfter))
        .sign(Algorithm.HMAC256(secret)));
  }
}

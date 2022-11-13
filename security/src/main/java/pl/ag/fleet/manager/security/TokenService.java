package pl.ag.fleet.manager.security;

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
    val payload = new TokenPayload(user.getUserId().getId(), user.getRole(),
        user.getCompanyId().getCompanyId());
    return Token.createToken(payload, secret, expiresAfter);
  }
}

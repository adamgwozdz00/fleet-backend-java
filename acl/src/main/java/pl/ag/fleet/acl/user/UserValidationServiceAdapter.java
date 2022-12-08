package pl.ag.fleet.acl.user;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import pl.ag.fleet.auth.AuthUserRepository;
import pl.ag.fleet.user.UserId;
import pl.ag.fleet.user.UserIdentityValidationService;

@Component
@RequiredArgsConstructor
public class UserValidationServiceAdapter implements UserIdentityValidationService {

  private final AuthUserRepository authUserRepository;

  // TODO validate if user is in same company as creator
  @Override
  public boolean isValid(UserId userId) {
    val user = this.authUserRepository.load(userId.getUserId());
    return user.isPresent();
  }
}

package pl.ag.fleet.user;

public interface UserIdentityValidationService {

  boolean isValid(UserId userId);
}

package pl.ag.fleet.application.user;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import pl.ag.fleet.application.user.UserData.Routes;
import pl.ag.fleet.company.CompanyProvider;
import pl.ag.fleet.manager.security.AuthenticatedUser;

@Component
@RequiredArgsConstructor
public class UserDataFactory {

  private final CompanyProvider companyDataProvider;

  public UserData createData(AuthenticatedUser user) {
    val role = user.getPrincipal().getRole();
    val companyData = companyDataProvider.getCompanyData(user.getPrincipal().getCompanyId());
    val userRoutes = new UserData(companyData.getCompanyName())
        .route(Routes.VEHICLES)
        .route(Routes.DRIVERS)
        .route(Routes.ACCOUNTS);

    switch (role) {
      case USER:
        return userRoutes;
      case ADMIN:
        return userRoutes.route(Routes.USERS);
      default:
        throw new UnsupportedOperationException("Not supported user type");
    }
  }
}

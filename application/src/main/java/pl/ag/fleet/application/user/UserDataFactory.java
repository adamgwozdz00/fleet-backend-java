package pl.ag.fleet.application.user;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import pl.ag.fleet.application.user.SelfUserData.Routes;
import pl.ag.fleet.company.CompanyProvider;
import pl.ag.fleet.manager.security.AuthenticatedUser;

@Component
@RequiredArgsConstructor
public class UserDataFactory {

  private final CompanyProvider companyDataProvider;

  public SelfUserData createData(AuthenticatedUser user) {
    val role = user.getPrincipal().getRole();
    val companyData = companyDataProvider.getCompanyData(user.getPrincipal().getCompanyId());

    switch (role) {
      case USER:
        return new SelfUserData(companyData.getCompanyName())
            .route(Routes.VEHICLES,1)
            .route(Routes.DRIVERS,2)
            .route(Routes.REPORTS,3)
            .route(Routes.ACCOUNTS,4);
      case ADMIN:
        return new SelfUserData(companyData.getCompanyName())
            .route(Routes.VEHICLES,1)
            .route(Routes.DRIVERS,2)
            .route(Routes.USERS,3)
            .route(Routes.REPORTS,4)
            .route(Routes.ACCOUNTS,5);
      default:
        throw new UnsupportedOperationException("Not supported user type");
    }
  }
}

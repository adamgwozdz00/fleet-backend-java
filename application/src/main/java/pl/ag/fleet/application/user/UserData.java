package pl.ag.fleet.application.user;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserData {

  private String companyName;
  private Set<String> routes;

  public UserData(String companyName) {
    this.companyName = companyName;
    this.routes = new HashSet<>();


  }

  public UserData route(Routes route) {
    this.routes.add(route.routeName);
    return this;
  }

  @AllArgsConstructor
  @Getter
  public enum Routes {
    USERS("users"),
    VEHICLES("vehicles"),
    DRIVERS("drivers"),
    ACCOUNTS("account");

    final String routeName;

  }
}

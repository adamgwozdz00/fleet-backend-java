package pl.ag.fleet.application.user;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {

  private Long companyId;
  private String userRole;
}

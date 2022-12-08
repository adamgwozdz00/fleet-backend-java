package pl.ag.fleet.application.authentication;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ag.fleet.auth.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotEmpty
  private String username;
  @NotEmpty
  private String password;
  @NotNull
  private UserRole role;
  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  @NotEmpty
  private String title;
}

package pl.ag.fleet.application.user;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyUserRequest {

  @NotNull
  public Long userId;
}

package pl.ag.fleet.application.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.manager.security.AuthenticatedUserContextHolder;
import pl.ag.fleet.user.VehicleUserDTO;

@Component
@RequiredArgsConstructor
public class UserRequestFactory {

  private final AuthenticatedUserContextHolder contextHolder;

  VehicleUserDTO create(UserParams params, long companyId) {
    if (params.getUserId() != null) {
      return new VehicleUserDTO(params.getUserId(), params.getVehicleId(), companyId);
    }
    return new VehicleUserDTO(contextHolder.getAuthenticatedUser().getPrincipal().getUserId(),
        params.getVehicleId(), companyId);
  }
}

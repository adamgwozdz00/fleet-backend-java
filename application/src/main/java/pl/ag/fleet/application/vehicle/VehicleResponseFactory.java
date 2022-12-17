package pl.ag.fleet.application.vehicle;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import pl.ag.fleet.manager.security.AuthenticatedUserContextHolder;
import pl.ag.fleet.manager.security.UserRole;
import pl.ag.fleet.vehicle.VehicleProvider;

@Component
@RequiredArgsConstructor
public class VehicleResponseFactory {

  private final AuthenticatedUserContextHolder contextHolder;

  public Vehicles create(VehicleParams params, VehicleProvider vehicleProvider) {
    val principal = contextHolder.getAuthenticatedUser().getPrincipal();
    if (is(params.getWithoutAssigment())) {
      return new Vehicles(vehicleProvider.getNotAssignedVehicles(principal.getCompanyId()));
    }

    if (params.getUserId() != null) {
      return new Vehicles(vehicleProvider.getVehicleByUserId(params.getUserId()));
    }

    if (principal.getRole() == UserRole.ADMIN) {
      return new Vehicles(vehicleProvider.getVehiclesByCompany(principal.getCompanyId()));
    }

    return new Vehicles(vehicleProvider.getVehicleByUserId(principal.getUserId()));
  }

  private boolean is(Boolean withoutAssigment) {
    if (withoutAssigment == null) {
      return false;
    }
    return withoutAssigment;
  }


}

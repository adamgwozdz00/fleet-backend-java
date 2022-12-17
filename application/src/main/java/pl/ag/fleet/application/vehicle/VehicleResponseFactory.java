package pl.ag.fleet.application.vehicle;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import pl.ag.fleet.common.Availability;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.common.UserId;
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
      return new Vehicles(
          vehicleProvider.getVehiclesByCompany(new CompanyId(principal.getCompanyId()),
              Availability.AVAILABLE));
    }

    if (params.getUserId() != null) {
      return new Vehicles(vehicleProvider.getVehicleByUserId(new UserId(params.getUserId())));
    }

    if (principal.getRole() == UserRole.ADMIN) {
      return new Vehicles(
          vehicleProvider.getVehiclesByCompany(new CompanyId(principal.getCompanyId()),
              Availability.EMPTY));
    }

    return new Vehicles(vehicleProvider.getVehicleByUserId(new UserId(principal.getUserId())));
  }

  private boolean is(Boolean withoutAssigment) {
    if (withoutAssigment == null) {
      return false;
    }
    return withoutAssigment;
  }


}

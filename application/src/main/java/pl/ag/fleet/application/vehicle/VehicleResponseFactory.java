package pl.ag.fleet.application.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.manager.security.PrincipalData;
import pl.ag.fleet.manager.security.UserRole;
import pl.ag.fleet.vehicle.VehicleProvider;

@Component
@RequiredArgsConstructor
public class VehicleResponseFactory {

  public Vehicles create(PrincipalData principal, VehicleProvider vehicleProvider) {
    if (principal.getRole() == UserRole.ADMIN) {
      return new Vehicles(vehicleProvider.getVehiclesByCompany(principal.getCompanyId()));
    }
    return new Vehicles(vehicleProvider.getVehicleByUserId(principal.getUserId()));
  }
}

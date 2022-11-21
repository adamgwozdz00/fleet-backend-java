package pl.ag.fleet.application.vehicle;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.manager.security.PrincipalData;
import pl.ag.fleet.vehicle.VehicleProvider;

@Component
@RequiredArgsConstructor
public class VehicleResponseFactory {

  public Vehicles create(PrincipalData principal, VehicleProvider vehicleProvider) {
    if (Objects.equals(principal.getRole(), "admin")) {
      return new Vehicles(vehicleProvider.getVehiclesByCompany(principal.getCompanyId()));
    }
    return new Vehicles(vehicleProvider.getVehicleByUserId(principal.getUserId()));
  }
}

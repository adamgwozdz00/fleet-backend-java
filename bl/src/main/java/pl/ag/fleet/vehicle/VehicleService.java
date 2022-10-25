package pl.ag.fleet.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {

  private final VehicleRepository vehicleRepository;

  public void createVehicle(VehicleDTO vehicle) {
    this.vehicleRepository.save(new Vehicle(new CompanyId(vehicle.getCompanyId()),
        new VehicleDetails(vehicle.getMake(), vehicle.getModel(), vehicle.getProductionYear(),
            vehicle.getFuelType(), vehicle.getVinNumber())));
  }
}

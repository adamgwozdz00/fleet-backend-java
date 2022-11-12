package pl.ag.fleet.vehicle;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {

  private final VehicleRepository vehicleRepository;

  public synchronized void createVehicle(VehicleDTO vehicle) {
    this.vehicleRepository.save(new Vehicle(new CompanyId(vehicle.getCompanyId()),
        new VehicleDetails(vehicle.getMake(), vehicle.getModel(), vehicle.getProductionYear(),
            vehicle.getFuelType(), vehicle.getVinNumber())));
  }

  public synchronized void updateVehicleState(VehicleId vehicleId, VehicleStateDTO state) {
    val vehicle = this.vehicleRepository.load(vehicleId);

    if (!exists(vehicle)) {
      throw new IllegalStateException("Vehicle not exists");
    }
    vehicle.updateState(
        new VehicleState(new DriverId(state.getDriverId()), new Liters(state.getLiters()),
            new Kilometers(state.getKilometers()), state.getTime(), vehicleId));

    this.vehicleRepository.save(vehicle);

  }

  public synchronized void addOrUpdateInsurance(VehicleId vehicleId, InsuranceDTO insurance) {
    val vehicle = this.vehicleRepository.load(vehicleId);

    if (!exists(vehicle)) {
      throw new IllegalStateException("Vehicle not exists");
    }
    vehicle.updateInsurance(
        new Insurance(insurance.getInsuranceTitle(), insurance.getExpirationDate(),
            insurance.getCost(), vehicleId));

    this.vehicleRepository.save(vehicle);

  }

  public synchronized void addOrUpdateOverview(VehicleId vehicleId, OverviewDTO overview) {
    val vehicle = this.vehicleRepository.load(vehicleId);

    if (!exists(vehicle)) {
      throw new IllegalStateException("Vehicle not exists");
    }

    vehicle.updateOverview(
        new Overview(overview.getOverviewTitle(), overview.getExpirationDate(),
            overview.getCost(), overview.getDescription(), vehicleId));

    this.vehicleRepository.save(vehicle);
  }

  private boolean exists(Vehicle vehicle) {
    return vehicle != null;
  }
}

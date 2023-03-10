package pl.ag.fleet.vehicle;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.common.Liters;
import pl.ag.fleet.common.VehicleId;

@Service
@RequiredArgsConstructor
public class VehicleService {

  private final VehicleRepository vehicleRepository;
  private final DriverAvailabilityService driverAvailabilityService;

  public synchronized void createVehicle(VehicleDTO vehicle) {
    this.vehicleRepository.save(new Vehicle(new CompanyId(vehicle.getCompanyId()),
        new VehicleDetails(vehicle.getMake(), vehicle.getModel(), vehicle.getProductionYear(),
            vehicle.getFuelType(), vehicle.getVinNumber())));
  }

  public synchronized void updateRepair(VehicleId vehicleId, RepairDTO repairDTO) {
    val vehicle = this.vehicleRepository.load(vehicleId);

    if (!exists(vehicle)) {
      throw new IllegalStateException("Vehicle not exists");
    }
    vehicle.updateRepair(new Repair(
        TimePeriod.of(repairDTO.getFrom(), repairDTO.getTo()),
        new ServiceName(repairDTO.getServiceName()),
        repairDTO.getTitle(),
        repairDTO.getCost()
    ));

    this.vehicleRepository.save(vehicle);
  }

  public synchronized Result updateVehicleState(VehicleId vehicleId, VehicleStateDTO state) {
    val vehicle = this.vehicleRepository.load(vehicleId);

    if (!exists(vehicle)) {
      throw new IllegalStateException("Vehicle not exists");
    }
    val result = vehicle.updateState(
        new VehicleState(new DriverId(state.getDriverId()), new Liters(state.getLiters()),
            new Kilometers(state.getKilometers()), state.getTime(), state.getStatus(),
            new Position(state.getLongitude(), state.getLatitude())), driverAvailabilityService);

    this.vehicleRepository.save(vehicle);

    return result;
  }

  public synchronized void addOrUpdateInsurance(VehicleId vehicleId, InsuranceDTO insurance) {
    val vehicle = this.vehicleRepository.load(vehicleId);

    if (!exists(vehicle)) {
      throw new IllegalStateException("Vehicle not exists");
    }
    vehicle.updateInsurance(
        new Insurance(insurance.getInsuranceTitle(), insurance.getExpirationDate(),
            insurance.getCost()));

    this.vehicleRepository.save(vehicle);

  }

  public synchronized void addOrUpdateOverview(VehicleId vehicleId, OverviewDTO overview) {
    val vehicle = this.vehicleRepository.load(vehicleId);

    if (!exists(vehicle)) {
      throw new IllegalStateException("Vehicle not exists");
    }

    vehicle.updateOverview(
        new Overview(overview.getOverviewTitle(), overview.getExpirationDate(),
            overview.getCost(), overview.getDescription()));

    this.vehicleRepository.save(vehicle);
  }

  public synchronized void delete(VehicleId vehicleId) {
    val vehicle = this.vehicleRepository.load(vehicleId);

    if (!exists(vehicle)) {
      throw new IllegalStateException("Vehicle not exists");
    }

    vehicle.archive();

    this.vehicleRepository.save(vehicle);
  }

  private boolean exists(Vehicle vehicle) {
    return vehicle != null;
  }

}

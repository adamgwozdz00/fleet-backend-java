package pl.ag.fleet.refuel;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.ag.fleet.common.Liters;
import pl.ag.fleet.common.VehicleId;

@Service
@RequiredArgsConstructor
public class RefuelService {

  private final VehicleRepository vehicleRepository;
  private final ReFuelRepository reFuelRepository;

  public void refuelVehicle(RefuelDTO refuelDTO) {
    val vehicleId = refuelDTO.getVehicleId();

    vetoIfVehicleNotExists(vehicleId);

    reFuelRepository.save(
        new ReFuel(vehicleId,
            new Liters(refuelDTO.getLiters()),
            new Cost(refuelDTO.getCost())));
  }

  private void vetoIfVehicleNotExists(VehicleId vehicleId) {
    if (this.vehicleRepository.exists(vehicleId)) {
      return;
    }

    throw new IllegalStateException("Vehicle not exists.");
  }
}

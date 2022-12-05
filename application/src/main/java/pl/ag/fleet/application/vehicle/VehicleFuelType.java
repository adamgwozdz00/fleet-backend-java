package pl.ag.fleet.application.vehicle;

import java.util.Arrays;
import java.util.List;
import lombok.Data;
import pl.ag.fleet.common.FuelType;

@Data
public class VehicleFuelType {

  List<String> fuelTypes;

  public VehicleFuelType() {
    this.fuelTypes = Arrays.asList(
        FuelType.DIESEL.name(),
        FuelType.GASOLINE.name(),
        FuelType.ELECTRIC.name(),
        FuelType.HYBRID.name()
    );
  }
}

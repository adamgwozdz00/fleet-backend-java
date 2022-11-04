package pl.ag.fleet.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class ActualData extends EventData {

  private VehicleId vehicleId;
  private DriverId driverId;
  private Liters fuelStateInLiters;
  private Kilometers kilometersState;

  public ActualData(VehicleId vehicleId, DriverId driverId, Liters fuelStateInLiters,
      Kilometers kilometersState) {
    super(EventType.ACTUAL_VEHICLE_DATA);
    this.vehicleId = vehicleId;
    this.driverId = driverId;
    this.fuelStateInLiters = fuelStateInLiters;
    this.kilometersState = kilometersState;
  }
}

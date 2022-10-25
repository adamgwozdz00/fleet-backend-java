package pl.ag.fleet.event;

import lombok.Value;

@Value
public class ActualDataEvent implements Event {

  private VehicleId vehicleId;
  private DriverId driverId;
  private Liters fuelStateInLiters;
  private Kilometers kilometersState;
  private EventTime time;

  public ActualDataEvent(VehicleId vehicleId, DriverId driverId, Liters fuelStateInLiters,
      Kilometers kilometersState) {
    this.vehicleId = vehicleId;
    this.driverId = driverId;
    this.fuelStateInLiters = fuelStateInLiters;
    this.kilometersState = kilometersState;
    this.time = new EventTime();
  }

  @Override
  public EventType getEventType() {
    return EventType.ACTUAL_VEHICLE_DATA;
  }
}

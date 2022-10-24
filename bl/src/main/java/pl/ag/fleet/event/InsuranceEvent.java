package pl.ag.fleet.event;

import lombok.Value;

@Value
public class InsuranceEvent implements Event {

  private VehicleId vehicleId;
  private EventTime time;
  private Insurance insurance;

  public InsuranceEvent(VehicleId vehicleId, Insurance insurance) {
    this.vehicleId = vehicleId;
    this.insurance = insurance;
    this.time = new EventTime();
  }

  @Override
  public String getEventType() {
    return this.getClass().getSimpleName();
  }
}

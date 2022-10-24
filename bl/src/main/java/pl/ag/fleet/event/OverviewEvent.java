package pl.ag.fleet.event;

import lombok.Value;

@Value
public class OverviewEvent implements Event {

  private VehicleId vehicleId;
  private EventTime time;
  private Overview overview;

  public OverviewEvent(VehicleId vehicleId, Overview overview) {
    this.vehicleId = vehicleId;
    this.overview = overview;
    this.time = new EventTime();
  }

  @Override
  public String getEventType() {
    return this.getClass().getSimpleName();
  }
}

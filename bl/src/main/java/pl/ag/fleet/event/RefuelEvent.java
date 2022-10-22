package pl.ag.fleet.event;

import lombok.Value;

@Value
public class RefuelEvent implements Event {

  private VehicleId vehicleId;
  private Liters liters;
  private Cost cost;
  private EventTime time;

  public RefuelEvent(VehicleId vehicleId, Liters liters, Cost cost) {
    this.vehicleId = vehicleId;
    this.liters = liters;
    this.cost = cost;
    this.time = new EventTime();
  }
}

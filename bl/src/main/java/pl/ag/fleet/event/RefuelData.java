package pl.ag.fleet.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RefuelData extends EventData {

  private VehicleId vehicleId;
  private Liters liters;
  private Cost cost;

  public RefuelData(VehicleId vehicleId, Liters liters, Cost cost) {
    super(EventType.REFUEL);
    this.vehicleId = vehicleId;
    this.liters = liters;
    this.cost = cost;
  }

}

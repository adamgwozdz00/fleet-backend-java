package pl.ag.fleet.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class OverviewData extends EventData {

  private VehicleId vehicleId;
  private Overview overview;

  public OverviewData(VehicleId vehicleId, Overview overview) {
    super(EventType.OVERVIEW);
    this.vehicleId = vehicleId;
    this.overview = overview;
  }

}

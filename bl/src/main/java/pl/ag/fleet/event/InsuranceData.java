package pl.ag.fleet.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class InsuranceData extends EventData {

  private VehicleId vehicleId;
  private Insurance insurance;

  public InsuranceData(VehicleId vehicleId, Insurance insurance) {
    super(EventType.INSURANCE);
    this.vehicleId = vehicleId;
    this.insurance = insurance;
  }

}

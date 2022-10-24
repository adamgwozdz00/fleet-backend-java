package pl.ag.fleet.event;

import lombok.Value;
import pl.ag.fleet.common.FuelType;

@Value
public class CreateVehicleEvent implements Event {

  private VinNumber vinNumber;
  private CompanyId companyId;
  private Make make;
  private Model model;
  private ProductionYear productionYear;
  private FuelType fuelType;
  private EventTime time;

  public CreateVehicleEvent(VinNumber vinNumber, CompanyId companyId, Make make, Model model,
      ProductionYear productionYear, FuelType fuelType) {
    this.vinNumber = vinNumber;
    this.companyId = companyId;
    this.make = make;
    this.model = model;
    this.productionYear = productionYear;
    this.fuelType = fuelType;
    this.time = new EventTime();
  }

  @Override
  public String getEventType() {
    return this.getClass().getSimpleName();
  }
}

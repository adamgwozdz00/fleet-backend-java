package pl.ag.fleet.event;

import lombok.EqualsAndHashCode;
import lombok.Value;
import pl.ag.fleet.common.FuelType;

@Value
@EqualsAndHashCode(callSuper = true)
public class CreateVehicleData extends EventData {

  private VinNumber vinNumber;
  private CompanyId companyId;
  private Make make;
  private Model model;
  private ProductionYear productionYear;
  private FuelType fuelType;

  public CreateVehicleData(VinNumber vinNumber, CompanyId companyId, Make make, Model model,
      ProductionYear productionYear, FuelType fuelType) {
    super(EventType.CREATE_VEHICLE);
    this.vinNumber = vinNumber;
    this.companyId = companyId;
    this.make = make;
    this.model = model;
    this.productionYear = productionYear;
    this.fuelType = fuelType;
  }

}

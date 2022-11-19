package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class VehicleRecord {

  private String vehicleId;
  private String make;
  private String model;
  private Integer productionYear;
  private String fuelType;
  private String vinNumber;
  private BigDecimal kilometers;
}

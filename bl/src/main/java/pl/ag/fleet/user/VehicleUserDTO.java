package pl.ag.fleet.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleUserDTO {

  private long userId;
  private String vehicleId;
  private long companyId;
}

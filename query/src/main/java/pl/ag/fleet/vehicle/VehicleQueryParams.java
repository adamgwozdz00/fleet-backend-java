package pl.ag.fleet.vehicle;

import lombok.Data;
import pl.ag.fleet.common.Availability;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.common.UserId;

@Data
public class VehicleQueryParams {

  private CompanyId companyId;
  private UserId userId;

  private Availability availability;
}

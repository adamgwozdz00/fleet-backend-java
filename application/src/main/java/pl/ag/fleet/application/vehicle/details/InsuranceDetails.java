package pl.ag.fleet.application.vehicle.details;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsuranceDetails {

  private List<InsuranceDetail> insuranceList;
}

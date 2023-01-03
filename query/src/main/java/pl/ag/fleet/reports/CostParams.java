package pl.ag.fleet.reports;

import java.time.Year;
import java.util.List;
import lombok.Value;

@Value
public class CostParams {

  private Long companyId;
  private List<Year> years;
}

package pl.ag.fleet.reports;

import java.time.Year;
import java.util.List;
import lombok.Value;
import pl.ag.fleet.common.CompanyId;

@Value
public class CostQueryParams {

  private CompanyId companyId;
  private List<Year> years;

  public boolean isNotEmptyYears() {
    return !this.years.isEmpty();
  }
}

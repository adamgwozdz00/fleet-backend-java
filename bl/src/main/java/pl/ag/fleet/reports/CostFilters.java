package pl.ag.fleet.reports;

import java.time.Year;
import java.util.List;
import lombok.Value;

@Value
public class CostFilters {

  private Long companyId;
  private List<Year> years;

  private boolean includeFuelCost;
  private boolean includeOverviewCost;
  private boolean includeInsuranceCost;
  private boolean includeRepairCost;
}

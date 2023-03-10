package pl.ag.fleet.application.reports;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostParams {

  private List<Integer> years = List.of();

  private boolean includeFuelCost = false;
  private boolean includeOverviewCost = false;
  private boolean includeInsuranceCost = false;
  private boolean includeRepairCost = false;
}

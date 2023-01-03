package pl.ag.fleet.reports;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostData {

  private FuelCostData fuelCostData;
  private InsuranceCostData insuranceCostData;
  private OverviewCostData overviewCostData;
  private RepairCostData repairCostData;

  private BigDecimal totalCost;
}

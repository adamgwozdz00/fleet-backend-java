package pl.ag.fleet.reports;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostData {

  private FuelCost fuelCost;
  private InsuranceCost insuranceCost;
  private OverviewCost overviewCost;
  private RepairCost repairCost;

  private TotalCost totalCost;

  public void calculateTotalCost() {
    var totalCost = new TotalCost(BigDecimal.ZERO);
    if (fuelCost != null) {
      totalCost = totalCost.add(this.fuelCost);
    }
    if (insuranceCost != null) {
      totalCost = totalCost.add(this.insuranceCost);
    }
    if (overviewCost != null) {
      totalCost = totalCost.add(this.overviewCost);
    }
    if (repairCost != null) {
      totalCost = totalCost.add(this.repairCost);
    }

    this.totalCost = totalCost;
  }
}

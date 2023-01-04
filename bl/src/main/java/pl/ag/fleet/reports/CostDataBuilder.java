package pl.ag.fleet.reports;

import java.math.BigDecimal;

public class CostDataBuilder {

  private final CostData costData;

  CostDataBuilder() {
    this.costData = new CostData(
        new FuelCost(BigDecimal.ZERO),
        new InsuranceCost(BigDecimal.ZERO),
        new OverviewCost(BigDecimal.ZERO),
        new RepairCost(BigDecimal.ZERO),
        new TotalCost(BigDecimal.ZERO)
    );
  }

  CostDataBuilder withInsuranceCost(InsuranceCost insuranceCost) {
    this.costData.setInsuranceCost(insuranceCost);
    return this;
  }

  CostDataBuilder withOverviewCost(OverviewCost overviewCost) {
    this.costData.setOverviewCost(overviewCost);
    return this;
  }

  CostDataBuilder withRepairCost(RepairCost repairCost) {
    this.costData.setRepairCost(repairCost);
    return this;
  }

  CostDataBuilder withFuelCost(FuelCost fuelCost) {
    this.costData.setFuelCost(fuelCost);
    return this;
  }

  CostDataBuilder withTotalCost(TotalCost totalCost) {
    this.costData.setTotalCost(totalCost);
    return this;
  }

  public CostDataBuilder withCalculatedTotalCost() {
    this.costData.calculateTotalCost();
    return this;
  }

  CostData build() {
    return costData;
  }

}

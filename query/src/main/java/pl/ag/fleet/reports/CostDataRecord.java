package pl.ag.fleet.reports;

import lombok.Value;

@Value
public class CostDataRecord {

  private RefuelCostDataRecord refuelCostDataRecord;
  private OverviewCostDataRecord overviewCostDataRecord;
  private InsuranceCostDataRecord insuranceCostDataRecord;
  private RepairCostDataRecord repairCostDataRecord;
  private TotalCostRecord totalCostRecord;

}

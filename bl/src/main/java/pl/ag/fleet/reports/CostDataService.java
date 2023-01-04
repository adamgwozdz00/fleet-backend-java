package pl.ag.fleet.reports;

public interface CostDataService {

  InsuranceCost getInsuranceCost(CostFilters costFilters);

  FuelCost getFueLCost(CostFilters costFilters);

  OverviewCost getOverviewCost(CostFilters costFilters);

  RepairCost getRepairCost(CostFilters costFilters);
}

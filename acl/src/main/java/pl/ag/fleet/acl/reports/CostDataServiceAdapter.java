package pl.ag.fleet.acl.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.reports.CostDataProvider;
import pl.ag.fleet.reports.CostDataService;
import pl.ag.fleet.reports.CostFilters;
import pl.ag.fleet.reports.FuelCost;
import pl.ag.fleet.reports.InsuranceCost;
import pl.ag.fleet.reports.OverviewCost;
import pl.ag.fleet.reports.RepairCost;

@Component
@RequiredArgsConstructor
public class CostDataServiceAdapter implements CostDataService {

  private final CostDataProvider costDataProvider;

  @Override
  public InsuranceCost getInsuranceCost(CostFilters costFilters) {
    return InsuranceCost.of(
        costDataProvider.providerInsurance(new CostParamsFactory().create(costFilters)).getCost());
  }

  @Override
  public FuelCost getFueLCost(CostFilters costFilters) {
    return FuelCost.of(
        costDataProvider.providerRefuel(new CostParamsFactory().create(costFilters)).getCost());
  }

  @Override
  public OverviewCost getOverviewCost(CostFilters costFilters) {
    return OverviewCost.of(
        costDataProvider.provideOverview(new CostParamsFactory().create(costFilters)).getCost());
  }

  @Override
  public RepairCost getRepairCost(CostFilters costFilters) {
    return RepairCost.of(
        costDataProvider.provideRepair(new CostParamsFactory().create(costFilters)).getCost());
  }
}

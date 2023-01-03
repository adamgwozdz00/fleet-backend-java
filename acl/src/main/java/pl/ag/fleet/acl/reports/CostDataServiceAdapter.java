package pl.ag.fleet.acl.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ag.fleet.reports.CostData;
import pl.ag.fleet.reports.CostDataProvider;
import pl.ag.fleet.reports.CostDataRecord;
import pl.ag.fleet.reports.CostDataService;
import pl.ag.fleet.reports.CostFilters;
import pl.ag.fleet.reports.CostParams;
import pl.ag.fleet.reports.FuelCostData;
import pl.ag.fleet.reports.InsuranceCostData;
import pl.ag.fleet.reports.OverviewCostData;
import pl.ag.fleet.reports.RepairCostData;

@Component
@RequiredArgsConstructor
public class CostDataServiceAdapter implements CostDataService {

  private final CostDataProvider costDataProvider;

  @Override
  public CostData getData(CostFilters costFilters) {
    CostDataRecord data = costDataProvider.provide(
        new CostParams(costFilters.getCompanyId(), costFilters.getYears()));
    return new CostData(
        new FuelCostData(data.getRefuelCostDataRecord().getCost()),
        new InsuranceCostData(data.getInsuranceCostDataRecord().getCost()),
        new OverviewCostData(data.getOverviewCostDataRecord().getCost()),
        new RepairCostData(data.getRepairCostDataRecord().getCost()),
        data.getTotalCostRecord().getCost()
    );
  }
}

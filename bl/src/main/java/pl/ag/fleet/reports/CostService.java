package pl.ag.fleet.reports;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostService {


  private final CostDataService costDataService;

  public CostData getCosts(CostFilters costFilters) {

    var costDataBuilder = new CostDataBuilder();

    if (costFilters.isIncludeFuelCost()) {
      val refuelCost = costDataService.getFueLCost(costFilters);
      costDataBuilder.withFuelCost(refuelCost);
    }

    if (costFilters.isIncludeInsuranceCost()) {
      val insuranceCost = costDataService.getInsuranceCost(costFilters);
      costDataBuilder.withInsuranceCost(insuranceCost);
    }

    if (costFilters.isIncludeOverviewCost()) {
      val overviewCost = costDataService.getOverviewCost(costFilters);
      costDataBuilder.withOverviewCost(overviewCost);

    }

    if (costFilters.isIncludeRepairCost()) {
      val repairCost = costDataService.getRepairCost(costFilters);
      costDataBuilder.withRepairCost(repairCost);
    }

    return costDataBuilder
        .withCalculatedTotalCost()
        .build();
  }


}

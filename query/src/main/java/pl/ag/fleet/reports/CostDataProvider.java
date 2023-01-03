package pl.ag.fleet.reports;

import static nu.studer.sample.Tables.INSURANCE;
import static nu.studer.sample.Tables.OVERVIEW;
import static nu.studer.sample.Tables.REPAIR;
import static nu.studer.sample.Tables.RE_FUEL;
import static org.jooq.impl.DSL.sum;
import static org.jooq.impl.DSL.year;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.common.VehicleId;

@Component
@RequiredArgsConstructor
public class CostDataProvider {

  private final DSLContext create;
  private final CommonKnowledgeProvider commonKnowledgeProvider;

  public CostDataRecord provide(CostParams costParams) {
    val vehicleIds = commonKnowledgeProvider.getVehiclesIdsOfCompany(
        new CompanyId(costParams.getCompanyId())).stream().map(VehicleId::getVehicleId).collect(
        Collectors.toList());

    val refuelCost = providerRefuel(costParams, vehicleIds);
    val insuranceCost = providerInsurance(costParams, vehicleIds);
    val overviewCost = provideOverview(costParams, vehicleIds);
    val repairCost = provideRepair(costParams, vehicleIds);
    val totalCost = new TotalCostRecord(
        refuelCost
            .add(insuranceCost)
            .add(overviewCost)
            .add(repairCost).
            getCost());
    return new CostDataRecord(refuelCost, overviewCost, insuranceCost, repairCost, totalCost);
  }

  private OverviewCostDataRecord provideOverview(CostParams costParams, List<String> vehicleIds) {
    var base = create.select(sum(OVERVIEW.OVERVIEW_COST))
        .from(OVERVIEW)
        .where(OVERVIEW.VEHICLE_ID.in(vehicleIds));

    if (!costParams.getYears().isEmpty()) {
      base.and(year(OVERVIEW.OVERVIEW_EXPIRATION_DATE).minus(1).in(costParams.getYears()));
    }

    return base.fetchOne()
        .into(OverviewCostDataRecord.class);
  }

  private InsuranceCostDataRecord providerInsurance(CostParams costParams,
      List<String> vehicleIds) {
    var base = create.select(sum(INSURANCE.INSURANCE_COST))
        .from(INSURANCE)
        .where(INSURANCE.VEHICLE_ID.in(vehicleIds));

    if (!costParams.getYears().isEmpty()) {
      base.and(year(INSURANCE.INSURANCE_EXPIRATION_DATE).minus(1).in(costParams.getYears()));
    }

    return base.fetchOne()
        .into(InsuranceCostDataRecord.class);
  }

  private RefuelCostDataRecord providerRefuel(CostParams costParams, List<String> vehicleIds) {
    var base = create.select(sum(RE_FUEL.COST))
        .from(RE_FUEL)
        .where(RE_FUEL.VEHICLE_ID.in(vehicleIds));

    if (!costParams.getYears().isEmpty()) {
      base.and(year(RE_FUEL.TIME).in(costParams.getYears()));
    }

    return base.fetchOne()
        .into(RefuelCostDataRecord.class);
  }

  private RepairCostDataRecord provideRepair(CostParams costParams, List<String> vehicleIds) {
    var base = create.select(sum(REPAIR.COST))
        .from(REPAIR)
        .where(REPAIR.VEHICLE_ID.in(vehicleIds));

    if (!costParams.getYears().isEmpty()) {
      base.and(year(REPAIR.FINISH_TIME).in(costParams.getYears()));
    }

    return base.fetchOne()
        .into(RepairCostDataRecord.class);
  }

}

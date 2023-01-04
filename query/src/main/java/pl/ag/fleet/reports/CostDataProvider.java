package pl.ag.fleet.reports;

import static nu.studer.sample.Tables.INSURANCE;
import static nu.studer.sample.Tables.OVERVIEW;
import static nu.studer.sample.Tables.REPAIR;
import static nu.studer.sample.Tables.RE_FUEL;
import static org.jooq.impl.DSL.sum;
import static org.jooq.impl.DSL.year;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.springframework.stereotype.Component;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.common.VehicleId;

@Component
@RequiredArgsConstructor
public class CostDataProvider {

  private final DSLContext create;
  private final CommonKnowledgeProvider commonKnowledgeProvider;

  public CostRecord provideOverview(CostQueryParams costQueryParams) {
    var base = create.select(sum(OVERVIEW.OVERVIEW_COST))
        .from(OVERVIEW)
        .where(OVERVIEW.VEHICLE_ID.in(getVehicleIds(costQueryParams.getCompanyId())));

    if (!costQueryParams.getYears().isEmpty()) {
      base.and(year(OVERVIEW.OVERVIEW_EXPIRATION_DATE).minus(1).in(costQueryParams.getYears()));
    }

    return finalize(base);
  }


  public CostRecord providerInsurance(CostQueryParams costQueryParams) {
    var base = create.select(sum(INSURANCE.INSURANCE_COST))
        .from(INSURANCE)
        .where(INSURANCE.VEHICLE_ID.in(getVehicleIds(costQueryParams.getCompanyId())));

    if (costQueryParams.isNotEmptyYears()) {
      base.and(year(INSURANCE.INSURANCE_EXPIRATION_DATE).minus(1).in(costQueryParams.getYears()));
    }

    return finalize(base);
  }

  public CostRecord providerRefuel(CostQueryParams costQueryParams) {
    var base = create.select(sum(RE_FUEL.COST))
        .from(RE_FUEL)
        .where(RE_FUEL.VEHICLE_ID.in(getVehicleIds(costQueryParams.getCompanyId())));

    if (costQueryParams.isNotEmptyYears()) {
      base.and(year(RE_FUEL.TIME).in(costQueryParams.getYears()));
    }

    return finalize(base);
  }

  public CostRecord provideRepair(CostQueryParams costQueryParams) {
    var base = create.select(sum(REPAIR.COST))
        .from(REPAIR)
        .where(REPAIR.VEHICLE_ID.in(getVehicleIds(costQueryParams.getCompanyId())));

    if (costQueryParams.isNotEmptyYears()) {
      base.and(year(REPAIR.FINISH_TIME).in(costQueryParams.getYears()));
    }

    return finalize(base);
  }

  private CostRecord finalize(SelectConditionStep<Record1<BigDecimal>> base) {
    return Optional.ofNullable(base.fetchOne().into(CostRecord.class))
        .orElse(new CostRecord(BigDecimal.ZERO));
  }

  private List<String> getVehicleIds(CompanyId companyId) {
    return commonKnowledgeProvider.getVehiclesIdsOfCompany(companyId).stream()
        .map(VehicleId::getVehicleId)
        .collect(Collectors.toList());
  }

}

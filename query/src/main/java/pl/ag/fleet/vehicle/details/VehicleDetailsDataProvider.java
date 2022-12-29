package pl.ag.fleet.vehicle.details;

import static nu.studer.sample.Tables.DRIVER;
import static nu.studer.sample.Tables.INSURANCE;
import static nu.studer.sample.Tables.OVERVIEW;
import static nu.studer.sample.Tables.VEHICLE;
import static nu.studer.sample.Tables.VEHICLE_STATE;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleDetailsDataProvider {

  private final DSLContext context;

  public List<InsuranceRecord> getInsuranceHistory(String vehicleId) {
    return
        context.select(INSURANCE.ID, INSURANCE.INSURANCE_COST, INSURANCE.INSURANCE_EXPIRATION_DATE,
                INSURANCE.INSURANCE_NAME)
            .from(INSURANCE)
            .where(INSURANCE.VEHICLE_ID.eq(vehicleId))
            .orderBy(INSURANCE.INSURANCE_EXPIRATION_DATE)
            .fetch()
            .into(InsuranceRecord.class);

  }

  public Optional<InsuranceRecord> getActualInsurance(String vehicleId) {
    return
        Optional.ofNullable(context.select(INSURANCE.ID, INSURANCE.INSURANCE_COST,
                    INSURANCE.INSURANCE_EXPIRATION_DATE,
                    INSURANCE.INSURANCE_NAME)
                .from(INSURANCE)
                .join(VEHICLE)
                .on(VEHICLE.INSURANCE_ID.eq(INSURANCE.ID))
                .where(INSURANCE.VEHICLE_ID.eq(vehicleId))
                .fetchOne())
            .map(record -> record.into(InsuranceRecord.class));
  }

  public List<DriverHistoryRecord> getDriverHistory(String vehicleId) {
    return
        context.select(DRIVER.ID, DRIVER.LAST_NAME, VEHICLE_STATE.KILOMETERS, VEHICLE_STATE.TIME)
            .from(VEHICLE_STATE)
            .join(DRIVER)
            .on(DRIVER.ID.eq(VEHICLE_STATE.ACTUAL_DRIVER_ID))
            .where(VEHICLE_STATE.VEHICLE_ID.eq(vehicleId))
            .orderBy(VEHICLE_STATE.TIME)
            .fetch()
            .into(DriverHistoryRecord.class);
  }

  public List<OverviewRecord> getOverviewData(String vehicleId, boolean onlyActual) {
    if (onlyActual) {
      return this.getActualOverview(vehicleId).stream().collect(Collectors.toList());
    }
    return this.getOverviewHistory(vehicleId);
  }

  public List<InsuranceRecord> getInsuranceData(String vehicleId, boolean onlyActual) {
    if (onlyActual) {
      return this.getActualInsurance(vehicleId).stream().collect(Collectors.toList());
    }
    return this.getInsuranceHistory(vehicleId);
  }

  public List<OverviewRecord> getOverviewHistory(String vehicleId) {
    return
        context.select(OVERVIEW.ID, OVERVIEW.OVERVIEW_COST, OVERVIEW.OVERVIEW_EXPIRATION_DATE,
                OVERVIEW.OVERVIEW_NAME, OVERVIEW.OVERVIEW_DESCRIPTION)
            .from(OVERVIEW)
            .where(OVERVIEW.VEHICLE_ID.eq(vehicleId))
            .orderBy(OVERVIEW.OVERVIEW_EXPIRATION_DATE)
            .fetch()
            .into(OverviewRecord.class);
  }

  public Optional<OverviewRecord> getActualOverview(String vehicleId) {
    return Optional.ofNullable(
        context.select(OVERVIEW.ID, OVERVIEW.OVERVIEW_COST, OVERVIEW.OVERVIEW_EXPIRATION_DATE,
                OVERVIEW.OVERVIEW_NAME, OVERVIEW.OVERVIEW_DESCRIPTION)
            .from(OVERVIEW)
            .join(VEHICLE).on(VEHICLE.OVERVIEW_ID.eq(OVERVIEW.ID))
            .where(VEHICLE.VEHICLE_ID.eq(vehicleId))
            .fetchOne()).map(record -> record.into(OverviewRecord.class));

  }
}

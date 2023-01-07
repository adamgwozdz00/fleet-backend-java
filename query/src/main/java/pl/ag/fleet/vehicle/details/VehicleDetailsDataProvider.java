package pl.ag.fleet.vehicle.details;

import static nu.studer.sample.Tables.DRIVER;
import static nu.studer.sample.Tables.INSURANCE;
import static nu.studer.sample.Tables.OVERVIEW;
import static nu.studer.sample.Tables.REPAIR;
import static nu.studer.sample.Tables.RE_FUEL;
import static nu.studer.sample.Tables.VEHICLE_STATE;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;
import pl.ag.fleet.common.VehicleId;

@Component
@RequiredArgsConstructor
public class VehicleDetailsDataProvider {

  private final DSLContext context;

  public List<InsuranceRecord> getInsuranceHistory(String vehicleId, boolean onlyActual) {
    var base = context.select(INSURANCE.ID, INSURANCE.INSURANCE_COST,
            INSURANCE.INSURANCE_EXPIRATION_DATE,
            INSURANCE.INSURANCE_NAME)
        .from(INSURANCE)
        .where(INSURANCE.VEHICLE_ID.eq(vehicleId))
        .orderBy(INSURANCE.INSURANCE_EXPIRATION_DATE.desc());

    if (onlyActual) {
      return base.limit(1).fetch().into(InsuranceRecord.class);
    }

    return base.fetch().into(InsuranceRecord.class);

  }

  public List<OverviewRecord> getOverviewHistory(String vehicleId, boolean onlyActual) {
    var base =
        context.select(OVERVIEW.ID, OVERVIEW.OVERVIEW_COST, OVERVIEW.OVERVIEW_EXPIRATION_DATE,
                OVERVIEW.OVERVIEW_NAME, OVERVIEW.OVERVIEW_DESCRIPTION)
            .from(OVERVIEW)
            .where(OVERVIEW.VEHICLE_ID.eq(vehicleId))
            .orderBy(OVERVIEW.OVERVIEW_EXPIRATION_DATE.desc());

    if (onlyActual) {
      return base.limit(1).fetch().into(OverviewRecord.class);
    }

    return base.fetch().into(OverviewRecord.class);
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

  public List<RefuelRecord> getRefuelHistory(VehicleId vehicleId) {
    return context.select(RE_FUEL.COST, RE_FUEL.LITERS, RE_FUEL.TIME)
        .from(RE_FUEL)
        .where(RE_FUEL.VEHICLE_ID.eq(vehicleId.getVehicleId()))
        .orderBy(RE_FUEL.TIME.desc())
        .fetch()
        .into(RefuelRecord.class);
  }

  public List<RepairRecord> getRepairsHistory(VehicleId vehicleId, boolean onlyLastRepair) {
    var result = context.select(RepairRecord.FIELDS)
        .from(REPAIR)
        .where(REPAIR.VEHICLE_ID.eq(vehicleId.getVehicleId()))
        .orderBy(REPAIR.START_TIME.desc())
        .fetch()
        .into(RepairRecord.class);
    if (onlyLastRepair && !result.isEmpty()) {
      return Collections.singletonList(result.get(0));
    }
    return result;
  }

  public List<VehicleStateRecord> getVehicleStateHistory(VehicleId vehicleId) {
    var result = context.select(VehicleStateRecord.FIELDS)
        .from(VEHICLE_STATE)
        .where(VEHICLE_STATE.VEHICLE_ID.eq(vehicleId.getVehicleId()))
        .orderBy(VEHICLE_STATE.TIME.desc())
        .fetch()
        .into(VehicleStateRecord.class);
    return result;
  }


}

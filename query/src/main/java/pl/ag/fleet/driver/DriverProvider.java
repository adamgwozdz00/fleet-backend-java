package pl.ag.fleet.driver;


import static nu.studer.sample.Tables.DRIVER;
import static nu.studer.sample.Tables.VEHICLE;
import static nu.studer.sample.Tables.VEHICLE_STATE;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.TableField;
import org.springframework.stereotype.Component;
import pl.ag.fleet.common.Availability;
import pl.ag.fleet.common.CompanyId;

@Component
@RequiredArgsConstructor
public class DriverProvider {


  private static final List<TableField<Record, ? extends Serializable>> DRIVER_FIELDS = Arrays.asList(
      DRIVER.ID,
      DRIVER.LAST_NAME,
      DRIVER.FIRST_NAME,
      DRIVER.SENIORITY,
      DRIVER.TITLE
  );
  private static final List<TableField<Record, ? extends Serializable>> DRIVER_HISTORY_FIELDS = Arrays.asList(
      VEHICLE.VEHICLE_ID,
      VEHICLE.MAKE,
      VEHICLE.MODEL,
      VEHICLE_STATE.KILOMETERS,
      VEHICLE_STATE.LITERS,
      VEHICLE_STATE.TIME
  );
  private final DSLContext create;

  public List<DriverRecord> getAllDrivers(CompanyId companyId, Availability availability) {
    switch (availability) {
      case TAKEN:
        return getTakenDrivers(companyId);
      case AVAILABLE:
        val taken = getTakenDrivers(companyId);
        val allCompanyDrivers = getAllDrivers(companyId);
        return removeTakenDrivers(allCompanyDrivers, taken);
      default:
        return getAllDrivers(companyId);
    }
  }

  public List<DriverHistoryRecord> getDriverHistory(Long driverId) {
    return create.select(DRIVER_HISTORY_FIELDS)
        .from(VEHICLE_STATE)
        .join(VEHICLE)
        .on(VEHICLE.VEHICLE_ID.eq(VEHICLE_STATE.VEHICLE_ID))
        .where(VEHICLE_STATE.ACTUAL_DRIVER_ID.eq(driverId))
        .fetch()
        .into(DriverHistoryRecord.class);
  }


  private List<DriverRecord> getAllDrivers(CompanyId companyId) {
    return create.select(DRIVER_FIELDS)
        .from(DRIVER)
        .where(DRIVER.COMPANY_ID.eq(companyId.getCompanyId()))
        .fetch()
        .into(DriverRecord.class);
  }

  private List<DriverRecord> getTakenDrivers(CompanyId companyId) {
    return create.select(DRIVER_FIELDS)
        .from(DRIVER)
        .leftJoin(VEHICLE_STATE)
        .on(VEHICLE_STATE.ACTUAL_DRIVER_ID.eq(DRIVER.ID))
        .where(DRIVER.COMPANY_ID.eq(companyId.getCompanyId()))
        .and(VEHICLE_STATE.TIME.between(LocalDate.now().atStartOfDay(),
            LocalDate.now().atStartOfDay().plusHours(24)))
        .fetch()
        .into(DriverRecord.class);
  }


  private List<DriverRecord> removeTakenDrivers(
      List<DriverRecord> allCompanyDrivers,
      List<DriverRecord> taken) {
    allCompanyDrivers.removeAll(taken);
    return allCompanyDrivers;
  }
}

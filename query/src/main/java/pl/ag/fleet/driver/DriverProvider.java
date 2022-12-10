package pl.ag.fleet.driver;

import static org.jooq.generated.Tables.DRIVER;
import static org.jooq.generated.Tables.VEHICLE;
import static org.jooq.generated.Tables.VEHICLE_STATE;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DriverProvider {

  private final DSLContext create;

  public List<DriverRecord> getAllDrivers(Long companyId) {
    return create.select(DRIVER.ID,
            DRIVER.LAST_NAME,
            DRIVER.FIRST_NAME,
            DRIVER.SENIORITY,
            DRIVER.TITLE)
        .from(DRIVER)
        .where(DRIVER.COMPANY_ID.eq(companyId))
        .fetch()
        .into(DriverRecord.class);
  }

  public List<DriverHistoryRecord> getDriverHistory(Long driverId) {
    return create.select(VEHICLE.VEHICLE_ID,
            VEHICLE.MAKE,
            VEHICLE.MODEL,
            VEHICLE_STATE.KILOMETERS,
            VEHICLE_STATE.LITERS,
            VEHICLE_STATE.TIME)
        .from(VEHICLE_STATE)
        .join(VEHICLE)
        .on(VEHICLE.VEHICLE_ID.eq(VEHICLE_STATE.VEHICLE_ID))
        .where(VEHICLE_STATE.ACTUAL_DRIVER_ID.eq(driverId))
        .fetch()
        .into(DriverHistoryRecord.class);
  }
}

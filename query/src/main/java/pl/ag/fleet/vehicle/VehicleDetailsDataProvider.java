package pl.ag.fleet.vehicle;

import static pl.ag.fleet.Tables.DRIVER;
import static pl.ag.fleet.Tables.INSURANCE;
import static pl.ag.fleet.Tables.VEHICLE_STATE;

import java.util.List;
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
}

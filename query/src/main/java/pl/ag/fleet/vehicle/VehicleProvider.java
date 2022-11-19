package pl.ag.fleet.vehicle;

import static org.jooq.generated.Tables.USER_VEHICLE;
import static pl.ag.fleet.Tables.VEHICLE;
import static pl.ag.fleet.Tables.VEHICLE_STATE;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleProvider {

  private final DSLContext create;

  public List<VehicleRecord> getVehiclesByCompany(Long companyId) {
    return create.select(VEHICLE.VEHICLE_ID,
            VEHICLE.MAKE,
            VEHICLE.MODEL,
            VEHICLE.PRODUCTION_YEAR,
            VEHICLE.FUEL_TYPE,
            VEHICLE.VIN_NUMBER,
            VEHICLE_STATE.KILOMETERS)
        .from(VEHICLE)
        .join(VEHICLE_STATE)
        .on(VEHICLE_STATE.ID.eq(VEHICLE.STATE_ID))
        .where(VEHICLE.COMPANY_ID.eq(companyId))
        .fetch()
        .into(VehicleRecord.class);
  }

  public boolean isVehicleAvailable(String vehicleId) {
    if (!isVehicleExists(vehicleId)) {
      return false;
    }

    val result = create.selectFrom(USER_VEHICLE)
        .where(USER_VEHICLE.VEHICLE_ID.eq(vehicleId))
        .and(USER_VEHICLE.USER_VEHICLE_ID.isNotNull())
        .fetch();

    return result.isEmpty();
  }

  private boolean isVehicleExists(String vehicleId) {
    return Optional.ofNullable(
        create.selectFrom(VEHICLE).where(VEHICLE.VEHICLE_ID.eq(vehicleId)).fetchOne()).isPresent();
  }
}
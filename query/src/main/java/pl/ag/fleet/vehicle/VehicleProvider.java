package pl.ag.fleet.vehicle;

import static org.jooq.generated.Tables.COMPANY_USER;
import static org.jooq.generated.Tables.USER_VEHICLE;
import static pl.ag.fleet.Tables.VEHICLE;
import static pl.ag.fleet.Tables.VEHICLE_STATE;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
        .leftJoin(VEHICLE_STATE)
        .on(VEHICLE_STATE.ID.eq(VEHICLE.STATE_ID))
        .where(VEHICLE.COMPANY_ID.eq(companyId))
        .fetch()
        .into(VehicleRecord.class);
  }

  public List<VehicleRecord> getVehicleByUserId(long userId) {
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
        .join(USER_VEHICLE)
        .on(USER_VEHICLE.VEHICLE_ID.eq(VEHICLE.VEHICLE_ID))
        .join(COMPANY_USER)
        .on(COMPANY_USER.ID.eq(USER_VEHICLE.USER_VEHICLE_ID))
        .where(COMPANY_USER.ID.eq(userId))
        .fetch()
        .into(VehicleRecord.class);
  }

  public List<VehicleRecord> getNotAssignedVehicles(long companyId) {
    return create.select(VEHICLE.VEHICLE_ID,
            VEHICLE.MAKE,
            VEHICLE.MODEL,
            VEHICLE.PRODUCTION_YEAR,
            VEHICLE.FUEL_TYPE,
            VEHICLE.VIN_NUMBER,
            VEHICLE_STATE.KILOMETERS)
        .from(VEHICLE)
        .leftJoin(VEHICLE_STATE)
        .on(VEHICLE_STATE.ID.eq(VEHICLE.STATE_ID))
        .where(VEHICLE.COMPANY_ID.eq(companyId))
        .and(VEHICLE.VEHICLE_ID.notIn(
            getAssignedVehicles(companyId).stream().map(v -> v.getVehicleId()).collect(
                Collectors.toList())))
        .fetch()
        .into(VehicleRecord.class);
  }

  public List<VehicleRecord> getAssignedVehicles(long companyId) {
    return create.select(VEHICLE.VEHICLE_ID,
            VEHICLE.MAKE,
            VEHICLE.MODEL,
            VEHICLE.PRODUCTION_YEAR,
            VEHICLE.FUEL_TYPE,
            VEHICLE.VIN_NUMBER,
            VEHICLE_STATE.KILOMETERS)
        .from(VEHICLE, VEHICLE_STATE, USER_VEHICLE)
        .where(VEHICLE.COMPANY_ID.eq(companyId))
        .and(VEHICLE_STATE.ID.eq(VEHICLE.STATE_ID))
        .and(USER_VEHICLE.VEHICLE_ID.eq(VEHICLE.VEHICLE_ID))
        .and(USER_VEHICLE.USER_VEHICLE_ID.isNotNull())
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

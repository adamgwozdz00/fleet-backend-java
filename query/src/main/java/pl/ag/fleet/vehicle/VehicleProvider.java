package pl.ag.fleet.vehicle;

import static org.jooq.generated.Tables.COMPANY_USER;
import static org.jooq.generated.Tables.USER_VEHICLE;
import static pl.ag.fleet.Tables.VEHICLE;
import static pl.ag.fleet.Tables.VEHICLE_STATE;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.TableField;
import org.springframework.stereotype.Component;
import pl.ag.fleet.common.Availability;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.common.UserId;

@Component
@RequiredArgsConstructor
public class VehicleProvider {

  private static final List<TableField<Record, ? extends Serializable>> FIELDS = Arrays.asList(
      VEHICLE.VEHICLE_ID,
      VEHICLE.MAKE,
      VEHICLE.MODEL,
      VEHICLE.PRODUCTION_YEAR,
      VEHICLE.FUEL_TYPE,
      VEHICLE.VIN_NUMBER,
      VEHICLE_STATE.KILOMETERS
  );
  private final DSLContext create;

  public List<VehicleRecord> getVehiclesByCompany(CompanyId companyId, Availability availability) {
    switch (availability) {
      case EMPTY:
        return getVehiclesByCompany(companyId);
      case TAKEN:
        return getTakenVehicles(companyId);
      case AVAILABLE:
        val taken = getTakenVehicles(companyId);
        val allCompanyVehicles = getVehiclesByCompany(companyId);
        return removeTakenVehicles(allCompanyVehicles, taken);
      default:
        throw new UnsupportedOperationException();
    }

  }

  private List<VehicleRecord> getVehiclesByCompany(CompanyId companyId) {
    return create.select(FIELDS)
        .from(VEHICLE, VEHICLE_STATE)
        .where(VEHICLE_STATE.ID.eq(VEHICLE.STATE_ID))
        .and(VEHICLE.COMPANY_ID.eq(companyId.getCompanyId()))
        .fetch()
        .into(VehicleRecord.class);
  }

  public List<VehicleRecord> getVehicleByUserId(UserId userId) {
    return create.select(FIELDS)
        .from(VEHICLE, VEHICLE_STATE, USER_VEHICLE, COMPANY_USER)
        .where(VEHICLE_STATE.ID.eq(VEHICLE.STATE_ID))
        .and(USER_VEHICLE.VEHICLE_ID.eq(VEHICLE.VEHICLE_ID))
        .and(COMPANY_USER.ID.eq(USER_VEHICLE.USER_VEHICLE_ID))
        .and(COMPANY_USER.ID.eq(userId.getUserId()))
        .fetch()
        .into(VehicleRecord.class);
  }

  private List<VehicleRecord> getTakenVehicles(CompanyId companyId) {
    return create.select(FIELDS)
        .from(VEHICLE, VEHICLE_STATE, USER_VEHICLE)
        .where(VEHICLE.COMPANY_ID.eq(companyId.getCompanyId()))
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

  private List<VehicleRecord> removeTakenVehicles(List<VehicleRecord> allCompanyVehicles,
      List<VehicleRecord> taken) {
    allCompanyVehicles.removeAll(taken);
    return allCompanyVehicles;
  }

  private boolean isVehicleExists(String vehicleId) {
    return Optional.ofNullable(
        create.selectFrom(VEHICLE).where(VEHICLE.VEHICLE_ID.eq(vehicleId)).fetchOne()).isPresent();
  }


}

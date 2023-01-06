package pl.ag.fleet.vehicle;


import static nu.studer.sample.Tables.COMPANY_USER;
import static nu.studer.sample.Tables.USER_VEHICLE;
import static nu.studer.sample.Tables.VEHICLE;
import static nu.studer.sample.Tables.VEHICLE_STATE;

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
        .from(VEHICLE)
        .leftJoin(VEHICLE_STATE)
        .on(VEHICLE_STATE.VEHICLE_ID.eq(VEHICLE.VEHICLE_ID))
        .where(VEHICLE.COMPANY_ID.eq(companyId.getCompanyId()))
        .and(VEHICLE.ARCHIVED.eq(Boolean.FALSE))
        .fetch()
        .into(VehicleRecord.class);
  }

  public List<VehicleRecord> getVehicleByUserId(UserId userId) {
    return create.select(FIELDS)
        .from(VEHICLE)
        .leftJoin(VEHICLE_STATE)
        .on(VEHICLE_STATE.VEHICLE_ID.eq(VEHICLE.VEHICLE_ID))
        .join(USER_VEHICLE)
        .on(USER_VEHICLE.VEHICLE_ID.eq(VEHICLE.VEHICLE_ID))
        .join(COMPANY_USER)
        .on(COMPANY_USER.ID.eq(USER_VEHICLE.USER_VEHICLE_ID))
        .where(COMPANY_USER.ID.eq(userId.getUserId()))
        .and(VEHICLE.ARCHIVED.eq(Boolean.FALSE))
        .fetch()
        .into(VehicleRecord.class);
  }

  private List<VehicleRecord> getTakenVehicles(CompanyId companyId) {
    return create.select(FIELDS)
        .from(VEHICLE)
        .leftJoin(VEHICLE_STATE)
        .on(VEHICLE_STATE.VEHICLE_ID.eq(VEHICLE.VEHICLE_ID))
        .join(USER_VEHICLE)
        .on(USER_VEHICLE.VEHICLE_ID.eq(VEHICLE.VEHICLE_ID))
        .join(COMPANY_USER)
        .on(COMPANY_USER.ID.eq(USER_VEHICLE.USER_VEHICLE_ID))
        .where(VEHICLE.COMPANY_ID.eq(companyId.getCompanyId()))
        .and(USER_VEHICLE.USER_VEHICLE_ID.isNotNull())
        .and(VEHICLE.ARCHIVED.eq(Boolean.FALSE))
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

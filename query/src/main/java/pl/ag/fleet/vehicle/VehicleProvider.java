package pl.ag.fleet.vehicle;


import static nu.studer.sample.Tables.COMPANY_USER;
import static nu.studer.sample.Tables.USER_VEHICLE;
import static nu.studer.sample.Tables.VEHICLE;
import static nu.studer.sample.Tables.VEHICLE_STATE;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.inline;
import static org.jooq.impl.DSL.max;
import static org.jooq.impl.DSL.select;
import static org.jooq.impl.DSL.selectDistinct;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SelectField;
import org.springframework.stereotype.Component;
import pl.ag.fleet.common.Availability;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.common.UserId;

@Component
@RequiredArgsConstructor
public class VehicleProvider {

  private static final List<SelectField> FIELDS = Arrays.asList(
      VEHICLE.VEHICLE_ID,
      VEHICLE.MAKE,
      VEHICLE.MODEL,
      VEHICLE.PRODUCTION_YEAR,
      VEHICLE.FUEL_TYPE,
      VEHICLE.VIN_NUMBER,
      VEHICLE_STATE.KILOMETERS
  );

  private static final List<SelectField> FIELDS_WITH_MOCK_KM = Arrays.asList(
      VEHICLE.VEHICLE_ID,
      VEHICLE.MAKE,
      VEHICLE.MODEL,
      VEHICLE.PRODUCTION_YEAR,
      VEHICLE.FUEL_TYPE,
      VEHICLE.VIN_NUMBER,
      inline(0).as(VEHICLE_STATE.KILOMETERS.getName())
  );
  private final DSLContext create;

  public List<VehicleRecord> getVehiclesByCompany(CompanyId companyId, Availability availability) {
    switch (availability) {
      case EMPTY:
        return getVehiclesByCompany(companyId);
      case TAKEN:
        return getTakenVehiclesByCompany(companyId);
      case AVAILABLE:
        val taken = getTakenVehiclesByCompany(companyId);
        val allCompanyVehicles = getVehiclesByCompany(companyId);
        return removeTakenVehicles(allCompanyVehicles, taken);
      default:
        throw new UnsupportedOperationException();
    }

  }

  private List<VehicleRecord> getVehiclesByCompany(CompanyId companyId) {
    return create.select(FIELDS)
        .from(VEHICLE, VEHICLE_STATE)
        .where(VEHICLE.ARCHIVED.eq(Boolean.FALSE))
        .and(VEHICLE.COMPANY_ID.eq(companyId.getCompanyId()))
        .and(VEHICLE_STATE.TIME.eq(selectLatestStateTime()))
        .unionAll(
            create.select(FIELDS_WITH_MOCK_KM)
                .from(VEHICLE)
                .where(VEHICLE.VEHICLE_ID.notIn(selectVehicleIdsWithState()))
                .and(VEHICLE.ARCHIVED.eq(Boolean.FALSE))
                .and(VEHICLE.COMPANY_ID.eq(companyId.getCompanyId()))).fetch()
        .into(VehicleRecord.class);

  }

  private List<VehicleRecord> getTakenVehiclesByCompany(CompanyId companyId) {
    return create.select(FIELDS)
        .from(VEHICLE, VEHICLE_STATE)
        .where(VEHICLE.ARCHIVED.eq(Boolean.FALSE))
        .and(VEHICLE.COMPANY_ID.eq(companyId.getCompanyId()))
        .and(VEHICLE_STATE.TIME.eq(selectLatestStateTime()))
        .and(VEHICLE.VEHICLE_ID.notIn(selectTakenVehicles()))
        .unionAll(
            create.select(FIELDS_WITH_MOCK_KM)
                .from(VEHICLE)
                .where(VEHICLE.VEHICLE_ID.notIn(selectVehicleIdsWithState()))
                .and(VEHICLE.VEHICLE_ID.notIn(selectTakenVehicles()))
                .and(VEHICLE.ARCHIVED.eq(Boolean.FALSE))
                .and(VEHICLE.COMPANY_ID.eq(companyId.getCompanyId()))
                .and(VEHICLE.VEHICLE_ID.in(selectTakenVehicles()))).fetch()
        .into(VehicleRecord.class);
  }

  public List<VehicleRecord> getVehicleByUserId(UserId userId) {
    return create.select(FIELDS)
        .from(VEHICLE, VEHICLE_STATE, USER_VEHICLE, COMPANY_USER)
        .where(VEHICLE.ARCHIVED.eq(Boolean.FALSE))
        .and(VEHICLE_STATE.TIME.eq(selectLatestStateTime()))
        .and(USER_VEHICLE.VEHICLE_ID.eq(VEHICLE.VEHICLE_ID))
        .and(COMPANY_USER.ID.eq(USER_VEHICLE.USER_VEHICLE_ID))
        .and(COMPANY_USER.ID.eq(userId.getUserId()))
        .unionAll(create.select(FIELDS_WITH_MOCK_KM)
            .from(VEHICLE, USER_VEHICLE, COMPANY_USER)
            .where(VEHICLE.VEHICLE_ID.notIn(selectVehicleIdsWithState()))
            .and(VEHICLE.ARCHIVED.eq(Boolean.FALSE))
            .and(USER_VEHICLE.VEHICLE_ID.eq(VEHICLE.VEHICLE_ID))
            .and(COMPANY_USER.ID.eq(USER_VEHICLE.USER_VEHICLE_ID))
            .and(COMPANY_USER.ID.eq(userId.getUserId()))
        )
        .fetch()
        .into(VehicleRecord.class);
  }

  private Field<String> selectTakenVehicles() {
    return field(selectDistinct(USER_VEHICLE.VEHICLE_ID)
        .from(USER_VEHICLE)
        .where(USER_VEHICLE.USER_VEHICLE_ID.isNotNull()));
  }


  private Field<String> selectVehicleIdsWithState() {
    return field(selectDistinct(VEHICLE_STATE.VEHICLE_ID).from(VEHICLE_STATE));
  }


  private Field<LocalDateTime> selectLatestStateTime() {
    return field(select(max(VEHICLE_STATE.TIME))
        .from(VEHICLE_STATE)
        .where(VEHICLE_STATE.VEHICLE_ID.eq(VEHICLE.VEHICLE_ID)));
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

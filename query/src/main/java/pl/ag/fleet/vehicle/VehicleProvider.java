package pl.ag.fleet.vehicle;

import static pl.ag.fleet.Tables.VEHICLE;
import static pl.ag.fleet.Tables.VEHICLE_STATE;

import java.util.List;
import lombok.RequiredArgsConstructor;
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

  public boolean isVehicleAvailable() {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}

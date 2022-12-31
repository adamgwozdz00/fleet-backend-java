package pl.ag.fleet.reports;

import static nu.studer.sample.Tables.VEHICLE;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.common.VehicleId;

@Component
@RequiredArgsConstructor
public class CommonKnowledgeProvider {

  private final DSLContext create;

  public List<VehicleId> getVehiclesIdsOfCompany(CompanyId companyId) {
    return create.select(VEHICLE.VEHICLE_ID).from(VEHICLE)
        .where(VEHICLE.COMPANY_ID.eq(companyId.getCompanyId()))
        .fetch()
        .into(VehicleId.class);
  }
}

package pl.ag.fleet.driver;

import static org.jooq.generated.Tables.DRIVER;

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
}

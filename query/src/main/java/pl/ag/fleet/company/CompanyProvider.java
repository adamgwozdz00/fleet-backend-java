package pl.ag.fleet.company;

import static nu.studer.sample.Tables.COMPANY;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyProvider {

  private final DSLContext create;

  public CompanyRecord getCompanyData(long companyId) {
    return create.select(COMPANY.NAME)
        .from(COMPANY)
        .where(COMPANY.ID.eq(companyId))
        .fetchOne()
        .into(CompanyRecord.class);
  }
}

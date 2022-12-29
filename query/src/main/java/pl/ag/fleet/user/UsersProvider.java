package pl.ag.fleet.user;

import static nu.studer.sample.Tables.AUTH_USER;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsersProvider {

  private final DSLContext create;


  public List<UserRecord> getAllUsersByCompanyId(long companyId) {
    return create.select(AUTH_USER.ID, AUTH_USER.FIRST_NAME, AUTH_USER.LAST_NAME, AUTH_USER.TITLE)
        .from(AUTH_USER)
        .where(AUTH_USER.COMPANY_ID.eq(companyId))
        .fetch()
        .into(UserRecord.class);
  }
}

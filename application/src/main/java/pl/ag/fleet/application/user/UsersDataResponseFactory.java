package pl.ag.fleet.application.user;

import java.util.List;
import java.util.stream.Collectors;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.ag.fleet.user.UserRecord;

@Component
public class UsersDataResponseFactory {

  public ResponseEntity<UsersData> create(List<UserRecord> allUsersByCompanyId, long userId) {
    val allUsersWithoutSelf = allUsersByCompanyId.stream().filter(user -> user.getId() != userId)
        .collect(
            Collectors.toList());
    return ResponseEntity.ok(new UsersData(allUsersWithoutSelf));
  }
}

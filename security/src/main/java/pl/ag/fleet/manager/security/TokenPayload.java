package pl.ag.fleet.manager.security;

import java.util.HashMap;
import java.util.Map;
import lombok.Value;
import lombok.val;

@Value
public class TokenPayload {

  private String userId;
  private String role;
  private Long companyId;

  Map<String, ?> toClaims() {
    val payloadClaims = new HashMap<String,Object>();
    payloadClaims.put("userId", userId);
    payloadClaims.put("role", role);
    payloadClaims.put("companyId", companyId);
    return payloadClaims;
  }
}

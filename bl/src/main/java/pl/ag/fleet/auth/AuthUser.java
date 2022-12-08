package pl.ag.fleet.auth;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_user", schema = "fleet")
@NoArgsConstructor
@Getter
public class AuthUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long companyId;
  private String username;

  private String password;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Embedded
  private UserDetails userDetails;

  public AuthUser(long companyId, String username, String password, UserRole role,
      UserDetails userDetails) {
    this.companyId = companyId;
    this.username = username;
    this.password = password;
    this.role = role;
    this.userDetails = userDetails;
  }

  public void encodePassword(AuthPasswordEncoder passwordEncoder) {
    this.password = passwordEncoder.encode(password);
  }
}

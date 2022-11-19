package pl.ag.fleet.user;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "fleet", name = "company_user")
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private UserId userId;
  private CompanyId companyId;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_vehicle_id")
  private Set<UserVehicle> vehicles;

  public User(UserId userId, CompanyId companyId) {
    this.userId = userId;
    this.companyId = companyId;
    this.vehicles = new HashSet<>();
  }

  Result add(VehicleId vehicle, VehicleAvailabilityService availabilityService) {
    if (availabilityService.isAvailable(vehicle)) {
      this.vehicles.add(new UserVehicle(vehicle));
      return Result.createSuccess();
    }
    return Result.createFail();
  }

  void remove(VehicleId vehicle) {
    this.vehicles.removeIf(v -> v.getVehicleId().equals(vehicle));
  }

  int getCountOfVehicles() {
    return this.vehicles.size();
  }
}

package pl.ag.fleet.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(schema = "fleet")
class UserVehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Getter
  private VehicleId vehicleId;

  UserVehicle(VehicleId vehicleId) {
    this.vehicleId = vehicleId;
  }
}

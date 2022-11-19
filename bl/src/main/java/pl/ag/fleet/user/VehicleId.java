package pl.ag.fleet.user;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class VehicleId {

  private String vehicleId;
}

package pl.ag.fleet.common;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class VehicleId implements Serializable {

  private String vehicleId;

  public VehicleId() {
    this.vehicleId = UUID.randomUUID().toString();
  }
}

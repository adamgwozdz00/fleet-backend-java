package pl.ag.fleet.vehicle;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
class ServiceName {

  private String serviceName;
}

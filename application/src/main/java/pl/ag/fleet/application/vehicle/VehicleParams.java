package pl.ag.fleet.application.vehicle;

import lombok.Data;
import pl.ag.fleet.common.Availability;

@Data
public class VehicleParams {

  Long userId;
  Availability availability;


  boolean isEmpty(){
    return userId == null && availability == null;
  }

}

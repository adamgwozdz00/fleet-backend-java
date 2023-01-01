package pl.ag.fleet.vehicle;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum VehicleStatus {
  ACTIVE("ACTIVE"), SERVICE("SERVICE");

  String value;

}

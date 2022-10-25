package pl.ag.fleet.event;

public enum EventType {
  CREATE_VEHICLE("CreateVehicleEvent"),
  ACTUAL_VEHICLE_DATA("ActualDataEvent"),
  REFUEL("RefuelEvent"),
  INSURANCE("InsuranceEvent"),
  OVERVIEW("OverviewEvent");


  private final String eventType;


  EventType(String eventType) {
    this.eventType = eventType;
  }

  public static EventType toEnum(String eventType) {
    for (var et : EventType.values()) {
      if (et.eventType.equals(eventType)) {
        return et;
      }
    }
    throw new IllegalArgumentException("Can't map to enum unknown value.");
  }

  public String getEventType() {
    return eventType;
  }
}

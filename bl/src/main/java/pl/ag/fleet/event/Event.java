package pl.ag.fleet.event;

import lombok.Value;

@Value
public class Event<T extends EventData> {

  private EventTime time;

  private T eventData;

  public Event(T eventData) {
    this.time = new EventTime();
    this.eventData = eventData;
  }

}

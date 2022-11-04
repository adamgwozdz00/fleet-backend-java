package pl.ag.fleet.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public abstract class EventData {
  private final EventType eventType;
}

package pl.ag.fleet.driver;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SeniorityOperation {
  INCREMENT("INCREMENT"), DECREMENT("DECREMENT"), PROMOTION("PROMOTION");

  String value;
}

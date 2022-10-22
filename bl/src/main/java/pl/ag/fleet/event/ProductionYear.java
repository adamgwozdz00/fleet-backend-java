package pl.ag.fleet.event;

import lombok.Value;

@Value
public class ProductionYear {

  private final static Integer FIRST_WORLD_CAR_PRODUCED = 1886;
  private Integer year;

  public ProductionYear(Integer year) {
    if (year < FIRST_WORLD_CAR_PRODUCED) {
      throw new IllegalArgumentException("Car too old.");
    }
    this.year = year;
  }
}

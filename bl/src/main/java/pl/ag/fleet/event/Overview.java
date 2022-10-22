package pl.ag.fleet.event;

import java.time.LocalDate;
import lombok.Value;

@Value
public class Overview {

  private String name;
  private LocalDate expirationDate;
  private Cost cost;
  private String description;
}

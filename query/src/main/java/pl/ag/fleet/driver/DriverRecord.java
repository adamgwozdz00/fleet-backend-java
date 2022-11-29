package pl.ag.fleet.driver;

import lombok.Value;

@Value
public class DriverRecord {

  private long id;
  private String lastName;
  private String firstName;
  private int seniority;
  private String title;
}

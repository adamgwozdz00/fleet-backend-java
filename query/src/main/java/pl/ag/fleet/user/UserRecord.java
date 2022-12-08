package pl.ag.fleet.user;

import lombok.Value;

@Value
public class UserRecord {

  private long id;
  private String firstName;
  private String lastName;
  private String title;

}

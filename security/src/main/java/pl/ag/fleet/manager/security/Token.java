package pl.ag.fleet.manager.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

  private String token;
  private long expiresIn;

}

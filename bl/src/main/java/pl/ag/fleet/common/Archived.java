package pl.ag.fleet.common;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Archived {

  private boolean archived;

  public static Archived init() {
    return new Archived(false);
  }

  public Archived archive() {
    if (this.archived) {
      throw new RuntimeException("Already archived.");
    }
    return new Archived(true);
  }
}

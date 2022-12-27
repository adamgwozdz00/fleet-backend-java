package pl.ag.fleet.driver;

import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
class Seniority {

  private int seniority;

  Seniority(int seniority) {
    vetoIfSeniorityIncorrect(seniority);
    this.seniority = seniority;
  }

  private void vetoIfSeniorityIncorrect(int seniority) {
    if (seniority < 0) {
      throw new IllegalArgumentException("Seniority of driver can't be lower than zero.");
    }
    if (seniority > 99) {
      throw new IllegalArgumentException("Seniority is impossible.");
    }
  }

  public Seniority increment() {
    return new Seniority(this.seniority + 1);
  }

  public Seniority decrement() {
    return new Seniority(this.seniority - 1);
  }
}

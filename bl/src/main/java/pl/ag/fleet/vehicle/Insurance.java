package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
class Insurance {

  private final static String UNDEFINED = "undefined";
  @Column(name = "insurance_name")
  private String name;
  @Column(name = "insurance_expiration_date")
  private LocalDate expirationDate;
  @Column(name = "insurance_cost")
  private BigDecimal cost;

  static Insurance initial() {
    return new Insurance(UNDEFINED, null, null);
  }

  Insurance validateAndReturn(Insurance newInsurance) {
    if (this.name.equals(UNDEFINED)) {
      return newInsurance;
    }

    if (newInsurance.expirationDate.isBefore(this.expirationDate)) {
      throw new RuntimeException("Insurance not valid, expires before actual insurance.");
    }
    return newInsurance;
  }
}

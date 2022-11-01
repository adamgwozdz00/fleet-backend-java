package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
class Insurance {

  private final static String UNDEFINED = "undefined";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "insurance_name")
  private String name;
  @Column(name = "insurance_expiration_date")
  private LocalDate expirationDate;
  @Column(name = "insurance_cost")
  private BigDecimal cost;
  @Embedded
  private VehicleId vehicleId;


  public Insurance(String name, LocalDate expirationDate, BigDecimal cost, VehicleId vehicleId) {
    this.name = name;
    this.expirationDate = expirationDate;
    this.cost = cost;
    this.vehicleId = vehicleId;
  }

  static Insurance initial(VehicleId vehicleId) {
    return new Insurance(UNDEFINED, null, null, vehicleId);
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

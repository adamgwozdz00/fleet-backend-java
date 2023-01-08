package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(schema = "fleet")
class Insurance implements Comparable<Insurance> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "insurance_name")
  private String name;
  @Column(name = "insurance_expiration_date")
  private LocalDate expirationDate;
  @Column(name = "insurance_cost")
  private BigDecimal cost;

  public Insurance(String name, LocalDate expirationDate, BigDecimal cost) {
    this.name = name;
    this.expirationDate = expirationDate;
    this.cost = cost;
  }

  @Override
  public int compareTo(Insurance o) {
    return expirationDate.compareTo(o.expirationDate);
  }
}

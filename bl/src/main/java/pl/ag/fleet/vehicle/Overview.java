package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
@Table(schema = "fleet")
class Overview implements Comparable<Overview> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "overview_name")
  private String name;
  @Column(name = "overview_expiration_date")
  private LocalDate expirationDate;
  @Column(name = "overview_cost")
  private BigDecimal cost;
  @Column(name = "overview_description")
  private String description;

  public Overview(String name, LocalDate expirationDate, BigDecimal cost, String description) {
    this.name = name;
    this.expirationDate = expirationDate;
    this.cost = cost;
    this.description = description;
  }


  @Override
  public int compareTo(Overview o) {
    return expirationDate.compareTo(o.expirationDate);
  }
}

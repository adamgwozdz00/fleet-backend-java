package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
@Table(schema = "fleet")
class Overview {

  private final static String UNDEFINED = "undefined";
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
  @Embedded
  private VehicleId vehicleId;

  public Overview(String name, LocalDate expirationDate, BigDecimal cost, String description,
      VehicleId vehicleId) {
    this.name = name;
    this.expirationDate = expirationDate;
    this.cost = cost;
    this.description = description;
    this.vehicleId = vehicleId;
  }

  Overview validateAndReturn(@NonNull Overview newOverview) {
    if (newOverview.expirationDate.isAfter(this.expirationDate)) {
      return newOverview;
    }
    throw new RuntimeException("Overview not valid, expires before actual overview.");
  }
}

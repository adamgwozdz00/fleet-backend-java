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

  static Overview initial(VehicleId vehicleId) {
    return new Overview(UNDEFINED, null, null, "", vehicleId);
  }

  Overview validateAndReturn(Overview newOverview) {
    if (this.name.equals(UNDEFINED)) {
      return newOverview;
    }
    if (newOverview.expirationDate.isBefore(this.expirationDate)) {
      throw new RuntimeException("Overview not valid, expires before actual overview.");
    }
    return newOverview;
  }
}

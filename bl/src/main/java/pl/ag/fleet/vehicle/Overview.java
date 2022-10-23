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
class Overview {

  private final static String UNDEFINED = "undefined";
  @Column(name = "overview_name")
  private String name;
  @Column(name = "overview_expiration_date")
  private LocalDate expirationDate;
  @Column(name = "overview_cost")
  private BigDecimal cost;
  @Column(name = "overview_description")
  private String description;

  static Overview initial() {
    return new Overview(UNDEFINED, null, null, "");
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

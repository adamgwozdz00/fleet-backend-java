package pl.ag.fleet.vehicle;

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repair", schema = "fleet")
@NoArgsConstructor
class Repair {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "from", column = @Column(name = "start_time")),
      @AttributeOverride(name = "to", column = @Column(name = "finish_time"))
  })
  private TimePeriod timePeriod;
  @AttributeOverride(name = "serviceName", column = @Column(name = "service_name"))
  @Embedded
  private ServiceName serviceName;
  private String title;
  private BigDecimal cost;

  public Repair(TimePeriod timePeriod, ServiceName serviceName, String title, BigDecimal cost) {
    this.timePeriod = timePeriod;
    this.serviceName = serviceName;
    this.title = title;
    this.cost = cost;
  }


  public RepairId getRepairId() {
    return new RepairId(this.id);
  }
}

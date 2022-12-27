package pl.ag.fleet.driver;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.ag.fleet.common.CompanyId;

@Entity
@NoArgsConstructor
@Getter
@Table(schema = "fleet")
public class Driver {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Embedded
  private CompanyId companyId;
  @Embedded
  private DriverDetails details;
  @Enumerated(EnumType.STRING)
  private Title title;
  @Embedded
  private Seniority seniority;

  Driver(CompanyId companyId, DriverDetails details, Seniority seniority) {
    this.companyId = companyId;
    this.details = details;
    this.title = Title.JUNIOR;
    this.seniority = seniority;
  }

  void promote() {
    if (title == Title.SENIOR) {
      throw new IllegalStateException("Can't promote, driver reach the highest grade.");
    }
    title = Title.SENIOR;
  }


  void updateSeniority(Seniority seniorityInYears) {
    this.seniority = seniorityInYears;
  }
}

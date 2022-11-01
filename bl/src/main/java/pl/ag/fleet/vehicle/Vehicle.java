package pl.ag.fleet.vehicle;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Vehicle {

  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private VehicleId id;
  @Embedded
  private CompanyId companyId;
  @Embedded
  private VehicleDetails details;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "state_id")
  private VehicleState state;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "overview_id")
  private Overview overview;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "insurance_id")
  private Insurance insurance;

  Vehicle(CompanyId companyId, VehicleDetails details) {
    this.id = new VehicleId();
    this.companyId = companyId;
    this.details = details;
    this.state = VehicleState.initial(this.id);
    this.overview = Overview.initial(this.id);
    this.insurance = Insurance.initial(this.id);
  }

  void updateState(VehicleState state) {
    this.vetoIfIncorrect(state);
    this.state = state;
  }

  void updateOverview(Overview overview) {
    this.overview = this.overview.validateAndReturn(overview);
  }

  void updateInsurance(Insurance insurance) {
    this.insurance = this.insurance.validateAndReturn(insurance);
  }

  private void vetoIfIncorrect(VehicleState state) {
    if (this.state.getActualKilometers().isLessOrEqualThan(state.getActualKilometers())) {
      return;
    }
    throw new RuntimeException("Incorrect vehicle state.");
  }
}

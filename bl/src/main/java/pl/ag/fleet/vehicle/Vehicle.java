package pl.ag.fleet.vehicle;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Embedded
  private CompanyId companyId;
  @Embedded
  private VehicleDetails details;
  @Embedded
  private VehicleState state;
  @Embedded
  private Overview overview;
  @Embedded
  private Insurance insurance;

  Vehicle(CompanyId companyId, VehicleDetails details) {
    this.companyId = companyId;
    this.details = details;
    this.state = VehicleState.initial();
    this.overview = Overview.initial();
    this.insurance = Insurance.initial();
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

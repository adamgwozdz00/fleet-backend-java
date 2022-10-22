package pl.ag.fleet.vehicle;

import lombok.Getter;

@Getter
class Vehicle {
  private Long id;
  private CompanyId companyId;
  private VehicleDetails details;
  private VehicleState state;

  Vehicle(CompanyId companyId, VehicleDetails details){
    this.companyId = companyId;
    this.details = details;
    this.state = VehicleState.initial();
  }

  void updateState(VehicleState state){
    this.vetoIfIncorrect(state);
  }

  private void vetoIfIncorrect(VehicleState state) {

  }
}

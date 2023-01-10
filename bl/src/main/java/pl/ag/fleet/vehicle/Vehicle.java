package pl.ag.fleet.vehicle;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.ag.fleet.common.Archived;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.common.VehicleId;

@Entity
@Table(schema = "fleet")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Vehicle {

  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private VehicleId id;
  @Embedded
  @AttributeOverride(name = "companyId", column = @Column(name = "company_Id"))
  private CompanyId companyId;
  @Embedded
  private VehicleDetails details;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "vehicle_id")
  private List<VehicleState> states;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "vehicle_id")
  private List<Overview> overviews;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "vehicle_id")
  private List<Insurance> insurances;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "vehicle_id")
  private List<Repair> repairs;

  @Embedded
  private Archived archived;

  Vehicle(CompanyId companyId, VehicleDetails details) {
    this.id = new VehicleId();
    this.companyId = companyId;
    this.details = details;
    this.archived = Archived.init();
    this.repairs = new ArrayList<>();
    this.states = new ArrayList<>();
    this.overviews = new ArrayList<>();
    this.insurances = new ArrayList<>();
  }

  void updateRepair(Repair repair) {
    this.repairs.add(repair);
  }

  Result updateState(VehicleState state, DriverAvailabilityService driverAvailabilityService) {
    if (!isValidNewNumberOfKilometers(state)) {
      return new Result(false, "Invalid number of kilometers.");
    }

    if (states.stream().anyMatch(s -> s.hasAnyDriverAt(state))) {
      return new Result(false, "Vehicle is busy.");
    }

    if (!state.isDriverAvailableAt(driverAvailabilityService)) {
      return new Result(false, "Driver is busy.");
    }

    this.states.add(state);
    return new Result(true, "");
  }


  void updateOverview(Overview overview) {
    this.overviews.add(overview);
  }

  void updateInsurance(Insurance insurance) {
    this.insurances.add(insurance);
  }

  void archive() {
    this.archived = this.archived.archive();
  }

  private boolean isValidNewNumberOfKilometers(VehicleState state) {
    if (states.isEmpty()) {
      return true;
    }

    return getLatestState().isValidNewNumberOfKilometers(state);
  }

  private VehicleState getLatestState() {
    return states.stream().max(VehicleState::compareTo).get();
  }

}

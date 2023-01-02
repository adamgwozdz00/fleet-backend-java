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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.ag.fleet.common.Archived;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.common.VehicleId;

@Getter
@Entity
@Table(schema = "fleet")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Vehicle {

  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private VehicleId id;
  @Embedded
  @AttributeOverride(name = "companyId", column = @Column(name = "company_Id"))
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

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "vehicle_id")
  private List<Repair> repairs;

  @AttributeOverride(name = "repairId", column = @Column(name = "last_repair"))
  private RepairId lastRepair;

  @Embedded
  private Archived archived;

  Vehicle(CompanyId companyId, VehicleDetails details) {
    this.id = new VehicleId();
    this.companyId = companyId;
    this.details = details;
    this.archived = Archived.init();
    this.repairs = new ArrayList<>();
  }

  void updateRepair(Repair repair) {
    this.repairs.add(repair);
    this.lastRepair = this.repairs.stream().max(Repair::compareTo).get().getRepairId();
  }

  void updateState(VehicleState state) {
    this.vetoIfIncorrect(state);
    this.state = state;
  }

  void updateOverview(Overview overview) {
    if (this.overview == null) {
      this.overview = overview;
      return;
    }
    this.overview = this.overview.validateAndReturn(overview);
  }

  void updateInsurance(Insurance insurance) {
    if (this.insurance == null) {
      this.insurance = insurance;
      return;
    }
    this.insurance = this.insurance.validateAndReturn(insurance);
  }

  void archive() {
    this.archived = this.archived.archive();
  }

  private void vetoIfIncorrect(VehicleState state) {
    if (this.state == null) {
      return;
    }
    if (this.state.getActualKilometers().isLessOrEqualThan(state.getActualKilometers())) {
      return;
    }
    throw new RuntimeException("Incorrect vehicle state.");
  }
}

package pl.ag.fleet.application.vehicle.details;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.common.VehicleId;
import pl.ag.fleet.vehicle.details.VehicleDetailsDataProvider;

@RestController
@RequestMapping("/vehicles/detalis")
@RequiredArgsConstructor
public class VehicleDetailsController {

  private final VehicleDetailsDataProvider vehicleDetailsDataProvider;

  @GetMapping("/insurances/{vehicleId}")
  public ResponseEntity<InsuranceDetails> getInsuranceHistory(@PathVariable String vehicleId,
      @RequestParam(defaultValue = "false") boolean onlyActual) {
    val result = this.vehicleDetailsDataProvider.getInsuranceData(vehicleId, onlyActual);
    return ResponseEntity.ok(new InsuranceDetails(result.stream().map(
            record -> new InsuranceDetail(record.getId(), record.getInsuranceName(),
                record.getInsuranceCost(), record.getInsuranceExpirationDate().toString()))
        .collect(Collectors.toList())));
  }

  @GetMapping("/overviews/{vehicleId}")
  public ResponseEntity<OverviewDetails> getOverviewHistory(@PathVariable String vehicleId,
      @RequestParam(defaultValue = "false") boolean onlyActual) {
    val result = this.vehicleDetailsDataProvider.getOverviewData(vehicleId, onlyActual);
    return ResponseEntity.ok(new OverviewDetails(result.stream().map(
        record -> new OverviewDetail(record.getId(), record.getOverviewName(),
            record.getOverviewCost(), record.getOverviewExpirationDate().toString(),
            record.getOverviewDescription())).collect(
        Collectors.toList())));
  }

  @GetMapping("/drivers/{vehicleId}")
  public ResponseEntity<DriverDetails> getDriverHistory(@PathVariable String vehicleId) {
    val result = this.vehicleDetailsDataProvider.getDriverHistory(vehicleId);
    return ResponseEntity.ok(new DriverDetails(result));
  }

  @GetMapping("/fuels/{vehicleId}")
  public ResponseEntity<RefuelDetails> getRefuelsHistory(@PathVariable String vehicleId) {
    val result = this.vehicleDetailsDataProvider.getRefuelHistory(new VehicleId(vehicleId));
    return ResponseEntity.ok(new RefuelDetails(result));
  }

  @GetMapping("/repairs/{vehicleId}")
  public ResponseEntity<RepairDetails> getRepairHistory(@PathVariable String vehicleId,
      @RequestParam(defaultValue = "false") boolean onlyLastRepair) {
    val result = this.vehicleDetailsDataProvider.getRepairsHistory(new VehicleId(vehicleId),
        onlyLastRepair);
    return ResponseEntity.ok(new RepairDetails(result));
  }

  @GetMapping("/states/{vehicleId}")
  public ResponseEntity<VehicleStateDetails> getVehicleStateHistory(
      @PathVariable String vehicleId) {
    val result = this.vehicleDetailsDataProvider.getVehicleStateHistory(new VehicleId(vehicleId));
    return ResponseEntity.ok(new VehicleStateDetails(result));
  }
}

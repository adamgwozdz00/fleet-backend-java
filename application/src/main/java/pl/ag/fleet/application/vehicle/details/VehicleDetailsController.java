package pl.ag.fleet.application.vehicle.details;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.vehicle.details.VehicleDetailsDataProvider;

@RestController
@RequestMapping("/vehicles/detalis")
@RequiredArgsConstructor
public class VehicleDetailsController {

  private final VehicleDetailsDataProvider vehicleDetailsDataProvider;

  @GetMapping("/insurances/{vehicleId}")
  public ResponseEntity<InsuranceDetails> getInsuranceHistory(@PathVariable String vehicleId) {
    val result = this.vehicleDetailsDataProvider.getInsuranceHistory(vehicleId);
    return ResponseEntity.ok(new InsuranceDetails(result.stream().map(
            record -> new InsuranceDetail(record.getId(), record.getInsuranceName(),
                record.getInsuranceCost(), record.getInsuranceExpirationDate().toString()))
        .collect(Collectors.toList())));
  }

  @GetMapping("/overviews/{vehicleId}")
  public ResponseEntity<OverviewDetails> getOverviewHistory(@PathVariable String vehicleId) {
    val result = this.vehicleDetailsDataProvider.getOverviewHistory(vehicleId);
    return ResponseEntity.ok(new OverviewDetails(result.stream().map(
        record -> new OverviewDetail(record.getId(), record.getOverviewName(),
            record.getOverviewCost(), record.getOverviewExpirationDate().toString(),
            record.getOverviewDescription())).collect(
        Collectors.toList())));
  }

  @GetMapping("/drivers/{vehicleId}")
  public ResponseEntity<DriverDetails> getDriverHistory(@PathVariable String vehicleId) {
    val result = this.vehicleDetailsDataProvider.getDriverHistory(vehicleId);
    return ResponseEntity.ok(new DriverDetails(result.stream().map(
        record -> new DriverDetail(record.getId(),
            record.getLastName(), record.getKilometers(),
            record.getTime().toString())).collect(
        Collectors.toList())));
  }

  @GetMapping("/insurances/actual/{vehicleId}")
  public ResponseEntity<InsuranceDetail> getActualInsurance(@PathVariable String vehicleId) {
    return ResponseEntity.ok(this.vehicleDetailsDataProvider.getActualInsurance(vehicleId)
        .map(record -> new InsuranceDetail(record.getId(), record.getInsuranceName(),
            record.getInsuranceCost(), record.getInsuranceExpirationDate().toString()))
        .orElse(new InsuranceDetail(-1, "", BigDecimal.ZERO, "")));
  }

  @GetMapping("/overviews/actual/{vehicleId}")
  public ResponseEntity<OverviewDetail> getActualOverview(@PathVariable String vehicleId) {
    return ResponseEntity.ok(this.vehicleDetailsDataProvider.getActualOverview(vehicleId)
        .map(record -> new OverviewDetail(record.getId(),
            record.getOverviewName(), record.getOverviewCost(),
            record.getOverviewExpirationDate().toString(), record.getOverviewDescription()))
        .orElse(new OverviewDetail(-1, "",
            BigDecimal.ZERO, "", "")));
  }
}

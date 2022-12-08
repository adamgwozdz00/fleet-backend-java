package pl.ag.fleet.application.vehicle;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.manager.security.AuthenticatedUserContextHolder;
import pl.ag.fleet.vehicle.InsuranceDTO;
import pl.ag.fleet.vehicle.OverviewDTO;
import pl.ag.fleet.vehicle.VehicleDTO;
import pl.ag.fleet.vehicle.VehicleId;
import pl.ag.fleet.vehicle.VehicleProvider;
import pl.ag.fleet.vehicle.VehicleService;
import pl.ag.fleet.vehicle.VehicleStateDTO;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

  private final VehicleService vehicleService;
  private final VehicleProvider vehicleProvider;
  private final AuthenticatedUserContextHolder contextHolder;
  private final VehicleResponseFactory vehicleResponseFactory;

  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody VehicleRequest request) {
    val companyId = contextHolder.getAuthenticatedUser().getPrincipal().getCompanyId();
    vehicleService.createVehicle(new VehicleDTO(companyId, request.getMake(), request.getModel(),
        request.getProductionYear(), request.getFuelType(), request.getVinNumber()));
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<Vehicles> getAllVehicles() {
    return ResponseEntity.ok(
        this.vehicleResponseFactory.create(contextHolder.getAuthenticatedUser().getPrincipal(),
            vehicleProvider));
  }

  @GetMapping("/fuels")
  public ResponseEntity<VehicleFuelType> getFuelTypes() {
    return ResponseEntity.ok(new VehicleFuelType());
  }

  @PutMapping("/{vehicleId}/states")
  public ResponseEntity<Void> updateState(@PathVariable String vehicleId, @RequestBody
  VehicleStateDTO request) {
    vehicleService.updateVehicleState(new VehicleId(vehicleId), request);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{vehicleId}/overviews")
  public ResponseEntity<Void> updateOverview(@PathVariable String vehicleId, @RequestBody
  OverviewDTO request) {
    vehicleService.addOrUpdateOverview(new VehicleId(vehicleId), request);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{vehicleId}/insurances")
  public ResponseEntity<Void> updateInsurance(@PathVariable String vehicleId, @RequestBody
  InsuranceDTO request) {
    vehicleService.addOrUpdateInsurance(new VehicleId(vehicleId), request);
    return ResponseEntity.ok().build();
  }
}

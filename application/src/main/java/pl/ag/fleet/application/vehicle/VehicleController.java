package pl.ag.fleet.application.vehicle;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.common.VehicleId;
import pl.ag.fleet.manager.security.AuthenticatedUserContextHolder;
import pl.ag.fleet.refuel.RefuelDTO;
import pl.ag.fleet.refuel.RefuelService;
import pl.ag.fleet.vehicle.InsuranceDTO;
import pl.ag.fleet.vehicle.OverviewDTO;
import pl.ag.fleet.vehicle.RepairDTO;
import pl.ag.fleet.vehicle.Result;
import pl.ag.fleet.vehicle.VehicleDTO;
import pl.ag.fleet.vehicle.VehicleProvider;
import pl.ag.fleet.vehicle.VehicleService;
import pl.ag.fleet.vehicle.VehicleStateDTO;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

  private final VehicleService vehicleService;
  private final RefuelService refuelService;
  private final VehicleProvider vehicleProvider;
  private final AuthenticatedUserContextHolder contextHolder;
  private final VehicleResponseFactory vehicleResponseFactory;

  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody VehicleBody request) {
    val companyId = contextHolder.getAuthenticatedUser().getPrincipal().getCompanyId();
    vehicleService.createVehicle(new VehicleDTO(companyId, request.getMake(), request.getModel(),
        request.getProductionYear(), request.getFuelType(), request.getVinNumber()));
    return ResponseEntity.ok().build();
  }

  @PostMapping("/csv")
  public ResponseEntity<Void> createMany(@RequestBody List<VehicleBody> request) {
    val companyId = contextHolder.getAuthenticatedUser().getPrincipal().getCompanyId();
    for (var vehicle : request) {
      vehicleService.createVehicle(new VehicleDTO(companyId, vehicle.getMake(), vehicle.getModel(),
          vehicle.getProductionYear(), vehicle.getFuelType(), vehicle.getVinNumber()));
    }
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<Vehicles> getAllVehicles(VehicleParams params) {
    return ResponseEntity.ok(
        this.vehicleResponseFactory.create(params,
            vehicleProvider));
  }

  @GetMapping("/fuels")
  public ResponseEntity<VehicleFuelType> getFuelTypes() {
    return ResponseEntity.ok(new VehicleFuelType());
  }

  @PutMapping("/{vehicleId}/states")
  public ResponseEntity<Result> updateState(@PathVariable String vehicleId, @RequestBody
  VehicleStateDTO request) {
    return ResponseEntity.ok(vehicleService.updateVehicleState(new VehicleId(vehicleId), request));
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

  @PutMapping("/{vehicleId}/repairs")
  public ResponseEntity<Void> updateInsurance(@PathVariable String vehicleId, @RequestBody
  RepairDTO request) {
    vehicleService.updateRepair(new VehicleId(vehicleId), request);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{vehicleId}/fuels")
  public ResponseEntity<Void> refuelVehicle(@PathVariable String vehicleId, @RequestBody
  RefuelDTO request) {
    refuelService.refuelVehicle(new VehicleId(vehicleId), request);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{vehicleId}/fuels/csv")
  public ResponseEntity<Void> refuelManyVehicles(@PathVariable String vehicleId, @RequestBody
  List<RefuelDTO> request) {
    for (var refuel : request) {
      refuelService.refuelVehicle(new VehicleId(vehicleId), refuel);
    }
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{vehicleId}")
  public ResponseEntity<Void> deleteVehicle(@PathVariable String vehicleId) {
    vehicleService.delete(new VehicleId(vehicleId));

    return ResponseEntity.ok().build();
  }
}

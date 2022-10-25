package pl.ag.fleet.acl;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import pl.ag.fleet.common.FuelType;
import pl.ag.fleet.event.EventDTO;
import pl.ag.fleet.event.EventSubscriber;
import pl.ag.fleet.event.EventType;
import pl.ag.fleet.vehicle.VehicleDTO;
import pl.ag.fleet.vehicle.VehicleService;

@Component
@RequiredArgsConstructor
public class VehicleEventSubscriber implements EventSubscriber {

  private final VehicleService vehicleService;

  // TODO simplify VV
  @Override
  public void subscribe(EventDTO data) {
    if (data.getEventType() == EventType.CREATE_VEHICLE) {
      val map = data.getData();
      val companyId = (Integer) ((Map<String, Object>) map.get("companyId")).get("id");
      val make = (String) ((Map<String, Object>) map.get("make")).get("make");
      val model = (String) ((Map<String, Object>) map.get("model")).get("model");
      val productionYear = (Integer) ((Map<String, Object>) map.get("productionYear")).get("year");
      val fuelType = (String) map.get("fuelType");
      val vinNumber = (String) ((Map<String, Object>) map.get("vinNumber")).get("vinNumber");
      val vehicleDTO = new VehicleDTO(companyId.longValue(), make, model, productionYear,
          FuelType.valueOf(fuelType), vinNumber);
      this.vehicleService.createVehicle(vehicleDTO);
    }
  }

  @Override
  public String getName() {
    return this.getClass().getName();
  }


}

package pl.ag.fleet.acl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import pl.ag.fleet.common.FuelType;
import pl.ag.fleet.event.EventSubscriber;
import pl.ag.fleet.event.ObservableEvent;
import pl.ag.fleet.vehicle.VehicleDTO;
import pl.ag.fleet.vehicle.VehicleId;
import pl.ag.fleet.vehicle.VehicleService;
import pl.ag.fleet.vehicle.VehicleStateDTO;

@Component
@RequiredArgsConstructor
public class VehicleEventSubscriber implements EventSubscriber {

  private final VehicleService vehicleService;
  private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());


  @Override
  public void subscribe(ObservableEvent observableEvent) {
    val data = mapper.valueToTree(observableEvent.getData());
    switch (observableEvent.getEventType()) {
      case CREATE_VEHICLE:
        val vehicleDTO = new VehicleDTO(data.get("companyId").asLong(),
            data.get("make").asText(),
            data.get("model").asText(),
            data.get("productionYear").asInt(),
            FuelType.valueOf(data.get("fuelType").asText().toUpperCase()),
            data.get("vinNumber").asText());
        this.vehicleService.createVehicle(vehicleDTO);
        return;

      case ACTUAL_VEHICLE_DATA:

        val vehicleId = new VehicleId(data.get("vehicleId").asText());
        val state = new VehicleStateDTO(data.get("driverId").asLong(),
            BigDecimal.valueOf(data.get("liters").asDouble()),
            BigDecimal.valueOf(data.get("kilometers").asDouble()),
            mapper.convertValue(observableEvent.getData().get("eventTime"), LocalDateTime.class));
        this.vehicleService.updateVehicleState(vehicleId, state);

    }


  }

  @Override
  public String getName() {
    return this.getClass().getName();
  }


}

package pl.ag.fleet.acl.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import pl.ag.fleet.common.FuelType;
import pl.ag.fleet.event.EventSubscriber;
import pl.ag.fleet.event.ObservableEvent;
import pl.ag.fleet.vehicle.InsuranceDTO;
import pl.ag.fleet.vehicle.OverviewDTO;
import pl.ag.fleet.vehicle.VehicleDTO;
import pl.ag.fleet.vehicle.VehicleId;
import pl.ag.fleet.vehicle.VehicleService;
import pl.ag.fleet.vehicle.VehicleStateDTO;

@RequiredArgsConstructor
public class VehicleEventSubscriber implements EventSubscriber {

  private final VehicleService vehicleService;
  private final ObjectMapper mapper;


  @Override
  public void subscribe(ObservableEvent observableEvent) {
    val data = mapper.valueToTree(observableEvent.getData());
    switch (observableEvent.getEventType()) {
      case CREATE_VEHICLE:
        createVehicle(data);
        break;
      case ACTUAL_VEHICLE_DATA:
        updateVehicleState(data);
        break;
      case INSURANCE:
        updateInsurance(data);
        break;
      case OVERVIEW:
        updateOverview(data);
        break;
    }


  }

  private void updateOverview(JsonNode data) {
    this.vehicleService.addOrUpdateOverview(new VehicleId(data.get("vehicleId").asText()),
        new OverviewDTO(data.get("overviewName").asText(),
            mapper.convertValue(data.get("expirationDate"), LocalDate.class),
            BigDecimal.valueOf(data.get("cost").asDouble()),
            data.get("description").asText()));
  }

  private void updateInsurance(JsonNode data) {
    this.vehicleService.addOrUpdateInsurance(new VehicleId(data.get("vehicleId").asText()),
        new InsuranceDTO(data.get("insuranceName").asText(),
            mapper.convertValue(data.get("expirationDate"), LocalDate.class),
            BigDecimal.valueOf(data.get("cost").asDouble())));
  }

  private void createVehicle(JsonNode data) {
    val vehicleDTO = new VehicleDTO(data.get("companyId").asLong(),
        data.get("make").asText(),
        data.get("model").asText(),
        data.get("productionYear").asInt(),
        FuelType.valueOf(data.get("fuelType").asText().toUpperCase()),
        data.get("vinNumber").asText());
    this.vehicleService.createVehicle(vehicleDTO);
  }

  private void updateVehicleState(JsonNode data) {
    val vehicleId = new VehicleId(data.get("vehicleId").asText());
    val state = new VehicleStateDTO(data.get("driverId").asLong(),
        BigDecimal.valueOf(data.get("liters").asDouble()),
        BigDecimal.valueOf(data.get("kilometers").asDouble()),
        mapper.convertValue(data.get("eventTime"), LocalDateTime.class));
    this.vehicleService.updateVehicleState(vehicleId, state);
  }

  @Override
  public String getName() {
    return this.getClass().getName();
  }


}

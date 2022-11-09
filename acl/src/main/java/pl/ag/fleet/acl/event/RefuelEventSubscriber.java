package pl.ag.fleet.acl.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.val;
import pl.ag.fleet.event.EventSubscriber;
import pl.ag.fleet.event.EventType;
import pl.ag.fleet.event.ObservableEvent;
import pl.ag.fleet.refuel.RefuelDTO;
import pl.ag.fleet.refuel.RefuelService;
import pl.ag.fleet.refuel.VehicleId;

@RequiredArgsConstructor
public class RefuelEventSubscriber implements EventSubscriber {

  private final RefuelService service;
  private final ObjectMapper mapper;

  @Override
  public void subscribe(ObservableEvent observableEvent) {
    if (observableEvent.getEventType() != EventType.REFUEL) {
      return;
    }

    val data = mapper.valueToTree(observableEvent.getData());

    service.refuelVehicle(new RefuelDTO(
        new VehicleId(data.get("vehicleId").asText()),
        BigDecimal.valueOf(data.get("liters").asDouble()),
        BigDecimal.valueOf(data.get("cost").asDouble())));


  }

  @Override
  public String getName() {
    return this.getClass().getName();
  }
}

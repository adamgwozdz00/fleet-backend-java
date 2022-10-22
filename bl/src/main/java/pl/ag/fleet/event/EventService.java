package pl.ag.fleet.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

  private final SendingService sendingService;
  private final EventFactory factory;

  public void process(CreateVehicleEvent event) {
    sendingService.send(factory.createEvent(event));
  }

  public void process(ActualDataEvent event) {
    sendingService.send(factory.createEvent(event));
  }

  public void process(RefuelEvent event) {
    sendingService.send(factory.createEvent(event));
  }

  public void process(OverviewEvent event) {
    sendingService.send(factory.createEvent(event));
  }

  public void process(InsuranceEvent event) {
    sendingService.send(factory.createEvent(event));
  }
}

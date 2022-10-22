package pl.ag.fleet.application.event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.event.CompanyId;
import pl.ag.fleet.event.CreateVehicleEvent;
import pl.ag.fleet.event.EventService;
import pl.ag.fleet.event.Make;
import pl.ag.fleet.event.Model;
import pl.ag.fleet.event.ProductionYear;
import pl.ag.fleet.event.VinNumber;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

  private final EventService service;

  @PostMapping("/create")
  public void pushEvent(@RequestBody CreateVehicleRequest request) {
    service.process(new CreateVehicleEvent(
        new VinNumber(request.getVinNumber()),
        new CompanyId(request.getCompanyId()),
        new Make(request.getMake()),
        new Model(request.getModel()),
        new ProductionYear(request.getProductionYear()),
        request.getFuelType()
    ));

  }
}

package pl.ag.fleet.application.event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.event.ActualData;
import pl.ag.fleet.event.CompanyId;
import pl.ag.fleet.event.Cost;
import pl.ag.fleet.event.CreateVehicleData;
import pl.ag.fleet.event.DriverId;
import pl.ag.fleet.event.Event;
import pl.ag.fleet.event.EventService;
import pl.ag.fleet.event.Insurance;
import pl.ag.fleet.event.InsuranceData;
import pl.ag.fleet.event.Kilometers;
import pl.ag.fleet.event.Liters;
import pl.ag.fleet.event.Make;
import pl.ag.fleet.event.Model;
import pl.ag.fleet.event.Overview;
import pl.ag.fleet.event.OverviewData;
import pl.ag.fleet.event.ProductionYear;
import pl.ag.fleet.event.RefuelData;
import pl.ag.fleet.event.VehicleId;
import pl.ag.fleet.event.VinNumber;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

  private final EventService service;

  @PostMapping("/create")
  public void pushEvent(@RequestBody CreateVehicleRequest request) {
    service.process(new Event(new CreateVehicleData(
        new VinNumber(request.getVinNumber()),
        new CompanyId(request.getCompanyId()),
        new Make(request.getMake()),
        new Model(request.getModel()),
        new ProductionYear(request.getProductionYear()),
        request.getFuelType()
    )));
  }

  @PostMapping("/actual")
  public void pushEvent(@RequestBody ActualDataRequest request) {
    service.process(new Event(new ActualData(
        new VehicleId(request.getVehicleId()),
        new DriverId(request.getDriverId()),
        new Liters(request.getFuelStateInLiters()),
        new Kilometers(request.getKilometersState())
    )));
  }

  @PostMapping("/refuel")
  public void pushEvent(@RequestBody RefuelEventRequest request) {
    service.process(new Event(new RefuelData(
        new VehicleId(request.getVehicleId()),
        new Liters(request.getLiters()),
        new Cost(request.getCost())
    )));
  }

  @PostMapping("/overview")
  public void pushEvent(@RequestBody OverviewEventRequest request) {
    service.process(new Event(new OverviewData(
        new VehicleId(request.getVehicleId()),
        new Overview(request.getName(), request.getExpirationDate(), new Cost(request.getCost()),
            request.getDescription())
    )));
  }

  @PostMapping("/insurance")
  public void pushEvent(@RequestBody InsuranceEventRequest request) {
    service.process(new Event(new InsuranceData(
        new VehicleId(request.getVehicleId()),
        new Insurance(request.getName(), request.getExpirationDate(), new Cost(request.getCost()))
    )));
  }
}

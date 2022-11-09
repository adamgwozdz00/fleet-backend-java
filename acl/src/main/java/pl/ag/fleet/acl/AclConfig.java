package pl.ag.fleet.acl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ag.fleet.acl.event.RefuelEventSubscriber;
import pl.ag.fleet.acl.event.VehicleEventSubscriber;
import pl.ag.fleet.event.EventObserver;
import pl.ag.fleet.refuel.RefuelService;
import pl.ag.fleet.vehicle.VehicleService;

@Configuration
public class AclConfig {

  @Autowired
  private VehicleService vehicleService;
  @Autowired
  private RefuelService refuelService;

  @Bean
  public EventObserver eventObserver() {
    val observer = new EventObserver();
    observer.addSubscriber(new VehicleEventSubscriber(vehicleService, mapper()));
    observer.addSubscriber(new RefuelEventSubscriber(refuelService, mapper()));
    return observer;
  }

  @Bean
  public ObjectMapper mapper() {
    return new ObjectMapper().registerModule(new JavaTimeModule());
  }
}

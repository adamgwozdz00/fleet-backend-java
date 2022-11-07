package pl.ag.fleet.acl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ag.fleet.event.EventObserver;
import pl.ag.fleet.vehicle.VehicleService;

@Configuration
public class AclConfig {

  @Autowired
  private VehicleService vehicleService;

  @Bean
  public EventObserver eventObserver() {
    val observer = new EventObserver();
    observer.addSubscriber(new VehicleEventSubscriber(vehicleService,mapper()));
    return observer;
  }

  @Bean
  public ObjectMapper mapper() {
    return new ObjectMapper().registerModule(new JavaTimeModule());
  }
}

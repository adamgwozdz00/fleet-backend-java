package pl.ag.fleet.acl;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ag.fleet.event.EventObserver;

@Configuration
public class EventObserverBean {

  @Autowired
  private VehicleEventSubscriber vehicleEventSubscriber;

  @Bean
  public EventObserver eventObserver() {
    val observer = new EventObserver();
    observer.addSubscriber(vehicleEventSubscriber);
    return observer;
  }
}

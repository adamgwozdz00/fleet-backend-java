package pl.ag.fleet.event;

import java.util.HashMap;
import java.util.Map;

public class EventObserver {

  private Map<String, EventSubscriber> subscribers;

  public EventObserver() {
    this.subscribers = new HashMap<>();
  }

  public void addSubscriber(EventSubscriber subscriber) {
    this.subscribers.put(subscriber.getName(), subscriber);
  }

  public void observe(ObservableEvent observableEvent) {
    this.subscribers.values().forEach(sub -> sub.subscribe(observableEvent));
  }

}

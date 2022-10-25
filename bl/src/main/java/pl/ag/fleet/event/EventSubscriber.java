package pl.ag.fleet.event;

public interface EventSubscriber {

  void subscribe(EventDTO data);

  String getName();

}

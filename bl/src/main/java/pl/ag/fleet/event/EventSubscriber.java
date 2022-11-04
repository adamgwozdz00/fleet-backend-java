package pl.ag.fleet.event;

public interface EventSubscriber {

  void subscribe(ObservableEvent data);

  String getName();

}

package pl.ag.fleet.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

  private final SendingService sendingService;
  private final EventDTOFactory factory;


  public  void process(Event event){
    sendingService.send(factory.create(event));
  }
}

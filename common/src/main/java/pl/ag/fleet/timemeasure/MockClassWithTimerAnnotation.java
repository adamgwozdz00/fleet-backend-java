package pl.ag.fleet.timemeasure;

import org.springframework.stereotype.Component;

@Component
public class MockClassWithTimerAnnotation {

  @Timer
  public void doSomething() {
    var charge = 0;
    for (var i = 0; i < 2_000_000_000L; i++) {
      charge++;
    }
    System.out.println("Done " + charge);
  }

}

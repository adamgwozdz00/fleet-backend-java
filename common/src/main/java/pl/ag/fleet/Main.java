package pl.ag.fleet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.ag.fleet.timemeasure.MockClassWithTimerAnnotation;

@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
    );
    context.scan("pl.ag.fleet.timemeasure");
    context.refresh();
    var mock = context.getBean(MockClassWithTimerAnnotation.class);
    mock.doSomething();
  }
}
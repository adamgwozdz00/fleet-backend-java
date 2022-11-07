package pl.ag.fleet.timemeasure;

import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Aspect
@Component
@EnableAspectJAutoProxy
public class TimerAspect {

  private final static Logger LOGGER = Logger.getLogger(TimerAspect.class.getName());

  @Around("@annotation(pl.ag.fleet.timemeasure.Timer)")
  private void beforeTimerAnnotation(JoinPoint joinPoint) {
    var startTime = System.currentTimeMillis();
    LOGGER.info(joinPoint.getSignature().getDeclaringTypeName() + " :: executed in : " + (
        System.currentTimeMillis()
            - startTime) + " ms");
  }

}

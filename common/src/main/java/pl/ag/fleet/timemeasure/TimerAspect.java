package pl.ag.fleet.timemeasure;

import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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
  private Object beforeTimerAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
    var startTime = System.currentTimeMillis();
    var join = joinPoint.proceed();
    var endTime = System.currentTimeMillis();
    LOGGER.info(joinPoint.getSignature().getDeclaringTypeName() + " :: executed in : " + (
        endTime
            - startTime) + " ms");
    return join;
  }

}

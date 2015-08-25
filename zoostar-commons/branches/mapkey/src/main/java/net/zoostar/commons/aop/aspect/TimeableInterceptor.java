package net.zoostar.commons.aop.aspect;

import net.zoostar.commons.aop.annotation.Timeable;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TimeableInterceptor {
	
	static final Logger log = LoggerFactory.getLogger(TimeableInterceptor.class);
	
	@Around(value="@annotation(timeable)")
	public Object interceptMethod(ProceedingJoinPoint joinPoint, Timeable timeable) throws Throwable {
		return intercept(joinPoint, timeable);
	}
	
	@Around(value="@within(timeable)")
	public Object interceptType(ProceedingJoinPoint joinPoint, Timeable timeable) throws Throwable {
		return intercept(joinPoint, timeable);
	}
	
	protected Object intercept(ProceedingJoinPoint joinPoint, Timeable timeable) throws Throwable {
		long time = System.currentTimeMillis();
		log.debug("Timed operation started for {}", joinPoint.getSignature());
		try {
			Object object = joinPoint.proceed();
			return object;
		} finally {
			time = System.currentTimeMillis() - time;
			if(timeable.threshold() > 0 && (time-timeable.threshold()) > 0) {
				log.warn("Call to {} took {} ms and went over the threshold limit of {} ms.",
						joinPoint.getSignature(), time, timeable.threshold());
			} else {
				log.info("Call to <{}> took {} ms.", joinPoint.getSignature(), time);
			}
		}
	}
}

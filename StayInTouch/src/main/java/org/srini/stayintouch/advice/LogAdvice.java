package org.srini.stayintouch.advice;

import java.security.Principal;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogAdvice {

	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	
	@Pointcut("execution (* org.srini.stayintouch.controllers.HomeController.gotoMain(..)) && args(principal, ..)")
	public void logLogin(Principal principal) {

	}

	@Before("logLogin(principal)")
	public void beforeLoginCall(Principal principal, JoinPoint joinPoint) {
		logger.info("1--------------->Entered User Name"+principal.getName());
		logger.info("2--------------->"+joinPoint.getSignature().toString());
		//one more comment
	}
	
	@Pointcut("execution (* org.srini.stayintouch.controllers.HomeController.getDays(..)) && args(year, month, ..)")
	public void getDays(Integer year, String month) {

	}

	@Before("getDays(year,month)")
	public void beforeGetDays(Integer year, String month, JoinPoint joinPoint) {
		logger.info("Year =>"+year+" month =>"+month);
	}
}

package org.srini.stayintouch.advice;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlLogAdvice {

	private static final Logger logger = LoggerFactory.getLogger(XmlLogAdvice.class);
	
	public void myAppLogger(JoinPoint jp){
		logger.info("Test value "+jp.getKind());
	}
}

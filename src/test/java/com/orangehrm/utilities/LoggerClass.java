package com.orangehrm.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerClass {
	
	  private static Logger logger = LogManager.getLogger(LoggerClass.class);
	

public static void main(String[] args) {
	
	logger.debug("My Debug Log");
	logger.info("My Info Log");
	logger.warn("My Warn Log");
	logger.error("My error log");
	logger.fatal("My fatal log");
	System.out.println("Test Completed");
}	

}

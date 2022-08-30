package com.camunda.util;

import java.util.logging.Logger;

public class LoggerUtil {
	private static Logger LOGGER;
	private Class userClass;
	
	// constructor
	public LoggerUtil(Class userClass) {
		this.userClass = userClass;
	}
	// method to provide logging services for a class
	public Logger getLogger() {
		setLogger();
		return LOGGER;
	}
	
	private void setLogger() {
		LoggerUtil.LOGGER = Logger.getLogger(userClass.getName());
	}

}

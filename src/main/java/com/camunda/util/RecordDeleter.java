package com.camunda.util;

import java.sql.PreparedStatement;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public class RecordDeleter {
	private Class userClass;
	private String query;
	
	public RecordDeleter(Class userClass, String query) {
		this.userClass = userClass;
		this.query = query;
	}
	
	public void deleteRecords() {
		// code to store the selected candidate to the database
		
		LoggerUtil loggerInstance = new LoggerUtil(userClass);
		Logger logger = loggerInstance.getLogger();
		
		DatabaseConnectionUtil db = new DatabaseConnectionUtil(query);
		PreparedStatement ps = db.getPreparedStatement();
		logger.info("Database accessed for deleting records!!!");
		
		try {	
			ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection();
		}

	}

}

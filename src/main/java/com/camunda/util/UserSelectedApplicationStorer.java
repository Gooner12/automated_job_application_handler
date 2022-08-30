package com.camunda.util;

import java.sql.PreparedStatement;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public class UserSelectedApplicationStorer {
	private Class userClass;
	private String query;
	private DelegateExecution execution;
	
	public UserSelectedApplicationStorer(Class userClass, String query, DelegateExecution execution) {
		this.userClass = userClass;
		this.query = query;
		this.execution = execution;
	}
	
	public void storeApplication() {
		// code to store the selected candidate to the database
		
		LoggerUtil loggerInstance = new LoggerUtil(userClass);
		Logger logger = loggerInstance.getLogger();
		
		DatabaseConnectionUtil db = new DatabaseConnectionUtil(query);
		PreparedStatement ps = db.getPreparedStatement();
		logger.info("Database accessed for storing application!!!");
		
		try {	
			// getting the business key of a process instance
			String id = (String)execution.getProcessInstance().getBusinessKey();
			ps.setString(1,id); 
			
			int record = ps.executeUpdate();
			System.out.println(record + " record has been inserted");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection();
		}

	}

}

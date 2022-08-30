package com.camunda.util;

import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public class RecordStorer {
	private Class userClass;
	private String query;
	private DelegateExecution execution;
	private LinkedHashMap<String, String> hash_map;
	
	public RecordStorer(Class userClass, String query, DelegateExecution execution, LinkedHashMap<String, String> hash_map) {
		this.userClass = userClass;
		this.query = query;
		this.execution = execution;
		this.hash_map = hash_map;
	}
	
	public void storeApplication() {
		// code to store an application to the database
		
		LoggerUtil loggerInstance = new LoggerUtil(userClass);
		Logger logger = loggerInstance.getLogger();
		
		DatabaseConnectionUtil db = new DatabaseConnectionUtil(query);
		PreparedStatement ps = db.getPreparedStatement();
		logger.info("Database accessed for storing application!!!");
		
		try {	
			String value;
			int index = 1;
			
			// getting all entries from the LinkedHashMap
	        Set<Map.Entry<String, String> > entrySet = hash_map.entrySet();
	        
	        // creating an iterator
	        Iterator<Map.Entry<String, String> > iterator = entrySet.iterator();
			
	        // inserting records in the database
	        while (iterator.hasNext()) {
	            value = iterator.next().getValue();
	            ps.setString(index, value);
	            index += 1;
	        }
			
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


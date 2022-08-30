package com.camunda.delegates;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.DatabaseConnectionUtil;
import com.camunda.util.LoggerUtil;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class QuickScreenerDelegate implements JavaDelegate {
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// code to get the records stored in the database
		String requirement_check;
		
		LoggerUtil loggerInstance = new LoggerUtil(QuickScreenerDelegate.class);
		Logger logger = loggerInstance.getLogger();
		
		DatabaseConnectionUtil db = new DatabaseConnectionUtil(ProcessConstants.RETRIEVE_QUERY);
		PreparedStatement ps = db.getPreparedStatement();
		logger.info("Database accessed for quick screening!!!");
		
		try {	
			// getting the business key of a process instance
			String id = (String)execution.getProcessInstance().getBusinessKey();
			ps.setString(1,id); 
		
			ResultSet rs = ps.executeQuery();	
			// sets the requirement met variable for checking later by one of the exclusive gateway
			while(rs.next()) {
				requirement_check = rs.getString(2);
				if (requirement_check.equalsIgnoreCase("true")) {
					execution.setVariable("requirement_satisfied", true);
				}
				else {
					execution.setVariable("requirement_satisfied", false);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection();
		}

	}

}

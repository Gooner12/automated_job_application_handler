package com.camunda.executionlisteners;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import com.camunda.delegates.QuickScreenerDelegate;
import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.DatabaseConnectionUtil;
import com.camunda.util.LoggerUtil;

public class QuickScreeningEndExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// this method will load the variables associated with the process instance stored in the database
		String name, has_referral, tailored_application, status, job_id;
		
		LoggerUtil loggerInstance = new LoggerUtil(QuickScreenerDelegate.class);
		Logger logger = loggerInstance.getLogger();
		
		DatabaseConnectionUtil db = new DatabaseConnectionUtil(ProcessConstants.RETRIEVE_QUERY);
		PreparedStatement ps = db.getPreparedStatement();
		logger.info("Database accessed at the end of quick screening!!!");
		
		try {	
			// getting the business key of a process instance
			String id = (String)execution.getProcessInstance().getBusinessKey();
			ps.setString(1,id); 
		
			ResultSet rs = ps.executeQuery();	
			// sets the requirement met variable for checking later by one of the exclusive gateway
			while(rs.next()) {
				name = rs.getString(1);
				status = rs.getString(3);
				has_referral = rs.getString(4);
				tailored_application = rs.getString(5);
				job_id = rs.getString(6);
				// setting the variables
				execution.setVariable("name", name);
				execution.setVariable("status", status);
				execution.setVariable("has_referral", has_referral);
				execution.setVariable("tailored_application", tailored_application);
				execution.setVariable("job_id", job_id);
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

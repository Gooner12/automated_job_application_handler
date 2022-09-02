package com.camunda.delegates;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.DatabaseConnectionUtil;
import com.camunda.util.LoggerUtil;

public class StoreOutcomeDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// code to store a record in the database
		
		LoggerUtil loggerInstance = new LoggerUtil(StoreOutcomeDelegate.class);
		Logger logger = loggerInstance.getLogger();
		
		DatabaseConnectionUtil db = new DatabaseConnectionUtil(ProcessConstants.STORE_OUTCOME);
		PreparedStatement ps = db.getPreparedStatement();
		logger.info("Database accessed for storing outcome!!!");
		
		try {	
			// getting the required variables
			String job_id, name, status, has_referral, tailored_application, waitlisted, competition, outcome, reason;
			job_id = (String)execution.getVariable("job_id");
			name = (String)execution.getVariable("name");
			status = (String)execution.getVariable("status");
			has_referral = (String)execution.getVariable("has_referral");
			tailored_application = (String)execution.getVariable("tailored_application");
			waitlisted = (String)execution.getVariable("waitlisted");
			competition = (String)execution.getVariable("competition");
			outcome = (String)execution.getVariable("outcome");
			reason = (String)execution.getVariable("reason");
			
			// query to add values
			ps.setString(1,job_id); 
			ps.setString(2,name); 
			ps.setString(3,status); 
			ps.setString(4,has_referral);
			ps.setString(5,tailored_application);
			ps.setString(6,waitlisted);
			ps.setString(7,competition);
			ps.setString(8,outcome);
			ps.setString(9,reason); 
		
			int record = ps.executeUpdate();
			System.out.println("Stored " + record + " record");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection();
		}
		}

}

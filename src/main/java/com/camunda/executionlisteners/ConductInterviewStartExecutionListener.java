package com.camunda.executionlisteners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.Variables;

import com.camunda.delegates.QuickScreenerDelegate;
import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.DatabaseConnectionUtil;
import com.camunda.util.LoggerUtil;

import java.lang.Math;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.logging.Logger;

public class ConductInterviewStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// here the system will provide the volume of the application to the interviewer
		// so that the interviewer will have a sense of job competitiveness
		
		LoggerUtil loggerInstance = new LoggerUtil(QuickScreenerDelegate.class);
		Logger logger = loggerInstance.getLogger();
		
		DatabaseConnectionUtil db = new DatabaseConnectionUtil(ProcessConstants.CHECK_VALID_CANDIDATE);
		PreparedStatement ps = db.getPreparedStatement();
		logger.info("Database accessed for checking the job application volume!!!");
		
		try {	
			// setting the condition for classifying valid candidates
			String has_requirement = "true";
			ps.setString(1,has_requirement); 
		
			ResultSet rs = ps.executeQuery();	
			// sets the requirement met variable for checking later by one of the exclusive gateway
			while(rs.next()) {
				int count = rs.getInt(1);
				if (count > 0 && count <= 7) {
					execution.setVariable("competition", "Low");
					execution.setVariable("add_threshold", 0);
				}
				else if (count > 7 && count <= 15) {
					execution.setVariable("competition", "Medium");
					execution.setVariable("add_threshold", 1);
				}
				else if (count > 15 && count <= 25) {
					execution.setVariable("competition", "High");
					execution.setVariable("add_threshold", 2);
				}
				else if (count > 25) {
					execution.setVariable("competition", "Very High");
					execution.setVariable("add_threshold", 3);
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

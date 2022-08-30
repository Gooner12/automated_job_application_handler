package com.camunda.delegates;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.DatabaseConnectionUtil;
import com.camunda.util.LoggerUtil;

public class CheckWaitListedApplicantDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// here we check if there are at least one candidate in the waiting list table
		LoggerUtil loggerInstance = new LoggerUtil(QuickScreenerDelegate.class);
		Logger logger = loggerInstance.getLogger();
		
		DatabaseConnectionUtil db = new DatabaseConnectionUtil(ProcessConstants.CHECK_WAITLISTED_CANDIDATE);
		PreparedStatement ps = db.getPreparedStatement();
		logger.info("Database accessed for checking waitlisted applicants!!!");
		
		try {		
			ResultSet rs = ps.executeQuery();	
			while(rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					execution.setVariable("applicants_waiting", true);
				}
				else {
					execution.setVariable("applicants_waiting", false);
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

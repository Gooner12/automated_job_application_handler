package com.camunda.delegates;

import java.sql.PreparedStatement;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.DatabaseConnectionUtil;
import com.camunda.util.LoggerUtil;

public class StoreCandidateScoreDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// here we write code to store the candidate score
		double candidate_score = (Double) execution.getVariable("score");
		
		
		LoggerUtil loggerInstance = new LoggerUtil(StoreCandidateScoreDelegate.class);
		Logger logger = loggerInstance.getLogger();
		
		DatabaseConnectionUtil db = new DatabaseConnectionUtil(ProcessConstants.UPDATE_SCORE);
		PreparedStatement ps = db.getPreparedStatement();
		logger.info("Database accessed for storing candidate score!!!");
		
		try {	
			// getting the business key of a process instance
			String id = (String)execution.getProcessInstance().getBusinessKey();
			ps.setDouble(1, candidate_score); 
			ps.setString(2, id);
		
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

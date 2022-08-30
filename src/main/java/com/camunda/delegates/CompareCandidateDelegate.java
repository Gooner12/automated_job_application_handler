package com.camunda.delegates;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.DatabaseConnectionUtil;
import com.camunda.util.LoggerUtil;

public class CompareCandidateDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// here we retrieve the highest rank of the candidate
		double candidate_score = (Double) execution.getVariable("score");
		LoggerUtil loggerInstance = new LoggerUtil(CompareCandidateDelegate.class);
		Logger logger = loggerInstance.getLogger();
		
		DatabaseConnectionUtil db = new DatabaseConnectionUtil(ProcessConstants.RETRIEVE_MAX_SCORE);
		PreparedStatement ps = db.getPreparedStatement();
		logger.info("Database accessed for comparing candidates!!!");
		
		try {	
			ResultSet rs = ps.executeQuery();	
			while(rs.next()) {
				double max_score = rs.getDouble(1);
//				execution.setVariable("max_score", Variables.doubleValue(max_score));
				if (candidate_score == max_score) {
					execution.setVariable("top_candidate", true);
				}
				else {
					execution.setVariable("top_candidate", false);
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

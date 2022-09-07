package com.camunda.delegates;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.DatabaseConnectionUtil;
import com.camunda.util.LoggerUtil;

public class PersonaliseMessageDelegate implements JavaDelegate {
	private String personalised_message;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// we will personalise message here to candidates
		String candidate_name = null;
		String outcome, reason, application_tailored, feedback, comment, competition;
		
		// access the candidate database to get the name of the unsuccessful applicant
		LoggerUtil loggerInstance = new LoggerUtil(QuickScreenerDelegate.class);
		Logger logger = loggerInstance.getLogger();
		
		DatabaseConnectionUtil db = new DatabaseConnectionUtil(ProcessConstants.RETRIEVE_QUERY);
		PreparedStatement ps = db.getPreparedStatement();
		logger.info("Database accessed for personalising message!!!");
		
		try {	
			// getting the business key of a process instance
			String id = (String)execution.getProcessInstance().getBusinessKey();
			ps.setString(1,id); 
		
			ResultSet rs = ps.executeQuery();	
			// sets the requirement met variable for checking later by one of the exclusive gateway
			while(rs.next()) {
				candidate_name = rs.getString(1);
			}
			// drafting custom message
			outcome = (String)execution.getVariable("outcome");
			reason = (String)execution.getVariable("reason");
			competition = (String)execution.getVariable("competition");
			application_tailored = (String)execution.getVariable("tailored_application");
<<<<<<< HEAD
			has_referral = (String)execution.getVariable("has_referral");
			feedback = "NA";
=======
>>>>>>> cc4f91efcf6b66730a5efe70a70a970830257d05
			if (outcome.contains("Unsuccessful")) {
				personalised_message = "We are sorry that we are unable to move forward with your application in this instance.";
				
				if (reason.equalsIgnoreCase("requirement not met")) {
					comment = " We determined that you have failed to meet the requirements of the job.";
					personalised_message = personalised_message + comment;
				}
				
				else if (reason.equalsIgnoreCase("found high scoring candidate")) {
					comment = " We have found a high-scoring candidate that fits the job role." +
				" Based on our evaluation, your application received the following result on our internal assessment:";
//							"\n" + " Job Requirement: Met " + "\n" +
//				"Application Tailored: " + application_tailored + "\n" + 
//							"Considered for interview: Yes" + "\n" +
//				"Last Status: Waitlisted" + "\n"
//						+ "Job Competition: " + competition ;
					personalised_message = personalised_message + comment;
				}
					
				else {
					comment = " Thank you for investing your time and effort throughout the application process."
							+ " We highly value your skills and experience."
							+ " The level of competition for this job is " + competition.toLowerCase() + "."
							+ " Please, contact the recruitment team if you need additional feedback about your application.";
					personalised_message = personalised_message + comment;
				}
					
				// adding feedback if necessary uncomment it once we move to storing data from application
//				String has_referral = (String)execution.getVariable("has_referral");
				if (application_tailored.equalsIgnoreCase("false")) {
					feedback = " Your application can be tailored to the job to improve your application.";
//					personalised_message = personalised_message + feedback;
				}
			
				
<<<<<<< HEAD
				if (has_referral.equalsIgnoreCase("false")) {
					feedback = " For future applications, you can consider getting a referral "
							+ "from the company employee to boost your application.";
//					personalised_message += feedback;		
				}
=======
//				if (has_referral.equalsIgnoreCase("false")) {
//					feedback = "For future applications, you can consider getting a referral "
//							+ "from the company employee to boost your application.";
//					personalised_message += feedback;
//				}
>>>>>>> cc4f91efcf6b66730a5efe70a70a970830257d05
					
			}
			
			else if (outcome.contains("Successful")) {
				personalised_message = "Congratulations! You've been selected as a suitable candidate for the role.";
			}
			
			execution.setVariable("feedback", feedback);
			execution.setVariable("message", personalised_message);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection();
		}
	}

}

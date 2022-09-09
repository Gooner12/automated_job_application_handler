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
		String outcome, reason, application_tailored, feedback, comment, competition, has_referral;
		
		// access the candidate database to get the name of the unsuccessful applicant
		LoggerUtil loggerInstance = new LoggerUtil(QuickScreenerDelegate.class);
		Logger logger = loggerInstance.getLogger();
		logger.info("Personalising message!!!");
		
		try {	
			// drafting custom message
			candidate_name = (String)execution.getVariable("name");
			outcome = (String)execution.getVariable("outcome");
			reason = (String)execution.getVariable("reason");
			competition = (String)execution.getVariable("competition");
			application_tailored = (String)execution.getVariable("tailored_application");
			has_referral = (String)execution.getVariable("has_referral");
			feedback = "";
			if (outcome.contains("Unsuccessful")) {
				personalised_message = "We are sorry that we are unable to move forward with your application in this instance.";
				
				if (reason.equalsIgnoreCase("requirement not met")) {
					comment = " We determined that you have failed to meet the requirements of the job.";
					personalised_message = personalised_message + comment;
				}
				
				else if (reason.equalsIgnoreCase("found high scoring candidate")) {
					comment = " We have found a high-scoring candidate that fits the job role." +
				" Based on our evaluation, your application received the following result on our internal assessment:";
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
				if (application_tailored.equalsIgnoreCase("false")) {
					feedback = feedback + " Your application can be tailored to the job to improve your application.";
				}
			
				
				if (has_referral.equalsIgnoreCase("false")) {
					feedback = feedback + " For future applications, you can consider getting a referral "
							+ "from the company employee to boost your application.";	
				}
					
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
	}

}

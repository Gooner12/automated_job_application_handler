package com.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendOutcomeMessageDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String business_key = (String)execution.getProcessInstance().getBusinessKey();
		
		// retrieving variables to send with the message
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
		
		// adjusting null values
		if (competition == null) {
			competition = "Not Applicable";
		}
		else {
			competition = competition;
		}
		
		if (waitlisted == null) {
			waitlisted = "Not Applicable";
		}
		else {
			waitlisted = waitlisted;
		}
		
		// sending unsuccessful message
		String outcome_message = (String)execution.getVariable("message");
		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("application_outcome")
		.setVariable("message", outcome_message)
		.setVariable("name", name)
		.setVariable("job_id", job_id)
		.setVariable("status", status)
		.setVariable("has_referral", has_referral)
		.setVariable("tailored_application", tailored_application)
		.setVariable("waitlisted", waitlisted)
		.setVariable("competition", competition)
		.setVariable("outcome", outcome)
		.setVariable("reason", reason)
		.processInstanceBusinessKey(business_key)
		.correlate();

	}

}

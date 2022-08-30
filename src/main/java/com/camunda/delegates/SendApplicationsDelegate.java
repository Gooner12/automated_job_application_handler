package com.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendApplicationsDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// sending the message loaded with variables

		String business_key = (String)execution.getProcessInstance().getBusinessKey();
		String name = (String)execution.getVariable("name");
		String status = (String)execution.getVariable("status");
		String requirement_met = (String)execution.getVariable("requirement_met");
		String referee = (String)execution.getVariable("has_referral");
		String tailored_application = (String)execution.getVariable("tailored_application");
		String job_id = (String)execution.getVariable("job_id");
		
		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("job_application")
//		.setVariable("business_key", business_key)
		.setVariable("name", name)
		.setVariable("status", status)
		.setVariable("requirement_met", requirement_met)
		.setVariable("referee", referee)
		.setVariable("tailored_application", tailored_application)
		.setVariable("job_id", job_id)
		.processInstanceBusinessKey(business_key)
		.correlate();	

	}

}

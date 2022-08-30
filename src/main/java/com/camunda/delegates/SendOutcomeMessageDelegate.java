package com.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendOutcomeMessageDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String business_key = (String)execution.getProcessInstance().getBusinessKey();
		
		// sending unsuccessful message
		String outcome = (String)execution.getVariable("message");
		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("application_outcome")
		.setVariable("message", outcome)
		.processInstanceBusinessKey(business_key)
		.correlate();

	}

}

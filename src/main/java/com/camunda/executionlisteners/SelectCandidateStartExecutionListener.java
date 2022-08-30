package com.camunda.executionlisteners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class SelectCandidateStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// add selected candidate variable as false as a default value at start
		// and based on user discretion, this value can be changed later
		execution.setVariable("selected_candidate", false);
	}

}

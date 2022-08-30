package com.camunda.executionlisteners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class LabelCandidateStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// add last candidate variable as false as a default value at start
		// and based on the pool status, this value can be changed later
		execution.setVariable("last_candidate", false);

	}

}

package com.camunda.executionlisteners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.RecordDeleter;



public class PositionFulfilledStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// we delete the records from the wait listed application table 
		RecordDeleter deleter = new RecordDeleter(PositionFulfilledStartExecutionListener.class, ProcessConstants.REMOVE_WAITLISTED_CANDIDATE);
		deleter.deleteRecords();

	}

}

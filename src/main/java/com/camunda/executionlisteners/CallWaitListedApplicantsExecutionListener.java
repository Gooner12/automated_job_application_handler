package com.camunda.executionlisteners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.RecordDeleter;

public class CallWaitListedApplicantsExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// we delete the records from the fast tracked application table 
		RecordDeleter deleter = new RecordDeleter(CallWaitListedApplicantsExecutionListener.class, ProcessConstants.REMOVE_FASTTRACKED_CANDIDATE);
		deleter.deleteRecords();

	}

}

package com.camunda.executionlisteners;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.RecordDeleter;



public class PositionFulfilledStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		// we delete the records from the wait listed application table 
		RecordDeleter deleter = new RecordDeleter(PositionFulfilledStartExecutionListener.class, ProcessConstants.REMOVE_WAITLISTED_CANDIDATE);
		deleter.deleteRecords();
		
		map = execution.getVariables();
		String competition_info = (String)map.get("competition");
		execution.setVariable("competition", competition_info);
		execution.setVariable("wait_listed", "true");

	}

}

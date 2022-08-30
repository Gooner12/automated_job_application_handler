package com.camunda.executionlisteners;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class BroadcastPositionFullStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		
		Map <String, Object> map = new HashMap<String, Object>();
		// passing a variable to receiving signals
		Object competition = execution.getVariable("competition");
		map.put("competition", competition);
		
		execution.getProcessEngineServices().getRuntimeService()
		.createSignalEvent("Position_full")
		.setVariables(map)
		.send();

	}

}

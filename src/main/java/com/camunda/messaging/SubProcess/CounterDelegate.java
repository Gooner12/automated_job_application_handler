package com.camunda.messaging.SubProcess;

import java.util.List;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.VariableInstance;

public class CounterDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		ProcessEngineServices processEngine = execution.getProcessEngineServices();
		RepositoryService repositoryService =  processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
//		String key = repositoryService.getProcessDefinition(execution.getProcessDefinitionId()).getKey();
//		System.out.println(key);
		
	    
	 // query for latest process definition with given name
	    ProcessDefinition myProcessDefinition =
	        repositoryService.createProcessDefinitionQuery()
	            .processDefinitionName("Sub Process")
	            .latestVersion()
	            .singleResult();
	    
	    String ref = (String)execution.getVariable("ref");
	    
	 // list all running/unsuspended instances of the process
	    List<ProcessInstance> processInstances =
	        runtimeService.createProcessInstanceQuery()
	            .processDefinitionId(myProcessDefinition.getId())
	            .variableValueEquals(ref, "yes")
//	            .active() // we only want the unsuspended process instances
	            .list();
	    
	            
//	    VariableInstance vs = runtimeService.createVariableInstanceQuery()
//	    		.variableValueEquals("ref", "Yes").singleResult();
//	    
	    
	    System.out.println(processInstances.size());
//	    System.out.println(vs);

	}

}

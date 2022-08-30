package com.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.UserSelectedApplicationStorer;

public class StoreSelectedCandidateDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		// call another class to store the wait listed candidate
				UserSelectedApplicationStorer storer = new UserSelectedApplicationStorer(StoreSelectedCandidateDelegate.class, ProcessConstants.TRANSFER_ROW_SELECTED, execution);
				storer.storeApplication();
	}

}

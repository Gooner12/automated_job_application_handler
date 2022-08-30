package com.camunda.delegates;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.messaging.SubProcess.ProcessConstants;
import com.camunda.util.KeyValueBundler;
import com.camunda.util.MapBuilder;
import com.camunda.util.RecordStorer;

public class StoreApplicationDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
//		// We include codes to store the application received from the applicants here
//		
//		// For now, we just print a statement, later we need to write the actual code
//		// getting the required variables needed for inserting in the table
//		String id = (String)execution.getProcessInstance().getBusinessKey();
//		String job_id = (String)execution.getVariable("job_id");
//		String name = (String)execution.getVariable("name");
//		String status = (String)execution.getVariable("status");
//		String requirement_met = (String)execution.getVariable("requirement_met");
//		String tailored_application = (String)execution.getVariable("tailored_application");
//
//		// accessing the referee details for storing into two tables: primary and foreign table
//		String has_referral = "false";
//		String referee_id = null;
//		String referee = (String)execution.getVariable("referee");
//		if (referee.contains("Ram Das")) {
//			has_referral = "true";
//			referee_id = "1";
//		}
//		else if (referee.contains("John Cena")) {
//			has_referral = "true";
//			referee_id = "2";
//		}
//		else if (referee.contains("Nazim Hussain")) {
//			has_referral = "true";
//			referee_id = "3";
//		}
//		else {
//			has_referral = "false";
//		}
//		execution.setVariable("has_referral", has_referral);
//		
//		// creating an array of string type containing values and building a map from it
////		String k[] = new String[] {"id", "job_id", "name", "status", "requirement_met", "has_referral", "tailored_application", "referee_id"};
//		String v[] = new String[] {id, job_id, name, status, requirement_met, has_referral, tailored_application, referee_id};
//		
//		// populating the job_application table
//		KeyValueBundler bundler_job = new KeyValueBundler(v);
//		LinkedHashMap<String, String> hash_map_job = bundler_job.bundler();
//        
//        // storing the record in the job_application table
//        RecordStorer storer_job = new RecordStorer(StoreApplicationDelegate.class, ProcessConstants.STORE_APPLICATIONS, execution, hash_map_job);
//        storer_job.storeApplication();
		
		System.out.println("Stored Application");
	}

}

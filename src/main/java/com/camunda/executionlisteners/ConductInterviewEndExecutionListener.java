package com.camunda.executionlisteners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.Variables;

import java.lang.Math;
import java.text.DecimalFormat;

public class ConductInterviewEndExecutionListener implements ExecutionListener {
	private static double score = 0;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// here candidate will be scored based on their credentials and their performance in the interview
		// performance score is provided using a random number generation method
		int min = 0;
		int max = 5;
		
		// logic to calculate score
		if (((String)execution.getVariable("has_referral")).equalsIgnoreCase("true")) {
			score += 10;
		}

//		if (((String)execution.getVariable("status")).equals("Permanent Resident") || ((String)execution.getVariable("status")).equals("Australian Citizen")) {
//			score += 3; // We'll uncomment this later
//		}
		
		if (((String)execution.getVariable("tailored_application")).equalsIgnoreCase("true")) {
			score += 3;
		}
		
		// generate a random number between 0 and 5 and add to the score to get the total score
		double interview_points = Math.random() * (max - min + 1) + min;
		score += interview_points;

		// Creating an object of DecimalFormat class for rounding to 8 decimal places
        DecimalFormat df_obj = new DecimalFormat("#.########");
        score = Double.valueOf(df_obj.format(score)); // Casting to double from string
		
		execution.setVariable("score", Variables.doubleValue(score));

	}

}

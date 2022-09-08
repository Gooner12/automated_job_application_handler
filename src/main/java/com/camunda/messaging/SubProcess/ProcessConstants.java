package com.camunda.messaging.SubProcess;

public class ProcessConstants {

  public static final String PROCESS_DEFINITION_KEY = "SubProcess"; // BPMN Process ID
  public static final String DB_USER_NAME = "root";
  public static final String DB_USER_PASSWORD = "korea123";
  public static final String RETRIEVE_QUERY = "SELECT Name, Requirement_met from Job_application Where Id = ?";
  public static final String RETRIEVE_REFERRED_CANDIDATE = "Select Count(*) from Job_application Where Has_referral = ?";
  public static final String TRANSFER_ROW_WAITLISTED = "Insert Into waitlisted_job_application (Id, Job_id, Name, Has_referral, Tailored_application) "
  		+ "SELECT Id, Job_id, Name, Has_referral, Tailored_application "
  		+ "FROM job_application "
  		+ "WHERE Id = ?";
  public static final String TRANSFER_ROW_SELECTED = "Insert Into fasttracked_job_application (Id, Job_id, Name, Has_referral, Tailored_application) "
	  		+ "SELECT Id, Job_id, Name, Has_referral, Tailored_application "
	  		+ "FROM job_application "
	  		+ "WHERE Id = ?";
  public static final String UPDATE_SCORE = "Update Fasttracked_job_application Set Score = ? Where Id = ?";
  public static final String RETRIEVE_MAX_SCORE = "Select Max(Score) from Fasttracked_job_application";
  public static final String CHECK_WAITLISTED_CANDIDATE = "Select Count(*) from Waitlisted_job_application";
  public static final String REMOVE_WAITLISTED_CANDIDATE = "Delete from Waitlisted_job_application";
  public static final String REMOVE_FASTTRACKED_CANDIDATE = "Delete from Fasttracked_job_application";
  public static final String STORE_APPLICATIONS = "Insert Into Job_application (Id, Job_id, Name, Status, Requirement_met, Has_referral, Tailored_application, Referee_id, Email) "
		  +"Values (?,?,?,?,?,?,?,?,?)";
  public static final String STORE_REFEREE = "Insert Into Referee (Id, Name) Values (?,?)";
  public static final String CHECK_VALID_CANDIDATE = "Select Count(*) from job_application Where Requirement_met = ?";
}

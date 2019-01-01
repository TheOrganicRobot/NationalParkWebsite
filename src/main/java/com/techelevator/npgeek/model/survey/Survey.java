package com.techelevator.npgeek.model.survey;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {

	private int surveyId;
	@NotBlank(message="Park is required")
	private String parkCode;
	@Email(message="Please enter a valid email")
	@NotBlank(message="Email is required")
	private String email;
	@NotBlank(message="State is required")
	private String state;
	@NotBlank(message="Activity level is required")
	private String activityLevel;
	private int survey_total;
	private String parkName;
	
	
	
	
	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	public int getSurvey_total() {
		return survey_total;
	}
	public void setSurvey_total(int survey_total) {
		this.survey_total = survey_total;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	
}

package com.techelevator.npgeek.model.survey;

import java.util.List;

public interface SurveyDao {

	public List<Survey> getAllSurveysByCount();
	public void saveSurvey(Survey survey);
	
}

package com.techelevator.npgeek.model.survey;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.park.Park;

@Component
public class JdbcSurveyDao implements SurveyDao{
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Survey> getAllSurveysByCount() {
		List<Survey> surveyList = new ArrayList<Survey>();
		String sqlSelectAllSurveys = "SELECT s.parkcode,p.parkname as parkName, count(s.parkcode) as survey_total\n" + 
				"FROM park p\n" + 
				"join  survey_result s\n" + 
				"ON s.parkcode = p.parkcode\n" + 
				"GROUP BY parkName,s.parkcode\n" + 
				"ORDER BY  survey_total DESC, parkname;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllSurveys);
		while(results.next()) {
			Survey survey = new Survey();
			survey = mapRowToSurvey(results);
			surveyList.add(survey);
		}
		return surveyList;
	}


	@Override
	public void saveSurvey(Survey survey) {
		int id = getNextId();
		String sqlInsertPost = "insert into survey_result(surveyid,parkcode,emailaddress,state,activitylevel) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sqlInsertPost, id, survey.getParkCode(),survey.getEmail(), survey.getState(), survey.getActivityLevel());
		
	}


private Survey mapRowToSurvey(SqlRowSet results) {
	Survey survey = new Survey();
	survey.setParkName(results.getString("parkName"));
	survey.setSurvey_total(results.getInt("survey_total"));
	survey.setParkCode(results.getString("parkcode"));
	
	return survey;
}
	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyid')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		int id = 0;
		if(results.next()) {
			id = results.getInt(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}
}

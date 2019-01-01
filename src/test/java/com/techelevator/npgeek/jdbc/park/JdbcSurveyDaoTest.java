package com.techelevator.npgeek.jdbc.park;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.jdbc.park.DAOIntegrationTest;
import com.techelevator.npgeek.model.survey.JdbcSurveyDao;
import com.techelevator.npgeek.model.survey.Survey;

public class JdbcSurveyDaoTest extends DAOIntegrationTest{

	private JdbcSurveyDao dao = new JdbcSurveyDao(getDataSource());
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource()) ;
	
	@Before
	public void truncateTables() {
		JdbcTemplate template = new JdbcTemplate(getDataSource());
		int numberOfRowsAffected = template.update("TRUNCATE TABLE survey_result,park CASCADE;");
		System.out.printf("%d records have been truncated.", numberOfRowsAffected);
	}
	
	@Before
	public void setup() {
		String insertPark = "INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('CVNP', 'Cuyahoga Valley National Park', 'Ohio', 32832, 696, 125, 0, 'Woodland', 2000, 2189849, 'Of all the paths you take in life, make sure a few of them are dirt.', 'John Muir', 'Though a short distance from the urban areas of Cleveland and Akron, Cuyahoga Valley National Park seems worlds away. The park is a refuge for native plants and wildlife, and provides routes of discovery for visitors. The winding Cuyahoga River gives way to deep forests, rolling hills, and open farmlands. Walk or ride the Towpath Trail to follow the historic route of the Ohio & Erie Canal', 0, 390);"; 
		jdbcTemplate.update(insertPark);
		String insertPark2 = "INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('ENP', 'Everglades National Park', 'Florida', 1508538, 0, 35, 0, 'Tropical', 1934, 1110901, 'There are no other Everglades in the world. They are, they have always been, one of the unique regions of the earth; remote, never wholly known. Nothing anywhere else is like them.', 'Marjory Stoneman Douglas', 'The Florida Everglades, located in southern Florida, is one of the largest wetlands in the world. Several hundred years ago, this wetlands was a major part of a 5,184,000 acre watershed that covered almost a third of the entire state of Florida. The Everglades consist of a shallow sheet of fresh water that rolls slowly over the lowlands and through billions of blades of sawgrass. As water moves through the Everglades, it causes the sawgrass to ripple like green waves; this is why the Everglades received the nickname \"River of Grass.\"', 8, 760);"; 
		jdbcTemplate.update(insertPark2);
		String insertSurvey = "INSERT INTO survey_result(surveyid,parkcode,emailaddress,state,activitylevel) VALUES('1','CVNP','all5@tc.com','Ohio','active');";	 
		jdbcTemplate.update(insertSurvey);
		
	}
	
	@Test
	public void test_get_all_cparks() {
		List<Survey> theSurvey = dao.getAllSurveysByCount();
		assertNotNull(theSurvey);
		assertEquals(1, theSurvey.size());
	}
	@Test
	public void test_save_survey() {
		Survey mySurvey = new Survey();
		mySurvey.setSurveyId(2);
		mySurvey.setParkCode("ENP");
		mySurvey.setEmail("all7@tc.com");
		mySurvey.setState("Indiana");
		mySurvey.setActivityLevel("sedentary");
		dao.saveSurvey(mySurvey);
		List<Survey> newSurvey = dao.getAllSurveysByCount();
		assertNotNull(mySurvey);
		assertEquals(2, newSurvey.size());
		assertEquals(2,mySurvey.getSurveyId());
		assertEquals("ENP",mySurvey.getParkCode());
		assertEquals("all7@tc.com",mySurvey.getEmail());
		assertEquals("Indiana",mySurvey.getState());
		assertEquals("sedentary",mySurvey.getActivityLevel());
		
	}


}

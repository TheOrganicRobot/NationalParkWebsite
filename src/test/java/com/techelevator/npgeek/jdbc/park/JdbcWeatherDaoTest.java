package com.techelevator.npgeek.jdbc.park;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.jdbc.park.DAOIntegrationTest;
import com.techelevator.npgeek.model.survey.JdbcSurveyDao;
import com.techelevator.npgeek.model.survey.Survey;
import com.techelevator.npgeek.model.weather.JdbcWeatherDao;
import com.techelevator.npgeek.model.weather.Weather;

public class JdbcWeatherDaoTest extends DAOIntegrationTest{

	private JdbcWeatherDao dao = new JdbcWeatherDao(getDataSource());
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource()) ;
	
	@Before
	public void truncateTables() {
		JdbcTemplate template = new JdbcTemplate(getDataSource());
		int numberOfRowsAffected = template.update("TRUNCATE TABLE weather,park CASCADE;");
		System.out.printf("%d records have been truncated.", numberOfRowsAffected);
	}
	
	@Before
	public void setup() {

		String insertWeather1 = "INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('CVNP',1,38,62,'rain');";
		String insertWeather2 ="INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('CVNP',2,38,56,'partly cloudy');";
		String insertWeather3 ="INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('CVNP',3,51,66,'partly cloudy');";
		String insertWeather4 ="INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('CVNP',4,55,65,'rain');";
		String insertWeather5 ="INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('CVNP',5,53,69,'thunderstorms');";
		String insertPark = "INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('CVNP', 'Cuyahoga Valley National Park', 'Ohio', 32832, 696, 125, 0, 'Woodland', 2000, 2189849, 'Of all the paths you take in life, make sure a few of them are dirt.', 'John Muir', 'Though a short distance from the urban areas of Cleveland and Akron, Cuyahoga Valley National Park seems worlds away. The park is a refuge for native plants and wildlife, and provides routes of discovery for visitors. The winding Cuyahoga River gives way to deep forests, rolling hills, and open farmlands. Walk or ride the Towpath Trail to follow the historic route of the Ohio & Erie Canal', 0, 390);"; 
		jdbcTemplate.update(insertPark);
		jdbcTemplate.update(insertWeather1);
		jdbcTemplate.update(insertWeather2);
		jdbcTemplate.update(insertWeather3);
		jdbcTemplate.update(insertWeather4);
		jdbcTemplate.update(insertWeather5);

		
	}
	
	@Test
	public void test_weather_by_park_code() {
		List<Weather> theWeather = dao.getWeatherByParkCode("CVNP");
		assertNotNull(theWeather);
		assertEquals(5, theWeather.size());
	}


}

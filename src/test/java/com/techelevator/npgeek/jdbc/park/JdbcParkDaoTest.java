package com.techelevator.npgeek.jdbc.park;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.jdbc.park.DAOIntegrationTest;
import com.techelevator.npgeek.model.park.JdbcParkDao;
import com.techelevator.npgeek.model.park.Park;

public class JdbcParkDaoTest extends DAOIntegrationTest{

	private JdbcParkDao dao = new JdbcParkDao(getDataSource());
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource()) ;
	
	@Before
	public void truncateTables() {
		JdbcTemplate template = new JdbcTemplate(getDataSource());
		int numberOfRowsAffected = template.update("TRUNCATE TABLE park CASCADE;");
		System.out.printf("%d records have been truncated.", numberOfRowsAffected);
	}
	
	@Before
	public void setup() {
		String insertPark = "INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('CVNP', 'Cuyahoga Valley National Park', 'Ohio', 32832, 696, 125, 0, 'Woodland', 2000, 2189849, 'Of all the paths you take in life, make sure a few of them are dirt.', 'John Muir', 'Though a short distance from the urban areas of Cleveland and Akron, Cuyahoga Valley National Park seems worlds away. The park is a refuge for native plants and wildlife, and provides routes of discovery for visitors. The winding Cuyahoga River gives way to deep forests, rolling hills, and open farmlands. Walk or ride the Towpath Trail to follow the historic route of the Ohio & Erie Canal', 0, 390);"; 
		jdbcTemplate.update(insertPark);
		
	}
	
	@Test
	public void test_get_all_cparks() {
		List<Park> thePark = dao.getAllParkInfo();
		assertNotNull(thePark);
		assertEquals(1, thePark.size());
	}
	@Test
	public void test_get_park_information_by_park_code() {
		Park thePark = dao.getParkByParkCode("CVNP");
		assertNotNull(thePark);
		assertEquals("CVNP",thePark.getParkCode());
		assertEquals("Cuyahoga Valley National Park",thePark.getName());
		assertEquals("Ohio",thePark.getState());
		assertEquals(32832,thePark.getAcreage());
		assertEquals(696,thePark.getElevationInFt());
		assertEquals(125.0,thePark.getMilesOfTrail(),0.0);
		assertEquals(0,thePark.getNumOfCampsites());
		assertEquals("Woodland",thePark.getClimate());
		assertEquals(2000,thePark.getYearFounded());
		assertEquals(2189849,thePark.getAnnualVisitors());
		assertEquals("Of all the paths you take in life, make sure a few of them are dirt.",thePark.getQuote());
		assertEquals("John Muir",thePark.getQuoteAuthor());
		assertEquals("Though a short distance from the urban areas of Cleveland and Akron, Cuyahoga Valley National Park seems worlds away. The park is a refuge for native plants and wildlife, and provides routes of discovery for visitors. The winding Cuyahoga River gives way to deep forests, rolling hills, and open farmlands. Walk or ride the Towpath Trail to follow the historic route of the Ohio & Erie Canal",thePark.getDescription());
		assertEquals(0,thePark.getEntryFee());
		assertEquals(390,thePark.getSpeciesCount());
		
		
	}

}

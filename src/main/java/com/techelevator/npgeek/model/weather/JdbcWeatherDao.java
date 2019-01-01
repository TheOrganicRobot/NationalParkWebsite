package com.techelevator.npgeek.model.weather;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.park.Park;

@Component
public class JdbcWeatherDao implements WeatherDao{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Weather> getWeatherByParkCode(String parkCode) {
		List<Weather> weatherList = new ArrayList<Weather>();

		String sqlParkQuery = "SELECT * FROM weather WHERE parkcode = ? ORDER BY fivedayforecastvalue;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlParkQuery, parkCode);
		while(results.next()) {
			weatherList.add(mapRowToWeather(results));
		}
		return weatherList;
	}
	
	private Weather mapRowToWeather(SqlRowSet results) {
		Weather weather = new Weather();
		
		weather.setParkCode(results.getString("parkcode"));
		weather.setForecastValue(results.getInt("fivedayforecastvalue"));
		weather.setLowTemp(results.getInt("low"));
		weather.setHighTemp(results.getInt("high"));
		weather.setForecast(results.getString("forecast"));

		return weather;
	}

}

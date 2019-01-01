package com.techelevator.npgeek.model.weather;

import java.util.List;

public interface WeatherDao {

	public List<Weather> getWeatherByParkCode(String parkCode);
	
}

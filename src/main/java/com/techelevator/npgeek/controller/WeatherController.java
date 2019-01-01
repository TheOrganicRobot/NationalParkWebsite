package com.techelevator.npgeek.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.npgeek.model.park.JdbcParkDao;
import com.techelevator.npgeek.model.weather.JdbcWeatherDao;

@Controller
@SessionAttributes("convert") 
public class WeatherController {

	@Autowired
	private JdbcWeatherDao jdbcWeatherDao;
	@Autowired
	private JdbcParkDao jdbcParkDao;
	
	@RequestMapping(path="/weather", method=RequestMethod.GET)
	public String displayFiveDayForecast(HttpServletRequest request, @RequestParam String parkCode, ModelMap map) {
		
		request.setAttribute("weatherList", jdbcWeatherDao.getWeatherByParkCode(parkCode));
		request.setAttribute("parkData", jdbcParkDao.getParkByParkCode(parkCode));
		return "weather";
	}
	@RequestMapping(path="/weatherConvertToCelcius", method=RequestMethod.POST)
	public String weatherCelciusConversion(@RequestParam(name="celcius") String convert, ModelMap map, @RequestParam String parkCode) {
		
		map.addAttribute("convert", convert);
		map.addAttribute("parkCode", parkCode);
		return "redirect:/weather";
	}
	@RequestMapping(path="/weatherConvertToFahrenheit", method=RequestMethod.POST)
	public String weatherFahrenheitConversion(@RequestParam(name="fahrenheit") String convert, ModelMap map, @RequestParam String parkCode) {
		
		map.addAttribute("convert", convert);
		map.addAttribute("parkCode", parkCode);
		return "redirect:/weather";
	}
	
}

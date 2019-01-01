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
public class ParkServiceController {

	@Autowired
	private JdbcParkDao jdbcParkDao;
	@Autowired
	private JdbcWeatherDao jdbcWeatherDao;
	
	
	@RequestMapping(path="/homepage", method=RequestMethod.GET)
	public String displayHomePage(HttpServletRequest request) {
		
		request.setAttribute("parksList", jdbcParkDao.getAllParkInfo());
		return "homepage";
	}
	
	@RequestMapping(path="/homepage", method=RequestMethod.POST)
	public String getParkDetailPage(HttpServletRequest request, @RequestParam String parkCode) {
		
	request.setAttribute("parkCode", jdbcParkDao.getParkByParkCode((parkCode)));
		return "redirect:/parkDetail";
	}
	
	
	@RequestMapping(path="/parkDetail", method=RequestMethod.GET)
	public String displayParkDetailPage(HttpServletRequest request, @RequestParam String parkCode, ModelMap map) {

		request.setAttribute("parkData", jdbcParkDao.getParkByParkCode(parkCode));
		request.setAttribute("weatherList", jdbcWeatherDao.getWeatherByParkCode(parkCode));
		
		return "parkDetail";
	}
	@RequestMapping(path="/convertToCelcius", method=RequestMethod.POST)
	public String celciusConversion(@RequestParam(name="celcius") String convert, ModelMap map, @RequestParam String parkCode) {
		
		map.addAttribute("convert", convert);
		map.addAttribute("parkCode", parkCode);
		return "redirect:/parkDetail";
	}
	@RequestMapping(path="/convertToFahrenheit", method=RequestMethod.POST)
	public String fahrenheitConversion(@RequestParam(name="fahrenheit") String convert, ModelMap map, @RequestParam String parkCode) {
		
		map.addAttribute("convert", convert);
		map.addAttribute("parkCode", parkCode);
		return "redirect:/parkDetail";
	}
}



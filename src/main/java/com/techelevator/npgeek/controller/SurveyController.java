package com.techelevator.npgeek.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.model.park.JdbcParkDao;
import com.techelevator.npgeek.model.survey.JdbcSurveyDao;
import com.techelevator.npgeek.model.survey.Survey;

@Controller
public class SurveyController {

	@Autowired
	private JdbcSurveyDao jdbcSurveyDao;
	@Autowired
	private JdbcParkDao jdbcParkDao;
	
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String displaySurveyPage(HttpServletRequest request,Model modelHolder) {
		if( !modelHolder.containsAttribute("register")) {
			modelHolder.addAttribute("survey",new Survey());
		request.setAttribute("parksList", jdbcParkDao.getAllParkInfo());
		}
		
		return "survey";
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.POST)
	public String getSurveyResultsPage(HttpServletRequest request, @RequestParam String email,
																   @RequestParam String parkCode,
																   @RequestParam String state,
																   @RequestParam String activityLevel,
																   Survey survey,Model modelHolder,
																   @Valid @ModelAttribute("survey") Survey newSurvey, 
																	BindingResult result,
																	RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "survey"; 
		}
		survey.setParkCode(parkCode);
		survey.setEmail(email);
		survey.setState(state);
		survey.setActivityLevel(activityLevel);		
		jdbcSurveyDao.saveSurvey(survey);
	
		
		return "redirect:/surveyResults";
	}
	
	@RequestMapping(path="/surveyResults", method=RequestMethod.GET)
	public String displaySurveyResultsPage(HttpServletRequest request) {
		request.setAttribute("surveyList", jdbcSurveyDao.getAllSurveysByCount());
		return "surveyResults";
	}
}

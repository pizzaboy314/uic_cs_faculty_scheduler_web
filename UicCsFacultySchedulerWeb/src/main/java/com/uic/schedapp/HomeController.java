package com.uic.schedapp;

import static data.FullcalendarConstants.DEFAULT_COL_BG;
import static data.FullcalendarConstants.DEFAULT_COL_TX;
import static data.FullcalendarConstants.PRE200_COL_BG;
import static data.FullcalendarConstants.PRE200_COL_TX;
import static data.FullcalendarConstants.PRE300_COL_BG;
import static data.FullcalendarConstants.PRE300_COL_TX;
import static data.FullcalendarConstants.PRE400_COL_BG;
import static data.FullcalendarConstants.PRE400_COL_TX;
import static data.FullcalendarConstants.PRE500_COL_BG;
import static data.FullcalendarConstants.PRE500_COL_TX;
import generated.mybatis.model.CourseModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import data.CoursesHandler;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSessionFactory sessFactory;
	
	@Autowired
	private CoursesHandler cHandler;
	
	private void addColorAttributes(Model model){
		model.addAttribute("pre200BGColor", PRE200_COL_BG);
		model.addAttribute("pre200TXColor", PRE200_COL_TX);
		model.addAttribute("pre300BGColor", PRE300_COL_BG);
		model.addAttribute("pre300TXColor", PRE300_COL_TX);
		model.addAttribute("pre400BGColor", PRE400_COL_BG);
		model.addAttribute("pre400TXColor", PRE400_COL_TX);
		model.addAttribute("pre500BGColor", PRE500_COL_BG);
		model.addAttribute("pre500TXColor", PRE500_COL_TX);
		model.addAttribute("defaultBGColor", DEFAULT_COL_BG);
		model.addAttribute("defaultTXColor", DEFAULT_COL_TX);
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 */
	@RequestMapping(value = {"", "/", "index"}, method = {RequestMethod.POST, RequestMethod.GET})
	public String home(Locale locale, Model model) throws IOException {
		List<CourseModel> courses;
		logger.info("Welcome to the faculty page! The client locale is {}.",
				locale);
		courses = cHandler.getAllCourses();
		addColorAttributes(model);

		model.addAttribute("courses", courses);
		
		return "tool";
	}
	
}

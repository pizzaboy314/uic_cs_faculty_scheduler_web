package com.uic.schedapp;

import generated.mybatis.model.CourseModel;

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

import static data.CourseColor.*;

@Controller
public class SchedulingToolController {
	private static final Logger logger = LoggerFactory
			.getLogger(SchedulingToolController.class);

	@Autowired
	private SqlSessionFactory sessFactory;
	
	@Autowired
	private CoursesHandler cHandler;
	
	private void addColorAttributes(Model model){
		model.addAttribute("pre200BGColor", PRE200_BG);
		model.addAttribute("pre200TXColor", PRE200_TX);
		model.addAttribute("pre300BGColor", PRE300_BG);
		model.addAttribute("pre300TXColor", PRE300_TX);
		model.addAttribute("pre400BGColor", PRE400_BG);
		model.addAttribute("pre400TXColor", PRE400_TX);
		model.addAttribute("pre500BGColor", PRE500_BG);
		model.addAttribute("pre500TXColor", PRE500_TX);
		model.addAttribute("defaultBGColor", DEFAULT_COL_BG);
		model.addAttribute("defaultTXColor", DEFAULT_COL_TX);
	}

	@RequestMapping(value = "/tool", method = RequestMethod.GET)
	public String schedPage(Locale locale, Model model) {
		List<CourseModel> courses;
		logger.info("Welcome to the faculty page! The client locale is {}.",
				locale);
		courses = cHandler.getAllCourses();
		addColorAttributes(model);

		model.addAttribute("courses", courses);

		return "tool";
	}
}

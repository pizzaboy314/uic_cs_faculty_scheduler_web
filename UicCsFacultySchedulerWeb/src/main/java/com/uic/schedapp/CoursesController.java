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

@Controller
public class CoursesController {
	private static final Logger logger = LoggerFactory.getLogger(CoursesController.class);
	
	@Autowired
	private CoursesHandler coursesHandler;
	@Autowired
	private SqlSessionFactory sessFactory;
	
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String coursesPage(Locale locale, Model model) {
		List<CourseModel> courses;
		logger.info("Welcome to the faculty page! The client locale is {}.", locale);
		
		courses = coursesHandler.getAllCourses();
		
		model.addAttribute("courses", courses);
		return "courses";
	}
	
	
}

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
public class SchedulingToolController {
	private static final Logger logger = LoggerFactory
			.getLogger(SchedulingToolController.class);

	@Autowired
	private SqlSessionFactory sessFactory;
	
	@Autowired
	private CoursesHandler cHandler;

	@RequestMapping(value = "/tool", method = RequestMethod.GET)
	public String schedPage(Locale locale, Model model) {
		List<CourseModel> courses;
		logger.info("Welcome to the faculty page! The client locale is {}.",
				locale);

		courses = cHandler.getAllCourses();

		model.addAttribute("courses", courses);

		return "tool";
	}
}

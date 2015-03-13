package com.uic.schedapp;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CoursesController {
	private static final Logger logger = LoggerFactory.getLogger(CoursesController.class);
	
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String aboutPage(Locale locale, Model model) {
		logger.info("Welcome to the faculty page! The client locale is {}.", locale);
		return "courses";
	}
	
}

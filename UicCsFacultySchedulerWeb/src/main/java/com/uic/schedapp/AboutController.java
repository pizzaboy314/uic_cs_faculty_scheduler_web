package com.uic.schedapp;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AboutController {
	private static final Logger logger = LoggerFactory.getLogger(AboutController.class);
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String aasdasd(Locale locale, Model model) {
		logger.info("Welcome to the about page! The client locale is {}.", locale);
		return "about";
	}
}

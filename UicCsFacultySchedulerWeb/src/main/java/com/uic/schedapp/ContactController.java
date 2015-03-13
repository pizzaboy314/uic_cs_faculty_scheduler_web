package com.uic.schedapp;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {
	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contactPage(Locale locale, Model model) {
		logger.info("Welcome to the about page! The client locale is {}.", locale);
		return "contact";
	}
}

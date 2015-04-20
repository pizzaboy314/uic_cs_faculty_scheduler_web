package com.uic.schedapp;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserManagementController {
	private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);
	
	@RequestMapping(value = "/um", method = RequestMethod.GET)
	public String umPage(Locale locale, Model model) {
		return "usermanagement";
	}
}

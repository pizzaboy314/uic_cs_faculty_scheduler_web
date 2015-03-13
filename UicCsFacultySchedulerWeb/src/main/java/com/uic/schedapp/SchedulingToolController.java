package com.uic.schedapp;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SchedulingToolController {
	private static final Logger logger = LoggerFactory.getLogger(SchedulingToolController.class);
	
	@RequestMapping(value = "/tool", method = RequestMethod.GET)
	public String schedPage(Locale locale, Model model) {
		return "tool";
	}
}

package com.uic.schedapp;

import generated.mybatis.model.CourseModel;

import java.lang.reflect.Field;
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

import com.uic.schedapp.service.FullcalendarService;

import data.CoursesHandler;
import data.FullcalendarConstants;
import static data.FullcalendarConstants.*;

@Controller
public class SchedulingToolController {
	private static final Logger logger = LoggerFactory
			.getLogger(SchedulingToolController.class);

	@Autowired
	private SqlSessionFactory sessFactory;
	
	@Autowired
	private CoursesHandler cHandler;
	
	private void addColorAttributes(Model model) throws Exception {
		Field[] fds = FullcalendarConstants.class.getDeclaredFields();
		for (Field f : fds){
			model.addAttribute(f.getName(), f.get(null));
		}
//		model.addAttribute("pre200BGColor", PRE200_COL_BG);
//		model.addAttribute("pre200TXColor", PRE200_COL_TX);
//		model.addAttribute("pre300BGColor", PRE300_COL_BG);
//		model.addAttribute("pre300TXColor", PRE300_COL_TX);
//		model.addAttribute("pre400BGColor", PRE400_COL_BG);
//		model.addAttribute("pre400TXColor", PRE400_COL_TX);
//		model.addAttribute("pre500BGColor", PRE500_COL_BG);
//		model.addAttribute("pre500TXColor", PRE500_COL_TX);
//		model.addAttribute("defaultBGColor", DEFAULT_COL_BG);
//		model.addAttribute("defaultTXColor", DEFAULT_COL_TX);
	}

	@RequestMapping(value = "/tool", method = RequestMethod.GET)
	public String schedPage(Locale locale, Model model) {
		List<CourseModel> courses;
		logger.info("Welcome to the faculty page! The client locale is {}.",
				locale);
		courses = cHandler.getAllCourses();
		try {
			addColorAttributes(model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("courses", courses);

		return "tool";
	}
}

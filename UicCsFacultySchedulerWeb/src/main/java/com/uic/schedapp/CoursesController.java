package com.uic.schedapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import data.Course;
import data.Instructor;

@Controller
public class CoursesController {
	private static final Logger logger = LoggerFactory.getLogger(CoursesController.class);
	
	public static List<Course> courses;
	
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String coursesPage(Locale locale, Model model) {
		logger.info("Welcome to the faculty page! The client locale is {}.", locale);
		
		try {
			downloadAndParseCourses();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		model.addAttribute("courses", courses);
		return "courses";
	}
	
	public void downloadAndParseCourses() throws IOException {
		courses = new ArrayList<Course>();
		
		String url = "https://www.uic.edu/ucat/courses/CS.html";
		URL source = null;
		try {
			source = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		URLConnection uc = source.openConnection();
		uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		uc.connect();
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		System.out.println("Parsing course data from web page...");
		
		Course c = new Course();
		String inputLine = in.readLine();
		while (inputLine != null) {
			c = new Course();
			
			if (inputLine.contains("<p><b>")) {
				String tmp = inputLine.substring(inputLine.indexOf("<b>"), inputLine.indexOf("</b>"));
				int number = Integer.parseInt(tmp.replaceAll("<b>", ""));
				
				inputLine = in.readLine();
				String name = inputLine.substring(inputLine.indexOf("<b>"), inputLine.indexOf("</b><br>")).replaceAll("<b>", "");
				
				tmp = inputLine.substring(inputLine.indexOf("<br><b>"), inputLine.indexOf(".</b>")).replaceAll("<br><b>", "");
				int underGradHours, gradHours = 0;
				if(tmp.contains("OR")){
					underGradHours = Integer.parseInt(tmp.trim().charAt(0) + "");
					tmp = tmp.replaceAll("\\d OR ", "");
					gradHours = Integer.parseInt(tmp.trim().charAt(0) + "");
				} else {
					underGradHours = Integer.parseInt(tmp.trim().charAt(0) + "");
				}
				
				c.setNumber(number);
				c.setName(name);
				c.setUnderGradHours(underGradHours);
				c.setGradHours(gradHours);
				
				courses.add(c);
			}
			
			if (inputLine.contains("Information provided by the Office of Programs and Academic Assessment.")) {
				inputLine = null;
			} else {
				inputLine = in.readLine();
			}
		}
		
		in.close();
		System.out.println("Finished parsing course data from web page...");
		
		Collections.sort(courses);
	}
	
}

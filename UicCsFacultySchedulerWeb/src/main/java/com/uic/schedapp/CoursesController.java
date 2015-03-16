package com.uic.schedapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		
		courses = new ArrayList<Course>();
		loadCourses();
		
		model.addAttribute("courses", courses);
		return "courses";
	}
	
	public static void loadCourses() {
		checkCourseTSV();
		
		String line;
		File dataPath = null;
		InputStream fis;
		BufferedReader br;
		try {
			dataPath = new ClassPathResource("staticdata").getFile();
			fis = new FileInputStream(dataPath.getAbsolutePath() + File.separator + "courses.tsv");
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			
			while((line = br.readLine()) != null){
				Course c = new Course();
				String[] fields = line.split("\t");
				
				c.setName(fields[0]);
				c.setNumber(Integer.parseInt(fields[1]));
				c.setUnderGradHours(Integer.parseInt(fields[2]));
				c.setGradHours(Integer.parseInt(fields[3]));
				
				if(c.getNumber() != 99){
					courses.add(c);
				}
			}
			Collections.sort(courses);
			
			br.close();
			br = null;
			fis = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean checkCourseTSV(){
		File dataPath = null;
		try {
			dataPath = new ClassPathResource("staticdata").getFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		File dataFile = new File(dataPath.getAbsolutePath() + File.separator + "courses.tsv");
		
		if (!dataPath.exists()) {
			dataPath.mkdirs();
			return false;
		} else if (!dataFile.exists()) {
			FileOutputStream oFile;
			try {
				dataFile.createNewFile();
				oFile = new FileOutputStream(dataFile, false);
				oFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		} else {
			return true;
		}
		
	}
	
}

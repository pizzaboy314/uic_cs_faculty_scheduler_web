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
import org.springframework.web.servlet.ModelAndView;

import data.Instructor;

@Controller
public class FacultyController {
	private static final Logger logger = LoggerFactory.getLogger(FacultyController.class);
	
	public static List<Instructor> instructors;
	
	@RequestMapping(value = "/faculty", method = RequestMethod.GET)
	public ModelAndView aboutPage(Locale locale, Model m) {				// what is locale and model?
		logger.info("Welcome to the faculty page! The client locale is {}.", locale);
		
		instructors = new ArrayList<Instructor>();
		loadInstructors();
		
		ModelAndView model = new ModelAndView("faculty");
		model.addObject("instructors", instructors);
		return model;
	}
	
	public static void loadInstructors() {
		checkInstructorTSV();
		
		String line;
		File dataPath = null;
		InputStream fis;
		BufferedReader br;
		try {
			dataPath = new ClassPathResource("staticdata").getFile();
			fis = new FileInputStream(dataPath.getAbsolutePath() + File.separator + "instructors.tsv");
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			
			while((line = br.readLine()) != null){
				Instructor dude = new Instructor();
				String[] fields = line.split("\t");
				
				dude.setName(fields[0]);
				dude.setTitle(fields[1]);
				dude.setEmail(fields[2]);
				dude.setDegName(fields[3]);
				dude.setDegYear(Integer.parseInt(fields[4]));
				dude.setBackground(fields[5]);
				dude.setCourse1(Integer.parseInt(fields[6]));
				dude.setCourse2(Integer.parseInt(fields[7]));
				dude.setCourse3(Integer.parseInt(fields[8]));
				dude.setCourse4(Integer.parseInt(fields[9]));
				dude.setCourse5(Integer.parseInt(fields[10]));
				dude.setCourse6(Integer.parseInt(fields[11]));
				dude.setCourse7(Integer.parseInt(fields[12]));
				dude.setCourse8(Integer.parseInt(fields[13]));
				
				instructors.add(dude);
			}
			Collections.sort(instructors);
			
			br.close();
			br = null;
			fis = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean checkInstructorTSV(){
		File dataPath = null;
		try {
			dataPath = new ClassPathResource("staticdata").getFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		File dataFile = new File(dataPath.getAbsolutePath() + File.separator + "instructors.tsv");
		
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

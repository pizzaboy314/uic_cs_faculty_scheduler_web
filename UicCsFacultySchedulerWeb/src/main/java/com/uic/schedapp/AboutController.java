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
public class AboutController {
	private static final Logger logger = LoggerFactory.getLogger(AboutController.class);
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String aboutPage(Locale locale, Model model) {
		logger.info("Welcome to the about page! The client locale is {}.", locale);
		resourceFileLoadingTesting();
		return "about";
	}
	
	public void resourceFileLoadingTesting(){
		System.out.println("--------------------------------------------");
		File folder = null;
		try {
			folder = new ClassPathResource("staticdata").getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File[] listOfFiles = folder.listFiles();
		for(File f : listOfFiles){
			System.out.println(f.getName());
		}
		System.out.println("--------------------------------------------");
	}
}

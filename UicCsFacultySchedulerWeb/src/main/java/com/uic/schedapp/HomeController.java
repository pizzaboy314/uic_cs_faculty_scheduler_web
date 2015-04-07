package com.uic.schedapp;

import generated.mybatis.dao.UIC_ClassMapper;
import generated.mybatis.model.UIC_Class;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSessionFactory sessFactory;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 */
	@RequestMapping(value = {"", "/", "index"}, method = {RequestMethod.POST, RequestMethod.GET})
	public String home(Locale locale, Model model) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);

		SqlSession sqlSession = sessFactory.openSession();
		StringBuilder randomString = new StringBuilder("Data Retrieved:\n");
		
		// EXAMPLE
		UIC_ClassMapper cm = sqlSession.getMapper(UIC_ClassMapper.class);
		List<UIC_Class> list = cm.selectByExample(null);
		for(UIC_Class c : list){
			randomString.append(c.getLONGNAME());
		}
		// EXAMPLE
		
		randomString.append("\nEnd of retrieval");
		
		model.addAttribute("someString", randomString);
		
		return "index";
	}
	
}

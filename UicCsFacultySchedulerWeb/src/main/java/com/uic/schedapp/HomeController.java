package com.uic.schedapp;

import generated.mybatis.dao.SgmTestMapper;
import generated.mybatis.model.SgmTest;

import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
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
		
		//FIXME This will depend on your configuration file the string "SgmTest" will change
//		SgmTestMapper testMapper = sqlSession.getMapper(SgmTestMapper.class);
//		List<SgmTest> v = testMapper.selectByExample(null);
//		for (SgmTest e : v){
//			randomString.append(e.toString() + "\n");
//		}
		randomString.append("\nEnd of retrieval");
		
		model.addAttribute("someString", randomString);
		
		return "index";
	}
	
}

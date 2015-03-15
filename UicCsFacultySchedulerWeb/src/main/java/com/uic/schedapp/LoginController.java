package com.uic.schedapp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uic.security.PasswordChecker;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private PasswordChecker checker;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response){
		String referer = request.getHeader("Referer");
		String user = (String) request.getParameter("user");
		String pass = (String) request.getParameter("pwd");
		logger.debug("Recievced username: " + user + ", password: " + pass);
		
		Cookie test = new Cookie("LoggedIn", "test");
		test.setMaxAge(600);
		if (checker.isValid(user, pass)){
			logger.debug("Accpeted username: " + user + ", password: " + pass);
			response.addCookie(test);
		}else{
			logger.debug("Invaid: " + user + ", password: " + pass);
		}
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response){
		String referer = request.getHeader("Referer");
		Cookie[] cookies = request.getCookies();
		logger.debug(cookies.toString());
		for (Cookie t: cookies){
			t.setComment("");
			t.setMaxAge(0);
			response.addCookie(t);
		}
		return "redirect:" + referer;
	}

}

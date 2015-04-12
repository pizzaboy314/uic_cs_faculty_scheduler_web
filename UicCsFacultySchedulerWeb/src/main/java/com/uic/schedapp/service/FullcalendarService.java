package com.uic.schedapp.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import data.CalendarDTO;

@Controller
public class FullcalendarService {

	@RequestMapping(value = "/CalendarJsonServlet", method = RequestMethod.GET)
	public void handleRequest(HttpServletRequest request, HttpServletResponse response){
		List<CalendarDTO> l = new ArrayList<CalendarDTO>();
		System.out.println("hhhh");
		//This needs to change
		addDTOs(l);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(new Gson().toJson(l));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addDTOs(List<CalendarDTO> l){
		CalendarDTO c = new CalendarDTO();
		c.setId(1);
		c.setStart("2013-07-28");
		c.setEnd("2013-07-29");
		c.setTitle("Task in Progress");
		l.add(c);
//
//		c = new CalendarDTO();
//		c.setId(2);
//		c.setStart("2013-07-26");
//		c.setEnd("2013-08-28");
//		c.setTitle("Task in Progress");
//		l.add(c);
	}
		 

}

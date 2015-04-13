package com.uic.schedapp.service;

import generated.mybatis.dao.SectionModelMapper;
import generated.mybatis.model.SectionModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import data.CalendarDTO;

@Controller
public class FullcalendarService {
	@Autowired
	SqlSessionFactory sf;

	@RequestMapping(value = "/CalendarJsonServlet", method = RequestMethod.GET)
	public void handleRequest(HttpServletRequest request, HttpServletResponse response){
		List<CalendarDTO> l = new ArrayList<CalendarDTO>();
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
		CalendarDTO c;
		SqlSession s = sf.openSession();
		SectionModelMapper scMapper = s.getMapper(SectionModelMapper.class);
		List<SectionModel> res = scMapper.selectByExample(null);
		for (SectionModel r: res){
			c = new CalendarDTO();
			c.setTitle("CS " + r.getCourseNumber());
			c.setStart(r.getStartTime());
			c.setEnd(r.getStopTime());
			l.add(c);
//			c.setStart("2015-02-12T10:00:00");
//			c.setEnd("2015-02-12T11:30:00");
//			c.setTitle("Task in Progress");
		}
		s.close();
	}
		 

}

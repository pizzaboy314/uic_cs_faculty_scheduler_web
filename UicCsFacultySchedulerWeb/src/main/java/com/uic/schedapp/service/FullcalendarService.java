package com.uic.schedapp.service;

import generated.mybatis.dao.SectionModelMapper;
import generated.mybatis.model.SectionModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import data.CalendarDTO;
import data.FullcalendarConstants;

@Controller
public class FullcalendarService {
	private static final Logger logger = LoggerFactory.getLogger(FullcalendarService.class);
	private static final String FC_EVENT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	private static final String DEFAULT_EVENT_LEN_FORMAT = "HH:mm:ss";
	private static final SimpleDateFormat fullcFomat = new SimpleDateFormat(FC_EVENT_FORMAT);
	private static final SimpleDateFormat deltaFormat = new SimpleDateFormat(DEFAULT_EVENT_LEN_FORMAT);
	
	static {//static block to set time zone to gmt no matter were its being pulled
		fullcFomat.setTimeZone(TimeZone.getTimeZone("GMT"));
		deltaFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	@Autowired
	private SqlSessionFactory sf;

	@RequestMapping(value = "/CalendarJsonServlet", method = RequestMethod.GET)
	public void handleRequest(HttpServletRequest request, HttpServletResponse response){
		List<CalendarDTO> l = new ArrayList<CalendarDTO>();
		//This needs to change
		logger.debug("Adding DTO's in GET...");
		addDTOs(l);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			logger.debug("Writing out DTO's in GET reponse...");
			out.write(new Gson().toJson(l));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/CalendarJsonServlet", method = RequestMethod.POST)
	public void handlePost(HttpServletRequest request, HttpServletResponse response){
		logger.debug("In Calendar servlet post");
		//Example from https://raw.githubusercontent.com/arshaw/fullcalendar/v2.1.1/demos/external-dragging.html
		String courseTitle = request.getParameter("title"),
				start = request.getParameter("startTime");
		@SuppressWarnings("rawtypes")
		Enumeration e = request.getParameterNames();
		for (Object o = e.nextElement(); e.hasMoreElements(); o = e.nextElement()){
			System.out.println(o.toString());
		}
		logger.debug("Course Recieved: " + courseTitle);
		logger.debug("Start Time Recieved: " + start);
		SqlSession s = sf.openSession();
		SectionModelMapper smMapper = s.getMapper(SectionModelMapper.class);
		
		try {
			Date javaStartTime = fullcFomat.parse(start);
			Date deltaDate = deltaFormat.parse(FullcalendarConstants.DEFAULT_EVENT_LENGTH);
			long jTime = javaStartTime.getTime(), dTime = deltaDate.getTime();
			Date endTime = new Date(jTime + dTime);
			SectionModel sm = new SectionModel();
			logger.debug("start time: " + fullcFomat.format(javaStartTime));
			logger.debug("end time: " + fullcFomat.format(endTime));
			sm.setCourseNumber(Integer.parseInt(courseTitle.substring(3)));
			sm.setStartTime(start);
			sm.setStopTime(fullcFomat.format(endTime));
			sm.setSectionNumber((int)(Math.random() * 200));
			smMapper.insert(sm);
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException e1){
			e1.printStackTrace();
		} finally {
			s.close();
		}
		
	}
	
	private static void setColors(CalendarDTO d, int num){
		if (num < 200){
			d.setBackgroundColor(FullcalendarConstants.PRE200_COL_BG);
			d.setTextColor(FullcalendarConstants.PRE200_COL_TX);
		} else if (num < 300){
			d.setBackgroundColor(FullcalendarConstants.PRE300_COL_BG);
			d.setTextColor(FullcalendarConstants.PRE300_COL_TX);
		} else if (num < 400){
			d.setBackgroundColor(FullcalendarConstants.PRE400_COL_BG);
			d.setTextColor(FullcalendarConstants.PRE400_COL_TX);
		} else if (num < 500){
			d.setBackgroundColor(FullcalendarConstants.PRE500_COL_BG);
			d.setTextColor(FullcalendarConstants.PRE500_COL_TX);
		} else {
			d.setBackgroundColor(FullcalendarConstants.DEFAULT_COL_BG);
			d.setTextColor(FullcalendarConstants.DEFAULT_COL_TX);
		}
	}
	
	private void addDTOs(List<CalendarDTO> l){
		CalendarDTO c;
		SqlSession s = sf.openSession();
		SectionModelMapper scMapper = s.getMapper(SectionModelMapper.class);
		List<SectionModel> res = scMapper.selectByExample(null);
		for (SectionModel r: res){
			c = new CalendarDTO();
			int cNum = r.getCourseNumber();
			c.setTitle("CS " + cNum);
			c.setStart(r.getStartTime());
			c.setEnd(r.getStopTime());
			setColors(c, cNum);
			l.add(c);
		}
		s.close();
	}
		 

}

package com.uic.schedapp.service;

import generated.mybatis.dao.SectionModelMapper;
import generated.mybatis.model.CourseModel;
import generated.mybatis.model.SectionModel;
import generated.mybatis.model.SectionModelExample;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import data.CalendarDTO;
import data.CoursesHandler;
import data.FullcalendarConstants;

@Controller
public class FullcalendarService {
	private static final Logger logger = LoggerFactory.getLogger(FullcalendarService.class);
	private static final String FC_EVENT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	private static final String DEFAULT_EVENT_LEN_FORMAT = "HH:mm:ss";
	private static final SimpleDateFormat fullcFomat = new SimpleDateFormat(FC_EVENT_FORMAT);
	private static final SimpleDateFormat deltaFormat = new SimpleDateFormat(DEFAULT_EVENT_LEN_FORMAT);
	

	@Autowired
	private CoursesHandler coursesHandler;
	
	static {//static block to set time zone to gmt no matter were its being pulled
		fullcFomat.setTimeZone(TimeZone.getTimeZone("GMT"));
		deltaFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	@Autowired
	private SqlSessionFactory sf;

	@RequestMapping(value = "/CalendarRetrieveStored", method = RequestMethod.GET)
	public void handleDataRetrieval(HttpServletRequest request, HttpServletResponse response){
		logger.debug("In Calendar retrieval servlet get");
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
	
	@RequestMapping(value = "/CalendarDropServlet", method = {RequestMethod.POST, RequestMethod.GET})
	public void handleDrop(HttpServletRequest request, HttpServletResponse response){
		logger.debug("In Calendar drop servlet post");
		//Example from https://raw.githubusercontent.com/arshaw/fullcalendar/v2.1.1/demos/external-dragging.html
		String courseTitle = request.getParameter("title"),
				start = request.getParameter("startTime");
		logger.debug("Course Recieved: " + courseTitle);
		logger.debug("Start Time Recieved: " + start);
		SqlSession s = sf.openSession();
		SectionModelMapper smMapper = s.getMapper(SectionModelMapper.class);
		
		try {
			SectionModel sm = getNewSectionModel(courseTitle, start);
			smMapper.insert(sm);
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException e1){
			e1.printStackTrace();
		} finally {

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
			s.close();
		}
		
	}
	
	@RequestMapping(value = "/CalendarUpdateServlet", method = RequestMethod.POST)
	public void handleUpdate(HttpServletRequest request, HttpServletResponse response){
		logger.debug("In Calendar update servlet post");
		SqlSession s = sf.openSession();
		SectionModelMapper smMapper = s.getMapper(SectionModelMapper.class);
		String courseTitle = request.getParameter("title"),
				oldStart = request.getParameter("oldStartTime"),
				oldEndTime = request.getParameter("oldEndTime"),
				start = request.getParameter("newStartTime"),
				endTime = request.getParameter("newEndTime");
		try {
			SectionModelExample sme = getRemovingSME(courseTitle, null, oldStart, oldEndTime);
			List<SectionModel> l = smMapper.selectByExample(sme);
			SectionModel sm = l.get(0);
			logger.debug(oldStart + "<>" + oldEndTime);
			logger.debug(start + "<>" + endTime);
			sm.setStartTime(start);
			sm.setStopTime(endTime);
			smMapper.updateByExample(sm, sme);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
//		logger.debug(courseTitle + "|" + oldStart + "|" + oldEndTime + "|" + start + "|" + endTime);
		
	}

	@RequestMapping(value = "/CalendarRemoveServlet", method = RequestMethod.POST)
	public void handleRemove(HttpServletRequest request, HttpServletResponse response){
		logger.debug("In Calendar remove servlet post");
		//Example from https://raw.githubusercontent.com/arshaw/fullcalendar/v2.1.1/demos/external-dragging.html
		String courseTitle = request.getParameter("title"),
				start = request.getParameter("startTime"),
				endTime = request.getParameter("endTime");
		logger.debug("Course Recieved: " + courseTitle);
		logger.debug("Start Time Recieved: " + start);
		SqlSession s = sf.openSession();
		SectionModelMapper smMapper = s.getMapper(SectionModelMapper.class);
		
		try {
			//TODO Section number should be used, once in the UI
			SectionModelExample sme = getRemovingSME(courseTitle, null, start, endTime);
			smMapper.deleteByExample(sme);
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException e1){
			e1.printStackTrace();
		} finally {
			s.close();
		}
	}
	
	/**
	 * Adds the color attributes to the response object
	 * 
	 * @param d Response object
	 * @param num Course number
	 */
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
		int i = 0;
		for (SectionModel r: res){
			c = new CalendarDTO();
			int cNum = r.getCourseNumber();
			c.setId(i++);
			c.setTitle("CS " + cNum);
			c.setStart(r.getStartTime());
			c.setEnd(r.getStopTime());
			setColors(c, cNum);
			l.add(c);
		}
		s.close();
	}
	
	private SectionModelExample getRemovingSME(String courseTitle, Integer sectionNum, String startTime, String endTime) throws ParseException{
		SectionModelExample sme = new SectionModelExample();
		sme.createCriteria().andCourseNumberEqualTo(Integer.parseInt(courseTitle.substring(3)))
			.andStopTimeEqualTo(endTime)
			.andStartTimeEqualTo(startTime)
//			.andSectionNumberEqualTo(sectionNum)//TODO uncomment when using section number
			;
		return sme;
	}
	
	private SectionModel getNewSectionModel(String courseTitle, String startTime) throws ParseException{
		SectionModel sm = new SectionModel();
		
		Date javaStartTime = fullcFomat.parse(startTime);
		Date deltaDate = deltaFormat.parse(FullcalendarConstants.DEFAULT_EVENT_LENGTH);
		long jTime = javaStartTime.getTime(), dTime = deltaDate.getTime();
		Date endTime = new Date(jTime + dTime);
		
		sm.setCourseNumber(Integer.parseInt(courseTitle.substring(3)));
		sm.setStartTime(startTime);
		sm.setStopTime(fullcFomat.format(endTime));
		
		//TODO get this value from the UI
		sm.setSectionNumber(new java.util.Random().nextInt(1000));
		
		return sm;
	}
		 

}

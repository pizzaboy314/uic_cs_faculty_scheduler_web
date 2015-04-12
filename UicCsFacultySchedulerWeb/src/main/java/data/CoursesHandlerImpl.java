package data;

import generated.mybatis.dao.CourseModelMapper;
import generated.mybatis.model.CourseModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CoursesHandlerImpl implements CoursesHandler{
	private static final long REFRESH_COURSES_TIME = 1000 * 60 * 10;//10 minutes
	private static final Logger logger = LoggerFactory.getLogger(CoursesHandlerImpl.class);
	private static List<CourseModel> courses = null;
	private static long lastUpdate = 0;
	
	private SqlSessionFactory sqlSessionFactory;
	
	public List<CourseModel> getAllCourses(){
		if (courses == null || lastUpdate + REFRESH_COURSES_TIME < System.currentTimeMillis()){
			lastUpdate = System.currentTimeMillis();
			try {
				loadFromDB();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return courses;
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	private void loadFromDB() throws IOException {
		downloadAndParseToDB();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		CourseModelMapper cm = sqlSession.getMapper(CourseModelMapper.class);
		courses = cm.selectByExample(null);
	}

	private void downloadAndParseToDB() throws IOException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		CourseModelMapper cm = sqlSession.getMapper(CourseModelMapper.class);

		String url = "https://www.uic.edu/ucat/courses/CS.html";
		URL source = null;
		try {
			source = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		URLConnection uc = source.openConnection();
		uc.addRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		uc.connect();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				uc.getInputStream()));
		System.out.println("Parsing course data from web page...");

		CourseModel c = new CourseModel();
		String inputLine = in.readLine();
		while (inputLine != null) {
			c = new CourseModel();

			if (inputLine.contains("<p><b>")) {
				String tmp = inputLine.substring(inputLine.indexOf("<b>"),
						inputLine.indexOf("</b>"));
				int number = Integer.parseInt(tmp.replaceAll("<b>", ""));

				inputLine = in.readLine();
				String name = inputLine.substring(inputLine.indexOf("<b>"),
						inputLine.indexOf("</b><br>")).replaceAll("<b>", "");

				tmp = inputLine.substring(inputLine.indexOf("<br><b>"),
						inputLine.indexOf(".</b>")).replaceAll("<br><b>", "");
				int underGradHours, gradHours = 0;
				if (tmp.contains("OR")) {
					underGradHours = Integer
							.parseInt(tmp.trim().charAt(0) + "");
					tmp = tmp.replaceAll("\\d OR ", "");
					gradHours = Integer.parseInt(tmp.trim().charAt(0) + "");
				} else {
					underGradHours = Integer
							.parseInt(tmp.trim().charAt(0) + "");
				}

				c.setNUMBER((Integer) number);
				c.setNAME(name);
				c.setUNDERGRADHOURS((Integer) underGradHours);
				c.setGRADHOURS((Integer) gradHours);

				if (cm.selectByPrimaryKey(c.getNUMBER()) == null) {
					cm.insert(c);
				}
			}

			if (inputLine
					.contains("Information provided by the Office of Programs and Academic Assessment.")) {
				inputLine = null;
			} else {
				inputLine = in.readLine();
			}
		}

		in.close();
		System.out.println("Finished parsing course data from web page...");
	}
}

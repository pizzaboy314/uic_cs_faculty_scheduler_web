package data;

import generated.mybatis.model.CourseModel;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

@Component("coursesHandler")
public interface CoursesHandler {
	public List<CourseModel> getAllCourses();
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory);
}

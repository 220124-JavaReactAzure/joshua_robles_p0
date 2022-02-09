package com.revature.course_app.services;

import com.revature.course_app.daos.CourseDAO;
import com.revature.course_app.models.Course;

public class CourseService {
	
	private final CourseDAO courseDao;
	
	public CourseService(CourseDAO courseDao) {
		super();
		this.courseDao = courseDao;
	}
	
	public Course registerNewCourse(Course newCourse) {
		return null;
	}

}

package com.revature.course_app.services;

import com.revature.course_app.daos.CourseDAO;
import com.revature.course_app.exceptions.InvalidRequestException;
import com.revature.course_app.exceptions.ResourcePersistenceException;
import com.revature.course_app.models.Course;
import com.revature.course_app.util.collections.List;

public class CourseService {
	
	private final CourseDAO courseDao;
	
	public CourseService(CourseDAO courseDao) {
		super();
		this.courseDao = courseDao;
	}
	
	public Course registerNewCourse(Course newCourse) {
		if(!isCourseValid(newCourse)) {
			throw new InvalidRequestException("Invalid course data provided");
		}
		
		Course persistedCourse = courseDao.create(newCourse);
		
		if(persistedCourse == null) {
			throw new ResourcePersistenceException("The course could not be persisted");
		}
		
		return persistedCourse;
	}
	
	public List<Course> getCoursesFromTeacherId(int teacherId) {
		return courseDao.findByTeacherId(teacherId);
	}
	
	public boolean isCourseValid(Course newCourse) {
		if(newCourse == null) return false;
		if(newCourse.getCourseId() > 0) return false;
		if(newCourse.getTeacherId() > 0) return false;
		return newCourse.getCourseName() != null || !newCourse.getCourseName().trim().equals("");
	}
	
	public List<Course> getAllCourses() {
		return courseDao.findAll();
	}

}

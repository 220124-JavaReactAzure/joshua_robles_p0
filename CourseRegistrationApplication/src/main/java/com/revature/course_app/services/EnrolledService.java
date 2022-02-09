package com.revature.course_app.services;

import com.revature.course_app.daos.EnrolledDAO;
import com.revature.course_app.exceptions.InvalidRequestException;
import com.revature.course_app.exceptions.ResourcePersistenceException;
import com.revature.course_app.models.Enrolled;
import com.revature.course_app.util.collections.List;

public class EnrolledService {

	private final EnrolledDAO enrolledDao;

	public EnrolledService(EnrolledDAO enrolledDao) {
		super();
		this.enrolledDao = enrolledDao;
	}

	public Enrolled registerNewCourse(Enrolled newEnrolled) {
		if (!isEnrolledValid(newEnrolled)) {
			throw new InvalidRequestException("Invalid course data provided");
		}

		Enrolled persistedCourse = enrolledDao.create(newEnrolled);

		if (persistedCourse == null) {
			throw new ResourcePersistenceException("The course could not be persisted");
		}

		return persistedCourse;
	}

	public boolean isEnrolledValid(Enrolled newEnrolled) {
		if (newEnrolled == null)
			return false;
		if (newEnrolled.getCourseId() > 0)
			return false;
		return (newEnrolled.getStudentId() < 0);
	}
	
	public List<Enrolled> getAllEnrolled() {
		return enrolledDao.findAll();
	}

}

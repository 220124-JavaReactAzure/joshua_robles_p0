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

	public Enrolled registerNewEnrolled(Enrolled newEnrolled) {
		if (!isEnrolledValid(newEnrolled)) {
			throw new InvalidRequestException("Invalid enrolled data provided");
		}

		Enrolled persistedEnrolled = enrolledDao.create(newEnrolled);

		if (persistedEnrolled == null) {
			throw new ResourcePersistenceException("The enrolled could not be persisted");
		}

		return persistedEnrolled;
	}

	public boolean isEnrolledValid(Enrolled newEnrolled) {
		if (newEnrolled == null)
			return false;
		if (newEnrolled.getCourseId() < 0)
			return false;
		if (newEnrolled.getStudentId() < 0)
			return false;
		
		return true;
	}
	
	public void removeEnrolled(Enrolled enrolled) {
		if(!isEnrolledValid(enrolled)) {
			throw new InvalidRequestException("Invalid enrolled data provided");
		}
		enrolledDao.delete(enrolled);
	}
	
	public List<Enrolled> getAllEnrolled() {
		return enrolledDao.findAll();
	}

}

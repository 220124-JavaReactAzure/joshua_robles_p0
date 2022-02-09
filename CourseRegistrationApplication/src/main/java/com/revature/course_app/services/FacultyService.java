package com.revature.course_app.services;

import com.revature.course_app.daos.FacultyDAO;
import com.revature.course_app.exceptions.AuthenticationException;
import com.revature.course_app.exceptions.InvalidRequestException;
import com.revature.course_app.exceptions.ResourcePersistenceException;
import com.revature.course_app.models.Faculty;
import com.revature.course_app.util.collections.List;

public class FacultyService {
	private final FacultyDAO facultyDao;
	private Faculty sessionFaculty;

	public FacultyService(FacultyDAO facultyDao) {
		this.facultyDao = facultyDao;
		sessionFaculty = null;
	}

	public Faculty getSessionFaculty() {
		return sessionFaculty;
	}

	public Faculty registerNewFaculty(Faculty newFaculty) {
		if (!isFacultyValid(newFaculty)) {
			throw new InvalidRequestException("Invalid user data provided");
		}

		boolean usernameAvailable = facultyDao.findByUsername(newFaculty.getUsername()) == null;

		if (!usernameAvailable) {
			throw new ResourcePersistenceException("The provided username was already taken.");
		}

		Faculty persistedFaculty = facultyDao.create(newFaculty);

		if (persistedFaculty == null) {
			throw new ResourcePersistenceException("The faculty could not be persisted");
		}

		return persistedFaculty;
	}

	public void authenticateFaculty(String username, String password) {

		if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
			throw new InvalidRequestException("Username or password is incorrect.. Try another please.");
		}

		Faculty authenticatedFaculty = facultyDao.findByUsernameAndPassword(username, password);

		if (authenticatedFaculty == null) {
			throw new AuthenticationException("Userdata was not found so unable to authenticate..");
		}

		sessionFaculty = authenticatedFaculty;
	}

	public boolean isFacultyValid(Faculty newFaculty) {
		if (newFaculty == null)
			return false;
		if (newFaculty.getUsername() == null || newFaculty.getUsername().trim().equals(""))
			return false;
		return newFaculty.getPassword() != null || !newFaculty.getPassword().trim().equals("");

	}

	public List<Faculty> getAllFacultys() {
		return facultyDao.findAll();
	}

	public void logout() {
		sessionFaculty = null;
	}

	public boolean isSessionActive() {
		return sessionFaculty != null;
	}
}

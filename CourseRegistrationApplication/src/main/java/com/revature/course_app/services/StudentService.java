package com.revature.course_app.services;

import com.revature.course_app.daos.StudentDAO;
import com.revature.course_app.exceptions.AuthenticationException;
import com.revature.course_app.exceptions.InvalidRequestException;
import com.revature.course_app.exceptions.ResourcePersistenceException;
import com.revature.course_app.models.Student;
import com.revature.course_app.util.collections.List;

public class StudentService {
	
	private final StudentDAO studentDao;
	private Student sessionStudent;
	
	public StudentService(StudentDAO studentDao) {
		this.studentDao = studentDao;
		sessionStudent = null;
	}
	
	public Student getSessionStudent() {
		return sessionStudent;
	}
	
	public Student registerNewStudent(Student newStudent) {
		if(!isStudentValid(newStudent)) {
			throw new InvalidRequestException("Invalid user data provided");
		}
		
		boolean usernameAvailable = studentDao.findByUsername(newStudent.getUsername()) == null;
		
		if(!usernameAvailable) {
			throw new ResourcePersistenceException("The provided username was already taken.");
		}
		
		Student persistedStudent = studentDao.create(newStudent);
		
		if(persistedStudent == null) {
			throw new ResourcePersistenceException("The student could not be persisted");
		}
		
		return persistedStudent;
	}
	
	public void authenticateStudent(String username, String password) {
		
		if(username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
			throw new InvalidRequestException("Username or password is incorrect.. Try another please.");
		}
		
		Student authenticatedStudent = studentDao.findByUsernameAndPassword(username, password);
		
		if(authenticatedStudent == null) {
			throw new AuthenticationException("Userdata was not found so unable to authenticate..");
		}
		
		sessionStudent = authenticatedStudent;
	}
	
	public boolean isStudentValid(Student newStudent) {
		if(newStudent == null) return false;
		if(newStudent.getUsername() == null || newStudent.getUsername().trim().equals("")) return false;
		return newStudent.getPassword() != null || !newStudent.getPassword().trim().equals("");
		
	}
	
	public List<Student> getAllStudents() {
		return studentDao.findAll();
	}
	
	public void logout() {
		sessionStudent = null;
	}
	
	public boolean isSessionActive() {
		return sessionStudent != null;
	}

}

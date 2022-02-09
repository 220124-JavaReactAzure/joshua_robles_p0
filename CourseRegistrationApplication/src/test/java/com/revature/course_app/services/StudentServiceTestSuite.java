package com.revature.course_app.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.course_app.daos.StudentDAO;
import com.revature.course_app.exceptions.InvalidRequestException;
import com.revature.course_app.exceptions.ResourcePersistenceException;
import com.revature.course_app.models.Student;

public class StudentServiceTestSuite {

	StudentService sut;
	StudentDAO mockStudentDAO;

	@Before
	public void testPrep() {
		mockStudentDAO = mock(StudentDAO.class);
		sut = new StudentService(mockStudentDAO);
	}

	@Test
	public void test_isStudentValid_returnsTrue_givenValidUser() {
		Student validStudent = new Student("valid", "valid");
		Student validStudent2 = new Student(5, "valid", "valid");

		boolean actualResult = sut.isStudentValid(validStudent);
		boolean actualResult2 = sut.isStudentValid(validStudent2);

		Assert.assertTrue(actualResult);
		Assert.assertTrue(actualResult2);
	}

	@Test
	public void test_isStudentValid_returnsFalse_givenUserWithUsername() {
		Student invalidStudent1 = new Student("", "valid");
		Student invalidStudent2 = new Student(null, "valid");
		Student invalidStudent3 = new Student("    ", "valid");

		boolean actualResult1 = sut.isStudentValid(invalidStudent1);
		boolean actualResult2 = sut.isStudentValid(invalidStudent2);
		boolean actualResult3 = sut.isStudentValid(invalidStudent3);

		Assert.assertFalse(actualResult1);
		Assert.assertFalse(actualResult2);
		Assert.assertFalse(actualResult3);
	}

	@Test
	public void test_isStudentValid_returnsFalse_givenUserWithPassword() {
		Student invalidStudent1 = new Student("valid", "");
		Student invalidStudent2 = new Student("valid", null);
		Student invalidStudent3 = new Student("valid", "    ");

		boolean actualResult1 = sut.isStudentValid(invalidStudent1);
		boolean actualResult2 = sut.isStudentValid(invalidStudent2);
		boolean actualResult3 = sut.isStudentValid(invalidStudent3);

		Assert.assertFalse(actualResult1);
		Assert.assertFalse(actualResult2);
		Assert.assertFalse(actualResult3);
	}

	@Test
	public void test_registerStudent_returnsTrue_givenValidStudent() {
		Student validStudent = new Student("valid", "valid");
		when(mockStudentDAO.findById(validStudent.getStudentId())).thenReturn(null);
		when(mockStudentDAO.findByUsername(validStudent.getUsername())).thenReturn(null);
		when(mockStudentDAO.findByUsernameAndPassword(validStudent.getUsername(), validStudent.getPassword()))
				.thenReturn(null);
		when(mockStudentDAO.create(validStudent)).thenReturn(validStudent);

		Student actualResult = sut.registerNewStudent(validStudent);

		Assert.assertNotNull(actualResult);
		verify(mockStudentDAO, times(1)).create(validStudent);
	}

	@Test(expected = InvalidRequestException.class)
	public void test_registerStudent_throwsInvalidRequestException_givenInvalidUser() {
		sut.registerNewStudent(null);
	}

	@Test(expected = ResourcePersistenceException.class)
	public void test_registerStudent_throwsResourcePersistenceException_givenStudentWithTakenUsername() {

		// Arrange
		Student usernameTakenStudent = new Student("valid", "valid");
		when(mockStudentDAO.findByUsername(usernameTakenStudent.getUsername())).thenReturn(usernameTakenStudent);
		when(mockStudentDAO.create(usernameTakenStudent)).thenReturn(usernameTakenStudent);

		// Act
		try {
			sut.registerNewStudent(usernameTakenStudent);
		} finally {
			// Assert
			verify(mockStudentDAO, times(0)).create(usernameTakenStudent);
		}
	}
}

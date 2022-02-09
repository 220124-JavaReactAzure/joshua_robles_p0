package com.revature.course_app.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.course_app.daos.FacultyDAO;
import com.revature.course_app.exceptions.InvalidRequestException;
import com.revature.course_app.exceptions.ResourcePersistenceException;
import com.revature.course_app.models.Faculty;

public class FacultyServiceTestSuite {
	FacultyService sut;
	FacultyDAO mockFacultyDAO;

	@Before
	public void testPrep() {
		mockFacultyDAO = mock(FacultyDAO.class);
		sut = new FacultyService(mockFacultyDAO);
	}

	@Test
	public void test_isFacultyValid_returnsTrue_givenValidUser() {
		Faculty validFaculty = new Faculty("valid", "valid");
		Faculty validFaculty2 = new Faculty(5, "valid", "valid");

		boolean actualResult = sut.isFacultyValid(validFaculty);
		boolean actualResult2 = sut.isFacultyValid(validFaculty2);

		Assert.assertTrue(actualResult);
		Assert.assertTrue(actualResult2);
	}

	@Test
	public void test_isFacultyValid_returnsFalse_givenUserWithUsername() {
		Faculty invalidFaculty1 = new Faculty("", "valid");
		Faculty invalidFaculty2 = new Faculty(null, "valid");
		Faculty invalidFaculty3 = new Faculty("    ", "valid");

		boolean actualResult1 = sut.isFacultyValid(invalidFaculty1);
		boolean actualResult2 = sut.isFacultyValid(invalidFaculty2);
		boolean actualResult3 = sut.isFacultyValid(invalidFaculty3);

		Assert.assertFalse(actualResult1);
		Assert.assertFalse(actualResult2);
		Assert.assertFalse(actualResult3);
	}

	@Test
	public void test_isFacultyValid_returnsFalse_givenUserWithPassword() {
		Faculty invalidFaculty1 = new Faculty("valid", "");
		Faculty invalidFaculty2 = new Faculty("valid", null);
		Faculty invalidFaculty3 = new Faculty("valid", "    ");

		boolean actualResult1 = sut.isFacultyValid(invalidFaculty1);
		boolean actualResult2 = sut.isFacultyValid(invalidFaculty2);
		boolean actualResult3 = sut.isFacultyValid(invalidFaculty3);

		Assert.assertFalse(actualResult1);
		Assert.assertFalse(actualResult2);
		Assert.assertFalse(actualResult3);
	}

	@Test
	public void test_registerFaculty_returnsTrue_givenValidFaculty() {
		Faculty validFaculty = new Faculty("valid", "valid");
		when(mockFacultyDAO.findById(validFaculty.getFacultyId())).thenReturn(null);
		when(mockFacultyDAO.findByUsername(validFaculty.getUsername())).thenReturn(null);
		when(mockFacultyDAO.findByUsernameAndPassword(validFaculty.getUsername(), validFaculty.getPassword()))
				.thenReturn(null);
		when(mockFacultyDAO.create(validFaculty)).thenReturn(validFaculty);

		Faculty actualResult = sut.registerNewFaculty(validFaculty);

		Assert.assertNotNull(actualResult);
		verify(mockFacultyDAO, times(1)).create(validFaculty);
	}

	@Test(expected = InvalidRequestException.class)
	public void test_registerFaculty_throwsInvalidRequestException_givenInvalidUser() {
		sut.registerNewFaculty(null);
	}

	@Test(expected = ResourcePersistenceException.class)
	public void test_registerFaculty_throwsResourcePersistenceException_givenFacultyWithTakenUsername() {

		// Arrange
		Faculty usernameTakenFaculty = new Faculty("valid", "valid");
		when(mockFacultyDAO.findByUsername(usernameTakenFaculty.getUsername())).thenReturn(usernameTakenFaculty);
		when(mockFacultyDAO.create(usernameTakenFaculty)).thenReturn(usernameTakenFaculty);

		// Act
		try {
			sut.registerNewFaculty(usernameTakenFaculty);
		} finally {
			// Assert
			verify(mockFacultyDAO, times(0)).create(usernameTakenFaculty);
		}
	}
}

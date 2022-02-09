package com.revature.course_app.menus.faculty;

import java.io.BufferedReader;

import com.revature.course_app.exceptions.InvalidRequestException;
import com.revature.course_app.menus.Menu;
import com.revature.course_app.models.Faculty;
import com.revature.course_app.services.FacultyService;
import com.revature.course_app.util.MenuRouter;

public class FRegistrationMenu extends Menu{
	private final FacultyService facultyService;

	public FRegistrationMenu(BufferedReader consoleReader, MenuRouter router, FacultyService facultyService) {
		super("FRegistration", "/f_registration", consoleReader, router);
		this.facultyService = facultyService;
	}

	@Override
	public void render() throws Exception {
		System.out.println("\nRegistration menu. Please insert account information..\n");

		System.out.println("Username: ");
		String username = consoleReader.readLine();

		System.out.println("Password: ");
		String password = consoleReader.readLine();

		Faculty newFaculty = new Faculty(username, password);

		try {
			facultyService.registerNewFaculty(newFaculty);
			router.transfer("/f_dashboard");
		} catch (InvalidRequestException e) {
			System.out.println("Unable to make new faculty account..");
		}

		router.transfer("/f_home");
	}
}

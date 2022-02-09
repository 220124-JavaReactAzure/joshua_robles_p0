package com.revature.course_app.menus.faculty;

import java.io.BufferedReader;

import com.revature.course_app.exceptions.AuthenticationException;
import com.revature.course_app.menus.Menu;
import com.revature.course_app.services.FacultyService;
import com.revature.course_app.util.MenuRouter;

public class FLoginMenu extends Menu{
	private final FacultyService facultyService;

	public FLoginMenu(BufferedReader consoleReader, MenuRouter router, FacultyService facultyService) {
		super("FLogin", "/f_login", consoleReader, router);
		this.facultyService = facultyService;
	}

	@Override
	public void render() throws Exception {
		System.out.println("\nEnter faculty account information..\n");

		System.out.println("Username: ");
		String username = consoleReader.readLine();

		System.out.println("Password: ");
		String password = consoleReader.readLine();

		try {
			facultyService.authenticateFaculty(username, password);
			router.transfer("/f_dashboard");
		} catch (AuthenticationException e) {
			System.out.println("Wrong password or username..");
		}

		router.transfer("/f_login");
	}
}

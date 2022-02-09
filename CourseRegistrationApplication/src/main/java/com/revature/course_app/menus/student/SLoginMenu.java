package com.revature.course_app.menus.student;

import java.io.BufferedReader;

import com.revature.course_app.exceptions.AuthenticationException;
import com.revature.course_app.menus.Menu;
import com.revature.course_app.services.StudentService;
import com.revature.course_app.util.MenuRouter;


public class SLoginMenu extends Menu {
	
	private final StudentService studentService;

	public SLoginMenu(BufferedReader consoleReader, MenuRouter router, StudentService studentService) {
		super("SLogin", "/s_login", consoleReader, router);
		this.studentService = studentService;
	}

	@Override
	public void render() throws Exception {
		System.out.println("\nEnter student account information..\n");
		
		System.out.println("Username: ");
		String username = consoleReader.readLine();
		
		System.out.println("Password: ");
		String password = consoleReader.readLine();
		
		try {
			studentService.authenticateStudent(username, password);
			router.transfer("/s_dashboard");
		} catch(AuthenticationException e) {
			System.out.println("Wrong password or username..");
		}
		
		router.transfer("/s_login");
	}
}

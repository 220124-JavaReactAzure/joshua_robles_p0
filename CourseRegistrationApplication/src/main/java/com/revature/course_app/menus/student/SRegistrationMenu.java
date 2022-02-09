package com.revature.course_app.menus.student;

import java.io.BufferedReader;

import com.revature.course_app.exceptions.AuthenticationException;
import com.revature.course_app.menus.Menu;
import com.revature.course_app.models.Student;
import com.revature.course_app.services.StudentService;
import com.revature.course_app.util.MenuRouter;

public class SRegistrationMenu extends Menu{
	private final StudentService studentService;

	public SRegistrationMenu(BufferedReader consoleReader, MenuRouter router, StudentService studentService) {
		super("SRegistration", "/s_registration", consoleReader, router);
		this.studentService = studentService;
	}

	@Override
	public void render() throws Exception {
		System.out.println("\nRegistration menu. Please insert account information..\n");
		
		System.out.println("Username: ");
		String username = consoleReader.readLine();
		
		System.out.println("Password: ");
		String password = consoleReader.readLine();
		
		Student newStudent = new Student(username, password);
		
		try {
			studentService.registerNewStudent(newStudent);
			router.transfer("/s_dashboard");
		} catch(AuthenticationException e) {
			System.out.println("Wrong password or username..");
		}
		
		router.transfer("/s_login");
	}
}

package com.revature.course_app.menus.student;

import java.io.BufferedReader;

import com.revature.course_app.menus.Menu;
import com.revature.course_app.models.Student;
import com.revature.course_app.services.StudentService;
import com.revature.course_app.util.MenuRouter;

public class SDashboardMenu extends Menu{
	
	private final StudentService studentService;

	public SDashboardMenu(BufferedReader consoleReader, MenuRouter router, StudentService studentService) {
		super("SDashboard", "/s_dashboard", consoleReader, router);
		this.studentService = studentService;
	}

	@Override
	public void render() throws Exception {
		
		Student sessionStudent = studentService.getSessionStudent();
		
		if (sessionStudent == null) {
			System.out.println("You are not logged in! Going back to login menu..");
			router.transfer("/s_login");
		}
		
		while(studentService.isSessionActive()) {
			System.out.println("Welcome " + sessionStudent.getUsername() + "\n");
			String menu = "1) View/Add available courses\n" + 
					"2) View/Remove current courses\n" + 
					"3) Logout\n" + 
					 "> ";

			System.out.print(menu);

			String userSelection = consoleReader.readLine();

			switch (userSelection) {
			case "1":
				System.out.println("View available courses selected:");
				router.transfer("/s_view_courses");
				break;
			case "2":
				System.out.println("View current courses selected:");
				router.transfer("/s_enrolled_courses");
				break;
			case "3":
				studentService.logout();
				break;
			default:
				System.out.println("The user made an invalid selection");
			}
		}
		
	}

}

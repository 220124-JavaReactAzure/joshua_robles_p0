package com.revature.course_app.menus.faculty;

import java.io.BufferedReader;

import com.revature.course_app.menus.Menu;
import com.revature.course_app.models.Faculty;
import com.revature.course_app.services.FacultyService;
import com.revature.course_app.util.MenuRouter;

public class FDashboardMenu extends Menu{
	
	private final FacultyService facultyService;

	public FDashboardMenu(BufferedReader consoleReader, MenuRouter router, FacultyService facultyService) {
		super("FDashboard", "/f_dashboard", consoleReader, router);
		this.facultyService = facultyService;
	}

	@Override
	public void render() throws Exception {

		Faculty sessionFaculty = facultyService.getSessionFaculty();

		if (sessionFaculty == null) {
			System.out.println("You are not logged in! Going back to login menu..");
			router.transfer("/f_login");
		}

		while (facultyService.isSessionActive()) {
			System.out.println("Welcome " + sessionFaculty.getUsername() + "\n");
			String menu = "1) Add new course\n" +
							"2) Remove current courses\n" +
							"3) Logout\n" + "> ";

			System.out.print(menu);

			String userSelection = consoleReader.readLine();

			switch (userSelection) {
			case "1":
				System.out.println("Add courses selected:");
				router.transfer("/f_add_courses");
				break;
			case "2":
				System.out.println("Remove courses selected:");
				router.transfer("/f_remove_courses");
				break;
			case "3":
				facultyService.logout();
				break;
			default:
				System.out.println("The user made an invalid selection");
			}
		}

	}
}

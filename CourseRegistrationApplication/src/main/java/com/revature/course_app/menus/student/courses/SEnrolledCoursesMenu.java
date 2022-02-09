package com.revature.course_app.menus.student.courses;

import java.io.BufferedReader;

import com.revature.course_app.exceptions.InvalidRequestException;
import com.revature.course_app.menus.Menu;
import com.revature.course_app.models.Course;
import com.revature.course_app.models.Enrolled;
import com.revature.course_app.models.Student;
import com.revature.course_app.services.EnrolledService;
import com.revature.course_app.services.StudentService;
import com.revature.course_app.util.MenuRouter;
import com.revature.course_app.util.collections.ArrayList;

public class SEnrolledCoursesMenu extends Menu {
	private final StudentService studentService;
	private final EnrolledService enrolledService;

	public SEnrolledCoursesMenu(BufferedReader consoleReader, MenuRouter router, StudentService studentService,
			EnrolledService enrolledService) {
		super("SEnrolledCourses", "/s_enrolled_courses", consoleReader, router);
		this.studentService = studentService;
		this.enrolledService = enrolledService;
	}

	@Override
	public void render() throws Exception {

		Student sessionStudent = studentService.getSessionStudent();

		if (sessionStudent == null) {
			System.out.println("You are not logged in! Going back to login menu..");
			router.transfer("/s_login");
		}

		// TODO fix it up so that if not an integer then go back
		while (studentService.isSessionActive()) {
			System.out.println("Here are the current available courses:\n");
			
			ArrayList<Enrolled> enrolledList = (ArrayList<Enrolled>) enrolledService.getAllEnrolled();

			for (int i = 0; i < enrolledList.size(); i++) {
				System.out.println(enrolledList.get(i).toString());
			}

			String menu = "1) Remove Course\n" +
							"2) Exit back to Dashboard\n" +
							"3) Logout\n" + "> ";

			System.out.print(menu);

			String userSelection = consoleReader.readLine();

			switch (userSelection) {
			case "1":
				System.out.println("Use previously printed enrolled information:");
				router.transfer("/s_remove_courses");
				break;
			case "2":
				System.out.println("Dashboard selected:");
				router.transfer("/s_dashboard");
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

package com.revature.course_app.menus.student.courses;

import java.io.BufferedReader;

import com.revature.course_app.exceptions.InvalidRequestException;
import com.revature.course_app.menus.Menu;
import com.revature.course_app.models.Enrolled;
import com.revature.course_app.models.Student;
import com.revature.course_app.services.EnrolledService;
import com.revature.course_app.services.StudentService;
import com.revature.course_app.util.MenuRouter;

public class SRemoveCoursesMenu extends Menu {
	private final StudentService studentService;
	private final EnrolledService enrolledService;

	public SRemoveCoursesMenu(BufferedReader consoleReader, MenuRouter router, StudentService studentService,
			EnrolledService enrolledService) {
		super("SRemoveCourses", "/s_remove_courses", consoleReader, router);
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
			System.out.println("Insert a valid course id or anything else to go back:\n");

			String userSelection = consoleReader.readLine();

			Enrolled enrolled = new Enrolled(Integer.parseInt(userSelection),
					studentService.getSessionStudent().getStudentId());

			try {
				enrolledService.removeEnrolled(enrolled);

				System.out.println("Sweet success! That course was trash..\n");
				router.transfer("/s_dashboard");

			} catch (InvalidRequestException e) {
				System.out.println("Unable to remove the course..");
				router.transfer("/s_dashboard");
			}

		}

	}
}

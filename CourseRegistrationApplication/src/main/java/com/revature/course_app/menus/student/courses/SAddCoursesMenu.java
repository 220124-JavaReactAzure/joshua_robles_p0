package com.revature.course_app.menus.student.courses;

import java.io.BufferedReader;

import com.revature.course_app.exceptions.InvalidRequestException;
import com.revature.course_app.menus.Menu;
import com.revature.course_app.models.Enrolled;
import com.revature.course_app.models.Student;
import com.revature.course_app.services.CourseService;
import com.revature.course_app.services.EnrolledService;
import com.revature.course_app.services.StudentService;
import com.revature.course_app.util.MenuRouter;
import com.revature.course_app.util.collections.ArrayList;

public class SAddCoursesMenu extends Menu {
	
	private final StudentService studentService;
	private final CourseService courseService;
	private final EnrolledService enrolledService;

	public SAddCoursesMenu(BufferedReader consoleReader, MenuRouter router,
			StudentService studentService, CourseService courseService, EnrolledService enrolledService) {
		super("SAddCourses", "/s_add_courses", consoleReader, router);
		this.studentService = studentService;
		this.courseService = courseService;
		this.enrolledService = enrolledService;
	}

	@Override
	public void render() throws Exception {

		Student sessionStudent = studentService.getSessionStudent();

		if (sessionStudent == null) {
			System.out.println("You are not logged in! Going back to login menu..");
			router.transfer("/s_login");
		}

		//TODO fix it up so that if not an integer then go back
		while (studentService.isSessionActive()) {
			System.out.println("Insert a valid course id or anything else to go back:\n");

			String userSelection = consoleReader.readLine();
			
			Enrolled newEnrolled = new Enrolled(Integer.parseInt(userSelection), studentService.getSessionStudent().getStudentId());

			try {
				enrolledService.registerNewEnrolled(newEnrolled);
				
				System.out.println("Sweet success! You got the course\n");
				router.transfer("/s_dashboard");
				
			} catch(InvalidRequestException e) {
				System.out.println("Unable to register to class..");
				router.transfer("/s_dashboard");
			}
			
		}
	}
}

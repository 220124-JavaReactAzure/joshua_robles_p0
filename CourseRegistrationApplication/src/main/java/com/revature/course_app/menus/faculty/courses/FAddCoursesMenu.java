package com.revature.course_app.menus.faculty.courses;

import java.io.BufferedReader;

import com.revature.course_app.exceptions.InvalidRequestException;
import com.revature.course_app.menus.Menu;
import com.revature.course_app.models.Course;
import com.revature.course_app.services.CourseService;
import com.revature.course_app.services.FacultyService;
import com.revature.course_app.util.MenuRouter;

public class FAddCoursesMenu extends Menu{
	private final FacultyService facultyService;
	private final CourseService courseService;

	public FAddCoursesMenu(BufferedReader consoleReader, MenuRouter router, FacultyService facultyService,
			CourseService courseService) {
		super("FAddCourses", "/f_add_courses", consoleReader, router);
		this.facultyService = facultyService;
		this.courseService = courseService;
	}

	@Override
	public void render() throws Exception {
		System.out.println("\nCreating course. Please insert course information..\n");

		System.out.println("Course name: ");
		String name = consoleReader.readLine();

		Course newCourse = new Course(facultyService.getSessionFaculty().getFacultyId(), name);

		try {
			courseService.registerNewCourse(newCourse);
			router.transfer("/f_dashboard");
		} catch (InvalidRequestException e) {
			System.out.println("Unable to make new course..");
		}

		router.transfer("/f_home");
	}
}

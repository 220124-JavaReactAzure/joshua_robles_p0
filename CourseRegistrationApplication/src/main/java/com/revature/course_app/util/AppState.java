package com.revature.course_app.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.revature.course_app.daos.CourseDAO;
import com.revature.course_app.daos.EnrolledDAO;
import com.revature.course_app.daos.FacultyDAO;
import com.revature.course_app.daos.StudentDAO;
import com.revature.course_app.menus.HomeMenu;
import com.revature.course_app.menus.student.SDashboardMenu;
import com.revature.course_app.menus.student.SHomeMenu;
import com.revature.course_app.menus.student.SLoginMenu;
import com.revature.course_app.menus.student.SRegistrationMenu;
import com.revature.course_app.menus.student.courses.SAddCoursesMenu;
import com.revature.course_app.menus.student.courses.SEnrolledCoursesMenu;
import com.revature.course_app.menus.student.courses.SViewCoursesMenu;
import com.revature.course_app.services.CourseService;
import com.revature.course_app.services.EnrolledService;
import com.revature.course_app.services.FacultyService;
import com.revature.course_app.services.StudentService;


public class AppState {
	
	private static boolean isRunning;
	private final MenuRouter router;
	
	public AppState() {
		isRunning = true;
		router = new MenuRouter();
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		
		StudentDAO studentDao = new StudentDAO();
		StudentService studentService = new StudentService(studentDao);
		
		FacultyDAO facultyDao = new FacultyDAO();
		FacultyService facutlyService = new FacultyService(facultyDao);
		
		CourseDAO courseDao = new CourseDAO();
		CourseService courseService = new CourseService(courseDao);
		
		EnrolledDAO enrolledDao = new EnrolledDAO();
		EnrolledService enrolledService = new EnrolledService(enrolledDao);
		
		router.addMenu(new HomeMenu(consoleReader, router));
		router.addMenu(new SHomeMenu(consoleReader, router));
		router.addMenu(new SLoginMenu(consoleReader, router, studentService));
		router.addMenu(new SRegistrationMenu(consoleReader, router, studentService));
		router.addMenu(new SDashboardMenu(consoleReader, router, studentService));
		router.addMenu(new SViewCoursesMenu(consoleReader, router, studentService, courseService));
		router.addMenu(new SAddCoursesMenu(consoleReader, router, studentService, courseService, enrolledService));
		router.addMenu(new SEnrolledCoursesMenu(consoleReader, router, studentService, enrolledService));

	}
	
	public void startup() {
		try {
			while(isRunning) {
				router.transfer("/home");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void shutdown() {
		isRunning = false;
	}

}

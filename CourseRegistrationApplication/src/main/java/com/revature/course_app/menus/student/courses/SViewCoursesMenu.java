package com.revature.course_app.menus.student.courses;

import java.io.BufferedReader;

import com.revature.course_app.menus.Menu;
import com.revature.course_app.models.Course;
import com.revature.course_app.models.Student;
import com.revature.course_app.services.CourseService;
import com.revature.course_app.services.StudentService;
import com.revature.course_app.util.MenuRouter;
import com.revature.course_app.util.collections.ArrayList;

public class SViewCoursesMenu extends Menu{
	
	private final StudentService studentService;
	private final CourseService courseService;
	
	public SViewCoursesMenu(BufferedReader consoleReader, MenuRouter router, StudentService studentService, CourseService courseService) {
		super("SViewCourses", "/s_view_courses", consoleReader, router);
		this.studentService = studentService;
		this.courseService = courseService;
	}
	
	@Override
	public void render() throws Exception {
		
		Student sessionStudent = studentService.getSessionStudent();
		
		if (sessionStudent == null) {
			System.out.println("You are not logged in! Going back to login menu..");
			router.transfer("/s_login");
		}
		
		while(studentService.isSessionActive()) {
			System.out.println("Here are the current available courses:\n");
			ArrayList<Course> courseList = (ArrayList<Course>) courseService.getAllCourses();
			
			for(int i = 0; i < courseList.size(); i++) {
				System.out.println(courseList.get(i).toString());
			}
			
			String menu = "1) Add Course\n" + 
					"2) Exit back to Dashboard\n" +
					"3) Logout\n" + 
					 "> ";

			System.out.print(menu);

			String userSelection = consoleReader.readLine();

			switch (userSelection) {
			case "1":
				System.out.println("View available courses selected:");
				router.transfer("/s_add_courses");
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

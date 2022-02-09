package com.revature.course_app.menus.faculty.courses;

import java.io.BufferedReader;

import com.revature.course_app.exceptions.InvalidRequestException;
import com.revature.course_app.menus.Menu;
import com.revature.course_app.models.Course;
import com.revature.course_app.models.Faculty;
import com.revature.course_app.services.CourseService;
import com.revature.course_app.services.FacultyService;
import com.revature.course_app.util.MenuRouter;
import com.revature.course_app.util.collections.ArrayList;
import com.revature.course_app.util.logging.Logger;

public class FRemoveCoursesMneu extends Menu{
	private final FacultyService facultyService;
	private final CourseService courseService;
	private final Logger logger;

	public FRemoveCoursesMneu(BufferedReader consoleReader, MenuRouter router, FacultyService facultyService,
			CourseService courseService) {
		super("FRemoveCourses", "/f_remove_courses", consoleReader, router);
		logger = Logger.getLogger(true);
		this.facultyService = facultyService;
		this.courseService = courseService;
	}

	@Override
	public void render() throws Exception {

		Faculty sessionFaculty = facultyService.getSessionFaculty();

		if (sessionFaculty == null) {
			System.out.println("You are not logged in! Going back to login menu..");
			router.transfer("/f_login");
		}

		// TODO fix it up so that if not an integer then go back
		while (facultyService.isSessionActive()) {
			System.out.println("Here are the current available courses:\n");

			ArrayList<Course> enrolledList = (ArrayList<Course>) courseService.getCoursesFromTeacherId(facultyService.getSessionFaculty().getFacultyId());

			for (int i = 0; i < enrolledList.size(); i++) {
				System.out.println(enrolledList.get(i).toString());
			}

			String menu = "Insert course_id to remove:\n ";

			System.out.print(menu);

			String userSelection = consoleReader.readLine();

			try {
				courseService.removeCourseUsingCIdAndFId(Integer.parseInt(userSelection), 
						facultyService.getSessionFaculty().getFacultyId());
				
				logger.log("Successfully removed course!\n");
				
				router.transfer("/f_dashboard");
			} catch (InvalidRequestException e) {
				System.out.println("Unable to remove the course..");
				router.transfer("/f_dashboard");
			}

		}
	}
}

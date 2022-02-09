package com.revature.course_app.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.revature.course_app.daos.StudentDAO;
import com.revature.course_app.menus.HomeMenu;
import com.revature.course_app.menus.student.SHomeMenu;
import com.revature.course_app.menus.student.SLoginMenu;
import com.revature.course_app.menus.student.SRegistrationMenu;
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
		
		router.addMenu(new HomeMenu(consoleReader, router));
		router.addMenu(new SHomeMenu(consoleReader, router));
		router.addMenu(new SLoginMenu(consoleReader, router, studentService));
		router.addMenu(new SRegistrationMenu(consoleReader, router, studentService));
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

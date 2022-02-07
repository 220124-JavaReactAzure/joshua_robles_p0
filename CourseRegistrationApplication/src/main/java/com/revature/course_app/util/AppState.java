package com.revature.course_app.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.revature.course_app.menus.HomeMenu;
import com.revature.course_app.menus.LoginMenu;


public class AppState {
	
	private static boolean isRunning;
	private final MenuRouter router;
	
	public AppState() {
		isRunning = true;
		router = new MenuRouter();
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		
		router.addMenu(new HomeMenu(consoleReader, router));
		router.addMenu(new LoginMenu(consoleReader, router));
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

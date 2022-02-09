package com.revature.course_app.menus.student;

import static com.revature.course_app.util.AppState.shutdown;

import java.io.BufferedReader;

import com.revature.course_app.menus.Menu;
import com.revature.course_app.util.MenuRouter;

public class SHomeMenu extends Menu {
	public SHomeMenu(BufferedReader consoleReader, MenuRouter router) {
		super("SHome", "/s_home", consoleReader, router);
	}

	@Override
	public void render() throws Exception {
		//TODO fill out the options for the application's requirements
		System.out.print("Hello student to the Course Registration Application.\n" +
							"1) Login\n" +
							"2) Register\n" +
							"3) Exit\n");
		
		String userSelection = consoleReader.readLine();
		
		switch(userSelection) {
		case "1":
			router.transfer("/s_login");
			break;
		case "2":
			router.transfer("/s_registration");
			break;
		case "3":
			shutdown();
			break;
		default:
			System.out.println("You have put an invalid input.\nPlease try again!");
		}
	}
}

package com.revature.course_app.menus;

import java.io.BufferedReader;

import com.revature.course_app.util.MenuRouter;
import static com.revature.course_app.util.AppState.shutdown;

public class HomeMenu extends Menu {

	public HomeMenu(BufferedReader consoleReader, MenuRouter router) {
		super("Home", "/home", consoleReader, router);
	}

	@Override
	public void render() throws Exception {
		System.out.print("Hello! Welcome to the course registration application.\n" +
							"Are you a student or faculty?\n" +
							"1) Student\n" +
							"2) Faculty\n" +
							"3) Exit\n");
		
		String userSelection = consoleReader.readLine();
		
		switch(userSelection) {
		case "1":
			router.transfer("/s_home");
			break;
		case "2":
			router.transfer("/f_home");
			break;
		case "3":
			shutdown();
			break;
		default:
			System.out.println("You have put an invalid input.\nPlease try again!");
		}
	}

}

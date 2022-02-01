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
		//TODO fill out the options for the application's requirements
		System.out.print("Hello!\nWelcome to the course registration application.\n" +
							"1) Login\n" +
							"2) Register\n" +
							"3)Exit\n");
		shutdown();

	}

}

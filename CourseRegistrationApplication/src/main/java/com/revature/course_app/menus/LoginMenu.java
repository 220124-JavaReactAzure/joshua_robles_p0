package com.revature.course_app.menus;

import java.io.BufferedReader;

import com.revature.course_app.util.MenuRouter;

public class LoginMenu extends Menu {

	public LoginMenu(BufferedReader consoleReader, MenuRouter router) {
		super("Login", "/login", consoleReader, router);
		
	}

	@Override
	public void render() throws Exception {
		System.out.println("Enter account information..");

	}

}

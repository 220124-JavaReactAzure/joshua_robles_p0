package com.revature.course_app.util.datasource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	// Singleton is created in this private static final
	private static final ConnectionFactory connectionFactory = new ConnectionFactory();
	// where properties are loaded into
	private Properties prop = new Properties();

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private ConnectionFactory() {
		try {
			// Used to load file into prop
			prop.load(new FileReader("src/main/resources/db.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Where singleton access is achieved
	public static ConnectionFactory getInstance() {
		return connectionFactory;
	}

	public Connection getConnection() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("admin"),
					prop.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

}

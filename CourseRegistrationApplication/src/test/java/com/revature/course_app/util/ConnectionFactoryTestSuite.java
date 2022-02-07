package com.revature.course_app.util;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import com.revature.course_app.util.datasource.ConnectionFactory;

public class ConnectionFactoryTestSuite {
	
	@Test
	public void test_getConnection_returnValidConnection_givenProvidedCredentials() {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			System.out.println("Connection made :)");
			Assert.assertNotNull(conn);;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

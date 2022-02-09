package com.revature.course_app.models;

import java.io.Serializable;
import java.util.Objects;

public class Faculty implements Serializable {

	private String facultyId;
	private String username;
	private String password;

	public Faculty() {
		super();
	}

	// Probably can drop this overloaded method
	public Faculty(String facultyId, String username, String password) {
		super();
		this.facultyId = facultyId;
		this.username = username;
		this.password = password;
	}
	
	public Faculty(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", username=" + username + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(facultyId, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return Objects.equals(facultyId, other.facultyId) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	
	

}

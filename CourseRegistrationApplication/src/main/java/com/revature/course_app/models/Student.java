package com.revature.course_app.models;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable{
	
	// Could potentially generalize the accounts and just make the only difference being the title
	private String studentId;
	private String username;
	private String password;
	
	public Student() {
		super();
	}
	
	//Not sure if need a version without ID

	public Student(String studentId, String username, String password) {
		super();
		this.studentId = studentId;
		this.username = username;
		this.password = password;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
		return "Student [studentId=" + studentId + ", username=" + username + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, studentId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(password, other.password) && Objects.equals(studentId, other.studentId)
				&& Objects.equals(username, other.username);
	}
	
	
	
}

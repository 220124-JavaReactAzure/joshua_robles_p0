package com.revature.course_app.models;

import java.util.Objects;

public class Enrolled {
	private int courseId;
	private int studentId;
	
	public Enrolled() {
		super();
	}
	
	public Enrolled(int courseId, int studentId) {
		this.courseId = courseId;
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "Enrolled [courseId=" + courseId + ", studentId=" + studentId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseId, studentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrolled other = (Enrolled) obj;
		return courseId == other.courseId && studentId == other.studentId;
	}
	
	
	
}

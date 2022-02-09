package com.revature.course_app.models;

import java.util.Objects;

public class Course {
	private int courseId;
	private int teacherId;
	private String courseName;
	
	public Course() {
		super();
	}
	
	public Course(int teacherId, String courseName) {
		super();
		this.teacherId = teacherId;
		this.courseName = courseName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", teacherId=" + teacherId + ", courseName=" + courseName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseId, courseName, teacherId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return courseId == other.courseId && Objects.equals(courseName, other.courseName)
				&& teacherId == other.teacherId;
	}
	

}

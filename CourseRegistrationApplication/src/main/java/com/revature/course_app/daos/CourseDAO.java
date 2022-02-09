package com.revature.course_app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.course_app.models.Course;
import com.revature.course_app.util.collections.ArrayList;
import com.revature.course_app.util.datasource.ConnectionFactory;

public class CourseDAO implements CrudDAO<Course> {

	public Course findByCourseId(int course_id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from course where course_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, course_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setTeacherId(rs.getInt("faculty_id"));
				course.setCourseName(rs.getString("course_name"));

				return course;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Course> findByTeacherId(int teacher_id) {
		ArrayList<Course> courseList = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from course where faculty_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, teacher_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setTeacherId(rs.getInt("faculty_id"));
				course.setCourseName(rs.getString("course_name"));

				courseList.add(course);
			}

			return courseList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Course create(Course newCourse) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "insert into course (faculty_id, course_name) values (?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, newCourse.getTeacherId());
			ps.setString(2, newCourse.getCourseName());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return newCourse;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<Course> findAll() {
		ArrayList<Course> courseList = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from course";
			Statement s = conn.createStatement();

			ResultSet resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				Course course = new Course();
				course.setCourseId(resultSet.getInt("course_id"));
				course.setTeacherId(resultSet.getInt("faculty_id"));
				course.setCourseName(resultSet.getString("course_name"));

				courseList.add(course);
			}

			return courseList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Course findById(int id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from course where course_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setTeacherId(rs.getInt("faculty_id"));
				course.setCourseName(rs.getString("course_name"));

				return course;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean update(Course updatedObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	//TODO not good logic and not safe guarding from other faculty access..
	public boolean deleteUsingCIdAndFId(int courseId, int facultyId) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "delete from enrolled where course_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, courseId);

			ps.executeUpdate();
			
			sql = "delete from course where course_id = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, courseId);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}
}

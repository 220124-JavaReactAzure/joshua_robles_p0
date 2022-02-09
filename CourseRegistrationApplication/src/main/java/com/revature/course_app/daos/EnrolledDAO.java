package com.revature.course_app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.course_app.models.Enrolled;
import com.revature.course_app.util.collections.ArrayList;
import com.revature.course_app.util.datasource.ConnectionFactory;

public class EnrolledDAO implements CrudDAO<Enrolled> {

	public Enrolled findByCourseId(int courseId) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from enrolled where course_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, courseId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Enrolled enrolled = new Enrolled();
				enrolled.setCourseId(rs.getInt("course_id"));
				enrolled.setStudentId(rs.getInt("student_id"));

				return enrolled;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Enrolled> findByStudentId(int studentId) {
		ArrayList<Enrolled> enrolledList = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from enrolled where student_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Enrolled enrolled = new Enrolled();
				enrolled.setCourseId(rs.getInt("course_id"));
				enrolled.setStudentId(rs.getInt("student_id"));

				enrolledList.add(enrolled);
			}

			return enrolledList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Enrolled create(Enrolled newEnrolled) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "insert into enrolled (course_id, student_id) values (?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, newEnrolled.getCourseId());
			ps.setInt(2, newEnrolled.getStudentId());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return newEnrolled;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<Enrolled> findAll() {
		ArrayList<Enrolled> enrolledList = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from enrolled";
			Statement s = conn.createStatement();

			ResultSet resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				Enrolled enrolled = new Enrolled();
				enrolled.setCourseId(resultSet.getInt("course_id"));
				enrolled.setStudentId(resultSet.getInt("student_id"));

				enrolledList.add(enrolled);
			}

			return enrolledList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Enrolled findById(int id) {
		return findByCourseId(id);
	}

	@Override
	public boolean update(Enrolled updatedObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}

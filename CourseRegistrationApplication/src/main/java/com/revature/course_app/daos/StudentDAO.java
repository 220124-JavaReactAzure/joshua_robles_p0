package com.revature.course_app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.course_app.models.Student;
import com.revature.course_app.util.collections.ArrayList;
import com.revature.course_app.util.datasource.ConnectionFactory;

public class StudentDAO implements CrudDAO<Student>{
	
	public Student findByUsernameAndPassword(String username, String password) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from student where username = ? and password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getString("student_id"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("password"));
				
				return student;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Student findByUsername(String username) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from student where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getString("student_id"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("password"));
				
				return student;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Student create(Student newObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Student> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Student updatedObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}

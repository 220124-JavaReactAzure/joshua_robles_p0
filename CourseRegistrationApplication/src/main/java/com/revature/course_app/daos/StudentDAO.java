package com.revature.course_app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.course_app.models.Student;
import com.revature.course_app.util.collections.ArrayList;
import com.revature.course_app.util.collections.List;
import com.revature.course_app.util.datasource.ConnectionFactory;

public class StudentDAO implements CrudDAO<Student>{
	
	public Student findByUsernameAndPassword(String username, String password) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from student where username = ? and user_password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt("student_id"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("user_password"));
				
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
				student.setStudentId(rs.getInt("student_id"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("user_password"));
				
				return student;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Student create(Student newStudent) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "insert into student (username, user_password) values (?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newStudent.getUsername());
			ps.setString(2, newStudent.getPassword());
			
			int rowsInserted = ps.executeUpdate();
			
			if (rowsInserted != 0) {
				return newStudent;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Student> findAll() {
		ArrayList<Student> studentList = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from student";
			Statement s = conn.createStatement();
			
			ResultSet resultSet = s.executeQuery(sql);
			
			while (resultSet.next()) {
				Student student = new Student();
				student.setStudentId(resultSet.getInt("student_id"));
				student.setUsername(resultSet.getString("username"));
				student.setPassword(resultSet.getString("user_password"));
				
				studentList.add(student);
			}
			
			return studentList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Student findById(int id) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from student where student_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt("student_id"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("user_password"));
				
				return student;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

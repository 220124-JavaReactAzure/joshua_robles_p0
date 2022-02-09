package com.revature.course_app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.course_app.models.Faculty;
import com.revature.course_app.util.collections.ArrayList;
import com.revature.course_app.util.datasource.ConnectionFactory;

public class FacultyDAO implements CrudDAO<Faculty> {

	public Faculty findByUsernameAndPassword(String username, String password) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from faculty where username = ? and user_password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Faculty faculty = new Faculty();
				faculty.setFacultyId(rs.getInt("faculty_id"));
				faculty.setUsername(rs.getString("username"));
				faculty.setPassword(rs.getString("user_password"));

				return faculty;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Faculty findByUsername(String username) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from faculty where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Faculty faculty = new Faculty();
				faculty.setFacultyId(rs.getInt("faculty_id"));
				faculty.setUsername(rs.getString("username"));
				faculty.setPassword(rs.getString("user_password"));

				return faculty;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Faculty create(Faculty newFaculty) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "insert into faculty (username, user_password) values (?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, newFaculty.getUsername());
			ps.setString(2, newFaculty.getPassword());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return newFaculty;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<Faculty> findAll() {
		ArrayList<Faculty> facultyList = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from faculty";
			Statement s = conn.createStatement();

			ResultSet resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				Faculty faculty = new Faculty();
				faculty.setFacultyId(resultSet.getInt("faculty_id"));
				faculty.setUsername(resultSet.getString("username"));
				faculty.setPassword(resultSet.getString("user_password"));

				facultyList.add(faculty);
			}

			return facultyList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Faculty findById(int id) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from student where student_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Faculty faculty = new Faculty();
				faculty.setFacultyId(rs.getInt("faculty_id"));
				faculty.setUsername(rs.getString("username"));
				faculty.setPassword(rs.getString("user_password"));
				
				return faculty;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(Faculty updatedObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}

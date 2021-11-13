package com.web.controller.member;

import java.sql.*;

import com.web.dbconnect.DBConnection;
import com.web.dtomodel.MemberDTO;

public class MemberDAO {
	
	private Connection conn;
	
	public MemberDAO() {
		try {
			conn = DBConnection.connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int checkUser(String email, String pwd) {
		String sql = "select pwd from USER where email = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString(1).equals(pwd)) return 1;
				else return 0;
			}
			
			return -1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -2; // �삤瑜�
	}
	
	public MemberDTO login(String email, String pwd) {
		String sql = "select * from USER where email = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String gender = rs.getString("gender");
			Date regDate = rs.getDate("regDate");
			
			MemberDTO user = new MemberDTO(id, email, pwd, name, gender, regDate);
			return user; 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void join(MemberDTO memberdto) {
		String sql = "insert into USER values(null, ?, ?, ?, ?, default)";
		
		String email = memberdto.getEmail();
		String pwd = memberdto.getPwd();
		String name = memberdto.getName();
		String gender = memberdto.getGender();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, gender);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int uniqueCheck(String email) {
		String sql = "select email from USER where email=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
				
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return 0;
			}
			else {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}

package com.web.member.controller;
import java.sql.*;

import com.web.dbconnect.DBConnection;

public class joinDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public joinDAO() {
		try {
			conn = DBConnection.connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int uniqueCheck(String email) {
		String sql = "select email from USER where email=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
				
			rs = pstmt.executeQuery();
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
	
	public void join(String email, String name, String pwd, String gender) {
		String sql = "insert into USER values(null, ?, ?, ?, ?, default)";
		
		String emailL = email;
		String pwdL = pwd;
		String nameL = name;
		String genderL = gender;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emailL);
			pstmt.setString(2, pwdL);
			pstmt.setString(3, nameL);
			pstmt.setString(4, genderL);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

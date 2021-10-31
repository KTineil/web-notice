package com.web.board.controller;

import java.sql.*;

import com.web.dbconnect.DBConnection;

public class WritePostDAO {
	int write(int uid, String title, String content, String files) {
		try {
			String sql = "insert into BOARD(uid, title, content, files) values(?, ?, ?, ?)";
			Connection conn = DBConnection.connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setString(4, files);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "select last_insert_id() as id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt("id");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}

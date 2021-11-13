package com.web.board.controller;

import java.sql.*;

import com.web.board.module.PostDTO;
import com.web.dbconnect.DBConnection;
import java.io.File;

public class PostDAO {
	void increaseHit(String id) {
		try {
			String bid = id;
			int hit = -1;
			String sql = "select hit from BOARD where id = " + bid;
			Connection conn = DBConnection.connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				hit = rs.getInt("hit");
			}
			
			sql = "update BOARD set hit = "+hit+"+1 where id = " + bid;
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public PostDTO getDetail(String id) {
		try {
			int bid = Integer.valueOf(id);
			String sql = "select BOARD.*, USER.name from BOARD inner join USER on BOARD.uid = USER.id where BOARD.id = ?";
			Connection conn = DBConnection.connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			String title = rs.getString("title");
			Timestamp regDate = rs.getTimestamp("regdate");
			int hit = rs.getInt("hit");
			String content = rs.getString("content");
			String uid = rs.getString("uid");
			String name = rs.getString("name");
			String fileName = rs.getString("fileName");
			String fileRealName = rs.getString("fileRealName");
			
			PostDTO boarddto = new PostDTO(bid, uid, title, name, content, regDate, hit, fileName, fileRealName);
			
			return boarddto;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void delete(String bid) {
		try {
			String sql = "delete from BOARD where id = ?";
			Connection conn = DBConnection.connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bid);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public void update(String bid, String content) {
		try {
			String sql = "update BOARD set content = ?" + ", filename = ?, filerealname = ? " + "where id = ?";
			Connection conn = DBConnection.connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, bid);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}

package com.web.board.controller;

import java.sql.*;

import com.web.board.model.BoardDTO;
import com.web.dbconnect.DBConnection;

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
	public BoardDTO getDetail(String id) {
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
			String files = rs.getString("files");
			String name = rs.getString("name");
			
			BoardDTO boarddto = new BoardDTO(bid, title, name, content, regDate, hit, files);
			
			return boarddto;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

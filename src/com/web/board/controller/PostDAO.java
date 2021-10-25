package com.web.board.controller;

import java.sql.*;

import com.web.board.model.BoardDTO;
import com.web.dbconnect.DBConnection;

public class PostDAO {
	public BoardDTO getDetail(String id, String writerName) {
		int bid = Integer.valueOf(id);
		String name = writerName;
		String sql = "select * from BOARD where id = ?";
		try {
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
			
			BoardDTO boarddto = new BoardDTO(bid, title, name, content, regDate, hit, files);
			
			return boarddto;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

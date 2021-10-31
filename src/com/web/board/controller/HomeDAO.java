package com.web.board.controller;

import java.sql.*;
import java.util.ArrayList;

import com.web.board.model.BoardDTO;
import com.web.dbconnect.DBConnection;

public class HomeDAO {
	public ArrayList<BoardDTO> get() {
		try {
			String sql = "select BOARD.title, BOARD.id, USER.name from BOARD inner join USER on USER.id = BOARD.uid order by BOARD.id desc limit 5";
			Connection conn = DBConnection.connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<BoardDTO> posts = new ArrayList<BoardDTO>();
			while(rs.next()) {
				String bid = rs.getString("id");
				String title = rs.getString("title");
				String name = rs.getString("name");
				BoardDTO boarddto = new BoardDTO(Integer.valueOf(bid), title, name, null, null, -1, null);
				posts.add(boarddto);
			}
			rs.close();
			stmt.close();
			conn.close();
			return posts;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

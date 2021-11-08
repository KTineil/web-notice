package com.web.comment.controller;

import java.sql.*;
import java.util.ArrayList;

import com.web.comment.model.CommentDTO;
import com.web.dbconnect.DBConnection;

public class CommentDAO {
	public ArrayList<CommentDTO> get(int bid) {
		try {
			String sql = "select COMMENT.board_id, COMMENT.content, COMMENT.date, USER.email, USER.name from COMMENT join USER on USER.id = COMMENT.uid where board_id = ?";
			Connection conn = DBConnection.connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<CommentDTO> comments = new ArrayList<CommentDTO>();
			while (rs.next()) {
				int board_id = rs.getInt("board_id");
				String content = rs.getString("content");
				String email_[] = rs.getString("email").split("@");
				String identify = email_[0];
				String mail = email_[1];
				String name = rs.getString("name");
				Timestamp date = rs.getTimestamp("date");
				
				int half = identify.length() / 2;
				if (half%2 == 1) {
					half += 1;
				}
				char[] halfId = new char[identify.length()-half];
				for (int i = 0; i < identify.length()-half; i++) {
					halfId[i] = identify.charAt(i);
				}
				identify = new String(halfId);
				for (int i = 0; i < half; i++) {
					identify += "*";
				}
				
				String email = identify+"@"+mail;
				CommentDTO commentdto = new CommentDTO(board_id, content, email, name, date);
				comments.add(commentdto);
			}
			return comments;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	public void insert(String content, int uid, int bid) {
		try {
			String sql = "insert into COMMENT(id, board_id, content, uid) values(?,?,?,?)";
			Connection conn = DBConnection.connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, findLastId(bid)+1);
			pstmt.setInt(2, bid);
			pstmt.setString(3, content);
			pstmt.setInt(4, uid);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public int findLastId(int bid) {
		try {
			String sql = "select id from COMMENT where board_id = ? order by id desc";
			Connection conn = DBConnection.connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("id");
			}
			else {
				return 1;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public void deleteReference(String bid) {
		try {
			String sql = "delete from COMMENT where board_id = ?";
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
}

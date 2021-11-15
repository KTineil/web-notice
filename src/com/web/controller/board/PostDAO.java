package com.web.controller.board;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import com.web.dbconnect.DBConnection;
import com.web.dtomodel.PostDTO;

import java.io.File;

public class PostDAO {
	void increaseHit(String boardId) {
		try {
			String bid = boardId;
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
	int write(PostDTO postdto) {
		try {
			String sql = "insert into BOARD(uid, title, content, filename, filerealname) values(?, ?, ?, ?, ?)";
			Connection conn = DBConnection.connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.valueOf(postdto.getUid()));
			pstmt.setString(2, postdto.getTitle());
			pstmt.setString(3, postdto.getContent());
			pstmt.setString(4, postdto.getFileName());
			pstmt.setString(5, postdto.getFileRealName());
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
	public PostDTO getPostDetail(String boardId) {
		try {
			int bid = Integer.valueOf(boardId);
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
			String sql = "update BOARD set content = ?";
			// 파일 변경 시 처리할 부분
//			if (fileName != null) {
//				sql += ", filename = ?, filerealname = ? ";
//			}
			sql += " where id = ?";
			
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
	public ArrayList<PostDTO> getPostList(String SearchValue, int page) {
		String sv = SearchValue;
		if (sv == null || sv.equals("")) {
			sv = "";
		}
		String sql = "select BOARD.*, USER.name, count(COMMENT.board_id) as CommentCnt from BOARD inner join USER on BOARD.uid = USER.id left join COMMENT on BOARD.id = COMMENT.board_id where BOARD.title like '%" + sv + "%' OR USER.name like '%" + sv + "%' group by BOARD.id order by BOARD.id desc limit ?, ?";
		try {
			Connection conn = DBConnection.connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			
			// 게시글 리스트 구하기
			pstmt.setInt(1, 20*(page-1));
			pstmt.setInt(2, 20*page);
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<PostDTO> list = new ArrayList<PostDTO>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writerName = rs.getString("name");
				Timestamp regDate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				String fileName = rs.getString("filename");
				
				PostDTO board = new PostDTO(id, null, title, writerName, "", regDate, hit, fileName, null);
				board.setCommentCnt(rs.getInt("CommentCnt"));
				list.add(board);
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int getLastPage() {
		String sql = "select count(*) as cnt from BOARD";
		try {
			Connection conn = DBConnection.connectDB();
			Statement stmt = conn.createStatement();
			
			// 마지막 페이지 구하기 
			ResultSet cntRs = stmt.executeQuery(sql);
			cntRs.next();
			int cnt = cntRs.getInt("cnt");
			int endOfPage = cnt%20 == 0 ? cnt/20: cnt/20+1;
			
			return endOfPage;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public ArrayList<PostDTO> getHomeList() {
		try {
			String sql = "select BOARD.title, BOARD.id, USER.name from BOARD inner join USER on USER.id = BOARD.uid order by BOARD.id desc limit 5";
			Connection conn = DBConnection.connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<PostDTO> posts = new ArrayList<PostDTO>();
			while(rs.next()) {
				String bid = rs.getString("id");
				String title = rs.getString("title");
				String name = rs.getString("name");
				PostDTO boarddto = new PostDTO(Integer.valueOf(bid), null, title, name, null, null, -1, null, null);
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

package com.web.board.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.Board;
import com.web.dbconnect.DBConnection;

@WebServlet("/freeboard")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// DB 연결 구문
			String sql = "select BOARD.*, USER.name from BOARD inner join USER where BOARD.uid = USER.id limit ?, ?";
			String cntSql = "select count(*) as cnt from BOARD";
			
			Connection conn = DBConnection.connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			Statement stmt = conn.createStatement();
			
			// 마지막 페이지 구하기 
			ResultSet cntRs = stmt.executeQuery(cntSql);
			cntRs.next();
			int cnt = cntRs.getInt("cnt");
			int endOfPage = cnt%20 == 0 ? cnt/20: cnt/20+1;
			
			// 페이지 수 / 파라미터 없을 시 1페이지 취급
			int page = req.getParameter("p") != null ? Integer.parseInt(req.getParameter("p")): 1;
			
			// 페이지 수 기반 화면에 보여질 게시글 수
			pstmt.setInt(1, 20*(page-1));
			pstmt.setInt(2, 20*page);
			ResultSet rs = pstmt.executeQuery();
			
			List<Board> list = new ArrayList<Board>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writerName = rs.getString("name");
				Date regDate = rs.getDate("date");
				int hit = rs.getInt("hit");
				
				Board board = new Board(id, title, writerName, "", regDate, hit);
				list.add(board);
			}
			
			// DB 연결 종료
			
			rs.close();
			stmt.close();
			cntRs.close();
			pstmt.close();
			conn.close();
			
			// 값 포워딩
			req.setAttribute("list", list);
			req.setAttribute("endOfPage", endOfPage);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/section/free.jsp");
			dispatcher.forward(req, resp);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
package com.web.board.controller;

import java.io.IOException;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.comment.controller.CommentDAO;

@WebServlet("/deletePostAction")
public class DeletePostAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// DB 레코드 삭제
		String bid = req.getParameter("bid");
		
		PostDAO postdao = new PostDAO();
		CommentDAO commentdao = new CommentDAO();
		
		commentdao.deleteReference(bid);
		postdao.delete(bid);
		
		// 첨부파일 삭제 (미구현)
		
		resp.sendRedirect("/freeboard");
	}
}

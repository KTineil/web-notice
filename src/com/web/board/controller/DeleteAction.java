package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.comment.controller.CommentDAO;

@WebServlet("/deleteAction")
public class DeleteAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("bid");
		String bid = req.getParameter("bid");
		PostDAO postdao = new PostDAO();
		CommentDAO commentdao = new CommentDAO();
		commentdao.deleteReference(bid);
		postdao.delete(bid);
		resp.sendRedirect("/freeboard");
	}
}

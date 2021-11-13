package com.web.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updatePostAction")
public class UpdatePostAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String content = req.getParameter("content");
		String bid = req.getParameter("bid");
		
		PostDAO postdao = new PostDAO();
		postdao.update(bid, content);
		
		resp.sendRedirect("post?bid="+bid);
	}
}

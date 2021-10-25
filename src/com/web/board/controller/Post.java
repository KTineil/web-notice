package com.web.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.board.model.BoardDTO;

@WebServlet("/post")
public class Post extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("bid");
		String name = req.getParameter("name");
		
		if (id == null || id.equals("")) {
			resp.sendRedirect("/freeboard");
		}
		
		PostDAO postdao = new PostDAO();
		BoardDTO boarddto = postdao.getDetail(id, name);
		
		req.setAttribute("post", boarddto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/section/post.jsp");
		dispatcher.forward(req, resp);
	}
}
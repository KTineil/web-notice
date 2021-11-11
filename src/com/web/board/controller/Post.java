package com.web.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.board.model.PostDTO;
import com.web.comment.controller.CommentDAO;
import com.web.comment.model.CommentDTO;

@WebServlet("/post")
public class Post extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("bid");
		
		if (id == null || id.equals("")) {
			resp.sendRedirect("/freeboard");
		}
		
		PostDAO postdao = new PostDAO();
		postdao.increaseHit(id);
		PostDTO boarddto = postdao.getDetail(id);
		
		CommentDAO commentdao = new CommentDAO();
		ArrayList<CommentDTO> comments = commentdao.get(Integer.valueOf(id));
		
		req.setAttribute("post", boarddto);
		req.setAttribute("comments", comments);
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/section/post.jsp");
		dispatcher.forward(req, resp);
	}
}
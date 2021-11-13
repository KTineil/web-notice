package com.web.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.controller.comment.CommentDAO;
import com.web.dtomodel.CommentDTO;
import com.web.dtomodel.PostDTO;

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
		PostDTO boarddto = postdao.getPostDetail(id);
		
		CommentDAO commentdao = new CommentDAO();
		ArrayList<CommentDTO> comments = commentdao.get(Integer.valueOf(id));
		
		req.setAttribute("post", boarddto);
		req.setAttribute("comments", comments);
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/section/post.jsp");
		dispatcher.forward(req, resp);
	}
}
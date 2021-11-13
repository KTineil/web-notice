package com.web.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dtomodel.PostDTO;

@WebServlet("/updatePost")
public class UpdatePost extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bid = req.getParameter("bid");
		
		PostDAO postdao = new PostDAO();
		PostDTO postdto = postdao.getPostDetail(bid);
		
		req.setAttribute("post", postdto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/section/updatePost.jsp");
		dispatcher.forward(req, resp);
		
	}
}

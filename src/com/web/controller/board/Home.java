package com.web.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dtomodel.PostDTO;

@WebServlet("/home")
public class Home extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PostDAO postdao = new PostDAO();
		ArrayList<PostDTO> posts = postdao.getHomeList();
		
		req.setAttribute("posts", posts);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/section/home.jsp");
		dispatcher.forward(req, resp);
	}
}

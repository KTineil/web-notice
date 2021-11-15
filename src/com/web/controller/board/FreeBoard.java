package com.web.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dtomodel.PostDTO;

@WebServlet("/freeboard")
public class FreeBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sv = req.getParameter("sv");
		int page = req.getParameter("p") != null ? Integer.parseInt(req.getParameter("p")): 1;
		
		PostDAO postdao = new PostDAO();
		List<PostDTO> list = postdao.getPostList(sv, page);
		
		int endOfPage = postdao.getLastPage();
		
		// 값 포워딩
		req.setAttribute("list", list);
		req.setAttribute("endOfPage", endOfPage);
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/section/freeBoard.jsp");
		dispatcher.forward(req, resp);
	}
}
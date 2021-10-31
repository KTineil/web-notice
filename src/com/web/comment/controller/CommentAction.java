package com.web.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/commentAction")
public class CommentAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		Cookie[] cookies = req.getCookies();
		boolean isLogin = checkLogin(cookies);
		if (isLogin) {
			CommentDAO commentdao = new CommentDAO();
			String uid = null;
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("uid"))
					uid = cookie.getValue();
			}
			String content = req.getParameter("commentContent");
			String bid = req.getParameter("bid");
			
			commentdao.insert(content, Integer.valueOf(uid), Integer.valueOf(bid));
			resp.sendRedirect("/post?bid="+bid);
		}
		else {
			resp.sendRedirect("/login.jsp");
		}
		
	}
	private boolean checkLogin(Cookie[] cookies) {
		Cookie[] cooks = cookies;
		
		for (Cookie cookie : cooks) {
			if (cookie.getName().equals("uid"))
				return true;
		}
		
		return false;
	}
}

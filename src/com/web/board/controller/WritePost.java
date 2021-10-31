package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/writePost")
public class WritePost extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		boolean isLogin = checkLogin(cookies);
		
		if (isLogin) {
			resp.sendRedirect("/writePost.jsp");
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

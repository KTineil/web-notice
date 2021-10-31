package com.web.board.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/writePostAction")
public class WritePostAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Cookie[] cookies = req.getCookies();
		String uid_ = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("uid")) {
				uid_ = cookie.getValue();
//				uid_ = URLDecoder.decode(cookie.getValue(), "UTF-8");
			}
		}
		int uid = Integer.valueOf(uid_);
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String files = req.getParameter("files");
		
		WritePostDAO wpdao = new WritePostDAO();
		int bid = wpdao.write(uid, title, content.replace("\r\n", "<br>"), files);
		
		if (bid == -1) {
			//오류
		}
		else {
			resp.sendRedirect("/post?bid="+bid);
		}
	}
}

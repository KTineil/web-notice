package com.web.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.module.LoginDTO;

@WebServlet("/loginAction")
public class LoginAction extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		
		LoginDAO loginDAO = new LoginDAO();
		int isExist = loginDAO.checkUser(email, pwd);
		
		switch (isExist) {
			case 1:
				// 로그인 성공!
				LoginDTO loginDTO = loginDAO.login(email, pwd);
				Cookie uidCook = new Cookie("uid", Integer.toString(loginDTO.getId()));
//				Cookie uidCook = new Cookie("uid", URLEncoder.encode(Integer.toString(loginDTO.getId()), "UTF-8"));
				Cookie emailCook = new Cookie("email", loginDTO.getEmail());
//				Cookie emailCook = new Cookie("email", URLEncoder.encode(loginDTO.getEmail(), "UTF-8"));
				Cookie nameCook = new Cookie("name", loginDTO.getName());
				resp.addCookie(uidCook);
				resp.addCookie(emailCook);
				resp.addCookie(nameCook);
				resp.sendRedirect("/home");
				break;
			case 0:
				// 비밀번호가 틀린 경우
			case -1:
				// 이메일 자체가 없는 경우
				resp.sendRedirect("/login-fail.jsp?fail=" + isExist);
				break;
			case -2: // 에러가 뜬 경우
				resp.sendRedirect("/errorpage.html");
				break;
			default:
				break;
		}
	}
}

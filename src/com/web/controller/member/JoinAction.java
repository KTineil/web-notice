package com.web.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dtomodel.MemberDTO;

@WebServlet("/joinAction")
public class JoinAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDAO memberdao = new MemberDAO();
		req.setCharacterEncoding("UTF-8");
		int isUnique = memberdao.uniqueCheck(req.getParameter("email"));
		
		switch (isUnique) {
		case 1:
			String email = req.getParameter("email");
			String name = req.getParameter("name");
			String pwd = req.getParameter("pwd");
			String gender = req.getParameter("gender");
			MemberDTO memberdto = new MemberDTO(-1, email, pwd, name, gender, null);
			memberdao.join(memberdto);
			resp.sendRedirect("/home");
			break;
		case 0:
			resp.sendRedirect("/join-fail.jsp");
			break;
		case -1:
			resp.sendRedirect("/errorpage.html");
			break;
		default:
			break;
		}
	}
}

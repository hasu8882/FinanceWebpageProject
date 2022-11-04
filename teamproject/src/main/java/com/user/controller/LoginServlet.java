package com.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.dto.MemberVo;
import com.user.dao.MemberDao;



@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		System.out.println(session.getAttribute("loginUser"));
		System.out.println(session);
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("member/login.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String url ="/member/login.jsp";
		
		MemberDao mDao = MemberDao.getInstance();
		
		String userId 	= request.getParameter("id");
		String userPwd 	= request.getParameter("pw");

		int result = mDao.checkUser(userId, userPwd);
		System.out.println("0");

		if(result == 1) {
			url = "main.jsp";
			MemberVo mVo = mDao.getMember(userId);
			System.out.println("1");
			HttpSession session = request.getSession();	
			session.setAttribute("loginUser", mVo);
			session.setAttribute("id", userId);
			System.out.println(session.getAttribute("loginUser"));
			System.out.println(session);
		}else if (result == 0) {
			System.out.println("비밀번호 불일치");
		}else {
			url = "member/login.jsp";
		}
		
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

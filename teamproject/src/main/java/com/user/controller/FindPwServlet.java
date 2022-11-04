package com.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.MemberDao;

@WebServlet("/findpw.do")
public class FindPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/findpw.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		String id = request.getParameter("id");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String email = request.getParameter("email");
		MemberDao mDao = MemberDao.getInstance();
		String resultpw = mDao.findpw(id,phone1,phone2,phone3,email);
		System.out.println(resultpw);
		if(resultpw != null) {
			request.setAttribute("message", "당신의 pw 는  "+resultpw);
			
		}else {
			request.setAttribute("message", "일치하는 조건의 유저가 없습니다.");
		}
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("member/findok.jsp");
		dispatcher.forward(request, response);
	}

}

package com.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.MemberDao;


@WebServlet("/checkId.do")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userId 	= request.getParameter("id");	
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.confirmID(userId);
		
		if(result==1) {
			request.setAttribute("message", "아이디 사용불가능");
		}else {
			request.setAttribute("message", "아이디 사용가능");
		}
		
		request.setAttribute("id", userId);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/checkId.jsp");
		dispatcher.forward(request, response);
			
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		}

}

package com.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.user.dao.MemberDao;
import com.user.dto.MemberVo;

@WebServlet("/adminUpdate.do")
public class AdminUpdateMemberServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id");
		MemberDao mDao = MemberDao.getInstance();
		MemberVo mVo = new MemberVo();

		mVo = mDao.getMember(id);
		request.setAttribute("member", mVo);
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("admin/adminUpdate.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		MemberDao mDao = MemberDao.getInstance();
		
		String id = request.getParameter("id");
		int pay = Integer.parseInt(request.getParameter("pay"));
		int admin = Integer.parseInt(request.getParameter("admin"));
		String name = request.getParameter("name");
		int result = mDao.UpdateaAdmin(id, name, pay, admin);
		if (result == 1) {
			System.out.println("good");
		} else {
			System.out.println("bad");
		}
		
		
		response.sendRedirect("membermanage.do");

	}

}

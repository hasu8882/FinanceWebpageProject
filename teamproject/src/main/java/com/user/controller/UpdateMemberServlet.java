package com.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.dao.MemberDao;
import com.user.dto.MemberVo;

@WebServlet("/update.do")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String userId 	= request.getParameter("id");	
		MemberDao mDao = MemberDao.getInstance();
		MemberVo mVo = mDao.getMember(userId);
		
		request.setAttribute("loginuser", mVo);
		HttpSession session = request.getSession();
		System.out.println(session);
		System.out.println(session.getAttribute("loginUser")); 
			
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("member/updateMember.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		int pay = Integer.parseInt(request.getParameter("pay"));
		System.out.println(pay);
		MemberDao mDao = MemberDao.getInstance();
		MemberVo mVo = new MemberVo();
		mVo.setId(id);
		mVo.setName(name);
		mVo.setPw(pw);
		mVo.setEmail(email);
		mVo.setPhone1(phone1);
		mVo.setPhone2(phone2);
		mVo.setPhone3(phone3);
		mVo.setPay(pay);
		
		int result = mDao.UpdateMember(mVo);
		if (result == 1) {
			System.out.println("업데이트 성공");
			HttpSession session = request.getSession();	
			session.setAttribute("loginUser", mVo);
			System.out.println(session.getAttribute("loginUser"));
			RequestDispatcher dispatcher;
			dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
		}else {
			System.out.println("업데이트 실패");
		}
//		RequestDispatcher dispatcher = request.getRequestDispatcher("login.do");
//		dispatcher.forward(request, response);
		response.sendRedirect("update.do");
	}

}

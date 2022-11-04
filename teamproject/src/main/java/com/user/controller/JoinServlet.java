package com.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dto.MemberVo;
import com.user.dao.MemberDao;


@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/join.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pw");
		int pay = Integer.parseInt(request.getParameter("pay"));
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String email = request.getParameter("email");
		int gender = Integer.parseInt(request.getParameter("gender"));
		
		MemberDao mDao = MemberDao.getInstance();
		MemberVo mVo = new MemberVo();
		mVo.setName(name);
		mVo.setId(id);
		mVo.setPw(pwd);
		mVo.setPay(pay);
		mVo.setPhone1(phone1);
		mVo.setPhone2(phone2);
		mVo.setPhone3(phone3);
		mVo.setEmail(email);
		mVo.setGender(gender);
		
		int result = mDao.insertMember(mVo);
		
		
		if(result == 1){ // 성공
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
		} else{ // 실패
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/join.jsp");
			dispatcher.forward(request, response);
		}
		

		
	}

}
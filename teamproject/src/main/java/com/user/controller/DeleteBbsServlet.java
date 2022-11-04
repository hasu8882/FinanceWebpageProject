package com.user.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.BbsDao;
import com.user.dto.BbsVo;

@WebServlet("/deleteBbs.do")
public class DeleteBbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String code = request.getParameter("code");
	
		
		BbsDao bDao = BbsDao.getInstance();
		BbsVo bVo = new BbsVo();
		
		bVo = bDao.selectbbsByCode(code);
		
		request.setAttribute("bbs", bVo);
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("bbs/updateBbs.jsp");
		dispatcher.forward(request, response);	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsDao bDao = BbsDao.getInstance();
		BbsVo bVo = new BbsVo();
		
		
		String code = request.getParameter("code");
	
		int result = bDao.deletebbs(code);
		
		if (result==1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
		response.sendRedirect("bbsList.do");
	}

}

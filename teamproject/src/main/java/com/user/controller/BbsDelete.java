package com.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.BbsDao;
import com.user.dto.BbsVo;


@WebServlet("/bbsDelete.do")
public class BbsDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsDao bDao = BbsDao.getInstance();
		BbsVo bVo = new BbsVo();
		int result = -1;
		String code = request.getParameter("code");
		
		// 데이터베이스로 부터 해당 코드의 정보를 삭제
		result = bDao.deletebbs(code);
		System.out.println(result);
		// 삭제 후 목록 페이지로 이동
		response.sendRedirect("bbsList.do");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

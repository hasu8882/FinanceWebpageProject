package com.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.StockDao;

/**
 * Servlet implementation class ModificationAsset
 */
@WebServlet("/updateAsset.do")
public class UpdateAsset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("finance/updateAsset.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		// post 방식 한글처리
		response.setContentType("text/html; charset=UTF-8");
		
		//1. stDap 객체 생성
		StockDao stDao = StockDao.getInstance();
		String userid = request.getParameter("userid");
		String code = request.getParameter("code");
		System.out.println("아이디: "+ userid);
		System.out.println("수량: "+ request.getParameter("qty"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		//2. 수저 함수 호출
		
		int result = stDao.updateAsset(userid, code,  qty);
		//3. 수량 수정 결과 리턴
		
		if (result == -1) {
			System.out.println("업데이트 실패");
		} else {
			System.out.println("업데이트 성공");
		}
		//4. 홈화면으로 이동		
		response.sendRedirect("scrape.do");
	}

}

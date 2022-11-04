package com.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.StockDao;

/**
 * Servlet implementation class DeleteAsset
 */
@WebServlet("/deleteAsset.do")
public class DeleteAsset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//삭제할 자산 정보를 불러옴
		String userid = request.getParameter("userid");
		String code= request.getParameter("code");
		
		//DAO에서 delete 함수 호출
		StockDao stDao = StockDao.getInstance();
		int result = stDao.deleteAsset(userid, code);
		//성공 여부 반환
		
		if (result == -1) {
			System.out.println("삭제 실패");
		} else {
			System.out.println("석제 성공");
		}
		
		response.sendRedirect("scrape.do");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

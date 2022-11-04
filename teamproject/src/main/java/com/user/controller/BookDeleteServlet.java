package com.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.dao.BookDao;
import com.user.dto.IncomeVo;
import com.user.dto.OutlayVo;

@WebServlet("/bookDelete.do")
public class BookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		response.setContentType("text/html; charset=UTF-8");
				
		String url = "book/account_book.jsp";
		
		String id = (String)request.getParameter("id");
		String num = (String)request.getParameter("num");	
		String div = (String)request.getParameter("div");	// 지출, 수입 테이블 구분하기 위해 받아온 값
		
		BookDao bookDao = new BookDao();
		
		// 세션 설정
		HttpSession session = request.getSession();			// 생성된 세션 객체 호출
		
		if(session.getAttribute("loginUser") == null) {		// 세션에 로그인한 회원 정보가 저장되어 있는지 확인 
			url = "main.jsp";
		}
		
		if(div.equals("1")) {
			OutlayVo outlayVo = new OutlayVo();
			 
			outlayVo.setId(id);
			outlayVo.setNum(Integer.parseInt(num));
			 
			int result = 0;
			 
			try {
				result = bookDao.deleteOutlay(outlayVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(result > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
			 
		}else if(div.equals("2")){
			IncomeVo incomeVo = new IncomeVo();
			
			incomeVo.setId(id);
			incomeVo.setNum(Integer.parseInt(num));
			
			int result = 0;
			
			try {
				result = bookDao.deleteIncome(incomeVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(result > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		}
		
		// 페이지 이동(forward 방식)
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request,response);		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}

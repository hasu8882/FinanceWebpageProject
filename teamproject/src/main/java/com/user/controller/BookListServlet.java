package com.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.dao.BookDao;
import com.user.dto.IncomeVo;
import com.user.dto.MemberVo;
import com.user.dto.OutlayVo;

@WebServlet("/bookList.do")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");		
		response.setContentType("text/html; charset=UTF-8");		
		
		String url = "book/account_book.jsp";
		
		String div = (String)request.getParameter("div");	// 지출, 수입 테이블 구분하기 위해 받아온 값
		String ymd = (String)request.getParameter("ymd");	// 현재 선택한 날짜로 데이터를 조회하기 위해서 받아온 값
		
		BookDao bookDao = new BookDao();
		
		// 세션 설정
		HttpSession session = request.getSession();			// 생성된 세션 객체 호출
				
		if(session.getAttribute("loginUser") == null) {		// 세션에 로그인한 회원 정보가 저장되어 있는지 확인 
			url = "main.jsp";
		}else {
			MemberVo memberVo = (MemberVo)session.getAttribute("loginUser");
			request.setAttribute("memberVo", memberVo);
		
		// 날짜별 지출, 수입 조회
		if(ymd != null) {			
			if(div != null) {
				if(div.equals("1")) {
					OutlayVo outlayVo = new OutlayVo();
					outlayVo.setDate1(ymd);
					outlayVo.setId(memberVo.getId());
					
					List<OutlayVo> outlayList = bookDao.outlayList(outlayVo);
					
					request.setAttribute("outlayList", outlayList);
				}else if(div.equals("2")) {
					IncomeVo incomeVo = new IncomeVo();
					incomeVo.setDate2(ymd);
					incomeVo.setId(memberVo.getId());
					
					List<IncomeVo> incomeList = bookDao.incomeList(incomeVo);
					
					request.setAttribute("incomeList", incomeList);
				}
			}
		}
		
		// 페이지 이동(forward 방식)
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request,response);	
	}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}

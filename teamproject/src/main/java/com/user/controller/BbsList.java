package com.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.BbsDao;
import com.user.dto.BbsVo;


@WebServlet("/bbsList.do")
public class BbsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsDao bDao = BbsDao.getInstance();
		BbsVo bVo = new BbsVo();
		
		// 기본 값 설정 (검색대상:column, 검색내용:keyword, 페이지:page)
		String column = "code";	// 검색 대상(분야)
		String keyword = "";	// 검색 내용(검색어)
		int page = 1;
		
		// 검색 입력 양식으로 부터 받은 검색 대상과 내용을 가져옴
		String t_column = request.getParameter("column");
		String t_keyword = request.getParameter("keyword");		
		String t_page = request.getParameter("p");			// page
		
		// 검색 대상, 내용, 페이지가 null이거나 ""이 아니라면, 사용
		if(t_column != null && !t_column.equals("")) {
			column = t_column;
		}
		if(t_keyword != null && !t_keyword.equals("")) {
			keyword = t_keyword;			
		}
		if(t_page != null && !t_page.equals("")) {
			page = Integer.parseInt(t_page);
		}
		List<BbsVo> bbslist = bDao.getBbsList(column, keyword, page);		// 모든 행 데이터 표시
		int count = bDao.getBbsCount(column, keyword);
		
		request.setAttribute("bbslist", bbslist);
		request.setAttribute("count", count);
		String url ="bbs/bbsList.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);	
	
	} 

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

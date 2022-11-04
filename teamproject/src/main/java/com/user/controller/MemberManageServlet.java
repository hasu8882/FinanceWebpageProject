package com.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.BbsDao;
import com.user.dao.MemberDao;
import com.user.dto.BbsVo;
import com.user.dto.MemberVo;


@WebServlet("/membermanage.do")
public class MemberManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberDao mDao =MemberDao.getInstance();
		MemberVo mVo = new MemberVo();
		
	
		
		// 기본 값 설정 (검색대상:column, 검색내용:keyword, 페이지:page)
		String column = "id";	// 검색 대상(분야)
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
		List<MemberVo> memberlist = mDao.getMemberList(column, keyword, page);
		int count = mDao.getmemberCount(column, keyword);
		
		request.setAttribute("memberlist", memberlist);
		request.setAttribute("count", count);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/membermanagement.jsp");
		dispatcher.forward(request, response);
	
	} 

	
			
		
	


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		}

}

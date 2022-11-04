package com.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.user.dao.MemberDao;
import com.user.dto.MemberVo;


@WebServlet("/deleteAdmin.do")
public class DeleteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
	
		
		MemberDao mDao = MemberDao.getInstance();
		MemberVo mVo = new MemberVo();
		
		mVo = mDao.getMember(id);
		
		request.setAttribute("member", mVo);
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("admin/deleteAdmin.jsp");
		dispatcher.forward(request, response);
			
}

protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	MemberDao mDao = MemberDao.getInstance();
	MemberVo mVo = new MemberVo();
	mVo.setId(id);
	mVo.setPw(pw);
	int result = mDao.DeleteMember(mVo);
	
	System.out.println(result);
	if(result == 1) {
		System.out.println("탈퇴 처리됨");
		response.sendRedirect("membermanage.do");
	}else {
		System.out.println("탈퇴 안됨");
		response.sendRedirect("adminUpdate.do");
	}
	
}

}

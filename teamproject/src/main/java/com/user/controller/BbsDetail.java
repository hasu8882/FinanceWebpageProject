package com.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.user.dao.BbsDao;
import com.user.dao.replyDao;
import com.user.dto.BbsVo;
import com.user.dto.ReplyVo;


@WebServlet("/bbsDetail.do")
public class BbsDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String code = request.getParameter("code");
		
//		System.out.println(0);
		BbsDao bDao = BbsDao.getInstance();
		BbsVo bVo = new BbsVo();
//		System.out.println(1);
		replyDao rDao = replyDao.getInstance();
		ReplyVo rVo = new ReplyVo();
//		System.out.println(2);
		bVo = bDao.selectbbsByCode(code);
//		System.out.println(3);
		List<ReplyVo> replylist = rDao.getreplyList(code);
//		System.out.println(replylist);
		request.setAttribute("bbs", bVo);
		request.setAttribute("replylist", replylist);
		
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("bbs/bbsDetail.jsp");
		dispatcher.forward(request, response);		
	
    
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		int code = Integer.parseInt(request.getParameter("code"));
		String code2=request.getParameter("code");
		String id = request.getParameter("id");
		String content = request.getParameter("content");
	
		replyDao rDao = replyDao.getInstance();
		ReplyVo rVo = new ReplyVo();
		
		rVo.setCode(code);

		rVo.setId(id);
		rVo.setContent(content);
		System.out.println("detail.do");
		int result = rDao.insertreply(rVo);
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("detail.do?code="+code2);
		dispatcher.forward(request, response);	
	
	}

}

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
import com.user.dao.replyDao;
import com.user.dto.BbsVo;
import com.user.dto.ReplyVo;


@WebServlet("/updatereply.do")
public class UpdateReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comment_code = request.getParameter("comment_code");	

		replyDao rDao = replyDao.getInstance();
		ReplyVo rVo = new ReplyVo();

		rVo = rDao.getreply(comment_code);
//		System.out.println(replylist);
		request.setAttribute("reply", rVo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("bbs/updatecomment.jsp");
		dispatcher.forward(request, response);}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String code = request.getParameter("code");	
		String comment_code = request.getParameter("comment_code");	
		String id = request.getParameter("id");	
		String content = request.getParameter("content");	
		
		replyDao rDao = replyDao.getInstance();
		rDao.updatereply(id, content, comment_code);
		
		System.out.println(0);
		BbsDao bDao = BbsDao.getInstance();
		BbsVo bVo = new BbsVo();

		bVo = bDao.selectbbsByCode(code);
		
		List<ReplyVo> replylist = rDao.getreplyList(code);
		
		request.setAttribute("bbs", bVo);
		request.setAttribute("replylist", replylist);

		String url = "bbs/bbsDetail.jsp?code=" + code;
		RequestDispatcher dispatcher;

		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	
	}

}

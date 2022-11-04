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

@WebServlet("/replyDelete.do")
public class ReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comment_code = request.getParameter("comment_code");	
		replyDao rDao = replyDao.getInstance();
		rDao.deletereply(comment_code);
		
		String code = request.getParameter("code");	
		
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}

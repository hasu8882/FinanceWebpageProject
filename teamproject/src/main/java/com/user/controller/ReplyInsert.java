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

@WebServlet("/replyinsert.do")
public class ReplyInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("replyinsert1");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		int code = Integer.parseInt(request.getParameter("code"));
		String code2 = request.getParameter("code");
		String id = request.getParameter("id");
		String content = request.getParameter("content");

		replyDao rDao = replyDao.getInstance();
		ReplyVo rVo = new ReplyVo();
		

		rVo.setCode(code);

		rVo.setId(id);
		rVo.setContent(content);
//		System.out.println("replyinsert");
		int result = rDao.insertreply(rVo);

		System.out.println("code2:" + code2);
		System.out.println("id:" + id);

		System.out.println(0);
		BbsDao bDao = BbsDao.getInstance();
		BbsVo bVo = new BbsVo();
		System.out.println(1);
		rVo = new ReplyVo();
		System.out.println(2);
		bVo = bDao.selectbbsByCode(code2);
		System.out.println(3);
		List<ReplyVo> replylist = rDao.getreplyList(code2);
		request.setAttribute("bbs", bVo);
		request.setAttribute("replylist", replylist);

		String url = "bbs/bbsDetail.jsp?code=" + code2;
		RequestDispatcher dispatcher;

		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		

	}

}

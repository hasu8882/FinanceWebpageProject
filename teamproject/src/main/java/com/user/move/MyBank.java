package com.user.move;

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
import com.user.dto.SumVo;


@WebServlet("/mybank.do")
public class MyBank extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		response.setContentType("text/html; charset=UTF-8");	
		
		
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("member/mybank.jsp");
		dispatcher.forward(request, response);
	}

}
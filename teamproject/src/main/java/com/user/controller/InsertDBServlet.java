package com.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.ScrapeDao;
import com.user.dao.StockDao;
import com.user.dto.ETFVo;
import com.user.dto.StockVo;

/**
 * Servlet implementation class InsertDBServlet
 */
@WebServlet("/insertDB.do")
public class InsertDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utr-8");
		PrintWriter writer = response.getWriter();
		
		ScrapeDao sdao = ScrapeDao.getInstance();		
		List<StockVo> kospiList = sdao.getStockList(0, "2022-10-07");
		List<StockVo> kosdaqList = sdao.getStockList(1, "2022-10-07");
		List<ETFVo> etfList = sdao.getETFList("2022-10-07");
		StockDao stDao = StockDao.getInstance();
		//kospi와 kosdaq array 합침
		kospiList.addAll(kosdaqList);
		int check = -1;
		
		
		check = stDao.insertStock(kospiList);

		
		check = stDao.insertETF(etfList);
		if (check == -1) {
			System.out.println("etf DB 등록 실패");
		} else {
			System.out.println("etf DB 등록 성공");
		}
		
//		writer.println("<h2> etf 스크래핑 결과 확인 </h2>");
//		writer.println("<br>");
//		for (int i=1;i< etfList .size();i++) {
//			writer.print("Company: "+etfList .get(i).getName()+"\t");
//			writer.print("Code: "+etfList .get(i).getCode()+"\t");
//			writer.print("Price: "+etfList .get(i).getPrice());
//			writer.println("<br>");
//
//		}
		
		response.sendRedirect("stockList.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.user.dao.ScrapeDao;
import com.user.dao.StockDao;
import com.user.dto.MyAssetVo;

/**
 * Servlet implementation class ScrapeServlet
 */
@WebServlet("/scrape.do")
public class StockIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utr-8");
//		String company = request.getParameter("company");
//		System.out.println("company: " + company);
//		Elements[] scrapeLists = new Elements[5];
//		scrapeLists = ScrapeData.getFinanceData();
		ScrapeDao sdao = ScrapeDao.getInstance();
		List<Elements> indexList = sdao.getFinanceIndex();
//		System.out.println("index : " +  indexList.get(0).text());
		PrintWriter writer = response.getWriter();

//		writer.println("<h2> 스크래핑 결과 확인 </h2>");
//		writer.println("<br>");
//		for (Element e:indexList.get(0)) {
//			writer.println("news: "+ e.text());
//			writer.println("<br>");
//
//		}		
//		
		request.setAttribute("indexList", indexList);

		// myasset
		StockDao stDao = StockDao.getInstance();
		List<MyAssetVo> mlist = stDao.selectMyAsset();
		request.setAttribute("myasset", mlist);
		/*
		 * writer.println("<h2> DB조회 확인 </h2>"); writer.println("<br>"); for (MyAssetVo
		 * m:mlist) { writer.println("name: "+ m.getName()); writer.println("<br>");
		 * writer.println("code: "+ m.getCode());
		 * 
		 * }
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("finance/financeHome.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

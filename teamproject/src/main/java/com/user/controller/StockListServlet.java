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

import com.user.dao.StockBoardDao;
import com.user.dto.ETFVo;
import com.user.dto.FactorVo;
import com.user.dto.StockVo;

/**
 * Servlet implementation class StockListServlet
 */
@WebServlet("/stockList.do")
public class StockListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utr-8");
		PrintWriter writer = response.getWriter();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utr-8");
	
        String market ="KOSPI";
        String keyword = "";
        int page=1;
        
        float perLower=0.0f;
        float perUpper=0.0f;
        float pbrLower=0.0f;
        float pbrUpper=0.0f;
        float divYield=0.0f;

        //키워드가 비어 있는 경우를 대비하여 컬럼과 키워드 값 임시 저장
        String t_market = request.getParameter("market");
        String t_keyword = request.getParameter("keyword");
        String t_page = request.getParameter("p");
        
        String t_perL = request.getParameter("per_lower");
        String t_perU = request.getParameter("per_upper");
        String t_pbrL = request.getParameter("pbr_lower");
        String t_pbrU =  request.getParameter("pbr_upper");
        String t_divYield = request.getParameter("div_yield");
        //null값이 아닌 경우
        if(t_market != null)
            market = t_market;

        if(t_keyword != null)
            keyword = t_keyword;
        if (t_page !=null && !t_page.equals("")){
			page = Integer.parseInt(t_page);
		}
//        System.out.println("page: " +page);
		//select all form oracle dB
        //팩터 객체 생성
        FactorVo fVo = new FactorVo();
        if (t_perL != null && !t_perL.equals("")) {
        	perLower = Float.parseFloat(t_perL);
        	fVo.setPerL(perLower);
        }
        if (t_perU != null && !t_perU.equals("")) {
        	perUpper = Float.parseFloat(t_perU);
        	fVo.setPerU(perUpper);
        }
        if (t_pbrL != null && !t_pbrL.equals("")) {
        	pbrLower = Float.parseFloat(t_pbrL);
        	fVo.setPbrL(pbrLower);
        }
        if (t_pbrU != null && !t_pbrU.equals("")) {
        	pbrUpper = Float.parseFloat(t_pbrU);
        	fVo.setPbrU(pbrUpper);
        }        
        
        if (t_divYield != null && !t_divYield.equals("")) {
        	divYield = Float.parseFloat(t_divYield);
        	fVo.setDivYield(divYield);
        }
//        System.out.println(fVo.getPerL());
//        System.out.println(fVo.getPerU());
//        System.out.println(fVo.getPbrL());
//        System.out.println(fVo.getPbrU());
//        System.out.println(fVo.getDivYield());
        
		StockBoardDao sDao = StockBoardDao.getInstance();
		
		Object assetList;
		Object assetCount;
		if( market.equals("ETF") ){			
			assetList =(List<ETFVo>)sDao.searchETFListByName(keyword,page);
			assetCount =(int) sDao.getETFCount(keyword);
			
		} else {
			assetList =(List<StockVo>)sDao.searchStockListByName(market,keyword,page,fVo);
			assetCount = (int)sDao.getStockCount(market,keyword,fVo);
//			System.out.println("게시물 수:"+ assetCount);
			
		}
		
		
		
		

//		System.out.println(etfList.get(0).getName());
//		System.out.println(etfList.get(1).getName());
//		writer.println("<h2> DB 조회확인 </h2>");
//		writer.println("<br>");
//		for (int i=0;i< etfList.size();i++) {
//			writer.println("Company: "+etfList.get(i).getName());
//			writer.println("<br>");
//
//		}
//		writer.close();
		

		request.setAttribute("market", market);	
		request.setAttribute("assetList", assetList );		
		request.setAttribute("assetCount",assetCount);
		RequestDispatcher dispatcher = request.getRequestDispatcher("finance/stockList.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

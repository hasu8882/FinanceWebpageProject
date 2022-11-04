package com.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.StockDao;
import com.user.dto.AssetVo;

/**
 * Servlet implementation class SearchAssetServlet
 */
@WebServlet("/searchAsset.do")
public class SearchAssetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//기본값 설정		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utr-8");
//		PrintWriter writer = response.getWriter();		
//        String market ="kospi";
//        String field = "code";
        String keyword = "";

        //키워드가 비어 있는 경우를 대비하여 컬럼과 키워드 값 임시 저장
//        String t_market = request.getParameter("market");
//        String t_field = request.getParameter("field");
        String t_keyword = request.getParameter("keyword");
        //null값이 아닌 경우
//        if(t_market != null)
//            market = t_market;
//        if(t_field != null)
//            field = t_field;
        if(t_keyword != null)
            keyword = t_keyword;

//        System.out.println("시장 : " +market);
//        System.out.println("필드: " + field);
//        System.out.println("키워드 값: " +keyword);
//        

//
//
//		writer.println("<h2> 스크래핑 결과 확인 </h2>");
//		writer.println("<br>");
//   
//		writer.println("market: "+t_market);
//		writer.println("<br>");
//		writer.println("field: "+t_field);
//		writer.println("<br>");</h2>");
//		writer.println("<br>");
   //   
//   		writer.println("market: "+t_market);
//   		writer.println("<br>");
//   		writer.println("field: "+t_field);
//   		writer.println("<br>");
//		writer.println("keyword: "+t_keyword);
//		
//		writer.close(); 
        
        
        //single tone 객체 생성
        StockDao sDao = StockDao.getInstance();
        // 넘겨온 매개변수로 자산 조회
        
        //1. etf 조회, by code        
        
        List<AssetVo> assetList = sDao.searchAssetbyName(keyword);
//		writer.println("<h2>  etf 조회확인 </h2>");
//		writer.println("<br>");
//		for (int i=0;i< list.size();i++) {
//			writer.print("asset name "+list.get(i).getName());
//			writer.print("&nbsp;");
//			writer.print("asset price "+list.get(i).getPrice());
//			writer.println("<br>");
//
//		}
//		writer.close();
        
      request.setAttribute("assetList", assetList);
      //포워드 바익으로 페이지 이동
      request.getRequestDispatcher("finance/addAsset.jsp").forward(request, response); 
        

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //기본값 설정		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utr-8");
		
//        String market ="kospi";
//        String field = "code";
//        String keyword = "";

        //키워드가 비어 있는 경우를 대비하여 컬럼과 키워드 값 임시 저장
//        String t_market = request.getParameter("market");
//        String t_field = request.getParameter("field");
//        String t_keyword = request.getParameter("keyword");
        //null값이 아닌 경우
//        if(t_market != null)
//            market = t_market;
//        if(t_field != null)
//            field = t_field;
//        if(t_keyword != null)
//            keyword = t_keyword;

//        System.out.println("시장 : " +market);
//        System.out.println("필드: " + field);
//        System.out.println("키워드 값: " +keyword);
        
//		PrintWriter writer = response.getWriter();		
//
//
//		writer.println("<h2> 스크래핑 결과 확인 </h2>");
//		writer.println("<br>");
//   
//		writer.println("market: "+t_market);
//		writer.println("<br>");
//		writer.println("field: "+t_field);
//		writer.println("<br>");
//		writer.println("keyword: "+t_keyword);
//		
//		writer.close();       
        

//        request.setAttribute("productList", productList);
        //포워드 바익으로 페이지 이동
//        request.getRequestDispatcher("product/productList.jsp").forward(request, response);
	}

}

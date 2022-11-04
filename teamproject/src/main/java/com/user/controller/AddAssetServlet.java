package com.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.StockDao;
import com.user.dto.AssetVo;
import com.user.dto.MyAssetVo;

/**
 * Servlet implementation class AddAssetServlet
 */
@WebServlet("/addAsset.do")
public class AddAssetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("finance/addAsset.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");		// post 방식 한글처리
		response.setContentType("text/html; charset=UTF-8");
		
		//1. sDap 객체 생성
		StockDao stDao = StockDao.getInstance();
		//2. 매개변수 수집
		String[] codes = request.getParameterValues("code");
		String[] qty = request.getParameterValues("qty");
		String id = request.getParameter("id");
	
		//3. code로 자산 불러와서 나의 자산 객체에 리스트에 저장
		
		List<MyAssetVo> mlist = new ArrayList<MyAssetVo>();
		for (String codeIdx:codes) {
			String[] str = codeIdx.split(",");			
			String code = str[0];
			int idx = Integer.parseInt(str[1])-1;
			AssetVo aVo= new AssetVo();			
			aVo = stDao.searchByCode(code);
			MyAssetVo mVo= new MyAssetVo();
			mVo.setUserid(id); //userid
			mVo.setCode(code);
			mVo.setName(aVo.getName());
			mVo.setPrice(aVo.getPrice());
			mVo.setLast_update(aVo.getLast_update());
			//add qty
			mVo.setQty(Integer.parseInt(qty[idx]));
			//add amount	
			mVo.setTot_amount();			
			mlist.add(mVo);			
		}
		
		//insert DB
		int result = stDao.addAsset(mlist);
		
		if (result == 1) {
			System.out.println("자산 등록 성공");
			response.sendRedirect("scrape.do");
		} else { 
			System.out.println("자산 등록 실패");
			
		}
		
		
//		PrintWriter writer = response.getWriter();		
//		writer.println("<h2> 전달값 확인 </h2>");
//		writer.println("<br>");
//		for (int i=0;i< mlist.size();i++) {
//			writer.println("<br>");
//			writer.println("code : "+ mlist.get(i).getCode());
//			writer.println("name : "+ mlist.get(i).getName());
//			writer.println("amount : "+ mlist.get(i).getTot_amount());
//			writer.println("date : "+ mlist.get(i).getLast_update());
//			writer.println("<br>");
//
//		}		
		
//		String[] check = request.getParameterValues("choice");
//		String[] qty = request.getParameterValues("qty");
//		PrintWriter writer = response.getWriter();
//		
//		writer.println("<h2> 전달값 확인 </h2>");
//		writer.println("<br>");
//		for (int i=0;i< check.length;i++) {
//			writer.println("check value: "+ check[i]);
//			writer.println("<br>");
//
//		}
//		
//		for (int i=0;i< qty.length;i++) {
//			writer.println("qty: "+ qty[i]);
//			writer.println("<br>");
//
//		}
		
	}

}

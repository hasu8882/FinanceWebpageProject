package com.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.BookDao;
import com.user.dto.IncomeVo;
import com.user.dto.OutlayVo;

@WebServlet("/bookUpdate.do")
public class BookUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");		
		response.setContentType("text/html; charset=UTF-8");
		
		String url = "/teamproject/bookList.do";		
		
		String div = (String)request.getParameter("div");	// 지출, 수입 테이블 구분하기 위해 받아온 값		
		
		BookDao bookDao = new BookDao();
		
		if(div.equals("1")) {
			OutlayVo outlayVo = new OutlayVo();
			
			// 지출 테이블 입력 값
			String id = request.getParameter("id");
			String num = request.getParameter("num");
			String old_num = request.getParameter("old_num");
			String use1 = request.getParameter("use1");
			String cash = request.getParameter("cash");
			String card = request.getParameter("card");
			String used_disposal = request.getParameter("used_disposal");
			String date1 = request.getParameter("date1");
			String memo1 = request.getParameter("memo1");
			
			int result = 0;
			
			outlayVo.setId(id);
			outlayVo.setNum(Integer.parseInt(num));
			outlayVo.setOld_num(Integer.parseInt(old_num));
			outlayVo.setUse1(use1);
			outlayVo.setCash(Integer.parseInt(cash));
			outlayVo.setCard(Integer.parseInt(card));
			outlayVo.setUsed_disposal(used_disposal);
			outlayVo.setDate1(date1);
			outlayVo.setMemo1(memo1);
			
			
			try {
				result = bookDao.updateOutlay(outlayVo);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			if(result > 0) {
				System.out.println("수정 성공");
				url = "/teamproject/bookList.do?ymd="+date1+"&div="+div+"#tab"+div;
			}else {
				System.out.println("수정 실패");
			}
		}else if(div.equals("2")) {
			IncomeVo incomeVo = new IncomeVo();
			
			// 수입 테이블 입력 값
			String id = request.getParameter("id");
			String num = request.getParameter("num");
			String old_num = request.getParameter("old_num");
			String use2 = request.getParameter("use2");
			String amount = request.getParameter("amount");
			String bankbook = request.getParameter("bankbook");
			String import_disposal = request.getParameter("import_disposal");
			String date2 = request.getParameter("date2");
			String memo2 = request.getParameter("memo2");
			
			int result = 0;
			
			incomeVo.setId(id);
			incomeVo.setNum(Integer.parseInt(num));
			incomeVo.setOld_num(Integer.parseInt(old_num));
			incomeVo.setUse2(use2);
			incomeVo.setAmount(Integer.parseInt(amount));
			incomeVo.setBankbook(bankbook);
			incomeVo.setImport_disposal(import_disposal);
			incomeVo.setDate2(date2);
			incomeVo.setMemo2(memo2);
			
			try {
				result = bookDao.updateIncome(incomeVo);
			} catch (Exception e) {
				result = -1;
				e.printStackTrace();
			}
			
			if(result > 0) {
				System.out.println("수정 성공");
				url = "/teamproject/bookList.do?ymd="+date2+"&div="+div+"#tab"+div;
			}else {
				System.out.println("수정 실패");
			}
		}
		
		response.sendRedirect(url);
	}
	

}

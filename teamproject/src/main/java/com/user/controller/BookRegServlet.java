package com.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.dao.BookDao;
import com.user.dto.IncomeVo;
import com.user.dto.MemberVo;
import com.user.dto.OutlayVo;

@WebServlet("/bookReg.do")
public class BookRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		 
		response.setContentType("text/html; charset=UTF-8");
		
		// 세션 설정
		HttpSession session = request.getSession();			// 생성된 세션 객체 호출
		
		String url = "/teamproject/bookList.do";
		
		String div = (String)request.getParameter("div");	// 지출, 수입 테이블 구분하기 위해 받아온 값
		
		BookDao bookDao = new BookDao();		
		MemberVo memberVo = (MemberVo)session.getAttribute("loginUser");		

		if(div.equals("1")) {
			OutlayVo outlayVo = new OutlayVo();
			
			// 지출 동적 테이블 JS 입력 값
			String num[] = request.getParameterValues("num_reg");
			String use1[] = request.getParameterValues("use1_reg");
			String cash[] = request.getParameterValues("cash_reg");
			String card[] = request.getParameterValues("card_reg");
			String used_disposal[] = request.getParameterValues("used_disposal_reg");
			String date1[] = request.getParameterValues("date1_reg");
			String memo1[] = request.getParameterValues("memo1_reg");
			
			int result = 0;
			
			for(int i=0; i<num.length; i++) {
				outlayVo.setId(memberVo.getId());
				outlayVo.setNum(Integer.parseInt(num[i]));
				outlayVo.setUse1(use1[i]);
				outlayVo.setCash(Integer.parseInt((cash[i] == null || cash[i] == "") ? "0" : cash[i]));
				outlayVo.setCard(Integer.parseInt((card[i] == null || card[i] == "") ? "0" : card[i]));
				outlayVo.setUsed_disposal(used_disposal[i]);
				outlayVo.setDate1(date1[i]);
				outlayVo.setMemo1(memo1[i]);
				
				try {
					result = bookDao.insertOutlay(outlayVo);
				} catch (Exception e) {
					result = -1;
					e.printStackTrace();
				}
			}
			
			if(result > 0) {
				System.out.println("등록 성공");
				url = "/teamproject/bookList.do?ymd="+date1[0]+"&div="+div+"#tab"+div;
			}else {
				System.out.println("등록 실패");
			}
		}else if(div.equals("2")) {
			IncomeVo incomeVo = new IncomeVo();
			
			// 수입 동적 테이블 JS 입력 값
			String num[] = request.getParameterValues("num_reg");
			String use2[] = request.getParameterValues("use2_reg");
			String amount[] = request.getParameterValues("amount_reg");
			String bankbook[] = request.getParameterValues("bankbook_reg");
			String import_disposal[] = request.getParameterValues("import_disposal_reg");
			String date2[] = request.getParameterValues("date2_reg");
			String memo2[] = request.getParameterValues("memo2_reg");
			
			int result = 0;
			
			for(int i=0; i<num.length; i++) {
				incomeVo.setId(memberVo.getId());
				incomeVo.setNum(Integer.parseInt(num[i]));
				incomeVo.setUse2(use2[i]);
				incomeVo.setAmount(Integer.parseInt(amount[i]));
				incomeVo.setBankbook(bankbook[i]);
				incomeVo.setImport_disposal(import_disposal[i]);
				incomeVo.setDate2(date2[i]);
				incomeVo.setMemo2(memo2[i]);
				
				try {
					result = bookDao.insertIncome(incomeVo);
				} catch (Exception e) {
					result = -1;
					e.printStackTrace();
				}
			}
			
			if(result > 0) {
				System.out.println("등록 성공");
				url = "/teamproject/bookList.do?ymd="+date2[0]+"&div="+div+"#tab"+div;
			}else {
				System.out.println("등록 실패");
			}
		}
		
		response.sendRedirect(url);
	}
}
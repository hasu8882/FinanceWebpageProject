package com.user.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.user.dao.BbsDao;
import com.user.dto.BbsVo;

/**
 * Servlet implementation class BbsUpdate
 */
@WebServlet("/bbsUpdate.do")
public class BbsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		
		// 상품 수정 링크 클릭시 삭제할 상품 정보를 표시
		BbsDao bDao = BbsDao.getInstance();
		BbsVo bVo = new BbsVo();
				
		// 데이터베이스에서 수정할 데이터 정보 확인		
		bVo = bDao.selectbbsByCode(code);
		
		request.setAttribute("bbs", bVo);
		
		// 페이지 이동 : 수정 페이지
		RequestDispatcher dispatcher = request.getRequestDispatcher("bbs/updateBbs.jsp");
		dispatcher.forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		
		BbsDao bDao = BbsDao.getInstance();
		BbsVo bVo=new  BbsVo();
		int result= -1;
		
		
		String savePath = "upload";
		ServletContext conext = getServletContext();
		String uploadFilePath = conext.getRealPath(savePath);
		System.out.println("저장파일 서버 경로"+ uploadFilePath);
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "UTF-8";

		try {
			MultipartRequest multi = new MultipartRequest(
					request, // request객페
					uploadFilePath, // 서버상의 실제 디렉토리
					uploadFileSizeLimit, // 최대 업로드 파일크기
					encType, // 인코딩방식
					new DefaultFileRenamePolicy()
					); // 정책: 동일이름 존재시, 새로운 이름부여
			
			//입력 양식을 통해 정보를 획득
			
			String id = multi.getParameter("id");
			int code = Integer.parseInt(multi.getParameter("code"));
			String title = multi.getParameter("title");
			String pictureurl = multi.getFilesystemName("pictureurl");
			String content = multi.getParameter("content");
			
			
			bVo.setId(id);
			bVo.setCode(code);
			bVo.setTitle(title);
			bVo.setPictureurl(pictureurl);
			bVo.setContent(content);
		
			System.out.println(bVo.toString());
			
			result=bDao.updatebbs(bVo);
			
			if(result==1) {
				System.out.println("글 수정 성공");
				response.sendRedirect("bbsList.do");
			}else {
				System.out.println("글 수정 실패");
				response.sendRedirect("bbsList.do");
			}
		
			
		} catch (Exception e) {
			System.out.println("예외 발생    :" + e);
			response.sendRedirect("bbsList.do");
		}

	}

}


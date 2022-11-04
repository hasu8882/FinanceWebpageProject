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


@WebServlet("/writeBbs.do")
public class WriteBbs extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("bbs/writeBbs.jsp");
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
			String title = multi.getParameter("title");
			String pictureurl = multi.getFilesystemName("pictureurl");
			String content = multi.getParameter("content");
		
			
		
			
						
			//pVo.setCode(code);
			bVo.setId(id);
			bVo.setTitle(title);
			bVo.setPictureurl(pictureurl);
			bVo.setContent(content);
			
			
			System.out.println(bVo.toString());
			
			result=bDao.insertbbs(bVo);
			
			if(result==1) {
				System.out.println("글등록 성공");
				response.sendRedirect("bbsList.do");
			}else {
				System.out.println("글등록 실패");
				response.sendRedirect("bbsList.do");
			}
		
			
		} catch (Exception e) {
			System.out.println("예외 발생    :" + e);
			response.sendRedirect("bbsList.do");
		}

	}

}

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<div class="row">
		<div class="col-4"></div>
		<div class="col-5">
			<div class="modal-header p-5 pb-4 ">
				<h2 class="fw-bold mb-0" >아이디 비밀번호 찾기</h2>
			</div>
			<div class="modal-header p-5 pb-4 " style="text-align:center">
			
			
				<h4 class="fw-bold mb-0" style="text-align:center">${message}</h4>
			
			</div>
			<button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary"
			onclick="location.href='login.do'">로그인</button>
		</div>
		

		
	</div>
	<div class="col-4"></div>

</body>
</html>
</body>
</html>
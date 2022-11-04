<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>   
</head>
<body>

<%@ include file="../navbar.jsp" %>
<div class="row">
	<div class="col-4">
	</div>
	<div class="col-4">
      <div class="modal-header p-5 pb-4 ">
        <!-- <h5 class="modal-title">Modal title</h5> -->
        <h2 class="fw-bold mb-0">로그인</h2>
      </div>

      <div class="modal-body p-5 pt-0">
        <form action="login.do" method="post">
          <div class="form-floating mb-3">
            <input type="text" class="form-control rounded-4"  name="id" placeholder="name@example.com">
            <label for="floatingInput">USER ID</label>
          </div>
          <div class="form-floating mb-3">
            <input type="password" class="form-control rounded-4"   name="pw" placeholder="Password">
            <label for="floatingPassword">Password</label>
          </div>
          <button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit">로그인</button>
          <div class="dropdown-divider"></div>
  	<a class="dropdown-item" href="join.do">아직 회원이 아니신가요? 회원가입</a>
  	<a class="dropdown-item" href="findpw.do">비밀번호를 까먹으셨나요?</a>
        </form>
      </div>
 </div>
	<div class="col-4">
	</div>
	</div>




  
 


</body>
</html>
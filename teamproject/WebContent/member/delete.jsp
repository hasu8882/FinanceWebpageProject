<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/member.js"></script>

</head>
<body>

<%@ include file="../navbar.jsp" %>
<div class="row">
	<div class="col-4">
	</div>
	<div class="col-4">
      <div class="modal-header p-5 pb-4 ">
        <!-- <h5 class="modal-title">Modal title</h5> -->
        <h2 class="fw-bold mb-0">회원탈퇴</h2>
      </div>

      <div class="modal-body p-5 pt-0">
        <form action="delete.do" method="post" name="frm">
         <h5 class="fw-bold mb-0">아이디</h5>
          <div class="form-floating mb-3">
            <input type="text" class="form-control rounded-4" name="id"
						value="${loginUser.id}" readonly>
          </div>
          <h5 class="fw-bold mb-0">이름</h5>
				<div class="form-floating mb-3">
					<input type="text" class="form-control rounded-4" name="name"
						value="${loginUser.name}" readonly>
				</div>
				<h5 class="fw-bold mb-0">비밀번호</h5>
          <div class="form-floating mb-3">
            <input type="password" class="form-control rounded-4"   name="pw" placeholder="Password">
            <label for="floatingPassword">Password</label>
          </div>
          <button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit" onclick="return checkJoin()">회원 탈퇴</button>
       
        </form>
      </div>
 </div>
	<div class="col-4">
	</div>
	</div>




  
 


</body>
</html>
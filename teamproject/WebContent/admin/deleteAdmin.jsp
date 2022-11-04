<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원삭제</title>
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<c:if test="${loginUser.admin == 1 }">
관리자만 들어 갈수 있습니다.
</c:if>
	<c:if test="${loginUser.admin == 0 }">

	<div class="modal-header p-5 pb-4 ">


		<h2 class="fw-bold mb-0" style="text-align: center">회원 삭제</h2>
	</div>
	<div class="row">
		<div class="col-2"></div>

		<div class="col-8">
			<form action="deleteAdmin.do" method="post" name="frm">
				<div class="form-floating mb-3">
					<h5 class="fw-bold mb-0">ID</h5>
					<input type="text" class="form-control rounded-4" name="id"
						value="${member.id}" readonly>
				</div>
				<h5 class="fw-bold mb-0">이름</h5>
				<div class="form-floating mb-3">
					<input type="text" class="form-control rounded-4" name="name"
						value="${member.name}">
				</div>
				<h5 class="fw-bold mb-0">이메일</h5>
				<div class="form-floating mb-3">
					<input type="email" class="form-control rounded-4" name="email"
						value="${member.email}" readonly> <label for="floatingInput">email</label>
				</div>
				<input type="hidden"  name="pw" value="${member.pw}">
				<div class="row g-3">
					<input type="submit" class="w-50 mb-2 btn btn-sm rounded-4 btn-primary"
						   value="회원 삭제">
					
				</div>

			</form>
		</div>
		<div class="col-2">
		</div>
	</div>
</c:if>
</body>
</html>

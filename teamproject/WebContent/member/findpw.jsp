<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<div class="row">
		<div class="col-4"></div>
		<div class="col-5">
			<div class="modal-header p-5 pb-4 ">
				<h2 class="fw-bold mb-0">비밀번호 찾기</h2>
			</div>
			<div class="modal-body p-15 pt-0">
				<form action="findpw.do" method="post" name="frm">
					<div class="form-floating mb-3">
						<input type="text" class="form-control rounded-4" name="id">
						<label for="floatingInput">USER ID</label> <a
							class="dropdown-item" href="findid.do">아이디를 까먹으셨나요?</a>
					</div>
					<div class="row g-3">
						<div class="col-sm">
							<input type="text" class="form-control" name="phone1"
								placeholder="phone" aria-label="phone">
						</div>
						<div class="col-1" style="text-align: center">
							<h5 class="fw-bold mb-0">-</h5>
						</div>
						<div class="col-sm">
							<input type="text" class="form-control" name="phone2"
								placeholder="phone" aria-label="phone">
						</div>
						<div class="col-1" style="text-align: center">
							<h5 class="fw-bold mb-0">-</h5>
						</div>
						<div class="col-sm">
							<input type="text" class="form-control" name="phone3"
								placeholder="phone" aria-label="phone">
						</div>
					</div>
					<br>
					<div class="form-floating mb-3">
						<input type="text" class="form-control rounded-4" name="email">
						<label for="floatingInput">email</label>
					</div>
					<button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary"
						type="submit" onclick="return findpw()">찾기</button>
				</form>
			</div>
		</div>
		<div class="col-4"></div>
	</div>
</body>
</html>



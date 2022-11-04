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
		<div class="col-2"></div>
		<div class="col-8">
		<div class="modal-header p-5 pb-4 ">
		<h2 class="fw-bold mb-0" style="text-align: center">정보 수정</h2>
	</div>
			<form action="update.do" method="post" name="frm">
				<div class="form-floating mb-3">
					<h5 class="fw-bold mb-0">ID</h5>
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
					<input type="password" class="form-control rounded-4" name="pw"
						placeholder="Password"> <label for="floatingPassword">Password</label>
				</div>
				<h5 class="fw-bold mb-0">비밀번호 확인</h5>
				<div class="form-floating mb-3">
					<input type="password" class="form-control rounded-4"
						name="pw_check" placeholder="Password"> <label
						for="floatingPassword">Password Check</label>
				</div>
				<div class="form-floating mb-3">
					<h5 class="fw-bold mb-0">회원 유형</h5>
					<input type="radio" name="pay" value=1 >유료 <input
						type="radio" name="pay" value=0>무료 <br />
				</div>
				<h5 class="fw-bold mb-0">핸드폰 번호</h5>
				<div class="row g-5">
					<div class="col-sm">
						<input type="text" class="form-control" placeholder="phone"
							aria-label="phone" name="phone1" value="${loginUser.phone1}">
					</div>
					<div class="col-1" style="text-align: center">
						<h5 class="fw-bold mb-0">-</h5>
					</div>
					<div class="col-sm">
						<input type="text" class="form-control" placeholder="phone"
							aria-label="phone" name="phone2" value="${loginUser.phone2}">
					</div>
					<div class="col-1" style="text-align: center">
						<h5 class="fw-bold mb-0">-</h5>
					</div>
					<div class="col-sm">
						<input type="text" class="form-control" placeholder="phone"
							aria-label="phone" name="phone3" value="${loginUser.phone3}">
					</div>
				</div>
				<br>
				<h5 class="fw-bold mb-0">이메일</h5>
				<div class="form-floating mb-3">
					<input type="email" class="form-control rounded-4" name="email"
						value="${loginUser.email}"> <label for="floatingInput">email</label>
				</div>
				<div class="row g-3">
					<input type="button" class="w-50 mb-2 btn btn-sm rounded-4 btn-primary"
						 onclick="location.href='delete.do'" value="회원 탈퇴">
					<button class="w-50 mb-2 btn btn-sm rounded-4 btn-primary"
						type="submit" onclick="return checkUpdate()">정보 변경</button>
				</div>

			</form>
		</div>
		<div class="col-2">
		</div>
	</div>

</body>
</html>



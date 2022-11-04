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
				<h2 class="fw-bold mb-0">회원가입</h2>
			</div>
			<form action="join.do" method="post" name="frm">
				<h5 class="fw-bold mb-0">아이디</h5>
				<div class="row">
					<div class="col-8">
						<div class="form-floating mb-3">
							<input type="text" class="form-control rounded-4" name="id"
								placeholder="User id"> <label for="floatingPassword">ID</label><input
								type="hidden" name="checkid">
						</div>
					</div>
					<div class="col-4">
						<div class="form-floating mb-3">
							<input type="button"
								class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" value="중복체크"
								onclick="checkId()">
						</div>
					</div>
				</div>
				<h5 class="fw-bold mb-0">비밀번호</h5>
				<div class="form-floating mb-3">
					<input type="password" class="form-control rounded-4" name="pw"
						placeholder="Password"> <label for="floatingPassword">Password</label>
				</div>
				<h5 class="fw-bold mb-0">비밀번호 체크</h5>
				<div class="form-floating mb-3">
					<input type="password" class="form-control rounded-4"
						name="pw_check" placeholder="Password"> <label
						for="floatingPassword">Password Check</label>
				</div>
				<h5 class="fw-bold mb-0">이름</h5>
				<div class="form-floating mb-3">
					<input type="text" class="form-control rounded-4" name="name"
						placeholder="Password"> <label for="floatingInput">Name</label>
				</div>
				<div class="form-floating mb-3">
					<h5 class="fw-bold mb-0">회원 유형</h5>
					<input type="radio" name="pay" value=1 checked>유료 <input
						type="radio" name="pay" value=0>무료 <br />
				</div>
				<h5 class="fw-bold mb-0">전화번호</h5>
				<div class="row g-5">
					<div class="col-sm">
						<input type="text" class="form-control" placeholder="phone"
							aria-label="phone" name="phone1">
					</div>
					<div class="col-1" style="text-align: center">
						<h5 class="fw-bold mb-0">-</h5>
					</div>
					<div class="col-sm">
						<input type="text" class="form-control" placeholder="phone"
							aria-label="phone" name="phone2">
					</div>
					<div class="col-1" style="text-align: center">
						<h5 class="fw-bold mb-0">-</h5>
					</div>
					<div class="col-sm">
						<input type="text" class="form-control" placeholder="phone"
							aria-label="phone" name="phone3">
					</div>
				</div>
				<br>
				<div class="form-floating mb-3">
					<h5 class="fw-bold mb-0">성별</h5>
					<input type="radio" name="gender" value=1 checked>남자 <input
						type="radio" name="gender" value=0>여자 <br />
				</div>
				<h5 class="fw-bold mb-0">이메일</h5>
				<div class="form-floating mb-3">
					<input type="email" class="form-control rounded-4" name="email"
						placeholder="email"> <label for="floatingInput">email</label>
				</div>
				<button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary"
					type="submit" onclick="return checkJoin()">확인</button>
			</form>
		</div>
		<div class="col-2"></div>
	</div>

</body>
</html>
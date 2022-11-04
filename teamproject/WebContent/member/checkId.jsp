<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복확인</title>
<script type="text/javascript" src="script/member.js"></script>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.rtl.min.css"
	integrity="sha384-+4j30LffJ4tgIMrq9CwHvn0NjEvmuDCOfk6Rpg2xg7zgOxWWtLtozDEEVvBPgHqE"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
	integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js"
	integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK"
	crossorigin="anonymous"></script>

</head>
<body>
	<h2 class="fw-bold mb-0">아이디 중복체크</h2>
	<form action="checkId.do" name="frm">
		<div class="row">
			<div class="col-5">
				<div class="form-floating mb-3">
					<input type="text" class="form-control rounded-4" name="id"
						value="${id}">
				</div>
			</div>
			<div class="col-4">
				<div class="form-floating mb-3">
					<button class="w-100 mb-2 btn rounded-4 btn-primary" type="submit"
						onclick="checkId()">중복체크</button>
				</div>
			</div>

		</div>
		<div class="row">
			<div class="col-5">
				<h2 class="fw-bold mb-0">${message}</h2>
			</div>
			<div class="col-4">
				<c:if test="${result==1}">
					<script type="text/javascript">
						opener.document.frm.id.valud = "";
					</script>
				</c:if>

				<c:if test="${result==-1}">
					<input type="button" class="w-100 mb-2 btn rounded-4 btn-primary"
						value="사용" onclick="idOk()">
				</c:if>
			</div>
		</div>


	</form>


</body>
</html>
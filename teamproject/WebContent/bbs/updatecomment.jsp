<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
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
	<h2 class="fw-bold mb-0">댓글 수정</h2>
	<form action="updatereply.do" method="post">
		<input type="hidden" name="code" value="${reply.code}">
		<input type="hidden" name="comment_code" value="${reply.comment_code}">
		<input type="text" name="id" value="${reply.id}" readonly>
		<textarea class="form-control" name="content" maxlength="200"
			style="height: 50px;">${reply.content}</textarea>
		<input type="submit" class="btn-primary pull-right" value="수정">
	</form>


</body>
</html>
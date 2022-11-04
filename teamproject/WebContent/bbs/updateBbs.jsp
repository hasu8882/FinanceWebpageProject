<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품수정페이지</title>

<script type="text/javascript" src="script/product.js"></script>
</head>
<body>
<%@ include file="../navbar.jsp" %>
	<div id="wrap" align="center">
		<form action="bbsUpdate.do" method="post" encType="multipart/form-data">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeee; text-align: center;">게시글수정페이지</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th style="width:50px">제목</th>
						<td><input type="text" class="form-control"
							 name="title" maxlength="50" value="${bbs.title }"></td>
					</tr>
					<tr>
					<th style="width:50px">id</th>
						<td><input type="text" class="form-control" name="id" value="${loginUser.id}"
							readonly /></td>
					</tr>
					<tr>
					<th style="width:50px"><img src="upload/${bbs.pictureurl}"></th>
						<td><textarea class="form-control" 
								name="content" maxlength="2048" style="height: 350px;">${bbs.content}</textarea></td>
					</tr>
					<tr>
						<td><input type="file" name="pictureurl"></td>
						
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="code" value="${bbs.code}">
			<input type="submit" class="btn-primary pull-right" value="수정">
			<input type="reset" value="취소"> 
			<input type="button" value="목록" onclick="location.href='bbsList.do'">
		</form>
	</div>
</body>
</html>
		
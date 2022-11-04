<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>

	<%@ include file="../navbar.jsp"%>
	<div id="wrap" align="center">
		<form action="writeBbs.do" method="post" encType="multipart/form-data">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeee; text-align: center;">게시판
							글쓰기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" class="form-control"
							placeholder="글 제목" name="title" maxlength="50"></td>
					</tr>
					<tr>
						<td><input type="text" class="form-control" name="id" value="${loginUser.id}"
							readonly /></td>
					</tr>
					<tr>
						<td><textarea class="form-control" placeholder="글 내용"
								name="content" maxlength="2048" style="height: 350px;"></textarea></td>
					</tr>
					<tr>
						<td><input type="file" name="pictureurl"></td>
						
					</tr>
				</tbody>
			</table>
			<input type="submit" class="btn-primary pull-right" value="글쓰기">
			<input type="reset" value="취소"> <input type="button"
				value="목록" onclick="location.href='bbsList.do'">
		</form>
	</div>
</body>
</html>
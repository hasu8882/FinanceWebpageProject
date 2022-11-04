
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세 페이지</title>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<br>
	<br>
	<div class="container">
		
		<table class="table table-borderless">
			<tr>
				<th colspan="5" align="left">&nbsp;&nbsp;[제목]&nbsp;&nbsp;${bbs.title}</th>
			</tr>
			<tr>
				<td colspan="4" align="left">&nbsp;&nbsp;[ID]&nbsp;&nbsp;${bbs.id}&nbsp;&nbsp;</td>
				<td align="right">&nbsp;&nbsp;[작성일자]&nbsp;&nbsp;${bbs.reg_date}&nbsp;&nbsp;</td>
				</tr>
				<% System.out.println("c if");%>
				<c:if test="${bbs.id == loginUser.id || loginUser.admin == 0}">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					
					<td align="right"><input type="button" value="수정"
						onclick="location.href='bbsUpdate.do?code=${bbs.code}'">
						 <input type="button" value="삭제" onclick="location.href='bbsDelete.do?code=${bbs.code}'"></td>

				</tr>
			</c:if>

			<c:if test="${empty bbs.pictureurl}">
				<tr>
				<% System.out.println("404 png");%>
					<td><img src="img/404.png" width="200" height="200"></td>
				</tr>
				<tr>
					<td>${bbs.content}</td>
				</tr>
			</c:if>
			<c:if test="${bbs.pictureurl != null}">
			<tr>
				<td><img src="upload/${bbs.pictureurl}"></td>
			</tr>
			<tr>
				<td>${bbs.content}</td>
			</tr>
			</c:if>
		</table>
	</div>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<table class="table table-striped"
			style="border: 1px solid #dddddd">
			<tbody>
				<c:forEach var="reply" items="${replylist}">
					<tr>
						<th align="left">&nbsp;&nbsp;[ID]&nbsp;&nbsp;${reply.id}&nbsp;&nbsp;</th>
						<td align="right">
					<c:if test="${reply.id == loginUser.id && loginUser.admin==0}">
						<input type="button" value="수정"
						onclick="location.href='updatereply.do?comment_code=${reply.comment_code}'"> 
						<input type="button" value="삭제"
						onclick="location.href='replyDelete.do?comment_code=${reply.comment_code}&code=${reply.code }'">
					</c:if>
					</td>
				</tr>
					<td  align="left">${reply.content}</td>
					<td align="right"> ${reply.reg_date}</td>
				</c:forEach>
		</table>
	</div>



	<c:if test="${loginUser.id != null}">
	<% System.out.println("abc"); %>
		<div class="container">
			<form action="replyinsert.do" method="post">
				<input type="hidden" name="bbs" value="${bbs}">
				<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">
					<tr>
						<td><input type="hidden" name="code" value="${bbs.code}">
							<input type="hidden" name="id" value="${loginUser.id}"></td>
						<td style="border-bottom: none;" valign="middle"><br> <br></td>
						<td><input type="text" style="height: 100px;"
							class="form-control" placeholder="상대방을 존중하는 댓글을 남깁시다."
							name="content"></td>
								<% System.out.println("form"); %>
						<td><br> <br> <input type="submit" class="btn-primary pull" value="댓글 작성"></td>
					</tr>
				</table>
			</form>
		</div>
	</c:if>
	<div class="container">
	<input type="button" value="목록" onclick="location.href='bbsList.do'">
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<%@ include file="../navbar.jsp" %>
<body>
		<br>
		<br>
<div class="container">
	<h2 class="fw-bold mb-0">게시판</h2>
		<table class="table table-striped">
			<tr>
				<th>코드</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성시간</th>
			</tr>
			<c:forEach var="bbs" items="${bbslist}">
				
				<tr>
					<th>${bbs.code}</th>
					<th><a href="bbsDetail.do?code=${bbs.code}">${bbs.title}</a></th>
					<th>${bbs.id}</th>
					<th>${bbs.reg_date}</th>
				</tr>
			
			</c:forEach>
		</table>
</div>
		
		<div class="text-center">
				<c:if test="${loginUser.id != null}">
<a class="btn btn-default pull-right" href="writeBbs.do">글쓰기</a>
</c:if>
		<c:if test="${loginUser.id == null}">
<input type="button" value="글쓰기" onclick="alert('로그인이 필요 합니다.');" style="cursor:pointer;" />
</c:if>
		<form action="bbsList.do">
			<select name="column">
				<option ${(param.column=="code")?"selected":""} value="code">게시물번호</option>
				<option ${(param.column=="id")?"selected":""} value="id">작성자</option>
				<option ${(param.column=="title")?"selected":""} value="title">제목</option>
			</select>
			<input type="text" name="keyword" value="${param.keyword}">
			<input type="submit" value="검색">
		</form>
		<c:set var="page" value="${(empty param.p)?1:param.p}"></c:set>
	<c:set var="startNum" value="${page-(page-1)%5}"></c:set>
	<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/5), '.')}"></c:set>
	
	<!-- 이전 페이지, 다음 페이지 링크 생성 -->
	<!-- 이전 페이지 링크를 생성하는 경우: (시작번호-1) 값이 0보다 커야 함 -->
	<c:if test="${startNum-1 > 0}">		<!-- startNum > 1 -->
		<a href="?command=board_list&p=${startNum-1}&column=${param.column}&keyword=${param.keyword}">이전 </a>
	</c:if>
	<!-- 이전 페이지 링크를 생성하지 못하는 경우: (시작번호-1) 값이 0보다 작거나 같아야함 -->
	<c:if test="${startNum-1 <= 0}">		<!-- startNum <= 1 --> 
		<span onclick="alert('이전 페이지가 없습니다.');">이전 </span>
	</c:if>
	
	<c:forEach var="i" begin="0" end="4">
		<!-- 마지막 게시물이 있는 페이지까지만 표시 -->
		<c:if test="${(i+startNum) <= lastNum}">
			<!-- // 해당 페이지인 경우, 스타일(컬러) 지정 -->
			<a style="color:${(page==(i+startNum))?'orange':''}" 
				href="?command=board_list&p=${i+startNum}&column=${param.column}&keyword=${param.keyword}">
				${i+startNum}
			</a>
		</c:if>			
	</c:forEach>
	
	<c:if test="${(startNum+4) < lastNum}">
		<a href="?command=board_list&p=${startNum+5}&column=${param.column}&keyword=${param.keyword}"> 다음</a>
	</c:if>
	<!-- 다음 페이지 링크를 생성하지 못하는 경우: (시작번호+4) 값이 마지막 숫자보다 커야함 -->
	<c:if test="${(startNum+4) >= lastNum}">
		<span onclick="alert('다음 페이지가 없습니다.');"> 다음</span>
	</c:if>
			
	</div>
		
</div>
</body>
</html>

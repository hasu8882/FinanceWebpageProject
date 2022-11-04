<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin 페이지</title>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<c:if test="${loginUser.admin == 1 }">
관리자만 들어 갈수 있습니다.
</c:if>
	<c:if test="${loginUser.admin == 0 }">

	
		<br>
		<br>
<div class="row">
	<div class="col-2">
	</div>
	<div class="col-8">
	<h2 class="fw-bold mb-0">회원관리</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th scope="col">이름</th>
					<th scope="col">아이디</th>
					<th scope="col">폰 번호</th>
					<th scope="col">이메일</th>
					<th scope="col">결제</th>
					<th scope="col">성별</th>
					<th scope="col">관리자 권한</th>
					<th scope="col">수정</th>
					
				</tr>
			</thead>
			<c:forEach var="memberlist" items="${memberlist}">
				<c:if test="${memberlist.id !=  loginUser.id}">
				<tbody>
					<tr>
						<td>${memberlist.name}</td>
						<td>${memberlist.id}</td>
						<td>${memberlist.phone1}-${memberlist.phone2}-${memberlist.phone3}</td>
						<td>${memberlist.email}</td>
						<td>
						<c:if test="${memberlist.pay == 0}">
					무료
					</c:if> 
					<c:if test="${memberlist.pay == 1}">
					유료
					</c:if>
					</td>
						<td><c:if test="${memberlist.gender == 1}">
					남자
					</c:if> <c:if test="${memberlist.gender == 0}">
					여자
					</c:if></td>
						<td><c:if test="${memberlist.admin == 1}">
					회원
					</c:if> <c:if test="${memberlist.admin == 0}">
					관리자
					</c:if></td>
						<td><a href="adminUpdate.do?id=${memberlist.id}">수정</a></td>
					</tr>

				</tbody>
				</c:if>
			</c:forEach>
		</table>
	</div>
	</c:if>
	
	<div class="text-center">
		<form action="membermanage.do">
			<select name="column">	
				<option ${(param.column=="id")?"selected":""} value="id">id</option>
				<option ${(param.column=="email")?"selected":""} value="email">이메일</option>
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
	
	
</body>
</html>
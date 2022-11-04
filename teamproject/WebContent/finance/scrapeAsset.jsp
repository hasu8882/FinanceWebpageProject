<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../navbar.jsp" %>
<h2>This page is scrape</h2>

<form name="frm" action="scrape.do" method="get" >
<table class="table">
	<thead>
	<tr>
		<td>뉴스</td>
		<td>환율</td>
		<td>지수</td>
		
	</tr>
	</thead>
	<tr >
		<td><input type="text" name="code"></td>
		<td><input type="text" name="company"></td>
		<td><input type="text" name="date"></td>
	</tr>
	<tr>
	<td colspan="2">
		<input type="submit" value="확인">
		<input type="reset" value="취소">
	</td>
	</tr>
</table>
</form>
<input type="button" value="스크랩" onclick="location.href='../scrape.do'">
</body>
</html>
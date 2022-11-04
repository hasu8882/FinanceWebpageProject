<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add Asset</title>
<link rel="stylesheet" type="text/css" href="css/asset.css">
<script type="text/javascript" src="script/asset.js"></script>
</head>
<body>

<div id="wrap" align="center" class="row">
<!-- 조회 -->
	    <form action="searchAsset.do" method="get" name="frm" enctype="multipart/form-data" >   
<!--     <table border="1"></table> -->
		<table>
        <tr>
            <th>자산 조회</th>
            <td>
<%--             <select name="market">
<!--                 선택된 값을 유지, 3항 연산자 사용 -->
                    <option ${(param.market == "kospi")?"selected":""} value="kospi">코스피</option>
                    <option ${(param.market == "kosdaq")?"selected":""} value="kosdaq">코스닥</option>
                    <option ${(param.market == "etf")?"selected":""} value="etf">etf</option>
            </select>
            &nbsp;
            <select name="field">
<!--                 선택된 값을 유지, 3항 연산자 사용 -->
                    <option ${(param.field == "code")?"selected":""} value="code">티커</option>
                    <option ${(param.field == "name")?"selected":""} value="name">자산</option>
            </select>
            &nbsp; --%>
            <input type="text" name="keyword" value="${param.keyword}">
            <input type="submit" value="검색">
            </td>           
        </tr>      
       </table>
      </form>
</div>
	<br>
	<hr>
<div id="wrap" align="center" class="row">
<!-- 	조회결과 -->

<form action="addAsset.do" method="post" name="frm" >   
<h3>자산조회 결과</h3>
   <table>
    	<tr> 
    		<th> 번호 </th> <th> 자산코드 </th><th> 자산 </th> <th> 가격 </th> <th class="qty"> 수량 </th><th> 선택 </th>
    	</tr>
    	
    	     <c:forEach var="asset" items="${assetList}" varStatus="idx">
	         <tr>	         	
	                 <td>${idx.count}</td>  
	                 <td>${asset.code}</td> 
	                  <td>${asset.name}</td> 
	                  <td>${asset.price}</td> 
	                  <td class="qty"><input type="text" name="qty" value=""  placeholder="자산 수량 입력" ></td> 
	                  <td><input type="checkbox" name="code" value="${asset.code},${idx.count}" ></td>     
	         </tr>
	        
         </c:forEach>
       
    </table>
    <input type="submit" value="등록">
	<input type="hidden" name="id" value="${loginUser.id}">
	<input type="reset" value="취소">
</form>
</div>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.jsoup.nodes.Element" %>
<%@ page import="org.jsoup.select.Elements"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Finance Home</title>

<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.rtl.min.css" integrity="sha384-+4j30LffJ4tgIMrq9CwHvn0NjEvmuDCOfk6Rpg2xg7zgOxWWtLtozDEEVvBPgHqE" crossorigin="anonymous"> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js" integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK" crossorigin="anonymous"></script> -->

</head>
<body>

<%@ include file="../navbar.jsp" %>


<div class="container">

	<div class="row">
		<table class="table table-striped">
        
         <tbody>
         <br>
         <tr><th>주요 뉴스</th></tr>
         <c:forEach var="news" items="${indexList[0]}">
          <tr>
                <td><a href="https://finance.naver.com${news.attr('href')}">"${news.text()}"</a></td>                
           </tr>
         </c:forEach>
         </tbody>
         </table>
	</div>
    <div class="row">
       <span style="border:white;text-align:left;padding-right:20px;color:red" ><strong>주요 지수 </strong></span>
       <div class="col">
        <table class="table table-striped">
         <thead>
         <tr>
            <th scope="row" colspan="3"><strong>환율</strong></th>
         </tr>
         </thead>
         <tbody>
           <tbody>
          <c:forEach var="index" items="${indexList[1]}" varStatus="status1">
	         <tr>	         	
	                 <c:forEach var="content" items="${index.text().split(' ')}" varStatus="status">	                 	
       					 	<td>${content}</td> 							 
	                	
	                </c:forEach> 
        
	         </tr>
	         
         </c:forEach>
                   <c:forEach var="index" items="${indexList[2]}" varStatus="status1">
	         <tr>	         	
	                 <c:forEach var="content" items="${index.text().split(' ')}" varStatus="status">
	                 	                 	
       					 <c:if test="${not(status1.count == 1 and status.count == 2)}">
       					 	<td>${content}</td>
   						 </c:if>  							 
	                	
	                </c:forEach> 
        
	         </tr>
	         
         </c:forEach>
         

         </tbody>
         </table>
       </div>
       <div class="col">
       <table class="table table-striped">
        <thead>
         <tr>
            <th scope="row" colspan="3"><strong>국내지수</strong></th>
         </tr>
         </thead>
         <tbody>
          <c:forEach var="index" items="${indexList[3]}" varStatus="status1">
	         <tr>	         	
	                 <c:forEach var="content" items="${index.text().split(' ')}" varStatus="status">	                 	
       					 	<td>${content}</td> 										 
	                	
	                </c:forEach> 
        
	         </tr>
	         
         </c:forEach>
        </tbody>
       </table>
       </div>
       <div class="col">
       <table class="table table-striped">
        <thead>
         <tr>
            <th scope="row" colspan="3"><strong>해외지수</strong></th>
         </tr>
         </thead>
         <tbody>

         <c:forEach var="int_ex" items="${indexList[4]}" varStatus="status1">
	         <tr>	         	
	                 <c:forEach var="content" items="${int_ex.text().split(' ')}" varStatus="status2">

   						 <td>${content}</td>					 
	                	
	                </c:forEach> 
        
	         </tr>
	         
         </c:forEach>
         </tbody>
         </table>
       </div>

    </div>
    <hr>
    <c:if test="${loginUser.id != null && loginUser.pay == 1}">
 <div class="row">
 <table>
 	<tr>
 		<th>${loginUser.name}(${loginUser.id}) 님의 자산</th>	 		
 		<th style="border:white; text-align:right;padding-right:20"><input type= "button" value="자산추가" onclick="location.href='addAsset.do'"></th>
 	</tr>
 </table>
    
 </div>

   <div class="row"> 
    <table class="table table-striped">
    <thead>
    <tr>
      <th scope="col">아이디</th>
      <th scope="col">자산</th>
      <th scope="col">자산 코드 </th>
      <th scope="col">가격</th>
      <th scope="col">수량</th>
      <th scope="col">금액</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="asset" items="${myasset}" varStatus="status">	
	          <c:if test="${ asset.userid eq loginUser.id}">
	         <tr>  
	   
					   <td>${asset.userid}</td>  <td>${asset.name}</td> <td>${asset.code}</td> <td>${asset.price}</td>  <td>${asset.qty}</td>  <td>${asset.tot_amount}</td> 
					   <td><a href="updateAsset.do?name=${asset.name}&qty=${asset.qty}&code=${asset.code}&userid=${loginUser.id}">수정 </a></td>
					   <td><a href="deleteAsset.do?code=${asset.code}&userid=${loginUser.id}">삭제 </a></td>
<%-- 				<td><a href="modifAsset.do?qty=${asset.qty}&code=${asset.code}&userid=${loginUser.userid}">수정 </a></td>
					   <td><a href="deleteAsset.do?qty=${asset.qty}&code=${asset.code}&userid=${loginUser.userid}">삭제 </a></td> --%>
		
	         </tr>
	         </c:if>  
       </c:forEach>
   

  </tbody>
	</table>
    </div>
</c:if>
</div>

</body>
</html>
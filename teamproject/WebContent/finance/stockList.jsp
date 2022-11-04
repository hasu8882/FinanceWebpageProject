<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/asset.css">
<script type="text/javascript" src="script/asset.js"></script>
</head>
<body>
<%@ include file="../navbar.jsp" %>
<br>
<div id="wrap" align="center" class="row">
     <form action="stockList.do" method="get">   
<!--     <table border="1"></table> -->
    <table>
        <tr>
            <th>조회구분</th>
            <td>
            <select name="market">
<!--                 선택된 값을 유지, 3항 연산자 사용 -->
                    <option ${(param.market == "KOSPI")?"selected":""} value="KOSPI">코스피</option>
                    <option ${(param.market == "KOSDAQ")?"selected":""} value="KOSDAQ">코스닥</option>
                    <option ${(param.market == "ETF")?"selected":""} value="ETF">ETF</option>
            </select>
            &nbsp;
            <input type="text" name="keyword" value="${param.keyword}">
            
            <td style="border:white;text-align:right;padding-right:20px">
           		<input type= "button" value="DB 업데이트" onclick="location.href='insertDB.do'">
            </td>
           </tr>
        <tr>
        	<th>PER</th>
        	<td>
        	 <input type="text" name="per_lower" style="width:50px">하한&nbsp;&nbsp;<input type="text" name="per_upper" style="width:50px">&nbsp;&nbsp;상한
        	</td>
        	<th>PBR</th>        	
        	 <td>
        	<input type="text" name="pbr_lower" style="width:50px">하한&nbsp;&nbsp;<input type="text" name="pbr_upper" style="width:50px">&nbsp;&nbsp;상한
        	</td>
        	 <td>
        	<input type="text" name="div_yield" style="width:50px">&nbsp;&nbsp;배당수익률(%)
        	</td>
        	 <td style="border:white;text-align:right;padding-right:10px">           
            <input type="submit" value="검색">
            </td>
        </tr>  
       
    </table>
    
    </form>

</div>
 <!-- Tab links -->
<!-- <div class="tab">
  <button class="tablinks" onclick="openAsset(event, 'Kospi')">KOSPI</button>
  <button class="tablinks" onclick="openAsset(event, 'kosdaq')">KOSDAQ</button>
  <button class="tablinks" onclick="openAsset(event, 'Etf')">ETF</button>
</div> -->

<!-- Tab content -->
<!-- <div id="Kospi" class="tabcontent"> -->
<div id="wrap" align="center" class="row">
<c:if test="${not(market == 'ETF')}">
	<table class="table">
         <thead class="thead-dark">
         <tr>
            <th >종목코드</th>
            <th >종목명</th>
            <th >종가</th>
            <th >대비</th>
            <th >등락률</th>
            <th >EPS</th>
            <th >PER</th>
            <th >선행 EPS</th>
            <th >선행 PER</th>
            <th >BPS</th>
            <th >PBR</th>
            <th >주당배당금</th>
            <th >배당수익률</th>
           
         </tr>
         </thead>
         <tbody>
           <tbody>
          
          <c:forEach var="asset" items="${assetList}" varStatus="status1">

	         <tr>	         	
	                 <td>${asset.code}</td>  
	                  <td>${asset.name}</td> 
	                  <td>${asset.price}</td>  
	                  <td>${asset.range_day}</td> 
	                  <td>${asset.change_day}</td> 
	                  <td>${asset.EPS}</td> 
	                  <td>${asset.PER}</td> 
	                  <td>${asset.fwdEPS}</td> 
	                  <td>${asset.fwdPER}</td>
	                  <td>${asset.BPS}</td> 
	                  <td>${asset.PBR}</td> 
	                  <td>${asset.dividend}</td> 
	                  <td>${asset.dividend_yield}</td> 
	                  
	         </tr>
	         
         </c:forEach>
		
         </tbody>
         </table>
 </c:if>

<!-- <div id="Etf" class="tabcontent"> -->
<c:if test="${market == 'ETF'}">
  	<table class="table">
         <thead class="thead-dark">
         <tr>
         	 <th>종목코드</th>
            <th class="col2" >종목명</th>
            <th>종가</th>
            <th >대비</th>
            <th >등락률</th>
            <th >NAV</th>
            <th >시가</th>
            <th >고가</th>
            <th >저가</th>
            <th >거래량</th>
            <th class="col1">거래대금</th>
            <th class="col1">시가총액</th>
            <th class="col1">순자산가치</th>
            <th class="col1">지수</th>
            <th >종가</th>
            <th >등락</th>
            <th >변동</th>
         </tr>
         </thead>
         <tbody>
           <tbody>
          <c:forEach var="etf" items="${assetList}" varStatus="status1">
	         <tr>	
	        	 <c:if test="${not(status1.count == 1)}">         	
	                 <td >${etf.code}</td>  
	                  <td class="col2">${etf.name}</td> 
	                  <td>${etf.price}</td>  
	                  <td>${etf.range_day}</td> 
	                  <td>${etf.change_day}</td> 
	                  <td>${etf.NAV}</td> 
	                  <td>${etf.open}</td> 
	                  <td>${etf.high}</td> 
	                  <td>${etf.low}</td>
	                  <td>${etf.volume}</td> 
	                  <td class="col1">${etf.tradeAMT}</td> 
	                  <td class="col1">${etf.marketCap}</td> 
	                  <td class="col1">${etf.netAsset}</td> 
	                  <td class="col1">${etf.indexName}</td> 
	                  <td>${etf.closeIndex}</td> 
	                  <td>${etf.rangeIndex}</td> 
	                  <td>${etf.changeIndex}</td> 
	               </c:if>     
	         </tr>
	         
         </c:forEach>

         </tbody>
         </table>
 </c:if> 
</div>



<c:set var="scount" value="${assetCount}"> </c:set>	
<c:set var="page" value="${(empty param.p)?1:param.p}"> </c:set>
<c:set var="startNum" value="${page -(page -1) %5}"> </c:set>
<!-- substringBefore -->
<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(scount/10),'.')}"> </c:set>
<!--현재 페이지 구성 -->
<div style="text-align:right;">
<table  class="table table-striped">
	<tr>
		<td style="border:white;text-align:right;padding-right:20px">
			<span>${(empty param.p)?1:param.p}</span>/${lastNum} pages
		</td>
	</tr>
</table>
</div>
<div  id="page" style="text-align:center">	

<!-- (시작 번호 -1) 값이 0보다 커야 함 -->
<!-- 1 6 11 17 22-->

<c:if test="${startNum-1>0 }">
	<a href="?p=${startNum-1}"> 이전 </a>
</c:if>

<!-- 1 2 3 4 5 -->
<c:if test="${startNum-1<=0 }">

	<span onclick="alert('이전페이지가 없습니다.');"> 이전</span>

</c:if>


<c:forEach var="i" begin="0" end="4">
	<%--  <c:if test ="${(i+startNum) <= lastNum}}"> --%>
		<a style="color:${(page == (i+startNum))?'orange':''}" 
		href="?p=${i+startNum}&market=${param.market}&keyword=${param.keyword}
		&per_lower=${param.per_lower}&per_upper=${param.per_upper}
		&pbr_lower=${param.pbr_lower}&pbr_upper=${param.pbr_upper}
		&div_yield=${param.div_yield}"  
		>
		${i+startNum}
		</a>
	 <%-- </c:if>  --%>
</c:forEach>

<!-- lastNum = 33 -->

<!-- 31 32 33 34 35 -->
<c:if test="${startNum+4 < lastNum }">
<a href="?p=${startNum+5 }&market=${param.market}&keyword=${param.keyword}">
	다음
</a>
</c:if>


<!-- 31 32 33  -->
<c:if test="${startNum+4>=lastNum }">
	<span onclick="alert('다음페이지가 없습니다.');">다음</span>
</c:if>


	
</div>
        
</body>
</html>
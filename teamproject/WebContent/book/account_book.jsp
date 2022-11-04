<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>simple 가계부</title>

<!-- excel download -->
<script lang="javascript" src="dist/xlsx.full.min.js"></script>

<!-- excel 저장 시 날짜 표시 -->
<script src="https://momentjs.com/downloads/moment.js"></script>

<!--  동적 테이블 생성을 위한 j-query 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!--  css 스타일 -->
<link rel="stylesheet" type="text/css" href="css/account_book.css"> 
<!--  탭메뉴 및 동적 테이블 생성을 위한 javascript  -->
<script type="text/javascript" src="js/account_book.js" charset="UTF-8"></script>

<!-- Sheet JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.14.3/xlsx.full.min.js"></script>

<!--FileSaver savaAs 이용 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js"></script>

</head>
<body>

	<%@ include file="../navbar.jsp" %>


<br>
<br>

<div class="row">
	<div class="col-7">      
		 <%@ include file="/book/calendar.jsp" %>
	</div>
	<div class="col-3">      
		<!-- <form>
			<textarea rows="18" cols="50" ></textarea>
		</form> 
		 -->
		 <%@ include file="/book/calcu.jsp" %>
	</div>			
</div>

<div id="main_side">
    <section class="buttons"><br>    	
        <label for="first"><a class="tb" href="#tab1">지출</a></label>
        <label for="second"><a class="tb" href="#tab2">수입</a></label>
    </section>
</div>
    <br>
    <br>
 <div class="container">  
    <div class="tab_item" id="tab1">      
    <h2 style="text-align: center;">지출</h2>
    
	<!-- 엑셀 파일 저장 테이블 -->
    <table border="1" id="excel_table1" style="margin: auto; text-align: center; display:none;">
		<colgroup>
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="300px">
			<col width="50px">
		</colgroup>
		<thead>
			<tr>
				<th>No.</th>
	     		<th>사용내역</th>
	            <th>현금</th>
	            <th>카드</th>            
	            <th>사용처분류</th>
	            <th>날짜</th>
	            <th>메모/비고</th>
	            <th></th>
	        </tr>
		</thead>
		<tbody id="tbody1">
			<c:if test="${not empty outlayList }">
		    	<c:forEach var="outlayVo" items="${outlayList }" varStatus="status">
		    		<tr>
						<td>${outlayVo.num }</td>
						<td>${outlayVo.use1 }</td>
						<td>${outlayVo.cash }</td>
						<td>${outlayVo.card }</td>
						<td>${outlayVo.used_disposal }</td>		
						<td>${outlayVo.date1 }</td>
						<td>${outlayVo.memo1 }</td>			
						<td>
						</td>
					</tr>
		    	</c:forEach>
		    </c:if>
		</tbody>
	</table>
    
    <form action="/teamproject/bookReg.do" method="post" onsubmit="return formChk1(this)" id="form1">
    	<input type="hidden" name="div" value="1" />
		<table border="1" id="table1" style="margin: auto; text-align: center; ">
			<colgroup>
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="300px">
				<col width="50px">
			</colgroup>
			<thead>
				<tr>
					<th>No.</th>
		     		<th>사용내역</th>
		            <th>현금</th>
		            <th>카드</th>            
		            <th>사용처분류</th>
		            <th>날짜</th>
		            <th>메모/비고</th>
		            <th></th>
		        </tr>		        
			</thead>
			
			<tbody id="tbody1">
				<c:if test="${not empty outlayList }">
			    	<c:forEach var="outlayVo" items="${outlayList }" varStatus="status">
			    		<tr>
							<td>${outlayVo.num }</td>
							<td>${outlayVo.use1 }</td>
							<td>${outlayVo.cash }</td>
							<td>${outlayVo.card }</td>
							<td>${outlayVo.used_disposal }</td>		
							<td>${outlayVo.date1 }</td>
							<td>${outlayVo.memo1 }</td>				
							<td>
								<c:if test="${memberVo.id eq outlayVo.id }">
									<a href="/teamproject/bookUpdateForm.do?id=${outlayVo.id }&num=${outlayVo.num}&div=1">
										수정
									</a>
									<br>
									<a href="/teamproject/bookDelete.do?id=${outlayVo.id }&num=${outlayVo.num}&div=1">
										삭제
									</a>
								</c:if>
							</td>
						</tr>
			    	</c:forEach>
			    </c:if>
			</tbody>
		
			<tfoot>
				<tr>
					<td><input type="number" min="1" max="20" placeholder="No." id="num" name="num"></td>
					<td><input type="text" placeholder="사용내역" id="use" name="use1"></td>
					<td><input type="number" min="0" placeholder="현금" id="cash" name="cash"></td>
					<td><input type="number" min="0" placeholder="카드" id="card" name="card"></td>
					<td>					
						<select id="used_disposal" name="used_disposal">						
							<option value="">선택하세요</option>						
		            		<optgroup label="식비">
		            			<option value="주식">주식</option>
		            			<option value="부식">부식</option>
		            			<option value="간식">간식</option>
		            			<option value="외식">외식</option>
		            			<option value="커피/음료">커피/음료</option>
		            			<option value="술/유흥">술/유흥</option>
		            			<option value="기타">기타</option>
		            		</optgroup>
		            		<optgroup label="주거/통신">	            		
		            			<option value="관리비">관리비</option>
		            			<option value="공과금">공과금</option>
		            			<option value="이동통신">이동통신</option>
		            			<option value="인터넷">인터넷</option>
		            			<option value="월세">월세</option>
		            			<option value="기타">기타</option>	            			
		            		</optgroup>
		            		<optgroup label="생활용품">
		            			<option value="가구/가전">가구/가전</option>
		            			<option value="주방/욕실">주방/욕실</option>
		            			<option value="잡화소모">잡화소모</option>
		            			<option value="기타">기타</option>	            			
		            		</optgroup>
		            		<optgroup label="의복/미용">
		            			<option value="의류비">의류비</option>
		            			<option value="패션잡화">패션잡화</option>
		            			<option value="헤어/뷰티">헤어/뷰티</option>
		            			<option value="세탁수선비">세탁수선비</option>
		            			<option value="기타">기타</option>	            			
		            		</optgroup>
		            		<optgroup label="건강/문화">
		            			<option value="운동/레저">운동/레저</option>
		            			<option value="문화생활">문화생활</option>
		            			<option value="여행">여행</option>
		            			<option value="병원비">병원비</option>
		            			<option value="보장성보험">보장성보험</option>
		            			<option value="기타">기타</option>
		            		</optgroup>
		            		<optgroup label="교육/육아">
		            			<option value="등록금">등록금</option>
		            			<option value="학원/교제비">학원/교제비</option>
		            			<option value="육아용품">육아용품</option>
								<option value="기타">기타</option>
		            		</optgroup>  
		            		<optgroup label="교통/차량">
		            			<option value="대중교통비">대중교통비</option>
		            			<option value="주유비">주유비</option>
		            			<option value="자동차보험">자동차보험</option>
		            			<option value="기타">기타</option>
		            		</optgroup>            	
		            		<optgroup label="경조사/회비">
		            			<option value="경조사비">경조사비</option>
		            			<option value="모임회비">모임회비</option>
		            			<option value="데이트">데이트</option>
		            			<option value="선물">선물</option>
		            			<option value="기타">기타</option>	            			
		            		</optgroup>            	
		            		<optgroup label="세금/이자">
		            			<option value="세금">세금</option>
		            			<option value="대출이자">대출이자</option>
		            			<option value="기타">기타</option>	            			
		            		</optgroup>            	
		            		<optgroup label="용돈/기타">
		            			<option value="용돈">용돈</option>
		            			<option value="기타">기타</option>	            			
		            		</optgroup>
		            		<optgroup label="저축/보험">
		            			<option value="예금">예금</option>
		            			<option value="적금">적금</option>
		            			<option value="펀드">펀드</option>
		            			<option value="보험">보험</option>
		            			<option value="투자">투자</option>
		            			<option value="기타">기타</option>
		            		</optgroup>
		            		<optgroup label="카드대금">
		            			<option value="카드대금">카드대금</option>	            			
		            		</optgroup>            	              	                   	          	
		            	</select>	            	
					</td>
					<td><input type="date" id="date1" name="date1"></td>
					<td><input type="text" id="memo1" name="memo1"></td>			
					<td>
						<button type="button" onclick="tableCreate1()">추가</button>
					</td>
				</tr>
			</tfoot>	
		</table>
	</form>
	<br>
	<br>
	
	<input type="button" value="저장" style="float: right; margin-left: 5px;" onclick="document.getElementById('form1').submit();">
	<input type="reset" value="취소" style="float: right; margin-left: 5px;">
	<button type="button" id="excelDownload1" class="download1" style="float: right;"><span>엑셀저장</span></button>
	</div>
	
	<div class="tab_item" id="tab2">
  	<h2 style="text-align: center;">수입</h2> 
  	
  	<!-- 엑셀 파일 저장 테이블 -->
  	<table border="1" id="excel_table2" style="margin: auto; text-align: center; display:none;">
		<colgroup>
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="200px">
			<col width="300px">
			<col width="50px">
		</colgroup>
		<thead>
			<tr>
				<th>No.</th>
		        <th>사용내역</th>
	        	<th>금액</th>
	        	<th>입금통장</th>
	        	<th>수입원분류</th>
	        	<th>날짜</th>
	        	<th>메모/비고</th>
	        	<th></th>	     
	        </tr>
		</thead>
		<tbody id="tbody2">
			<c:if test="${not empty incomeList }">
		    	<c:forEach var="incomeVo" items="${incomeList }" varStatus="status">
		    		<tr>
						<td>${incomeVo.num }</td>
						<td>${incomeVo.use2 }</td>
						<td>${incomeVo.amount }</td>
						<td>${incomeVo.bankbook }</td>
						<td>${incomeVo.import_disposal }</td>		
						<td>${incomeVo.date2 }</td>
						<td>${incomeVo.memo2 }</td>				
						<td>
						</td>
					</tr>
		    	</c:forEach>
		    </c:if>
		</tbody>
	</table>	
	 	
  	<form action="/teamproject/bookReg.do" method="post" onsubmit="return formChk2(this)" id="form2">
		<input type="hidden" name="div" value="2" />
		<table border="1" id="table2" style="margin: auto; text-align: center;">
			<colgroup>
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="200px">
				<col width="300px">
				<col width="50px">
			</colgroup>
			<thead>
				<tr>
					<th>No.</th>
			        <th>사용내역</th>
		        	<th>금액</th>
		        	<th>입금통장</th>
		        	<th>수입원분류</th>
		        	<th>날짜</th>
		        	<th>메모/비고</th>
		        	<th></th>	     
		        </tr>
			</thead>
			<tbody id="tbody2">
				<c:if test="${not empty incomeList }">
			    	<c:forEach var="incomeVo" items="${incomeList }" varStatus="status">
			    		<tr>
							<td>${incomeVo.num }</td>
							<td>${incomeVo.use2 }</td>
							<td>${incomeVo.amount }</td>
							<td>${incomeVo.bankbook }</td>
							<td>${incomeVo.import_disposal }</td>		
							<td>${incomeVo.date2 }</td>
							<td>${incomeVo.memo2 }</td>
							<td>
								<c:if test="${memberVo.id eq incomeVo.id }">
									<a href="/teamproject/bookUpdateForm.do?id=${incomeVo.id }&num=${incomeVo.num}&div=2#tab2">
										수정
									</a>
									<br>
									<a href="/teamproject/bookDelete.do?id=${incomeVo.id }&num=${incomeVo.num}&div=2#tab2">
										삭제
									</a>
								</c:if>
							</td>
						</tr>
			    	</c:forEach>
			    </c:if>
			</tbody>
		
			<tfoot>
				<tr>
					<td><input type="number" min="1" max="20" placeholder="No." id="num2" name="num"></td>
					<td><input type="text" placeholder="사용내역" id="use2" name="use2"></td>
					<td><input type="number" min="0" placeholder="금액" id="amount" name="amount"></td>
					<td><input type="text" placeholder="입금통장" id="bankbook" name="bankbook"></td>
					<td>
						<select id="import_disposal" name="import_disposal">
							<option value="">선택하세요</option>	
		            		<optgroup label="주수입">
		            			<option value="급여">급여</option>
		            			<option value="상여">상여</option>
		            			<option value="사업소득">사업소득</option>	            			
		            			<option value="기타">기타</option>
		            		</optgroup>
		            		<optgroup label="부수입">	            		
		            			<option value="이자/배당금">이자/배당금</option>	            			
		            			<option value="기타">기타</option>	            			
		            		</optgroup>
		            		<optgroup label="전원이월">
		            			<option value="전원이월">전원이월</option>
		            			<option value="잔액조정">잔액조정</option>	            			
		            			<option value="기타">기타</option>	            			
		            		</optgroup>
		            		<optgroup label="저축/보험">
		            			<option value="예금">예금</option>
		            			<option value="적금">적금</option>
		            			<option value="펀드">펀드</option>
		            			<option value="보험">보험</option>
		            			<option value="투자">투자</option>
		            			<option value="기타">기타</option>	
		            		</optgroup>
		            	</select>
		            </td>
					<td><input type="date" id="date2" name="date2"></td>
					<td><input type="text" id="memo2" name="memo2"></td>			
					<td>
						<button type="button" onclick="tableCreate2()">추가</button>
					</td>
				</tr>
			</tfoot>	
		</table>
	</form>
	<br>
	<br>
	
	<input type="button" value="저장" style="float: right; margin-left: 5px;" onclick="document.getElementById('form2').submit();">
	<input type="reset" value="취소" style="float: right; margin-left: 5px;">
	<button type="button" id="excelDownload2" class="download2" style="float: right;"><span>엑셀저장</span></button>
	</div>

</div>

	
 <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.14.3/xlsx.full.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js"></script>
 
 <!-- 캘린더 연동 스크립트 -->
 <script>
 	$(function(){
 		$("div.fc-daygrid-day-top>a").click(function(){
 			console.log(123);
 			var temp = $("h2.fc-toolbar-title").html();		//현재 달력의 년월을 받아옴
 			var ym = temp.replace("년","-").replace("월","").replace(" ","").split("-");
 			var day = $(this).html().replace('일','');
 			
 			if(day < 10){
 				day = '0'+day;
 			}
 			
 			var year = ym[0];
 			var month = (ym[1] < 10) ? '0'+ym[1] : ym[1];
 			var ymd = year+"-"+month+"-"+day
 			
 			var attr = $(location).attr("href");
 			var attrArr = attr.split("#tab");
 			
 			var div = attrArr[1];							// 지출 수입을 구분하기 위한 값
 			
 			location.href="/teamproject/bookList.do?ymd="+ymd+"&div="+div+"#tab"+div;
 		});
 	});
 	
 	 	

 </script>
 <script type="text/javascript" src="js/main.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro@4cac1a6/css/all.css" />

</body>
</html>
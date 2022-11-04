<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자산 수정 </title>
</head>
<body>
<div id="wrap" align="center" class="row">
<!-- 조회 -->
	    <form action="updateAsset.do" method="post" >   
    <table class="table table-striped">
    <thead>
    <tr>
      <th scope="col">아이디</th>
      <th scope="col">자산</th>
      <th scope="col">자산 코드 </th>
      <th scope="col">수량</th>
    </tr>
    </thead>
    <tbody>
	         <tr>  	   
				<td><input type="text" name="userid" value="${param.userid}" readonly></td>
				 <td><input type="text"  name="name" value="${param.name}" readonly></td> 
				 <td><input type="text" name="code" value="${param.code}" readonly></td> 
				 <td><input type="text" name="qty" value="${param.qty}"></td> 		
	         </tr>
  </tbody>
	</table>
	 <input type="submit" value="수정">
	<input type="reset" value="취소">
      </form>
</div>
</body>
</html>
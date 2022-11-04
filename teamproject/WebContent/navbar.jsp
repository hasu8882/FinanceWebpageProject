<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

   
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.rtl.min.css" integrity="sha384-+4j30LffJ4tgIMrq9CwHvn0NjEvmuDCOfk6Rpg2xg7zgOxWWtLtozDEEVvBPgHqE" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js" integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK" crossorigin="anonymous"></script>
   
</head>
<body>
<nav class="navbar navbar-light bg-light">
  <div class="container-fluid">
    <a href="main.do">
      <img src="img/logo.png" alt="" width="150" height="30" class="d-inline-block align-text-top">
    </a>
    <ul class="nav nav-pills">
     <c:if test="${loginUser.admin == 0 }">
    <li class="nav-item dropdown">
    <a class="nav-link dropdown"  href="membermanage.do" >관리자 페이지</a>
  	</li>
    </c:if>
    <c:if test="${loginUser.id != null}">
  <li class="nav-item">
    <a class="nav-link" aria-current="page" href="bookList.do">가계부</a>
  </li>
  </c:if>
  <li class="nav-item dropdown">
    <a class="nav-link dropdown" data-bs-toggle="dropdown" href="#" >금융</a>
    <ul class="dropdown-menu">
   	 	<li><a class="dropdown-item" href="scrape.do">금융홈</a></li>
   	 	<li><a class="dropdown-item" href="stockList.do">종목찾기</a></li>
    	
    </ul>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="bbsList.do">게시판</a>
  </li>
  
 <li class="nav-item dropdown">
 	<c:if test="${loginUser.id != null}">
    <a class="nav-link dropdown" data-bs-toggle="dropdown" href="#" >
		<img src="img/user.png" alt="" width="30" height="30" class="d-inline-block align-text-top">
	</a>
    <ul class="dropdown-menu">
   	 	<li><a class="dropdown-item" href="update.do">회원 정보 수정</a></li>
    	<li><a class="dropdown-item" href="mybank.do">${loginUser.id}님의 가계부</a></li>
    </ul>
    </c:if>
   
    <c:if test="${loginUser.id == null}">
    <a class="nav-link dropdown" data-bs-toggle="dropdown" href="#"  >
		<img src="img/lock.png" alt="" width="30" height="30" class="d-inline-block align-text-top">
	</a>
     <li class="nav-pills">
    	<a class="nav-link active" href="login.do">로그인</a>
  	</li>
    </c:if>
     <c:if test="${loginUser.id != null}">
    <li class="nav-pills">
    <a class="nav-link active" href="logout.do">로그아웃</a>
  </li>
  </c:if>
</ul>
  </div>
</nav>
</body>
</html>
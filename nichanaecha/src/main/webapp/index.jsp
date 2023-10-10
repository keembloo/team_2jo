<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/nichanaecha/css/index.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
</head>
<body>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="header.jsp" %>
	<div class="container-xl">
		<div class="mainimgBox">
			<div class="titleText">누구보다 빠르게!<br>누구보다 정확하게!<br>쉽게하는 중고차 경매</div>
			<div class="blacksolid"></div>
			<img src="/nichanaecha/img/indexCar.jpg">
		</div>
		<div class="linkBox">
			<a href="./map/map.jsp"> <button class="btnGo" type="button">내근처 매물 보러가기 </button></a>
			<a href="./auction/carsubmit.jsp"> <button class="btnGo" type="button">내차경매 등록하기 </button></a>
		</div>
		<!-- 
		<div class="mainimgBox2">
			<img src="/nichanaecha/img/indexCar2.jpg">
		</div>
		
		<a href="/nichanaecha/auction/carinfo.jsp?cno=1"> <button type="button"> 고연진샘플 </button></a>
		 -->
	</div>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="footer.jsp" %>
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	  <script src="/nichanaecha/js/index.js" type="text/javascript"></script>
</body>
</html> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/nichanaecha/css/auction/auction.css" rel="stylesheet">
</head>
<body>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../header.jsp" %>
	<div class="container-xl">
	<h3>경매등록 페이지</h3>
		<form class="carsubmitForm">
			<div class="auctionzone">
				<div class="auctiontitle">
					<span>경매 제목</span>  		
					<input type="text" class="atitle" name="atitle">			
				</div>
				<div class="auctioncontent">
					<span>경매 내용</span>
					<textarea class="acontent" name="acontent" placeholder="내용을 입력해 주세요."></textarea>	
				</div>
				<div class="auctionenddate">
					경매종료 날짜  	<input type="date" class="aenddate" name="aenddate">			
				</div>
				<div class="auctionprice">
					가격  			<input type="number" class="aprice" name="aprice">		
				</div>
			</div>
			<div class="buttonzone">
				<button class="bbtn" onclick="auction()" type="button">등록</button>
				<a href="../index.jsp"><button class="bbtn">취소</button></a>
			</div>
		</form>
	</div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ac4a57d8a5927d34020a891fcdbbcbd"></script>
	
	<script src="../js/auction/carsubmit.js" type="text/javascript"></script>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../footer.jsp" %>
</body>
</html>
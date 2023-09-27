<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/nichanaecha/css/auction/carsubmit.css" rel="stylesheet">
</head>
<body>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../header.jsp" %>
	<div class="container-xl">
	<h3>경매등록 페이지</h3>
		<form class="carsubmitForm">
			<div>
				<div>
					경매 제목 : 		<input type="text" class="ccompany">	<br/>
					경매 내용 : 		<input type="text" class="cnum">		<br/>
					경매등록 날짜 : 	<input type="text" class="csize">		<br/>
					경매종료 날짜 : 	<input type="text" class="cc">			<br/>
							
				</div>
				<div>
					<!-- 첨부파일에 사진 첨부하면 사진 보여지는 구간  -->
				</div>
			</div>
			<div>
				<button onclick="" type="button">등록</button>
				<button>취소</button>
			</div>
		</form>
	</div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ac4a57d8a5927d34020a891fcdbbcbd"></script>
	
	<script src="../js/auction/carsubmit.js" type="text/javascript"></script>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../footer.jsp" %>
</body>
</html>
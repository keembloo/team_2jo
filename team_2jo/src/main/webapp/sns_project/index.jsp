<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/team_2jo/sns_project/css/index.css" rel="stylesheet">
</head>
<body>
	<%@include file ="header.jsp" %>

	<div class="wrap"> <!-- 전체구역 -->
	
		<div class="contentbox"> <!-- 게시물1개 -->
			<div class="img_area">
				<img src="/team_2jo\sns_project\img\firy.jpg">
			</div>
			<div class="content">
				<div class="time">30분전</div>		
				<div class="infotext">ㅁㄴㅇㄻㄴㅇㄹㄴㅇㄹㄴㅁㅇ리ㅓㄴ;ㅣㅇ러ㅣ;넝ㅁㄻㅇ집가고싶다아아아아앙</div>		
			</div>
			<button class="btn_update" type="button">수정</button>
			<button onclick="ondelete()" class="btn_delete" type="button">삭제</button>
		</div> <!-- 게시물1개end -->
	
	
	</div><!-- 전체구역 end -->


	<!-- 최신 JQUERY 불러오기 : AJAX 메소드 사용하기 위해 : JS가 외부로부터 통신하기 위해 -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src=js/index.js" type="text/javascript"></script>
	<script src="/team_2jo/sns_project/js/index.js" type="text/javascript"></script>
</body>
</html>
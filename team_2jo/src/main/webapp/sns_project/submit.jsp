<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/team_2jo/sns_project/css/submit.css" rel="stylesheet">
</head>
<body>
	<%@include file ="header.jsp" %>

	<div class="wrap"> <!-- 전체구역 -->
	<h3>글 등록 페이지</h3>
	
	<form class="writeform"> 
		첨부파일: <input class="bfile" name="bfile" type="file" ><br>
		내용: <input class="bcontent" name="bcontent" type="text"><br>
		비밀번호: <input class="bpwd" name="bpwd" type="password"><br>
		내용: <input class="bcontent" name="bcontent" type="text"><br>
		<button onclick="bwrite()"  name="" type="button">등록</button>
		<button type="button">뒤로가기</button><br>
	
	</form>
	
	
	</div><!-- 전체구역 end -->


	<!-- 최신 JQUERY 불러오기 : AJAX 메소드 사용하기 위해 : JS가 외부로부터 통신하기 위해 -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<script src="/team_2jo/sns_project/js/submit.js" type="text/javascript"></script>
</body>
</html>
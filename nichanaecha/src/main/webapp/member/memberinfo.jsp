<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../header.jsp" %>
	<div> <!-- 전체구역  -->
		<div></div> <!-- 회원정보 구역 -->
		<div>
			<div></div> <!-- 포인트구역 -->
			<div></div> <!-- 등록매물정보구역 -->
			<div></div> <!-- 입찰매물정보구역 -->
		</div> <!-- 컨텐츠구역 -->
	</div> <!-- 전체구역 end -->


	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../footer.jsp" %>
	<script src="/nichanaecha/js/member/memberinfo.js" type="text/javascript"></script>
</body>
</html>
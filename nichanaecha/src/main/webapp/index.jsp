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
	<%@include file ="header.jsp" %>
	<div class="container-xl">
		<a href="./auction/carsubmit.jsp"> <button>내차경매 등록하기 </button></a>
	</div>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="footer.jsp" %>
</body>
</html>
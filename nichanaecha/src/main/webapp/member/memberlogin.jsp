<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- memberlogin css 가져오기 -->
	<link href="../css/member/memberlogin.css" rel="stylesheet">

</head>
<body>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../header.jsp" %>
	
	<div class="webcontainer"><!-- 회원가입 전체구역 시작 -->
		<form class="signupForm"><!-- 폼 전송시 각 input에 name속성 -->
			아이디 : <input name="mid" class="mid" type="text">
			<br/>
			비밀번호 : <input name="mpwd" class="mpwd" type="password"> 
			<br/>
		
		
			<!-- 로그인 유효성검사 구역 -->
			<div class="logincheckbox"></div>
			<!-- 아이디/비밀번호찾기 구역 -->
			<div class="findbtnbox">
				<a href="#">아이디찾기</a>
				<a href="#">비밀번호찾기</a>
			</div>
			<br/>
			<button class="signupbtn" onclick="login()" type="button">로그인</button>
		</form>
		
	</div><!-- 회원가입 전체구역 끝 -->

	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../footer.jsp" %>
	<!-- memberlogin JS 가져오기 -->
	<script src="../js/member/memberlogin.js" type="text/javascript"> </script>
</body>
</html>
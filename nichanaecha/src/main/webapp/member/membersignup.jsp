<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="../css/member/membersignup.css" rel = "stylesheet">
</head>
<body>

<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../header.jsp" %>

	
	<div class="container-xl">
		<div><!-- 회원가입box -->
			
			<form class="signupForm" name="signupForm">
				아이디
				<div class="idBox">
					<input onkeyup="idcheck()"type="text" name="mid" class="mid">
				</div>
				<div class="idValBox"><!-- 아이디 유효성 검사  --></div>
				비밀번호
				<div class="pwdBox" >
					<input onkeyup="pwcheck()" type="password" name="mpw" class="mpw">
				</div>
				비밀번호확인
				<div class="pwcheckBox">
					<input onkeyup="pwcheck()" type="password" name="checkPw" class="checkPw">
				</div>
				<div class="valBox"><!-- 비밀번호유효성 --></div>
				전화번호
				<div class="phoneBox">
					<input onkeyup="phonecheck()" type="text" class="mphone" name="mphone" > 
				</div>
				<div class="valphone"><!-- 전화번호유효성 --></div>
				이름
				<div class="nameBox">
					<input type="text" name="mname"> 
				</div>
				주소
				<div class="adsBox">
					<input type="text" name="mads"> 
				</div>
			</form>
			
			<button class="signupBtn" onclick="signup()">회원가입</button>
		
		</div>
	
	</div>
	
	
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../footer.jsp" %>



	<script src="/nichanaecha/js/member/membersignup.js" type="text/javascript"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="" rel = "stylesheet">
</head>
<body>

<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../header.jsp" %>


	<div><!-- 회원가입box -->
		
		<form class="signupForm" name="signupForm">
			아이디
			<div class="idBox">
				<input type="text" name="signupId">
			</div>
			비밀번호
			<div class="pwdBox" >
				<input type="password" name="signupPw">
			</div>
			비밀번호확인
			<div class="pwcheckBox">
				<input type="password" name="checkPw">
			</div>
			전화번호
			<div class="phoneBox">
				<input type="text" name="signupPhone"> 
			</div>
			이름
			<div class="nameBox">
				<input type="text" name="signupName"> 
			</div>
			주소
			<div class="phoneBox">
				<input type="text" name="signupPhone"> 
			</div>
		</form>
		
		<button class="signupBtn" onclick="signup()">회원가입</button>
	
	</div>
	
	
	
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../footer.jsp" %>



	<script src="/nichanaecha/js/member/membersignup.js" type="text/javascript"></script>
</body>
</html>
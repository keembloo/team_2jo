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

	
	<div class="container-xl">
		<div><!-- 회원가입box -->
			
			<form class="signupForm" name="signupForm">
				아이디
				<div class="idBox">
					<input type="text" name="mid" class="mid">
				</div>
				비밀번호
				<div class="pwdBox" >
					<input onkeyup="pwcheck()" type="password" name="mpw" class="mpw">
				</div>
				비밀번호확인
				<div class="pwcheckBox">
					<input onkeyup="pwcheck()" type="password" name="checkPw" class="checkPw">
				</div>
				<div class="valBox"></div>
				전화번호
				<div class="phoneBox">
					<input type="text" name="mphone"> 
				</div>
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
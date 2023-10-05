<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/nichanaecha/css/member/memberedit.css" rel="stylesheet">
</head>
<body>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file="../header.jsp"%>
	<div class="container-xl"> <!-- 전체구역 -->
		<div class="row"> <!-- row  -->
			<div class="lInfo nav flex-column col-3">
				<!-- 사이드바 -->
				<a class="nav-link active" aria-current="page" href="/nichanaecha/member/memberinfo.jsp">마이페이지</a> 
				<a class="nav-link" href="/nichanaecha/member/memberedit.jsp">내정보 수정</a> 
				<a class="nav-link" href="/nichanaecha/member/memberpoint.jsp">포인트 입출금 내역확인</a>
			</div>
			<div class="memberContent col-9">
				<div class="infobox">
					<!-- js에서 출력 -->
				</div>
				<div>
					<div>새로운주소</div>
					<div>새로운연락처</div>
					<div>비밀번호확인</div>
				</div>
			</div>
		</div> <!-- row end -->
	</div> <!-- 전체구역 end -->
	
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file="../footer.jsp"%>
	<script src="/nichanaecha/js/member/memberedit.js"
		type="text/javascript"></script>
</body>
</html>
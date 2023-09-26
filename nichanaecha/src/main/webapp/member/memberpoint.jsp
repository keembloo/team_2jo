<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/nichanaecha/css/member/memberpoint.css" rel="stylesheet">
</head>
<body>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file="../header.jsp"%>
	
	<div class="container-xl">
		<!-- 전체구역  -->
		<div class="row">
			<div class="lInfo nav flex-column col-3">
				<!-- 사이드바 -->
				<a class="nav-link active" aria-current="page" href="/nichanaecha/member/memberinfo.jsp">마이페이지</a> 
				<a class="nav-link" href="#">내정보 수정</a> 
				<a class="nav-link" href="/nichanaecha/member/memberpoint.jsp">포인트 입출금 내역확인</a>
				
			</div>
			<div class="infoContent col-9">
				<!-- 메인페이지정보내용 -->
				<div class="cashInfo">
					<!-- 회원정보,보유포인트 -->
					<!-- js출력 -->
				</div>
				
				<div class="lineBar">
				</div>
				
				<!-- 포인트 입출금내역 -->
				<table> </table>
				
			</div> <!-- infoContent end -->
			
		</div> <!-- row end -->
		
	</div> <!-- 전체구역end -->
	
	
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file="../footer.jsp"%>
	<script src="/nichanaecha/js/member/memberpoint.js"
		type="text/javascript"></script>
</body>
</html>
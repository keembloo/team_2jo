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
	
	<div class="container-xl"> <!-- 전체구역  -->
		<div class="row">
			<div class="lInfo nav flex-column col-3"> <!-- 사이드바 -->
			  <a class="nav-link active" aria-current="page" href="#">마이페이지</a>
			  <a class="nav-link" href="#">내정보</a>
			  <a class="nav-link" href="#">포인트입출금내역</a>
			  <a class="nav-link" href="#">등록차량정보</a>
			  <a class="nav-link disabled" aria-disabled="true">입찰매물정보</a>
			  <a class="nav-link disabled" aria-disabled="true">찜목록</a>
			</div>
			
			<div class="infoContent col-9"> <!-- 메인페이지정보내용 -->
				<div class="cashInfo"> <!-- 회원정보,보유포인트 -->
					<!-- js출력 -->
				</div>
				
				<div class="autionInfo"> <!-- 등록 매물 갯수와 매물 정보 -->
					<!-- js출력 -->
				</div>
				
				<div class="handInfo"> <!-- 입찰 매물 갯수와 매물 정보 -->
					<!-- js출력 -->
				</div>
				
				<div class="wishInfo"> <!-- 찜 갯수와 찜경매 정보 -->
					<!-- js출력 -->
				</div>
			</div> <!-- row end -->
		</div>
	</div> <!-- 전체구역 end -->


	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../footer.jsp" %>
	<script src="/nichanaecha/js/member/memberinfo.js" type="text/javascript"></script>
</body>
</html>


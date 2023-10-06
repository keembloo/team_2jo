<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/nichanaecha/css/member/memberinfo.css" rel="stylesheet">
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
				<a class="nav-link" href="/nichanaecha/member/memberedit.jsp">내정보 수정</a> 
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
				
				<!-- ---------- 등록매물정보구역 ---------- -->
				<div class="autionInfo">
					<!-- 등록 매물 갯수와 매물 정보 -->
					<div class="menuText">
						<!-- js출력 -->
					</div>

				
					<div id="imageCarousel" class="carousel carousel-dark slide">
						<div class="carousel-inner">
							<!--  js 등록매물 제품 출력 -->
							
						</div> <!-- carousel-inner end -->
						<!-- js 캐러셀 버튼 출력 -->

					</div> <!-- imageCarousel end -->
				</div> <!-- autionInfo end -->


				<div class="lineBar">
				</div>

				<!-- ---------- 입찰한 매물정보구역 ---------- -->
				<div class="handInfo">
					<div class="menuText2">
					<!-- 입찰 매물 갯수와 매물 정보 -->
					<!-- js출력 -->
					</div>
					
					
					<div id="imageCarousel2" class="carousel carousel-dark slide">
						<div class="carousel-inner carouselMyauction">
							<!--  js 등록매물 제품 출력 -->
							
						</div> <!-- carousel-inner end -->
						<!-- js 캐러셀 버튼 출력 -->
						
					</div> <!-- imageCarousel end -->
					
				</div> 
				
				<div class="lineBar">
				</div>
				
				<!-- ------------- 찜 정보 구역 ---------------- -->
				
				<!-- 찜 갯수와 찜경매 정보 -->
				<div class="wishInfo">
					<div class="menuText3">
					
					<!-- js출력 -->
					</div>
					
					
					<div id="imageCarousel3" class="carousel carousel-dark slide">
						<div class="carousel-inner carouselMywishlist">
							<!--  js 등록매물 제품 출력 -->
							
						</div> <!-- carousel-inner end -->
						<!-- js 캐러셀 버튼 출력 -->
					
					</div> <!-- imageCarousel end -->
				</div>
			</div>
			<!-- row end -->
		</div>
	</div>
	<!-- 전체구역 end -->


	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file="../footer.jsp"%>
	<script src="/nichanaecha/js/member/memberinfo.js"
		type="text/javascript"></script>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/nichanaecha/css/auction/carinfo.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../header.jsp" %>

	<div class="container-xl allBox"><!-- 전체 구역 -->
	
		<div class="top">22년식 부가티 시론</div>
		
		<div class="middle"><!-- 이미지, 경매상황 -->
			<div>이미지</div>
			<div class="rightBox"><!-- 경매상황 -->
				<div><p>현재입찰가</p><p>177,000,000</p><p>만원</p></div>
				<div class="auctionPayBox">
					<div>최근입찰</div>
					<div class="auctionBox"><!-- js반복문예정 -->
						<div class="auction">
							<li>177,000,000만원</li>
							<li>1시간 전</li>
						</div>
					</div>

				</div>
				<div><p>종료</p><p>13시간전</p>
				</div>
				<div class="buttonBox">
					<button type="button">스크랩</button>
					<button type="button">입찰</button>
				</div>
			</div>
		
		
		</div>
		
		
		<div class="bottom"><!-- 매물상세안내 -->
			<p>매물상세안내</p>
		
		</div>
		
		
	
	</div>






	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../footer.jsp" %>
</body>
</html>
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
	<%@include file="../header.jsp"%>

	<div class="container-xl">
		<!-- 전체구역  -->
		<div class="row">
			<div class="lInfo nav flex-column col-3">
				<!-- 사이드바 -->
				<a class="nav-link active" aria-current="page" href="#">마이페이지</a> <a
					class="nav-link" href="#">내정보</a> <a class="nav-link" href="#">포인트입출금내역</a>
				<a class="nav-link" href="#">등록차량정보</a> <a class="nav-link disabled"
					aria-disabled="true">입찰매물정보</a> <a class="nav-link disabled"
					aria-disabled="true">찜목록</a>
			</div>

			<div class="infoContent col-9">
				<!-- 메인페이지정보내용 -->
				<div class="cashInfo">
					<!-- 회원정보,보유포인트 -->
					<!-- js출력 -->
				</div>

				<div class="autionInfo">
					<!-- 등록 매물 갯수와 매물 정보 -->
					<div class="menuText">
						<div>등록 매물 정보</div>
						<div>등록 차량수 : 총 2대</div>
					</div>


					<div id="imageCarousel" class="carousel carousel-dark slide">



						<div class="carousel-inner">



							<div class="carousel-item active">

								<div class="row row-cols-1 row-cols-md-2 g-4 px-5">

									<div class="col">
										<div class="card">
											<img src="image1.jpg" class="card-img-top" alt="Image 1">
											<div class="card-body">
												<h5 class="card-title">Card title 1</h5>
												<p class="card-text">This is a longer card with
													supporting text.</p>
											</div>
										</div>
									</div>

									<div class="col">
										<div class="card">
											<img src="image2.jpg" class="card-img-top" alt="Image 2">
											<div class="card-body">
												<h5 class="card-title">Card title 2</h5>
												<p class="card-text">This is a longer card with
													supporting text.</p>
											</div>
										</div>
									</div>

								</div>
							</div>

							<div class="carousel-item">
								<div class="row row-cols-1 row-cols-md-2 g-4">
									<div class="col">
										<div class="card">
											<img src="image3.jpg" class="card-img-top" alt="Image 3">
											<div class="card-body">
												<h5 class="card-title">Card title 3</h5>
												<p class="card-text">This is a longer card with
													supporting text.</p>
											</div>
										</div>
									</div>
									<div class="col">
										<div class="card">
											<img src="image4.jpg" class="card-img-top" alt="Image 4">
											<div class="card-body">
												<h5 class="card-title">Card title 4</h5>
												<p class="card-text">This is a longer card with
													supporting text.</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<button class="carousel-control-prev cbtnCss" type="button"
							data-bs-target="#carouselExampleControls" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next cbtnCss" type="button"
							data-bs-target="#carouselExampleControls" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>

					</div>
				</div>

				<div class="handInfo">
					<!-- 입찰 매물 갯수와 매물 정보 -->
					<!-- js출력 -->
				</div>

				<div class="wishInfo">
					<!-- 찜 갯수와 찜경매 정보 -->
					<!-- js출력 -->
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


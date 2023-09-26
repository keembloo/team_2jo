<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="../css/map/map.css" rel="stylesheet">
</head>
<body>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../header.jsp" %>

	<div class="container-xl"> <!-- 메인 구역 -->
			
			<div class="row optionArea py-2 m-0 mb-2 justify-content-between"> <!-- 옵션 구역 -->
				<div class="col-8 row"> <!-- 옵션 선택 박스 -->
					
					<div class="dropdown carType col-2"> <!-- 제조사 -->
						  <button class="dropdown-toggle dropbutton" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
						    제조사
						  </button>
						  <div class="dropdown-menu dropmenu p-3">
							
							<div class="form-check">
							  <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
							  <label class="form-check-label" for="flexCheckDefault">
							    Default checkbox
							  </label>
							</div>
							<div class="form-check">
							  <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
							  <label class="form-check-label" for="flexCheckChecked">
							    Checked checkbox
							  </label>
							</div>
							
						  </div>
					</div>
					
					<div class="dropdown carType col-2"> <!-- 차종 -->
						  <button class="dropdown-toggle dropbutton" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
						    차종
						  </button>
						  <div class="dropdown-menu dropmenu p-3">
							
							<div class="form-check">
							  <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
							  <label class="form-check-label" for="flexCheckDefault">
							    Default checkbox
							  </label>
							</div>
							<div class="form-check">
							  <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
							  <label class="form-check-label" for="flexCheckChecked">
							    Checked checkbox
							  </label>
							</div>
							
						  </div>
					</div>
					
					<div class="dropdown carType col-2"> <!-- 연식 -->
						  <button class="dropdown-toggle dropbutton" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
						    연식
						  </button>
						  <div class="dropdown-menu dropmenu p-3">
							
							<label for="customRange1" class="form-label">Example range</label>
							<input type="range" class="form-range" id="customRange1">
							
						  </div>
					</div>
					
					<div class="dropdown carType col-2"> <!-- 주행거리 -->
						  <button class="dropdown-toggle dropbutton" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
						    주행거리
						  </button>
						  <div class="dropdown-menu dropmenu p-3">
							
							<label for="customRange1" class="form-label">Example range</label>
							<input type="range" class="form-range" id="customRange1">
							
						  </div>
					</div>
					
					<div class="dropdown carType col-2"> <!-- 연료 -->
						  <button class="dropdown-toggle dropbutton" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
						    연료
						  </button>
						  <div class="dropdown-menu dropmenu p-3">
							
							<div class="form-check">
							  <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
							  <label class="form-check-label" for="flexCheckDefault">
							    Default checkbox
							  </label>
							</div>
							<div class="form-check">
							  <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
							  <label class="form-check-label" for="flexCheckChecked">
							    Checked checkbox
							  </label>
							</div>
							
						  </div>
					</div>
					
					<div class="dropdown carType col-2"> <!-- 가격 -->
						  <button class="dropdown-toggle dropbutton" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
						    가격
						  </button>
						  <div class="dropdown-menu dropmenu p-3">
							
							<label for="customRange2" class="form-label">최소 가격</label>
							<input type="range" class="form-range" min="0" max="5" step="0.5" value="0" id="customRange2">
							
							<label for="customRange2" class="form-label">최대 가격</label>
							<input type="range" class="form-range" min="0" max="5" step="0.5" value="5" id="customRange2">
							
						  </div>
					</div>
					
					
					
					
				
				</div> <!-- 옵션 선택 종료 -->
				
			
				<div class="col-4 text-end"> <!-- 검색 구역 -->
					<span><img src="/nichanaecha/img/searchIcon.png" width="7%">
					<input class="carSearch" type="text" placeholder="주소, 차량명"></span>
				
				</div> <!-- 검색 구역 종료 -->
			
				</div> <!-- 옵션 구역 종료 -->
				
				
				<div> <!-- 지도 구역 -->
				
					<div id="map" style="width:100%;height:800px;">
						<!-- 리스트 출력 구역 -->
						<div class="auctionList">
							
							<div class="row listHeader p-3"><!-- 게시물 출력 헤더 -->
								<div class="listClose col-2"><!-- 닫기 버튼 -->
									<img src="../img/left_arrow.svg" style="width:70%;height:70%">
								</div> 
								<div class="listTitle col-8 text-center">경기도 안산시</div> <!-- 지역 타이틀  -->
								<div class="col-2"></div>
							</div>
								
								<div class="cardList my-1">
									<div class="card mb-1 p-2" style="width:width: 100%;">
									  <img src="../auction/img/Sonata.png" class="card-img-top" alt="...">
									  <div class="card-body">
									    <h5 class="card-title">2017년식 현대 팰리세이드 7인승 SUV 경매</h5>
									    <p class="card-text">7인승 SUV 현대 팰리세이드를 경매로 판매합니다. 대가족 및 모험가를 위한 완벽한 선택입니다.</p>
									  </div>
									  <ul class="list-group list-group-flush">
									    <li class="list-group-item">경매 시작일 : 2023-09-26</li>
									    <li class="list-group-item">경매 종료일 : 2023-09-26</li>
									    <li class="list-group-item">현재 입찰가 : 14,000,000원</li>
									  </ul>
									</div>
									
									<div class="card mb-1 p-2" style="width: 100%;">
									  <img src="../auction/img/Grandeur.png" class="card-img-top" alt="...">
									  <div class="card-body">
									    <h5 class="card-title">2019년형 현대 그랜저 경매</h5>
									    <p class="card-text">프리미엄 세단의 품격을 경험하세요.</p>
									  </div>
									  <ul class="list-group list-group-flush">
									    <li class="list-group-item">경매 시작일 : 2023-10-10</li>
									    <li class="list-group-item">경매 종료일 : 2023-10-15</li>
									    <li class="list-group-item">현재 입찰가 : 21,500,000원</li>
									  </ul>
									</div>
								
								
								</div>
								
							
								
							
							
						</div>
					</div>
				
				</div> <!-- 지도 구역 종료 -->
			
			
			
			<div> <!-- 리스트 출력 구역  -->
			</div> <!-- 리스트 출력 종료  -->
			
	
	 
	</div> <!-- 메인 구역 종료 -->
	
	
	
	<%@include file ="../footer.jsp" %>
	
	<!-- 카카오 지도 api -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4b3eb62505233f4a4ab13fff2d0ea438&libraries=clusterer"></script>
	
	<script src="../js/map/map.js"></script>
	
</body>
</html>
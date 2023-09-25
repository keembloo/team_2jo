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
			
				<div id="map" style="width:100%;height:800px;"></div>
			
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
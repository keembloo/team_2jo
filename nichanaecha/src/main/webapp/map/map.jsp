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
			
			<div class="row"> <!-- 옵션 구역 -->
				<div class="col-6 row"> <!-- 옵션 선택 박스 -->
					
					<div class="dropdown carType col-3"> <!-- 제조사 -->
						  <button class="btn dropdown-toggle dropbutton" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
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
					
					<div class="dropdown carType col-3"> <!-- 차종 -->
						  <button class="btn dropdown-toggle dropbutton" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
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
					
					<div class="dropdown carType col-3"> <!-- 연식 -->
						  <button class="btn dropdown-toggle dropbutton" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
						    연식
						  </button>
						  <div class="dropdown-menu dropmenu p-3">
							
							<label for="customRange1" class="form-label">Example range</label>
							<input type="range" class="form-range" id="customRange1">
							
						  </div>
					</div>
					
					<div class="dropdown carType col-3"> <!-- 주행거리 -->
						  <button class="btn dropdown-toggle dropbutton" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
						    주행거리
						  </button>
						  <div class="dropdown-menu dropmenu p-3">
							
							<label for="customRange1" class="form-label">Example range</label>
							<input type="range" class="form-range" id="customRange1">
							
						  </div>
					</div>
					
					
					
					
					<div> </div>  <!-- 차종 -->
					<div> </div>  <!-- 차종 -->
					<div> </div>  <!-- 차종 -->
				
				</div> <!-- 옵션 선택 종료 -->
				
				<div> <!-- 검색 구역 -->
				
				
				</div> <!-- 검색 구역 종료 -->
			
			</div> <!-- 옵션 구역 종료 -->
			
			
			<div> <!-- 지도 구역 -->
			
			
			
			</div> <!-- 지도 구역 종료 -->
			
			
			
			<div> <!-- 리스트 출력 구역  -->
			
			
			</div> <!-- 리스트 출력 종료  -->
			
	
	 
	</div> <!-- 메인 구역 종료 -->
	
	
	
	<%@include file ="../footer.jsp" %>
	
	
	<script src="../js/map/map.js"></script>
	
</body>
</html>
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
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="현대" id="ccheck1" checked>
							  <label class="form-check-label" for="ccheck1">
							    현대
							  </label>
							</div>
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="기아" id="ccheck2" checked>
							  <label class="form-check-label" for="ccheck2">
							    기아
							  </label>
							</div>
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="르노삼성" id="ccheck3" checked>
							  <label class="form-check-label" for="ccheck3">
							    르노삼성
							  </label>
							</div>
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="쉐보레" id="ccheck4" checked>
							  <label class="form-check-label" for="ccheck4">
							    쉐보레
							  </label>
							</div>
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="쌍용" id="ccheck5" checked>
							  <label class="form-check-label" for="ccheck5">
							    쌍용
							  </label>
							</div>
							
						  </div>
					</div>
					
					<div class="dropdown carType col-2"> <!-- 차종 -->
						  <button class="dropdown-toggle dropbutton" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
						    차종
						  </button>
						  <div class="dropdown-menu dropmenu p-3">
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="대형" id="scheck1" checked>
							  <label class="form-check-label" for="scheck1">
							    대형
							  </label>
							</div>
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="중형" id="scheck2" checked>
							  <label class="form-check-label" for="scheck2">
							    중형
							  </label>
							</div>
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="준중형" id="scheck3" checked>
							  <label class="form-check-label" for="scheck3">
							    준중형
							  </label>
							</div>
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="소형" id="scheck4" checked>
							  <label class="form-check-label" for="scheck4">
							    소형
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
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="휘발유" id="ccheck1" checked>
							  <label class="form-check-label" for="ccheck1">
							    휘발유
							  </label>
							</div>
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="디젤" id="ccheck2" checked>
							  <label class="form-check-label" for="ccheck2">
							    디젤
							  </label>
							</div>
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="LPG" id="ccheck3" checked>
							  <label class="form-check-label" for="ccheck3">
							    LPG
							  </label>
							</div>
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="하이브리드" id="ccheck4" checked>
							  <label class="form-check-label" for="ccheck4">
							    하이브리드
							  </label>
							</div>
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="전기" id="ccheck5" checked>
							  <label class="form-check-label" for="ccheck5">
							    전기
							  </label>
							</div>
							
							<div class="form-check py-1">
							  <input class="form-check-input" type="checkbox" value="수소" id="ccheck5" checked>
							  <label class="form-check-label" for="ccheck5">
							    수소
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
					<span><img onclick="adsSearch()" src="/nichanaecha/img/searchIcon.png" width="7%">
					<input class="SearchBox adsSearch" type="text" placeholder="주소 검색 [지번, 도로명]"></span>
				</div> <!-- 검색 구역 종료 -->
			
				</div> <!-- 옵션 구역 종료 -->
				
				
				<div> <!-- 지도 구역 -->
				
					<div id="map" style="width:100%;height:800px;">
						<!-- 리스트 출력 구역 -->
						<div class="auctionList">
							
							<div class="row listHeader p-3"><!-- 게시물 출력 헤더 -->
								<div onclick="listClose()" class="listClose col-2"><!-- 닫기 버튼 -->
									<img src="../img/left_arrow.svg" style="width:70%;height:70%">
								</div> 
								
								<div class="listTitle col-8 text-center"></div> <!-- 지역 타이틀  -->
								
								<div class="col-2"></div>
								
							</div>
							
							<div class="row justify-content-center">
								<div class="col-8 border-bottom"> <!-- 검색 구역 -->
										<span><img src="/nichanaecha/img/searchIcon.png" width="7%">
										<input onkeyup="auctionSearch()" class="SearchBox aucSerchValue" type="text" placeholder="경매 제목, 내용 검색"></span>
								</div> <!-- 검색 구역 종료 -->
							</div>
							
							<div class="cardList my-1">
								<!-- 매물 출력 구역 -->
							</div>
							
							
						</div>
					</div>
				
				</div> <!-- 지도 구역 종료 -->
			
			
			
			<div> <!-- 리스트 출력 구역  -->
			</div> <!-- 리스트 출력 종료  -->
			
	
	 
	</div> <!-- 메인 구역 종료 -->
	
	
	
	<%@include file ="../footer.jsp" %>
	
	<!-- 카카오 지도 api -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4b3eb62505233f4a4ab13fff2d0ea438&libraries=clusterer,services"></script>

	<script src="../js/map/map.js"></script>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/nichanaecha/css/auction/carsubmit.css" rel="stylesheet">
</head>
<body>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../header.jsp" %>
	<div class="container-xl">
	<h3>차량등록 페이지</h3>
		<form class="carsubmitForm">
			<div>
				<div>
					제조사 : 			<select id="ccompany" name="ccompany">
										<option value="기아">기아</option>
										<option value="르노삼성">르노삼성</option>
										<option value="쉐보레">쉐보레</option>
										<option value="쌍용">쌍용</option>
										<option value="현대">현대</option>
									</select>											<br/>
					차량번호 : 		<input type="text" name="cnum" class="cnum">		<br/>
					차량종류 : 		<select id="csize" name="csize"> 
										<option value="대형">대형</option>
										<option value="중형">중형</option>
										<option value="준중형">준중형</option>
										<option value="소형">소형</option>
									</select>											<br/>
					배기량 : 			<input type="text" name="cc" class="cc">			<br/>
					연료 : 			<select id="coil" name="coil">
										<option value="휘발유">휘발유</option>
										<option value="디젤">디젤</option>
										<option value="LPG">LPG</option>
										<option value="하이브리드">하이브리드</option>
										<option value="전기">전기</option>
										<option value="수소">수소</option>
									</select>											<br/>
					차량명 : 			<input type="text" name="cname" class="cname">		<br/>
					제조년월 : 		<input type="month" name="cdate" class="cdate">		<br/>
					KM : 			<input type="text" name="ckm" class="ckm">			<br/>
					
					
					차량 등록 주소 : 	<div id="map" style="width:300px;height:350px;"></div>
									<p><em>지도를 클릭해주세요!</em></p> 
									<div id="clickLatlng"></div>
					차량 사진 : 		<div class="fileDropBox" style="width: 300px; height: 100px; border: 1px solid red;" >
										여기에 드래그 해서 파일을 올려주세요.
									</div>			
				</div>
				<div>
					<!-- 첨부파일에 사진 첨부하면 사진 보여지는 구간  -->
				</div>
			</div>
			<div>
				<button onclick="bcarsubmit()" type="button" >등록</button>
				<button>취소</button>
			</div>
		</form>
	</div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ac4a57d8a5927d34020a891fcdbbcbd&libraries=services"></script>
	
	<script src="../js/auction/carsubmit.js" type="text/javascript"></script>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../footer.jsp" %>
</body>
</html>
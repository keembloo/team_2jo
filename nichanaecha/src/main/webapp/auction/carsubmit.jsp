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
	<div class="webcontainer">
	<h3>등록페이지</h3>
		<form class="carsubmitForm">
			<div>
				<div>
					제목 : 		<input type="text" name="btitle" class="btitle"> <br/>
					가격 : 		<input type="text" name="bprice" class="bprice"> <br/>
					첨부파일 : 	<input type="file" name="bfile" class="bfile"> <br/>
				</div>
				<div>
					<!-- 첨부파일에 사진 첨부하면 사진 보여지는 구간  -->
				</div>
			</div>
			본문 : 		<textarea name="bcontent" class="bcontent" rows="" cols=""></textarea>	<br/>
			<div>
				<button onclick="bcarsubmit()">등록</button>
				<button>취소</button>
			</div>
		</form>
	</div>
	<script src="../js/auction/carsubmit.js" type="text/javascript"></script>
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file ="../footer.jsp" %>
</body>
</html>
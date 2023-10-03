<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/nichanaecha/css/auction/buymember.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
</head>
<body>
	<%@include file ="../header.jsp" %>
	<div class="container-xl allBox"><!-- 전체 구역 -->
	<h2>경매내역</h2>
    <table class="table table-striped">
	  <thead>
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">입찰금액</th>
	      <th scope="col">시간</th>
	      <th scope="col">아이디</th>
	    </tr>
	  </thead>
	  <tbody class="buyno">
	 	<!-- js -->
	  </tbody>
	</table>

	
	
	
	
	
	
	</div>
	
	<%@include file ="../footer.jsp" %>
	
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
   <script src="../js/auction/buymember.js" type="text/javascript"></script>
</body>
</html>
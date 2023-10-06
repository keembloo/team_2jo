<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/nichanaecha/css/member/memberedit.css" rel="stylesheet">
</head>
<body>
<div class="modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file="../header.jsp"%>
	<div class="container-xl"> <!-- 전체구역 -->
		<div class="row"> <!-- row  -->
			<div class="lInfo nav flex-column col-3">
				<!-- 사이드바 -->
				<a class="nav-link active" aria-current="page" href="/nichanaecha/member/memberinfo.jsp">마이페이지</a> 
				<a class="nav-link" href="/nichanaecha/member/memberedit.jsp">내정보 수정</a> 
				<a class="nav-link" href="/nichanaecha/member/memberpoint.jsp">포인트 입출금 내역확인</a>
			</div>
			<div class="memberContent col-9">
				<div class="infobox">
					<!-- js에서 출력 -->
				</div>
				

			</div>	
		</div> <!-- row end -->
	</div> <!-- 전체구역 end -->
	
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file="../footer.jsp"%>
	<script src="/nichanaecha/js/member/memberedit.js"
		type="text/javascript"></script>
</body>
</html>
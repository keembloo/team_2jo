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
	<!-- jsp  파일안에 다른 jsp파일 import -->
	<%@include file="../header.jsp"%>
	
	<!-- 모달1 -->
	<div class="modal fade" id="adressmodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="adressmodalLabel" aria-hidden="true" data-bs-show="false">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="adressmodalLabel">주소 수정하기</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form class="modal-body">
	      	<input class="newDataInput" type="text" placeholder="새로운 주소">
	        <input class="pwcheck" type="password" placeholder="비밀번호 확인" autocomplete="on">
	      </form>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" onclick="resetInput(0)" data-bs-dismiss="modal">닫기</button>
	        <button type="button" class="btn btn-primary" onclick="editDataSend(0)">확인</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 모달2 -->
	<div class="modal fade" id="adressmodal2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="adressmodal2Label" aria-hidden="true" data-bs-show="false">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="adressmodal2Label">연락처 수정하기</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form class="modal-body">
	     	<input class="newDataInput" type="text" placeholder="새로운 연락처">
	        <input class="pwcheck" type="password" placeholder="비밀번호 확인" autocomplete="on">
	      </form>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" onclick="resetInput(1)" data-bs-dismiss="modal">닫기</button>
	        <button type="button" class="btn btn-primary" onclick="editDataSend(1)">확인</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 모달3 -->
	<div class="modal fade" id="adressmodal3" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="adressmodal3Label" aria-hidden="true" data-bs-show="false">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="adressmodal3Label">비밀번호 수정하기</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      	<form>
		        <input class="newDataInput" onkeyup="keyCheck()" type="password" placeholder="새로운 비밀번호" autocomplete="on">
		        <input class="newDataInputCheck" onkeyup="keyCheck()" type="password" placeholder="새로운 비밀번호 확인" autocomplete="on">
		        <input class="pwcheck" type="password" placeholder="기존 비밀번호" autocomplete="on">
	        </form>
	        <div class="checkhtml"></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" onclick="resetInput(2)" data-bs-dismiss="modal">닫기</button>
	        <button type="button" class="btn btn-primary" onclick="editDataSend(2)">확인</button>
	      </div>
	    </div>
	  </div>
	</div>
	
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
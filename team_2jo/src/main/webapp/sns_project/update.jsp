<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>Insert title here</title>
   <link href="/team_2jo/sns_project/css/submit.css" rel="stylesheet">
</head>
<body>
   <%@include file ="header.jsp" %>

   <div class="wrap"> <!-- 전체구역 -->
   <h3>글 수정 페이지</h3>
   
   <form class="updateform"> 
      <p>첨부파일</p>
      <input onchange="prechange(this)" class="bfile" name="bfile" type="file" >
      <div class="preview"> 
      	<img class="preimg" src="">
      </div>
      
      <p>내용</p>
      <textarea class="bcontent" name="bcontent"></textarea>
      <button onclick="bwrite()" class="btn_update1" name="" type="button">수정</button>
      <button class="btn_comback" type="button">뒤로가기</button>
   
   </form>
   
   
   </div><!-- 전체구역 end -->

	
   
   <!-- 최신 JQUERY 불러오기 : AJAX 메소드 사용하기 위해 : JS가 외부로부터 통신하기 위해 -->
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
   
   <script src="/team_2jo/sns_project/js/submit.js" type="text/javascript"></script>
	<script src="./js/update.js"></script>

</body>
</html>
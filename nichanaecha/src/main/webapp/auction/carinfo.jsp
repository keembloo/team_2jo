<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
	<link href="/nichanaecha/css/auction/carinfo.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
</head>
<body>
   <!-- jsp  파일안에 다른 jsp파일 import -->
   <%@include file ="../header.jsp" %>

   <div class="container-xl allBox"><!-- 전체 구역 -->
   
      <div class="top atitle"><!-- js --></div>
      
      <div class="middle"><!-- 이미지, 경매상황 -->
         <!-- 이미지( 캐러셀 시작) -->
         <div id="carouselExample" class="carousel slide">
            <div class="imgbox carousel-inner">
               <!-- carousel-inner : 캐러셀 안에 넣을 이미지 목록 구역   -->
               <!-- carousel-item : 이미지1개당 / active : 현재 보고있는 이미지를 표현해주는 css클래스명  -->
            </div>
            
         
         </div>
         <!-- 이미지 캐러셀 끝 -->
         
         
         
         
         <div class="rightBox"><!-- 경매상황 -->
            <div><p>현재입찰가</p><p class="batPay"><!-- js --></p><p>만원</p></div>
            <div class="auctionPayBox">
               <div>최근입찰</div>
               <div class="auctionBox"><!-- js반복문예정 -->
                  <!-- 
                  <div class="auction">
                     <li>177,000,000만원</li>
                     <li>1시간 전</li>
                  </div>
                   -->
               </div>

            </div>
            <div><p class="auctionState"><!-- js --></p><p class="remain"><!-- js --></p>
            </div>
            <div class="buttonBox">
               <button onclick="clipping()"  type="button">스크랩<span class="state"></span></button>
               <button type="button">입찰</button>
            </div>
         </div>
      
<!-- -------------------------- 모달창 써보기 -------------------------------------------------->

      <!-- Button trigger modal -->
<div class="btn">
    <button  type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
     경매참여
   </button>
   
   <!-- Modal -->
   <div class="modal fade" id="exampleModal"  tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
     <div class="modal-dialog modal-dialog-centered">
       <div class="modal-content">
         <div class="modal-header">
           <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
         </div>
         <div class="modal-body">
              <div>현재가:</div>
              <div >보유금액:</div>
              입찰금액 : <input class="bprice" type="text" >
           
         </div>
         <div class="modal-footer">
           <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
           <button onclick="batting()" type="button" class="btn btn-primary " id="submitBtn" >입찰</button>
         </div>
       </div>
     </div>
   </div>
</div>

<!------------------------- 모달창 써보기 -------------------------->      
      </div>
      
      
      <div class="bottom"><!-- 매물상세안내 -->
         <p>매물상세안내</p>
         
         <div class="detailBox">
            <div class="box1">
               <div class="ccompanyBox">
                  <ul>
                     <li>제조사</li>
                     <li class="ccompany"></li>
                  </ul>
               </div>
               <div class="csizeBox">
                  <ul>
                     <li>차종</li>
                     <li class="csize"></li>
                  </ul>
               </div>
               <div class="cnameBox">
                  <ul>
                     <li>차량명</li>
                     <li class="cname"></li>
                  </ul>
               </div>
            </div>
            
            <div class="box2">
               <div class="coilBox">
                  <ul>
                     <li>연료</li>
                     <li class="coil"></li>
                  </ul>
               </div>
               <div class="ccBox">
                  <ul>
                     <li>배기량</li>
                     <li class="cc"></li>
                  </ul>
               </div>
               <div class="cdateBox">
                  <ul>
                     <li>연식</li>
                     <li class="cdate"></li>
                  </ul>
               </div>
            </div>
            
            <div class="box3">
               <div class="ckmBox">
                  <ul>
                     <li>주행거리</li>
                     <li class="ckm"></li>
                  </ul>
               </div>
               <div class="cadsBox">
                  <ul>
                     <li>차량등록주소</li>
                     <li class="cads"></li>
                  </ul>
               </div>
               <div class="#">
                  <ul>
                     <li></li>
                     <li></li>
                  </ul>
               </div>
            </div>
            
         </div><!-- detailBox -->
         
         <div class="acontent">경매내용</div>
      </div>
      
      
   
   </div>






   <!-- jsp  파일안에 다른 jsp파일 import -->
   <%@include file ="../footer.jsp" %>
   
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
   <script src="../js/auction/carinfo.js" type="text/javascript"></script>
</body>
</html>
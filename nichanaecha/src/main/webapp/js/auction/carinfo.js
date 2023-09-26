console.log('상세페이지출력')

let ano=new URL(location.href).searchParams.get("ano"); //경매게시글번호




//(개별)상세페이지 출력 [9월24일 고연진]--------------------------------------------------------
auctionPrint(29530);

function auctionPrint(ano){
   clipState();
   //if(loginMid==''){location.href='../member/memberlogin.jsp'}
   //경매글 출력(차량정보,게시물정보)

   $.ajax({
        url : "/nichanaecha/AuctionController",     
        method : "get", 
        async: false,  
        data : {ano:ano},      
         success : r=>{
           console.log('경매내용,차정보출력 성공')
           console.log(r);
               
         // 제목
            document.querySelector('.atitle').innerHTML=`${r.atitle}`

            //캐러셀(여러개이미지)
            let imgbox=document.querySelector('.imgbox');
            let html=``;
            //객체를 배열로 바꾸기
            Object.values(r.car.imglist).forEach((img,i)=>{
              //첫번째 이미지만 active
              html+=`<div class="carousel-item ${ i==0 ? 'active' : '' }">
                        <img src="/nichanaecha/auction/img/${img}" class="d-block w-100" alt="...">
                     </div>`
           })
            imgbox.innerHTML=html;
            
            //차량정보
            document.querySelector('.batPay').innerHTML=`${r.car.ccompany}`
            document.querySelector('.csize').innerHTML=`${r.car.csize}`
            document.querySelector('.cname').innerHTML=`${r.car.cname}`
            document.querySelector('.coil').innerHTML=`${r.car.coil}`
            document.querySelector('.cc').innerHTML=`${r.car.cc}`
            document.querySelector('.cdate').innerHTML=`${r.car.cdate}`
            document.querySelector('.ckm').innerHTML=`${r.car.ckm}`
            document.querySelector('.cads').innerHTML=`${r.car.cads}`
            document.querySelector('.acontent').innerHTML=`${r.acontent}`
            
            
            //경매상태
            
            
            //남은시간
            let 
         } ,       
         error : e=>{console.log('통신실패');console.log(e)} ,         
   });

}

/* 남은 시간 구하는 방법-----------------------------------------------------------


// 기준 날짜 설정 (예: 2023년 9월 30일 12:00:00)
var 기준날짜 = new Date('2023-09-30T12:00:00Z').getTime();

// 타이머 업데이트 함수
function 업데이트타이머() {
    // 현재 시간 구하기
    var 현재시간 = new Date().getTime();

    // 기준 날짜와 현재 시간 사이의 차이 계산
    var 차이 = 기준날짜 - 현재시간;

    if (차이 <= 0) {
        // 기준 날짜가 지남
        document.getElementById('타이머').innerHTML = '시간 종료';
    } else {
        // 시간, 분, 초 계산
        var 시간 = Math.floor(차이 / (1000 * 60 * 60));
        var 분 = Math.floor((차이 % (1000 * 60 * 60)) / (1000 * 60));
        var 초 = Math.floor((차이 % (1000 * 60)) / 1000);

        // 결과를 화면에 표시
        document.getElementById('타이머').innerHTML = 시간 + '시간 ' + 분 + '분 ' + 초 + '초';
    }
}

// 1초마다 타이머 업데이트 호출
setInterval(업데이트타이머, 1000);

// 페이지 열었을 때 초기 타이머 설정
업데이트타이머();




 */









//스크랩 기능 [9월24일 고연진] -onclick()--------------------------------------------------------
/* 
   기능: 버튼 누를 시 wishlist 테이블에 추가되고 스크립트 모양 변경
   필요한거: 회원번호, 게시물번호
*/

function clipping(){
   console.log('스크랩onclick함수 실행')
   //회원에 한해 사용 가능하도록
   if(loginMid==''){location.href='../member/memberlogin.jsp'}
   $.ajax({
         url : "/nichanaecha/WishListController",     
        method : "post",  
        async: false, 
        data : {ano:ano}, //회원번호는 세션에 저장(전달x)      
         success : r=>{console.log('통신성공');console.log(r)
            if(r){
              console.log('스크랩성공');
              clipState();
            
            }
         } ,       
         error : e=>{console.log(e)} ,         
   });


}//f()

// 스크랩 성공 시 출력되는 함수 [9월24일 고연진]-------------------------------------------------------
/*
   clipping() 함수 성공 시 실행 . 스크랩 성공 시 아이콘 변경.
   유효성검사:비로그인시, 로그인 시 
 */

function clipState(){
   let state= document.querySelector('.state');
   //비회원
   if(localStorage==false){state.innerHTML='♡'}
   //회원
     $.ajax({
         url : "/nichanaecha/WishListController",     
          method : "get",
         async : false ,   
         data : {type:'findByWish',ano:ano},      
         success : r=>{console.log('통신성공'+r)
            if(r){state.innerHTML='♥'}//찜하기성공
            else{state.innerHTML='♡'}//찜취소
         } ,       
         error : e=>{console.log(e)} ,         
   });

}//f()



// 입찰 등록 [9월26일 고연진]-------------------------------------------------------------------------

function batting(){console.log('batting() 실행')
   if(loginMid==''){location.href=location.href='/nichanaecha/member/memberlogin.jsp'}
   let bprice = document.querySelector('.bprice').value;
   $.ajax({
      	url : "/nichanaecha/BattingController",     
     	method : "post",   
     	data : {ano:ano,bprice:bprice},      
      	success : r=>{console.log('통신성공');console.log(r)
      		if(r){
				alert('입찰등록 성공');
				console.log('입찰버튼눌림');
				document.getElementById('submitBtn').addEventListener('click', function() {
 				 $('#myModal').modal('hide');
					});

      		}
      		else{alert('입찰등록 실패')}
      	} ,       
      	error : e=>{console.log('통신실패')} ,         
   });


}//f()


//입찰내역출력[9월26일]------------------------------------------------

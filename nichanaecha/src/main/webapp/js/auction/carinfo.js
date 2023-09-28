
console.log('상세페이지출력')

let ano=new URL(location.href).searchParams.get("ano"); //경매게시글번호




//(개별)상세페이지 출력 [9월24일 고연진]--------------------------------------------------------

auctionPrint(ano);
clipState();
	
function auctionPrint(ano){
   //if(loginMid==''){location.href='../member/memberlogin.jsp'}
   //경매글 출력(차량정보,게시물정보)
	
   $.ajax({
        url : "/nichanaecha/AuctionController",     
        method : "get", 
        async: false,  
        data : {ano:ano},      
        success : r=>{
           console.log('차정보출력성공')
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
            document.querySelector('.ccompany').innerHTML=`${r.car.ccompany}`
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
            //let 
         } ,       
         error : e=>{console.log('전체페이지출력통신실패');console.log(e)} ,         
   });

//등록가격 가져오는 함수 -----------------------------------------------
startPrice(ano);
function startPrice(ano){console.log('등록가격가져오는함수실행')
	 $.ajax({
        url : "/nichanaecha/AuctionController",     
        method : "get", 
        async: false,  
        data : {ano:ano},      
        success : r=>{
           console.log('등록가격 통신 성공')
           console.log(r.aprice);
           document.querySelector('.startPrice').innerHTML=`${r.aprice}원` 
   
         } ,       
         error : e=>{console.log('등록가져오기실패');console.log(e)} ,         
   });
	
	
	
}//f()

//경매가격 가져오는 함수----------------------------------------------------------
aprice(ano)
function aprice(ano){console.log('입찰가격가져오는함수실행')
	$.ajax({
        url : "/nichanaecha/BattingController",     
        method : "get", 
        async: false,  
        data : {type:'price',ano:ano, count:1},      
        success : r=>{
           console.log('현재가격 통신 성공')
           console.log(r);
           console.log(r[0].bprice)
          
          document.querySelector('.aprice').innerHTML=`${r[0].bprice}원` 
   
         } ,       
         error : e=>{console.log('현재가격가져오기실패');console.log(e)} ,         
   });
	
	
	
}//f()


/*		
		console.log('입찰내역출력함수실행됨?')
	    $.ajax({
        url : "/nichanaecha/BattingController",     
        method : "get",   
        async : false ,
        data : {type:'topByBatting',ano:ano },// 최근 추가된 3개의 입찰
         success : r=>{
           console.log('경매상황내용출력성공');console.log(r);

         //1.현재가격 (class="batPay")
         //document.querySelector('.batPay').innerHTML=`${r[0].bprice}`

         //2.경매진행상황(class="auctionBox")출력박스/ 출력물 class="auction"
         let html=``;
         let auctionBox=document.querySelector('.auctionBox');
         r.forEach((b)=>{
            html+=`
                  <div class="auction">
                     <li>${b.bprice}만원</li>
                     <li>${b.bDate}</li>
                  </div>
            
            `
         })
         auctionBox.innerHTML=html;

         } ,       
         error : e=>{console.log('경매상황내용출력실패');+e;} ,         
   });
	


*/
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
   if(loginMid==0){location.href='../member/memberlogin.jsp'; return}
   $.ajax({
         url : "/nichanaecha/WishListController",     
        method : "post",  
        async: false, 
        data : {ano:ano}, //회원번호는 세션에 저장(전달x)      
         success : r=>{console.log('찜하기통신성공');console.log(r)
            if(r){
              console.log('스크랩성공');
              clipState();
            
            }
         } ,       
         error : e=>{console.log(e); console.log('찜하기통신실패')} ,         
   });


}//f()

// 스크랩 성공 시 출력되는 함수 [9월24일 고연진]-------------------------------------------------------
/*
   clipping() 함수 성공 시 실행 . 스크랩 성공 시 아이콘 변경.
   유효성검사:비로그인시, 로그인 시 
 */
//clipState();
function clipState(){ console.log('찜하기상태변화함수실행')
   let state= document.querySelector('.state');
   //비회원
   if(localStorage == false){state.innerHTML='♡';}
   
   //회원
     $.ajax({
         url : "/nichanaecha/WishListController",     
          method : "get",
         async : false ,   
         data : {type:'findByWish',ano:ano},      
         success : r=>{console.log('찜상태통신성공')
            if(r){state.innerHTML='♥'}//찜하기성공
            else{state.innerHTML='♡'}//찜취소
         } ,       
         error : e=>{console.log(e)} ,         
   });

}//f()



// 입찰 등록 [9월26일 고연진]-------------------------------------------------------------------------
/*
1. 경매참여 누르기 onclick="battingBtn()" 실행
2. (bs) 모달창 열림
3. 보유금액과 현재가격을 가져옴 (class="modalAprice")(class="valMcash")
4. <input> 입력 (class="bprice")
5.유효성검사
 5-1. 보유금액보다 클 것
 5-2. 이전 입찰테이블의 가격보다 클 것
 5-3. 조건에 만족하지 않을 시 onkey을 통해 알림. 버튼 비활성화 상태
6. 입찰추가클릭onclick="batting()"  실행
7. 클라이언트소켓.send(JOSON.stringify(bprice))
8. 서버소켓에서 onMessage(Session session,String bprice)
9. 전달 받은 내용을 누적뿌려주기 (class="auction")




 */
function battingBtn(){console.log('battingBtn() 실행')
   if(loginMid==''){location.href=location.href='/nichanaecha/member/memberlogin.jsp'}
  	


 

}//f()


//입찰내역출력[9월26일]------------------------------------------------
function batPrint(ano){
		console.log('입찰내역출력함수실행됨?')
	    $.ajax({
        url : "/nichanaecha/BattingController",     
        method : "get",   
        async : false ,
        data : {type:'topByBatting',ano:ano,count:3 },// 최근 추가된 3개의 입찰
         success : r=>{
           console.log('경매상황내용출력성공');console.log(r);

         //1.현재가격 (class="batPay")
         //document.querySelector('.batPay').innerHTML=`${r[0].bprice}`

         //2.경매진행상황(class="auctionBox")출력박스/ 출력물 class="auction"
         let html=``;
         let auctionBox=document.querySelector('.auctionBox');
         r.forEach((b)=>{
            html+=`
                  <div class="auction">
                     <li>${b.bprice}만원</li>
                     <li>${b.bdate}</li>
                  </div>
            
            `
         })
         auctionBox.innerHTML=html;

         } ,       
         error : e=>{console.log('경매상황내용출력실패');+e;} ,         
   });
	
	
	
}

//소켓 통신---------------------------------------------------------

let clientSocket= new WebSocket('ws://localhost:80/nichanaecha/BattingSocket')
console.log('클라이언트소켓생성')
clientSocket.onopen=e=>{console.log('클라이언트소켓열림')
	}//socket(e)

clientSocket.onmessage=e=>{onMsg(e);}
//서버에 메시지 전송--------------------------------------
function batting(){
	//1.값호출
	let bprice = document.querySelector('.bprice').value;
    console.log('입력받음금액>'); console.log(bprice);
    //2.유효성검사
    
    //3. 메시지전송
    clientSocket.send(JSON.stringify(bprice));
    //document.querySelector('.msg').value=``
    
	
}//f()
	
//메시지 받았을때 -----------------------------------------
function onMsg(e){
	console.log('서버소켓으로부터 받은 객ㅊㅔ');console.log(e);
	console.log('가격얼마> '); console.log(e.data);
	
}//f()

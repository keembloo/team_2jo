
console.log('carinfo.js 실행')

let ano=new URL(location.href).searchParams.get("ano"); //경매게시글번호
//console.log('전달받은게시물번호')
//console.log(ano)
let timer=0; //시간변수.남은시간
let x; // 종료날짜(문자)
let timerInter;
let mcash=0;//회원보유금액
let nowPay=0; 
let valPay=[false,false] //보유금액,현재가
let bprice = document.querySelector('.bprice').value;
let valCheck=document.querySelector('.valCheck'); //입찰금액유효성검사 위치

//(개별)상세페이지 출력 [9월24일 고연진]--------------------------------------------------------

clipState();
auctionPrint(ano);

	
function auctionPrint(ano){
	console.log('상세페이지출력')
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
		 // 시작입찰가
		 	document.querySelector('.startPrice').innerHTML=`${r.aprice}원` 

/*
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
*/            
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
            
         //남은시간
           
           x = `${r.aenddate}`;
   		  console.log('종료날짜'+x);
   			settimer();
         
            
            
            
            
         } ,       
         error : e=>{console.log('상세페이지통신실패');console.log(e)} ,         
   });

	aprice(ano)


}//f()


//경매가격 가져오는 함수----------------------------------------------------------

function aprice(ano){console.log('입찰가격가져오는함수실행')
	$.ajax({
        url : "/nichanaecha/BattingController",     
        method : "get", 
        async: false,  
        data : {type:'price',ano:ano, count:1},      
        success : r=>{
           console.log('현재가격 통신 성공')
           console.log(r);
           console.log(r[0].bprice)//최근입찰가격
           console.log(r[0].bDate)//경매추가날짜
          nowPay=r[0].bprice
          document.querySelector('.aprice').innerHTML=`${r[0].bprice}원` 
         
   		

   			
         } ,       
         error : e=>{console.log('현재가격가져오기실패');console.log(e)} ,         
   });
	
	
	
}//f()

//날짜 변환 함수(문자->숫자로 바꿔서 객체만듦)------------------------------------------------------

function endDate(x){console.log('날짜변환함수실행')
	let dateTime = x.split(" "); 
	//console.log(dateTime);
	let date = dateTime[0]; 
	//console.log('날짜/시간쪼개기 날짜' +date);
	let time = dateTime[1]; 
	//console.log('시간' + time);
	
	let endDate=date.split("-");
	let endYear=parseInt(endDate[0]); 
	//console.log('끝나는연도'+endYear)
	let endMonth=parseInt(endDate[1]); 
	//console.log('끝나는달'+endMonth)
	let endDay=parseInt(endDate[2]); 
	//console.log('끝나는날짜'+endDay);
	
	let endTime=time.split(":");
	let endHour=parseInt(endTime[0]); 
	//console.log('끝나는시간'+endHour)
	let endMinutes=parseInt(endTime[1]); 
	//console.log('끝나는분'+endMinutes)
	let endSecond=parseInt(endTime[2]);
	//console.log('끝나는초'+endSecond)
	
	return new Date(endYear,endMonth,endDay,endHour,endMinutes,endSecond); 
}//f()

//타이머 함수-----------------------------------------------


function settimer(){ 
	console.log('타이머함수실행')
	//숫자로 바꾼 날짜
	let endTime=endDate(x);
	//console.log('날짜변화함수에서 결과값 돌려 받음?????')
	// console.log(endTime)

	timerInter=setInterval(()=>{
	
		let nowTime=new Date();//현재시간
		timer=endTime-nowTime;

		document.querySelector('.auctionState').innerHTML=`종료까지`;
		let days=Math.floor(timer/(1000*60*60*24)); 
		//console.log('남은날짜'+days);
		let hours=Math.floor((timer/(1000*60*60))%24);
		//console.log('남은시간'+hours);
		let min=Math.floor((timer/(1000*60))%60);
		//console.log('남은분'+min);
		let sec=Math.floor((timer/1000)%60);
		//console.log('남은초'+sec);
		timer--;
		document.querySelector('.remain').innerHTML=`${days}일 ${hours}:${min}:${sec}`
		



		if(timer<=0){
			clearInterval(timerInter);
			//종료 알림
			document.querySelector('.auctionState').innerHTML=`경매종료`;
			// sql 업데이트
			$.ajax({
       		  url : "/nichanaecha/AuctionController",     
      	      method : "put",   
         	  data : {ano:ano},      
          	  success : r=>{
					console.log('경매산태변경 통신성공');
         			console.log(r)
      					} ,       
              error : e=>{console.log('경매상태변경통신실패')} ,         
		   });
		
		//입찰버튼 비활성화
		
		
	  }
		
	},1000)
	
	console.log('타이머함수종료')
	
}//f()






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
         success : r=>{console.log('찜하기통신성공');
            if(r){
              console.log('스크랩성공');
              clipState();
            
            }
         } ,       
         error : e=>{console.log(e); console.log('찜하기통신실패')} ,         
   });//ajax()


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
   if(localStorage == false){
	   console.log('비로그인일때');console.log(localStorage)
	   state.innerHTML='♡';}
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
3. 보유금액과 현재가격을 가져옴 (class="valAprice")(class="valMcash")
4. <input> 입력 (class="bprice")
5.유효성검사
 5-1. 보유금액보다 클 것
 5-2. 이전 입찰테이블의 가격보다 클 것
 5-3. 조건에 만족하지 않을 시 onkey을 통해 알림. 버튼 비활성화 상태
6. 입찰추가클릭onclick="batting()"  실행
7. 등록 성공 시 회원의 보유금액 차감, 기존 회원에게 포인트 돌려줌.
7. 클라이언트소켓.send(JOSON.stringify(bprice))
8. 서버소켓에서 onMessage(Session session,String bprice)
9. 전달 받은 내용을 누적뿌려주기 (class="auction")



 */



function battingBtn(){console.log('battingBtn() 실행')
	if(loginMid==''){location.href=location.href='/nichanaecha/member/memberlogin.jsp'}
	
//보유금액가져오기	
	 $.ajax({
        url : "/nichanaecha/MemberPointController",     
        method : "get",   
        data : {type:'mpointView'},      
        success : r=>{console.log('보유금액가져오기통신성공')
         	console.log('규리쓰컨트롤러에서가져온mno정보');console.log(r);
         	document.querySelector('.valMcash').innerHTML=`${r.mcash}원`
         	mcash=`${r.mcash}`; 
         } ,       
         error : e=>{console.log('보유금액가져오기실패')} ,         
 	  });
 	  
//현재가격
	document.querySelector('.valAprice').innerHTML=	`${nowPay}원`
 	  
 	  
 	  
 	  }//f()
 	  


/*
function valPay(){
	//유효성검사	
	//보유금액보다클때
	if(mcash>bprice){valPay[0]=true; }
	else{valPay[0]=false; valCheck.innerHTML=`보유금액을 넘길 수 없습니다`}
	//현재가보다클때
	valPay[1]=true;
	//보유금액보다크고 현재가보다 작을때 버튼 활성화 &알림창 띄워주기
	if(valPay[true,true]){document.querySelector('.val').disabled=false;}
	
	
}
*/
function batting(){
	let bprice=document.querySelector('.bprice').value
   	$.ajax({
		   
        url : "/nichanaecha/BattingController",     
        method : "post",   
        data : {ano:ano,bprice:bprice},      
        success : r=>{console.log('입찰등록통신성공');console.log(r)
            if(r){
            alert('입찰등록 성공');
            document.querySelector('#closebtn').click()//코드가 실행 된 뒤에 #를 강제로 닫겠다는거임. close안에 기능

            }
            else{alert('입찰등록 실패')}
         } ,       
         error : e=>{console.log('통신실패')} ,         
   });
	
	
	
}//f()




/*
//입찰내역출력[9월26일]------------------------------------------------
function batPrint(ano){
		console.log('입찰내역출력함수실행')
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

*/

//소켓 통신---------------------------------------------------------

let clientSocket= new WebSocket('ws://localhost:80/nichanaecha/BattingSocket')
console.log('클라이언트소켓생성')
clientSocket.onopen=e=>{console.log('클라이언트소켓열림')}//socket(e)
/*
clientSocket.onmessage=e=>{onMsg(e);}
*/


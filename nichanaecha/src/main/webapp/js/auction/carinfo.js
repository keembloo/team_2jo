console.log('carinfo.js 실행')
let ano = 0;



//console.log('전달받은게시물번호')
//console.log(ano)
let timer=0; //시간변수.남은시간
let x; // 종료날짜(문자)
let timerInter;
let mcash=0;//회원보유금액
let nowPay=0; 
let bprice;
let endTime;//종료시간

let clientSocket= new WebSocket('ws://localhost:80/nichanaecha/BattingSocket')
console.log('클라이언트소켓생성')

clientSocket.onopen=e=>{console.log('클라이언트소켓열림')}//socket(e)

clientSocket.onmessage=e=>nowContent(e)

auctionPrint();

//소켓 통신(메세지)---------------------------------------------------------
	
function nowContent(e){
batPrint(ano)
	
}//f()

//(개별)상세페이지 출력 [9월24일 고연진]--------------------------------------------------------

clipState();


	
function auctionPrint(){
	console.log('상세페이지출력')
   //if(loginMid==''){location.href='../member/memberlogin.jsp'}
   //경매글 출력(차량정보,게시물정보)
	//let firstTime=new Date();
	//console.log('현재시간(문자)')
	//console.log(firstTime)
	
   let cno = new URL(location.href).searchParams.get("cno"); //경매게시글번호
   console.log("cno: "+cno)


   $.ajax({
        url : "/nichanaecha/AuctionController",     
        method : "get", 
        async: false,  
        data : {cno : cno},      
        success : r=>{
           console.log('차정보출력성공')
           console.log("r.ano : "+r.ano);
           console.log("ano : "+ano);
           
           ano = r.ano;
           console.log("ano : "+ano)
               
         // 제목
            document.querySelector('.atitle').innerHTML=`${r.atitle}`
		 // 시작입찰가
		 	document.querySelector('.startPrice').innerHTML=`${r.aprice}원` 


            //캐러셀(여러개이미지)
            let imgbox=document.querySelector('.imgbox');
            let html=``;
            Object.values( r.car.imglist).forEach((img,i)=>{
					  //첫번째 이미지에만 active 삽입
					  html+=`
					  	<div class="carousel-item ${ i==0 ? 'active' : '' }">
					      <img src="/nichanaecha/auction/img/${img}" class="d-block w-100" alt="...">
					    </div>
					  `
				  })
      			imgbox.innerHTML=html;

         document.querySelector('.buymember').innerHTML=
	 
	 	` <a href="/nichanaecha/auction/buymember.jsp?ano=${ano}"><button style="" type="button" >입찰내역</button></a>`
           
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
   		  //console.log('종료날짜'+x);
   			settimer();
   			
   			aprice(ano)
			batPrint(ano)
            
         } ,       
         error : e=>{console.log('상세페이지통신실패');console.log(e)} ,         
   });

	

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
           //console.log(r[0].bprice)//최근입찰가격
           //console.log(r[0].bDate)//경매추가날짜
          nowPay=r[0].bprice
          document.querySelector('.aprice').innerHTML=`${r[0].bprice}원` 
         
   		

   			
         } ,       
         error : e=>{console.log('현재가격가져오기실패');console.log(e)} ,         
   });
	
	
	
}//f()

//날짜 변환 함수(문자->숫자로 바꿔서 객체만듦)------------------------------------------------------

function endDate(z){console.log('날짜변환함수실행')
	let dateTime = z.split(" "); 
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
	
	return new Date(endYear,endMonth-1,endDay,endHour,endMinutes,endSecond); 
}//f()

//타이머 함수-----------------------------------------------
/*
1. 타이머 실행
2. 타이머 종료되면 경매 상태 변경

 */

//?????????????????한달차이 나는거 같음 .. 
//타이머<0 시에 새로고침 없이 AJAX 통신 가능?
function settimer(){ 
	console.log('타이머함수실행')
	//숫자로 바꾼 날짜
	endTime=endDate(x);
	//console.log('날짜변화함수에서 결과값 돌려 받음?????')
	 console.log(endTime)

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
		hours=hours<10?"0"+hours:hours;
		min=min<10?"0"+min:min;
		sec=sec<10?"0"+sec:sec;
		
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
					console.log('경매상태변경 통신성공');
         			console.log(r)
      					} ,       
              error : e=>{console.log('경매상태변경통신실패')} ,         
		   });
		
		//입찰버튼 비활성화
		
		
	  }
		
	},1000)
	
	

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
              alert('스크랩성공')
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

function clipState(){ console.log('찜하기상태변화함수실행')
   let state= document.querySelector('.state');
   
//?????????????????비로그인일때 innerHTML 실행 안됨.   
   //비회원
   if(loginMid == ''){
	   console.log('비로그인일때');console.log(localStorage)
	   state.innerHTML='♡';return}
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



// 입찰등록 관련
/*
1. 경매참여 누르기 onclick="battingBtn()" 실행
2. (bs) 모달창 열림
3. 보유금액 가져옴 (class="valMcash")
4. <input> 입력 (class="bprice")
5.유효성검사
 5-1. 보유금액보다 클 것(조건에 만족하지 않을 시 onkey을 통해 알림. 버튼 비활성화 상태)
 5-2. 이전 입찰테이블의 가격보다 클 것(sql문 실패메세지)
6. 입찰추가클릭onclick="batting()"  실행
7. 등록 성공 시 회원의 보유금액 차감
8. 기존 회원에게 포인트 돌려줌.
	8-1 sql 두번째 사람 번호 가져옴
	8-2 포인트테이블에서 포인트내용과 일치한 것 중 제일 위에 금액 가져옴
	8-2 회원번호에 위 가격 더해줌

 */


//경매참여버튼 누를 시-----------------------------------------------------------
function battingBtn(){console.log('battingBtn() 실행')
	if(loginMid==''){location.href=location.href='/nichanaecha/member/memberlogin.jsp'}
	console.log('경매참여버튼누름')
/*	
//보유금액가져오기	
	 $.ajax({
        url : "/nichanaecha/MypageController",     
        method : "get", 
        async: false,   
        data : {type:'mview'},      
        success : r=>{console.log('보유금액가져오기통신성공')
         	console.log('규리쓰마이페이지컨트롤러에서가져온mno정보');console.log(r);
         	document.querySelector('.valMcash').innerHTML=`${r.mcash}원`
         	mcash=`${r.mcash}`; 
         } ,       
         error : e=>{console.log('보유금액가져오기실패')} ,         
 	  });
*/
 	  
 	  
 }//f()
 	  

// 유효성검사 함수 (onkeyup) -----------------------------------------------------
/*
1. 보유금액 가져오기
2. onkeyup알림
3. 버튼활성화
*/
function valPay(){
	console.log('입력중')
	bprice=document.querySelector('.bprice').value
	let valCheck=document.querySelector('.valCheck'); //입찰금액유효성검사 위치
	//유효성검사 (입력받은값 bprice)	


}//f()



//입찰등록버튼 누를 시[10월3일 고연진]------------------------------------------------------
function batting(){
	bprice = document.querySelector('.bprice').value;
/*
//1.포인트차감
	  $.ajax({
         url : "/nichanaecha/MemberPointController",     
        method : "put",   
        async: false, 
        //포인트내용알려줄 속성 하나 추가해야될듯 ,
        data : {type:3,ano:ano, gold:bprice}, //type3 :포인트출금    
         success : r=>{console.log('포인트차감통신성공')} ,       
         error : e=>{console.log(e)} ,         
   });

*/

//2.입찰등록	
   	$.ajax({
		   
        url : "/nichanaecha/BattingController",     
        method : "post",  
        async: false,  
        data : {ano:ano,bprice:bprice},      
        success : r=>{console.log('입찰등록통신성공');console.log(r)
            if(r){
            alert('입찰등록 성공');
 
 //????????????얘 왜 안됨 ,, 
            bprice.innerHTML=``;
            document.querySelector('#closebtn').click()//코드가 실행 된 뒤에 #를 강제로 닫겠다는거임. close안에 기능

            }
            else{alert('입찰등록 실패')}
         } ,       
         error : e=>{console.log('입찰등록통신실패')} ,         
   });
	
//3.이전 입찰자에게 포인트 돌려줌	
	
}//f()





//입찰내역출력관련------------------------------------------------
/*
1. 입찰내역버튼 클릭 시 전체 내역 출력 [추후 페이징처리] onclick="batView()"	
2. 상세페이지 열렸을 시 최근 3개 출력 위치 class="auction"
3. 소켓통신 출력 class ="auctionSocket" 위치

return new Date(endYear,endMonth,endDay,endHour,endMinutes,endSecond); 

dTime=입장시간(nowTime)-endDate(b.bDate)

 */
function batPrint(ano){
		console.log('입찰내역출력함수실행')

	    $.ajax({
        url : "/nichanaecha/BattingController",     
        method : "get",   
        async : false ,
        data : {type:'topByBatting',ano:ano,count:3 },// 최근 추가된 3개의 입찰
        success : r=>{
        	console.log('상위3개내용출력성공');console.log(r);
        	let html=``;
         	let auctionBox=document.querySelector('.auctionBox');
         	r.forEach((b)=>{
				//console.log('full상위날짜')
				//console.log(`${b.bDate}`); 
	         	//let buyDate=endDate(`${b.bDate}`);
	         	//console.log('상위입찰날짜')
	         	//console.log(buyDate.getDate())
	         	//console.log('상위 분')
	         	//console.log(buyDate.getMinutes())
	         	//console.log('오늘 날짜')
	      
	        	html+=`
	                  <div class="auction">
	                     <li>${b.bprice}만원</li>
	                     <li>${b.bDate}</li>
	                  </div>
	            
	            `
  /*          	
            	if(now.getDate()==buyDate.getDate()&&now.getMonth()==buyDate.getMonth()&&now.getFullYear()==buyDate.getFullYear()){
	            	console.log('날짜가 완전히 동일할때,')
	            	html+=`
	                  <div class="auction">
	                     <li>${b.bprice}만원</li>
	                     <li>${endYear}-${endMinutes}-${endSecond}</li>
	                  </div>
	            
	            `
					
				}
				else{
					console.log('날짜가 다를 때')
					 html+=`
	                  <div class="auction">
	                     <li>${b.bprice}만원</li>
	                     <li>${endHour}:${endMonth}:${endDay}</li>
	                  </div>
	            
	            `
					
				}
  */   
        	
         	})//forEach
         auctionBox.innerHTML=html;

         } ,       
         error : e=>{console.log('경매상황내용출력실패');+e;} ,         
   });
	
	
	
}//f()











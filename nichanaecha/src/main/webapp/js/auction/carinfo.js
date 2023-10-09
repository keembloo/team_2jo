console.log('carinfo.js 실행')

let cno = new URL(location.href).searchParams.get("cno");

let ano = 0;
let timer=0; //시간변수.남은시간
let x; // 종료날짜(문자)
let timerInter;
let mcash=0;//회원보유금액
let astate=4 //경매상태
let nowPay=0; //현재가
let endTime;//종료시간
let gold;//입찰참여금액
let returnMno // 환급받을사람고유번호
let returnBprice//환급받을금액
let price//시작금액 
let getBprice; //입력받은 입찰가

//입찰내용출력을 위한 소켓
let clientSocket= new WebSocket('ws://localhost:80/nichanaecha/BattingSocket')
console.log('클라이언트소켓생성')
clientSocket.onopen=e=>{console.log('클라이언트소켓열림')}//socket(e)
clientSocket.onmessage=e=>nowContent(e)
//페이지출력
auctionPrint(cno);

//입찰상황출력소켓)---------------------------------------------------------
function nowContent(e){
batPrint(ano)
}//f()

//(개별)상세페이지 출력--------------------------------------------------------
function auctionPrint(cno){
	console.log('상세페이지출력함수실행')
	
   	$.ajax({
        url : "/nichanaecha/AuctionController",     
        method : "get", 
        async: false,  
        data : {type:'상세페이지조회',cno : cno},      
        success : r=>{
            console.log('차정보출력성공'); console.log(r)
            ano = r.ano;//경매번호 
            astate=r.astate//경매상태
        	clipState();//스크랩상태변화 
            document.querySelector('.atitle').innerHTML=`${r.atitle}` //경매글제목
		 	// 시작입찰가
		 	let startPrice=document.querySelector('.startPrice')
		 	price=r.aprice;//시작가
         	startPrice.innerHTML=`${price.toLocaleString()}원`
         	
         	//이미지 출력 위치
            let mainCarImg=document.querySelector('.mainCarImg');
           	//이미지객체를 배열로 반환
           	let carImg= Object.values(r.car.imglist)[0]
           	console.log(carImg)
         	
         	mainCarImg.innerHTML=`<img src="/nichanaecha/auction/img/K7.png">`
           
            
			//전체 경매 내역 출력으로 가기 위한 <a>
       		document.querySelector('.buymember').innerHTML=` <a href="/nichanaecha/auction/buymember.jsp?ano=${ano}"><button class="b" type="button" >입찰내역</button></a>`
         	//경매종료 시
         	if(astate!=0){document.querySelector('.valTime1').style.display="none"; document.querySelector('.valTime2').style.display="none"}
	
        	 //차량정보
            document.querySelector('.ccompany').innerHTML=`${r.car.ccompany}`
            document.querySelector('.csize').innerHTML=`${r.car.csize}`
            document.querySelector('.cname').innerHTML=`${r.car.cname}`
            document.querySelector('.coil').innerHTML=`${r.car.coil}`
            document.querySelector('.cc').innerHTML=`${r.car.cc}cc`
            document.querySelector('.cdate').innerHTML=`${r.car.cdate}`
            document.querySelector('.ckm').innerHTML=`${r.car.ckm}km`
            document.querySelector('.cads').innerHTML=`${r.car.cads}`
            document.querySelector('.acontent').innerHTML=`${r.acontent}`
            
        	//경매종료날짜
          	x = `${r.aenddate}`;
   			settimer();
   		    //입찰상황출력
			batPrint(ano)
         } ,       
         error : e=>{console.log('상세페이지통신실패');console.log(e)} ,         
   });

}//f()

//입찰내역출력관련---------------------------------------------------------------------------------
/*
1. 입찰내역버튼 클릭 시 전체 내역 출력 [추후 페이징처리] onclick="batView()"	
2. 처음 열렸을 때, 상위 10개 출력
3. 소켓통신 이후 입찰 출력을 다시 한번 실행해줌
 */
function batPrint(ano){
		console.log('입찰내역출력함수실행')
	    $.ajax({
        url : "/nichanaecha/BattingController",     
        method : "get",   
        async : false ,
        data : {type:'topByBatting',ano:ano,count:10 },// 최근 추가된 10개의 입찰
        success : r=>{
        	console.log('상위10개내용출력성공');//console.log(r);
        	if(r==null){return;} //입찰참여가 처음인 글
        	
        	let html=``;
         	let auctionBox=document.querySelector('.auctionBox');
         	r.forEach((b)=>{
	      		a=b.bprice //입찰가
	        	html+=`
	                  <div class="auction">
	                     <li>${a.toLocaleString()}원</li>
	                     <li>${b.bDate}</li>
	                  </div>
	            `
         	})//forEach
          auctionBox.innerHTML=html;
          nowPay=r[0].bprice; //최신 입찰가
		  document.querySelector('.aprice').innerHTML=`${(r[0].bprice).toLocaleString()}원`
         } ,       
         error : e=>{console.log('경매상황내용출력실패');+e;} ,         
   });
	
}//f()



//경매참여버튼 누를 시-----------------------------------------------------------
function battingBtn(){console.log('battingBtn() 실행')
	if(loginMid==''){location.href=location.href='/nichanaecha/member/memberlogin.jsp'}

	//0.본인은 입찰 참여 불가능
		$.ajax({
	      	url : "/nichanaecha/AuctionController",     
	     	method : "get",   
	     	data : {type:'본인글유효성',ano:ano},   
	            async: false,   
	      	success : r=>{console.log('본인글 유효성 검사 통신성공');console.log(r)} ,       
	      	error : e=>{console.log('본인글 유효성 검사통신실패')} ,         
	   });

	
	//1.보유금액가져오기	
	 $.ajax({
        url : "/nichanaecha/MypageController",     
        method : "get", 
        async: false,   
        data : {type:'mview'},      
        success : r=>{console.log('보유금액가져오기통신성공')
         	//console.log('마이페이지컨트롤러에서가져온mno정보');console.log(r);
         	mcash=r.mcash; //보유금액
         	document.querySelector('.myCash').innerHTML=`${mcash.toLocaleString()}원`
         } ,       
         error : e=>{console.log('보유금액가져오기실패')} ,         
 	  });
 }//f()
 	  


//입찰등록버튼 누를 시[1,2,3 순서 수정 추천]------------------------------------------------------
function batting(){
	console.log('batting()함수실행')
	getBprice = document.querySelector('.bprice'); 
	gold=getBprice.value// 입력받은 입찰가
	//console.log(gold);
	
	//0.경매상태확인하고 종료됐으면 batting() 실행 종료
	let valTime=checkAstate()
	if(valTime==false){return;}
	console.log('1 유효성 검사 후 batting()함수실행')
	//0.보유금액을 넘기지 않도록 유효성
	let valmcash=valMcash(getBprice.value);
	if(valmcash==false){return;}
	console.log('2유효성 검사 후 batting()함수실행')
	//0. 현재가보다 높은 가격
	let valnowpay=valNowPay(getBprice.value);
	if(valnowpay==false){return;}
	console.log('3유효성검사 후 batting()실행')

	//1. 등록 전 가장 상위에 있는 사람에게 금액 돌려줌.
	//console.log('돌려주는 함수 실행됨??')
	getBuyTop(ano)

	//2. 신규 입찰자의 보유금액 차감(update)+포인트테이블 (add)
	  $.ajax({
        url : "/nichanaecha/MemberPointController",     
        method : "put",   
        async: false, 
        data : {type:'입찰참여출금',ano:ano, gold:getBprice.value},  
        success : r=>{console.log('포인트차감통신성공')} ,       
        error : e=>{console.log(e)} ,         
   });


	//3.입찰등록
   	$.ajax({
        url : "/nichanaecha/BattingController",     
        method : "post",  
        async: false,  
        data : {ano:ano,bprice:gold},  
        success : r=>{console.log('입찰등록통신성공'); //console.log(r)
            if(r){
            	alert('입찰등록 성공');
           		getBprice=``;
           		document.querySelector('#closebtn').click()//코드가 실행 된 뒤에 #를 강제로 닫겠다(close안에 기능사용 가능)
             }//i
            else{alert('입찰등록 실패')}
         } ,       
         error : e=>{console.log('입찰등록통신실패')} ,         
   });
	
}//f()

//이전입찰자에게 돈 돌려주는 함수[10월6일 고연진]--------------------------------------
/*
	1.가장 최근에 입찰한 사람의 mno와 가격을 가져옴
	2.결과를 pointcontroller로 전달
 */
function getBuyTop(ano){
	$.ajax({
      	url : "/nichanaecha/BattingController",     
     	method : "get",   
     	data : {type:'getBuyTop',ano:ano},   
        async: false,   
      	success : r=>{ 
			  console.log('배팅컨트롤러에서 상위 한개의 값을 가져오는 아작스 통신성공'); //console.log(r)
			  // ------------------- 아래는 샘플 또는 기존에 입찰가 가 없일때 [ !!! 최초 입찰 ]
			 if(r==null){ return;} 
			  // ------------------- 아래는 샘플 또는 기존에 입찰가 가 있을때 [ 최초 입찰일대 아래 코드가 실행되는 데이터가 없으므로 오류 발생 ]
			  returnMno=r.mno; //환급 받을 회원번호
			  returnBprice=r.bprice//환급 받을 금액
			  $.ajax({
      			url : "/nichanaecha/MemberPointController",     
     			method : "put",   
     			data : {type:'입찰금환급', mno:returnMno, gold:returnBprice},   
         	    async: false,   
      			success : b=>{
					  console.log('환급성공')
      			} ,       
	      			
	     error : e=>{console.log('포인트컨트롤러연동통신실패')} ,         
	  		 });
      		
      	 // ---------------------------------------------------------- 
			  } ,       
      	error : e=>{console.log('최근입찰자정보가져오기 통신실패')} ,         
   });

	
}//f()



//날짜 변환 함수(문자->숫자로 바꿔서 객체만듦)------------------------------------------------------

function endDate(z){console.log('날짜변환함수실행')
	let dateTime = z.split(" "); 
	let date = dateTime[0]; 
	let time = dateTime[1]; 
	let endDate=date.split("-");
	let endYear=parseInt(endDate[0]); 
	let endMonth=parseInt(endDate[1]); 
	let endDay=parseInt(endDate[2]); 
	let endTime=time.split(":");
	let endHour=parseInt(endTime[0]); 
	let endMinutes=parseInt(endTime[1]); 
	let endSecond=parseInt(endTime[2]);
	return new Date(endYear,endMonth-1,endDay,endHour,endMinutes,endSecond); 
}//f()

//타이머 함수-----------------------------------------------
/*
1. 타이머 실행
2. 타이머 종료되면 경매 상태 변경
 */

function settimer(){ 
	console.log('타이머함수실행')
	endTime=endDate(x);//숫자로 바꾼 날짜

	//1초마다함수실행
	timerInter=setInterval(()=>{
	
		let nowTime=new Date();//현재시간
		timer=endTime-nowTime;

		document.querySelector('.auctionState').innerHTML=`종료`;
		let days=Math.floor(timer/(1000*60*60*24)); 
		let hours=Math.floor((timer/(1000*60*60))%24);
		let min=Math.floor((timer/(1000*60))%60);
		let sec=Math.floor((timer/1000)%60);
		hours=hours<10?"0"+hours:hours;
		min=min<10?"0"+min:min;
		sec=sec<10?"0"+sec:sec;
		
		timer--;
		document.querySelector('.remain').innerHTML=`${days}일  ${hours}:${min}:${sec} `
		
		//타이머가 종료 ()
		if(timer<=0){
			clearInterval(timerInter);
			//종료 알림
			document.querySelector('.auctionState').innerHTML=`경매종료`;
			document.querySelector('.remain').innerHTML=``
			document.querySelector('.valTime1').style.display="none"
			document.querySelector('.valTime2').style.display="none"
			
			// sql 업데이트(astate필드 0->1로 변경)
			$.ajax({
       		  url : "/nichanaecha/AuctionController",     
      	      method : "put",   
         	  data : {ano:ano},      
          	  success : r=>{
					console.log('경매상태변경 통신성공');
      					} ,       
              error : e=>{console.log('경매상태변경통신실패')} ,         
		   });
			//입찰자확정함수(10분뒤 확정)
			setTimeout(() => alert('입찰자와 매칭되었습니다')
				,600000);//setTimeout()
		
	  }//타이머종료if
		
	},1000) //setInterval()실행
	

}//f()



//스크랩 기능 onclick()--------------------------------------------------------
/* 
   기능: 버튼 누를 시 wishlist 테이블에 추가되고 스크립트 모양 변경
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
				 clipState();
            }
         } ,       
         error : e=>{console.log(e); console.log('찜하기통신실패')} ,         
   });//ajax()

}//f()


// 스크랩 성공 시 출력되는 함수-------------------------------------------------------
/*
   clipping() 함수 성공 시 실행 . 스크랩 성공 시 아이콘 변경.
 
 */

function clipState(){ console.log('찜하기상태변화함수실행')
   let state= document.querySelector('.state');
  
   //비회원
   if(loginMid == ''){state.innerHTML='♡';return}
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

//경매상태가 0이 아닐 때 종료되는 함수[10월6일 고연진]------------------------------------------------
function checkAstate(){
		 $.ajax({
	        url : "/nichanaecha/AuctionController",     
	        method : "get",   
	        async: false, 
	        data : {type:'거래종료유효성',ano:ano},      
	        success : r=>{
				console.log('거래종료됐음을 확인하는 ajax 통신성공')
	         	astate=r	//거래종료시 상태
	         	if(r!=0){
					 alert('경매가 종료되었습니다');
					 document.querySelector('#closebtn').click()
					 return false;
					 }
	         } ,       
	         error : e=>{console.log('거래종료됐음을 확인하는 ajax 통신실패')} ,         
	   });
} //f()

//보유금액을 넘지 않도록 (유효성검사)---------------------------------------------------
function valMcash(x){
	if(x>mcash){
		alert('보유금액을 확인해주세요');
		document.querySelector('#closebtn').click();
		return false;}
}//f()

//현재가격을 넘기는 유효성검사 ------------------------------------------------------
function valNowPay(x){
	if(x<=nowPay){
		alert('입찰금액은 현재가보다 높여주세요');
		document.querySelector('#closebtn').click();
		return false;
	}
}//f()












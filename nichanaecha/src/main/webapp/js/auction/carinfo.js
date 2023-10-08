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

let clientSocket= new WebSocket('ws://localhost:80/nichanaecha/BattingSocket')
console.log('클라이언트소켓생성')

clientSocket.onopen=e=>{console.log('클라이언트소켓열림')}//socket(e)

clientSocket.onmessage=e=>nowContent(e)

auctionPrint(cno);

//소켓 통신(메세지)---------------------------------------------------------
	
function nowContent(e){
batPrint(ano)
	
}//f()

//(개별)상세페이지 출력 [9월24일 고연진]--------------------------------------------------------

function auctionPrint(cno){console.log('상세페이지출력함수실행')
	
	
   $.ajax({
        url : "/nichanaecha/AuctionController",     
        method : "get", 
        async: false,  
        data : {type:'상세페이지조회',cno : cno},      
        success : r=>{
           console.log('차정보출력성공')
           console.log(r)
           //console.log(r.car.carAddress.cads)
           ano = r.ano;
          // console.log("차 정보에서 가져온 ano >  "+ano)
           astate=r.astate
          // console.log('경매상태'); console.log(astate)
         clipState();      
         // 제목
            document.querySelector('.atitle').innerHTML=`${r.atitle}`
          //  console.log('제목들어옴')
		 // 시작입찰가
		 	let startPrice=document.querySelector('.startPrice')
		 	price=r.aprice;
		 //	console.log('시작입찰가 가져온 가격 > ')
		 //	console.log(price)
         	startPrice.innerHTML=`${price.toLocaleString()}원`
         //	console.log('시작입찰가 ㅇㅋ')
         //이미지 출력
            let imgbox=document.querySelector('.imgbox');
            
		//전체 경매 내역 출력으로 가기 위한 <a>
         document.querySelector('.buymember').innerHTML=
	 		` <a href="/nichanaecha/auction/buymember.jsp?ano=${ano}"><button class="b" type="button" >입찰내역</button></a>`

         //console.log('경매상태' + astate)
         if(astate!=0){
			 document.querySelector('.valTime1').style.display="none"
			 document.querySelector('.valTime2').style.display="none"
			 }
	
		console.log('차량정보 ㅇㅋ')
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
            
           console.log('차량정보끝까지 ㅇㅋ??') 
         //끝나는날짜
           x = `${r.aenddate}`;
   		  //console.log('종료날짜'+x);
   		//  console.log('끝나는 날짜')
   		 // console.log(x)
   			settimer();
   			
   		//입찰내역출력
   		//console.log('배팅함수 출력되야됨!!!!!!!!!!!!!!!!!!!')
			batPrint(ano)
            
         } ,       
         error : e=>{console.log('상세페이지통신실패');console.log(e)} ,         
   });

	

}//f()

//입찰내역출력관련------------------------------------------------
/*
1. 입찰내역버튼 클릭 시 전체 내역 출력 [추후 페이징처리] onclick="batView()"	
2. 처음 열렸을 때, 상위 10개 출력
3. 소켓통신 이후 입찰 출력을 다시 한번 실행해줌

return new Date(endYear,endMonth,endDay,endHour,endMinutes,endSecond); 


 */
function batPrint(ano){
		
		console.log('입찰내역출력함수실행')
		//let bprice=0;
	    $.ajax({
        url : "/nichanaecha/BattingController",     
        method : "get",   
        async : false ,
        data : {type:'topByBatting',ano:ano,count:10 },// 최근 추가된 10개의 입찰
        success : r=>{
        	console.log('상위10개내용출력성공');//console.log(r);
        	if(r==null){return;}
        	
        	let html=``;
         	let auctionBox=document.querySelector('.auctionBox');
         	//let aprice = document.querySelector('.aprice');
         	r.forEach((b)=>{

	      		a=b.bprice
	        	html+=`
	                  <div class="auction">
	                     <li>${a.toLocaleString()}원</li>
	                     <li>${b.bDate}</li>
	                  </div>
	            
	            `

         	})//forEach
         auctionBox.innerHTML=html;
         console.log('상위1등금액 > '); 
         nowPay=r[0].bprice;
         console.log('상위금액'+nowPay);
		document.querySelector('.aprice').innerHTML=`${(r[0].bprice).toLocaleString()}원`
         } ,       
         error : e=>{console.log('경매상황내용출력실패');+e;} ,         
   });
	
	
	
}//f()






//경매참여버튼 누를 시-----------------------------------------------------------
function battingBtn(){console.log('battingBtn() 실행')
	if(loginMid==''){location.href=location.href='/nichanaecha/member/memberlogin.jsp'}
	console.log('경매참여버튼누름')

//0.본인은 입찰 참여 불가능
	$.ajax({
      	url : "/nichanaecha/AuctionController",     
     	method : "get",   
     	data : {type:'본인글유효성',ano:ano},   
            async: false,   
      	success : r=>{console.log('본인글 유효성 검사 통신성공');console.log(r)} ,       
      	error : e=>{console.log('본인글 유효성 검사통신실패')} ,         
   });


//보유금액가져오기	
	 $.ajax({
        url : "/nichanaecha/MypageController",     
        method : "get", 
        async: false,   
        data : {type:'mview'},      
        success : r=>{console.log('보유금액가져오기통신성공')
         	console.log('규리쓰마이페이지컨트롤러에서가져온mno정보');console.log(r);
         	mcash=r.mcash;
         	document.querySelector('.myCash').innerHTML=`${mcash.toLocaleString()}원`
         	//console.log('전역변수에 선언된 가격 변경됨')
         	//console.log(mcash);
         } ,       
         error : e=>{console.log('보유금액가져오기실패')} ,         
 	  });

 	  
 	  
 }//f()
 	  





//입찰등록버튼 누를 시[10월3일 고연진]------------------------------------------------------
function batting(){
	
	//-----입력받은 입찰가  ----------------
	getBprice = document.querySelector('.bprice'); // 
	
	console.log('batting()함수실행')
	gold=getBprice.value
	console.log(gold);
	// -----------------------------
	
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
	console.log('돌려주는 함수 실행됨??')
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


//1.입찰등록	

   	
   	
   	$.ajax({
		   
        url : "/nichanaecha/BattingController",     
        method : "post",  
        async: false,  
        data : {ano:ano,bprice:gold},  // 여기에 bprice 가 없다는데요??       bprice:bprice.value -> bprice:gold   gold로 하면 문제가 없어보이는데 맞을까요?? 잠시만요!
        success : r=>{console.log('입찰등록통신성공');console.log(r)
            if(r){
            alert('입찰등록 성공');

            getBprice=``;
            document.querySelector('#closebtn').click()//코드가 실행 된 뒤에 #를 강제로 닫겠다는거임. close안에 기능
           
            //입찰등록 시 알람메시지 
            	/*
            	1. 입찰한 사람한테 출금 상태 알림
            	2. 글 등록한 사람한테 경매가격변동 알림
            	
            	
            	 */
          // pointAlarm('입찰',gold)

            
  
            

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
			  console.log('배팅컨트롤러에서 상위 한개의 값을 가져오는 아작스 통신성공')
			  console.log(r)
			  // ------------------- 아래는 샘플 또는 기존에 입찰가 가 없일때 [ !!! 최초 입찰 ]
			 if(r==null){ return;} 
			  	
			  // ------------------- 아래는 샘플 또는 기존에 입찰가 가 있을때 [ 최초 입찰일대 아래 코드가 실행되는 데이터가 없으므로 오류 발생 ]
			  
			 // console.log('최근입찰자정보가져오기 통신성공');console.log(r)
			 // console.log('최근입찰자mno> '+r.mno);
			 // console.log('최근입찰가bprice> '+r.bprice)
			  returnMno=r.mno; console.log('최근입찰자mno> '+returnMno);
			  returnBprice=r.bprice
			  $.ajax({
      			url : "/nichanaecha/MemberPointController",     
     			method : "put",   
     			data : {type:'입찰금환급', mno:returnMno, gold:returnBprice},   
         	    async: false,   
      			success : b=>{
					  //console.log('규리님포인트컨트롤러통신성공');console.log(b);
      				  //console.log('환급성공')
      			
      			} ,       
	      			
	     error : e=>{console.log('규리님포인트컨트롤러연동통신실패')} ,         
	  		 });
      		
      		 // ---------------------------------------------------------- 

			  
			  } ,       
      	error : e=>{console.log('최근입찰자정보가져오기 통신실패')} ,         
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

function settimer(){ 
	console.log('타이머함수실행')
	//숫자로 바꾼 날짜
	endTime=endDate(x);
	//console.log('날짜변화함수에서 결과값 돌려 받음?????')
	 console.log(endTime)

	//1초마다함수실행
	timerInter=setInterval(()=>{
	
		let nowTime=new Date();//현재시간
		timer=endTime-nowTime;

		document.querySelector('.auctionState').innerHTML=`종료`;
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
         			//console.log(r)
         			
      					} ,       
              error : e=>{console.log('경매상태변경통신실패')} ,         
		   });
			//입찰자확정함수(10분뒤 확정)
			setTimeout(() => alert('입찰자와 매칭되었습니다')
			 
				,600000);//setTimeout()
			
		
		
	  }//타이머종료if
		
	},1000) //setInterval()실행
	
	

}//f()






//스크랩 기능 [9월24일 고연진] -onclick()--------------------------------------------------------
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
 
 */

function clipState(){ console.log('찜하기상태변화함수실행')
   let state= document.querySelector('.state');
   
  
   //비회원
   if(loginMid == ''){
	  // console.log('비로그인일때');console.log(localStorage)
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





/*
function pointAlarm(x,y){ console.log ('알람함수실행')
				console.log(x);
				console.log(y)
	              $.ajax({
      			  url : "/nichanaecha/AlarmController",     
      			  method : "post",   
        		  async: false, 
        		  data : {type:x,gold:y},      
         		  success : r=>{
					 console.log('[알람등록] 입찰자에게 출금 상태 알림 통신성공');
         		  	 console.log(r)
         		  } ,       
         		  error : e=>{console.log('[알람등록] 입찰자에게 출금 상태 알림 통신실패')} ,         
   			});
	
	
}//f()


*/


//경매상태가 0이 아닐 때 종료되는 함수[10월6일 고연진]------------------------------------------------
function checkAstate(){
		 $.ajax({
	        url : "/nichanaecha/AuctionController",     
	        method : "get",   
	        async: false, 
	        data : {type:'거래종료유효성',ano:ano},      
	        success : r=>{
				console.log('거래종료됐음을 확인하는 ajax 통신성공')
	         	astate=r
	         //	console.log('거래종료되었을때 상태' +r)
	         	
	         	if(r!=0){
					 alert('경매가 종료되었습니다');
					 document.querySelector('#closebtn').click()
					return false;
					 }
	         } ,       
	         error : e=>{console.log('거래종료됐음을 확인하는 ajax 통신실패')} ,         
	   });
} //f()

//보유금액을 넘지 않도록 유효성검사[10월6일 고연진]----------------------------------------------------
function valMcash(x){
	//console.log('valMcash()함수로 넘어온 bprice');
	//console.log(x)
	if(x>mcash){
		alert('보유금액을 확인해주세요');
		document.querySelector('#closebtn').click();
		return false;}
}//f()

//현재가격을 넘기는 유효성검사 [10월6일 고연진]------------------------------------------------------
function valNowPay(x){
	//console.log('valNowPay()로 넘겨받은 값');
	//console.log(nowPay);
	if(x<=nowPay){
		alert('입찰금액은 현재가보다 높여주세요');
		document.querySelector('#closebtn').click();
		return false;
		
	}
}//f()












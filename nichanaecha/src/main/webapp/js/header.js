 
// 1. 현재 로그인된 회원정보 요청
let loginMid = '';	// 로그인 성공된 아이디를 가지고 있는 변수 

//let mno=0;//로그인된 회원 번호

//let ano=0; //접속자가 쓴 글 번호


getMemberInfo();
//console.log('회원번호'+loginMid);
function getMemberInfo(){
	//ajax 이용한 서블릿세션 정보 가져오기
	$.ajax({
		url : "/nichanaecha/MemberFindController",
		method : "get",
		async : false,		// 비동기화(기본값=true), 동기화(false) 설정하는 속성 (우선적인 ajax실행 응답이 필요할때)
		data : { type : "info" },
		success : r => {
			// console.log(r); 
			let submenu = document.querySelector('.submenu')
			let html = ``;	// 로그인 상태에 따른 서로 다른 html 구성
			if( r == null ){// 비로그인
				loginMid = '';
				html += 
					`<li class="nav-item active"><a class="nav-link" href="/nichanaecha/member/membersignup.jsp">회원가입</a></li>
	                 <li class="nav-item"><a class="nav-link" href="/nichanaecha/member/memberlogin.jsp">로그인</a></li>`;
			}else{//로그인
				loginMid = r.mid;
			
			
				html +=
					`<li class="nav-item nav-link"> ${ r.mid } 님 </li>
					 <li class="nav-item"><a onclick="logout()" class="nav-link" href="#">로그아웃</a></li>
	                 <li class="nav-item"><a class="nav-link" href="/nichanaecha/member/memberinfo.jsp">마이페이지</a></li>
	                 
	                 
	                 `;
			}
			// 구성된 html 대입
			submenu.innerHTML = html;
		}, error : e => {}
	});
}

//2. 로그아웃 함수 [서블릿에 세션을 삭제 그리고 로그아웃 성공시 메인페이지로 이동]
function logout(){
	$.ajax({
		url : "/nichanaecha/MemberFindController",
		method : "get",
		data : {type : "logout"},
		success : r => {
			alert('로그아웃 되었습니다.');
			location.href="/nichanaecha/index.jsp";
		},
		error : e => {}
	})
}


//알람 소켓[10월8일 고연진]
/*
	1. 입찰이 추가된 글 찾기
	2. 글의 작성자
	3. 접속된 사람의 아이디와 작성자가 동일하면 메세지 보냄.

 */



let MSocket= new WebSocket(`ws://localhost:80/nichanaecha/AlarmSocket/${loginMid}`);
//console.log('알람관련 클라이언트 소켓 생성');
MSocket.onopen=e=>{
	//console.log('클라이언트소켓열림');
	
}




// 접속한 사람이 등록한 글 가져오기 [수정필요/기능 실행에 필요한 함수는 아님]
function findAuctionMid(){
	//console.log('경매글 작성자 mid 찾는 함수 실행 ')
	  $.ajax({
         url : "/nichanaecha/AuctionController",     
        method : "get",   
        async: false, 
        data : {type:'경매글작성자',loginMid:'loginMid'},      
         success : r=>{console.log('경매글작성자 찾는 ajax 통신성공')
         //	console.log(r)
         //	console.log(r.mid)
         //	console.log(r.ano)
         	ano=r.ano
         } ,       
         error : e=>{console.log('경매글작성자 찾는 ajax 통신실패')} ,         
   });
}






//mno 회원이 참여한 모든 경매글 가져오기
function getBuyAuation(mno){
	  $.ajax({
         url : "/nichanaecha/BattingController",     
        method : "get",   
        async: false, 
        data : {type:'getBuyAuation',mno:mno},      
         success : r=>{console.log('입찰자가 참여한 모든 경매글 가져오는 ajax 통신성공')
         	console.log(r)
         
         } ,       
         error : e=>{console.log('입찰자가 참여한 모든 경매글 가져오는 ajax 통신실패')} ,         
   });

}


MSocket.onclose=e=>{console.log('서버소켓과 통신이 끝났음. 로그아웃 시 출력되야됨 ,,')};


MSocket.onmessage=e=>{ console.log(e); }


//3. 알람메세지 출력 함수[10월7일 고연진] - 미완성
function alarmPrint(x){
	
	
	`<div class="position-relative "><!-- x버튼 누르면 전체 비움. -->
   							 <div class="position-absolute top-0 end-0 alarmBox">
    							<div class="topBox"><!-- js -->
    								<div class="date">2022-12-12</div>
    								<button type="button" class="btn-close" aria-label="Close"></button>
    							</div>
    							<div class="middleBox"><!-- js -->
    								알람내용아아아아ㅏ아아아ㅏ앙
    							</div>
    	
    							<div class="bottomBox"><!-- js -->
    								<button>수락</button>
    								<button>거절</button>
   							 	</div>
    
  							  </div><!-- 상위배치 -->
    					</div><!-- "position-relative" 상위꼭대기배치를위한 박스 -->
	`
	
	
}//f()



// 입출금 관련 알람 등록 [10월7일 고연진]
	// x(문자)= 입찰/환급/입금/출금  , y(숫자)=금액 
function pointAlarm(x,y){ console.log ('입출금관련알람등록 함수실행')
				console.log(x);
				console.log(y);
	              $.ajax({
      			  url : "/nichanaecha/AlarmController",     
      			  method : "post",   
        		  async: false, 
        		  data : {type:x,gold:y},      
         		  success : r=>{
					 console.log('[알람등록] 입출금 상태 알람 통신성공');
         		  	 console.log(r)
         		  } ,       
         		  error : e=>{console.log('[알람등록] 입출금 상태 알림 통신실패')} ,         
   			});
	
	
}//f()
	
	
	
	

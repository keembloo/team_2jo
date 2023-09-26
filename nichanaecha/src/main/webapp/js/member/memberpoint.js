//console.log('포인트실행');
mpointView();
// 규리, 멤버 회원정보 출력
function mpointView(){
	// 로그인된 회원번호 전달 하거나 컨트롤러에서 세션으로 불러와야함 ! 
	//console.log(loginMid);
	if (loginMid == '') {
		alert('로그인된 회원만 볼수 있습니다. 로그인페이지로 돌아갑니다.');
		location.href='/nichanaecha/member/memberlogin.jsp';
		return;
	 }// 로그인된 상태면
		$.ajax({
			url : "/nichanaecha/MemberPointController" , 
			async : false ,
			method : "get" ,
			data : {type : "mpointView"} ,
			success : r => { //console.log("js연결성공");
				//console.log('r.mno : '+r.mno);
				//console.log('r: '+r);
				//console.log('r[0].mno : '+r[0].mno);
				
				let cashInfo = document.querySelector('.cashInfo');

				cashInfo.innerHTML = `<div class="textId">'${r.mname}' 님</div>
									<div class="textPoint">보유 포인트 : <span>${r.mcash.toLocaleString()}원</span></div>
									<button class="btn btn-primary" onclick="inputPoint(${r.mno})" type=button>입금</button>
									<button class="btn btn-danger" onclick="outputPoint(${r.mno},${r.mcash})" type=button>출금</button>`;
				PointAllView();
			} , 
			error : e => {console.log("실패");}		
		})
}


// 규리 입금
function inputPoint(){
	//console.log('입금실행');
	let gold = prompt('입금하실 금액을 입력해주세요');
	if (gold < 10000 ){
		alert('입금 금액은 10000원 이상이어야합니다');
	} else {
		//console.log("mno"+mno);
		//console.log("gold"+gold);
		$.ajax({
				url : "/nichanaecha/MemberPointController" ,
				async : false ,
				method : "put" ,
				data : { type : 1 , gold : gold } , // type 1이면 입금
				success : r => { //console.log("js연결성공");
					alert('입금 성공');
					mpointView();
				} , 
				error : e => {console.log("실패"+e);}
		})
	}
}


// 규리 출금
function outputPoint(mcash){
	//console.log('출금실행');
	let gold = prompt('출금하실 금액을 입력해주세요');
	if ( gold > mcash) {
		alert('출금금액은 보유 포인트를 초과할 수 없습니다');
	} else {
		$.ajax({
				url : "/nichanaecha/MemberPointController" , 
				async : false ,
				method : "put" ,
				data : { type : 2 , gold : gold } ,  // type 2이면 출금
				success : r => { //console.log("js연결성공");
					alert('출금 성공');
					mpointView();
				} , 
				error : e => {console.log("실패"+e);}
		})
	}
}

// 규리 포인트내역 전체 출력 
function PointAllView(){
		$.ajax({
			url : "/nichanaecha/MemberPointController" , 
			async : false ,
			method : "get" ,
			data : { type : "PointAllView" } ,  
			success : r => { //console.log("js연결성공");
				
				
			} , 
			error : e => {console.log("실패"+e);}
		})
}



console.log('마이페이지');
console.log(loginMid);
mview();

function mview(){
	// 로그인된 회원번호 전달 하거나 컨트롤러에서 세션으로 불러와야함 ! 
	console.log(loginMid);
	if (loginMid == '') {
		alert('로그인된 회원만 볼수 있습니다. 로그인페이지로 돌아갑니다.');
		//location.href='/nichanaecha/member/memberlogin.jsp';
		return;
	 }// 로그인된 상태면} 
		
		$.ajax({
			url : "/nichanaecha/MemberController" , 
			async : false ,
			method : "get" ,
			data : {} ,
			success : r => { //console.log("js연결성공");
				//console.log('r.mno : '+r.mno);
				console.log('r: '+r);
				console.log('r[0].mno : '+r[0].mno);
				let rInfo = document.querySelector('.rInfo');
				let lInfo = document.querySelector('.lInfo');
				let bottomInfo = document.querySelector('.bottomInfo');
				let autionInfo = document.querySelector('.autionInfo');
				
				lInfo.innerHTML = `<div>id : ${r[0].mid}</div>
								   <div>전화번호 : ${r[0].mphone}</div>
								   <div>등록 차량 대수 : 2대(추후해야함)</div>
								   <div>입찰 차량 대수 : 2대(추후해야함)</div>`;
						
				rInfo.innerHTML = `<div>${r[0].mname}님</div>
							<div>보유 포인트 : ${r[0].mcash}원</div>
							<button onclick="inputPoint(${r[0].mno})" type=button>입금</button>
							<button onclick="outputPoint(${r[0].mno},${r[0].mcash})" type=button>출금</button>`;
							
				bottomInfo.innerHTML = `<div>
											<div>등록 매물 정보</div>
											<div>차량이미지</div>
											<div>차량 등록번호 : ${r[0].cno}</div>
										</div>`;			
							
				autionInfo.innerHTML = `<div>
											<div>입찰 매물 정보</div>
											<div>차량이미지</div>
											<div>등록번호 : </div>
										</div>`;			

				
			} , 
			error : e => {console.log("실패");}		
		})
}

// 규리 입금
function inputPoint(mno){
	//console.log('입금실행');
	let gold = prompt('입금하실 금액을 입력해주세요');
	if (gold < 10000 ){
		alert('입금 금액은 10000원 이상이어야합니다');
	} else {
		console.log("mno"+mno);
		console.log("gold"+gold);
		$.ajax({
				url : "/nichanaecha/MemberController" ,
				method : "put" ,
				data : { type : 1 , mno : mno , gold : gold } , // type 1이면 입금
				success : r => { //console.log("js연결성공");
					alert('입금 성공');
					mview();
				} , 
				error : e => {console.log("실패"+e);}
		})
	}
}


// 규리 출금
function outputPoint(mno , mcash){
	//console.log('출금실행');
	let gold = prompt('출금하실 금액을 입력해주세요');
	if ( gold > mcash) {
		alert('출금금액은 보유 포인트를 초과할 수 없습니다');
	} else {
		$.ajax({
				url : "/nichanaecha/MemberController" , 
				method : "put" ,
				data : { type : 2 , mno : mno , gold : gold } ,  // type 2이면 출금
				success : r => { //console.log("js연결성공");
					alert('출금 성공');
					mview();
				} , 
				error : e => {console.log("실패"+e);}
		})
	}
}




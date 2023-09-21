//console.log('마이페이지');
//console.log(loginMid);
allView();
// 규리 , 마이페이지 전체 출력 함수
function allView(){ 
	mview(); 
	myAuctionView(); 
}

// 규리, 멤버 회원정보 출력
function mview(){
	// 로그인된 회원번호 전달 하거나 컨트롤러에서 세션으로 불러와야함 ! 
	//console.log(loginMid);
	if (loginMid == '') {
		alert('로그인된 회원만 볼수 있습니다. 로그인페이지로 돌아갑니다.');
		location.href='/nichanaecha/member/memberlogin.jsp';
		return;
	 }// 로그인된 상태면
		$.ajax({
			url : "/nichanaecha/MypageController" , 
			async : false ,
			method : "get" ,
			async : false,
			data : {type : "mview"} ,
			success : r => { //console.log("js연결성공");
				//console.log('r.mno : '+r.mno);
				//console.log('r: '+r);
				//console.log('r[0].mno : '+r[0].mno);
				
				let cashInfo = document.querySelector('.cashInfo');

				cashInfo.innerHTML = `<div class="textId">'${r.mname}' 님</div>
									<div class="textPoint">보유 포인트 : <span>${r.mcash.toLocaleString()}원</span></div>
									<button class="btn btn-primary" onclick="inputPoint(${r.mno})" type=button>입금</button>
									<button class="btn btn-danger" onclick="outputPoint(${r.mno},${r.mcash})" type=button>출금</button>`;
			} , 
			error : e => {console.log("실패");}		
		})
}


// 규리 등록매물정보(캐러셀) 출력
function myAuctionView(){

	$.ajax({
			url : "/nichanaecha/MypageController" , 
			method : "get" ,
			data : { type : "myAuctionView" } ,
			success : jsonArray => { 
				//console.log(jsonArray);
					let count = 0;
					let html ='';
					document.querySelector('.menuText').innerHTML = 
												`<div>나의 등록 매물 정보</div>
												<div>등록 차량수 : 총 ${jsonArray.length}대</div>`;
						
				jsonArray.forEach( (p,i)=>{
					//console.log('카이미지리스트 : '+Object.values(p.carimglist)[0]);
					//Object.values() : 주어진 객체의 속성 값들을 배열로 반환
					
					count++;
					// 제품1개 html 마크업
					html += `<div class="col"> <!-- 제품1개 -->
								<div class="card">
									<a href="/nichanaecha/auction/carinfo.jsp?ano=${p.ano}">
										<img src="/nichanaecha/img/seltosImgSample.jpg" class="card-img-top" alt="Image 1">
										<div class="card-body">
											<h5 class="card-title">${p.atitle}</h5>
											<div class="card-text">경매 등록번호 : ${p.ano}</div>
											<div class="card-text">최소 입찰 금액 : ${p.aprice.toLocaleString()}원</div>
											<div class="card-text">경매 종료 : ${p.aenddate}</div>
										</div>
									</a>
								</div>
							</div>`;
					// <a href="/nichanaecha/auction/carinfo.jsp?ano=${p.ano}"> 클릭시 carinfo 의 url로 ano 보냄
					if (count%2==0) { // 매물 2개씩 담기위해 나눔
						//console.log(html);
						document.querySelector('.carousel-inner').innerHTML += 
						`<div class="carousel-item"> <!-- 제품 2개세트 -->
							<div class="abox2 row row-cols-md-2 g-4 px-5">
							</div>
						</div>`;
						// html 추가해주기 제품1개씩 
						document.querySelectorAll('.abox2')[Math.floor(i/2)].innerHTML = html; // Math.floor(i/2) 인덱스를 2로 나누고 소수점을 버림
						html=''; // 제품 2개씩 넣어줘야하기 때문에 넣고나면 초기화
					} // if end
				}); // forEach end 
				// 첫번째 carousel-item 에 active 클래스를 넣어줘야 실행됨
				document.querySelectorAll('.carousel-item')[0].className += ' active'; // 띄어쓰기 안하면 실행X
			} , 
			error : e => {console.log("실패"+e);}
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
				url : "/nichanaecha/MypageController" ,
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
				url : "/nichanaecha/MypageController" , 
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




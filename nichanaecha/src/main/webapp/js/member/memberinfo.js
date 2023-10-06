//console.log('마이페이지');
//console.log(loginMid);

// 규리 , 마이페이지 전체 출력 함수	
mview(); 
	
	

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
			data : {type : "mview"} ,
			success : r => { //console.log("js연결성공");
				//console.log('r.mno : '+r.mno);
				//console.log(r);
				//console.log('r[0].mno : '+r[0].mno);
				
				let cashInfo = document.querySelector('.cashInfo');

				cashInfo.innerHTML = `<div class="textId">'${r.mname}' 님</div>
									<div class="textPoint">보유 포인트 : <span>${r.mcash.toLocaleString()}원</span></div>`;
				mySubmitcarView();
			} , 
			error : e => {console.log("실패");}		
		})
}


// 규리 등록매물정보(캐러셀) 출력
function mySubmitcarView(){

	$.ajax({
			url : "/nichanaecha/MypageController" , 
			method : "get" ,
			async : false,
			data : { type : "mySubmitcarView" } ,
			success : jsonArray => { 
				//console.log(jsonArray);
				//let count = 0;
				//let html ='';
				document.querySelector('.menuText').innerHTML = 
											`<div>나의 등록 매물 정보</div>
											<div>등록 차량수 : 총 ${jsonArray.length}대</div>`;
				let count = 0;
				let html ='';
				if (jsonArray.length==0) {
					document.querySelector('.carousel-inner').innerHTML += `<div class="notext">등록된 매물이 없습니다.</div>`;
					myAuctionView();
					return;
				}
			

				jsonArray.forEach( (p,i)=>{
					//console.log(p);
					//console.log('카이미지리스트 : '+Object.values(p.car.imglist)[0]);
					//Object.values() : 주어진 객체의 속성 값들을 배열로 반환
	
					count++;
					// 제품1개 html 마크업
					html += `<div class="col"> <!-- 제품1개 -->
								<div class="card">
									<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
										<img src="/nichanaecha/auction/img/seltosImgSample.jpg" class="card-img-top" alt="Image 1">
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
					if(jsonArray.length <3) {count==2;} // jsonArray길이가 3미만일경우 2개로인식해서 출력될수있게
					if (jsonArray.length == 1 || count%2==0) { // 매물 2개씩 담기위해 나눔 /1개일때도 일단출력은되어야함
						//console.log(html);
						document.querySelector('.carousel-inner').innerHTML += 
						`<div class="carousel-item"> <!-- 제품 2개세트 -->
							<div class="abox row row-cols-md-2 g-4 px-5">
							</div>
						</div>`;
						// html 추가해주기 제품1개씩 
						document.querySelectorAll('.abox')[Math.floor(i/2)].innerHTML = html; // Math.floor(i/2) 인덱스를 2로 나누고 소수점을 버림
						html=''; // 제품 2개씩 넣어줘야하기 때문에 넣고나면 초기화
					} // if end
				}); // forEach end 
				// 첫번째 carousel-item 에 active 클래스를 넣어줘야 실행됨
				document.querySelectorAll('.carousel-item')[0].className += ' active'; // 띄어쓰기 안하면 실행X
				document.querySelector('#imageCarousel').innerHTML += `<button class="carousel-control-prev" type="button"
							data-bs-target="#imageCarousel" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						
						<button class="carousel-control-next" type="button"
							data-bs-target="#imageCarousel" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>`;
				myAuctionView(); 
			} , 
			error : e => {console.log("실패"+e);}
		})
}


// 규리 입찰한 매물정보(캐러셀) 출력
function myAuctionView(){
	

	$.ajax({
			url : "/nichanaecha/MypageController" , 
			method : "get" ,
			async : false,
			data : { type : "myAuctionView" } ,
			success : jsonArray => { //console.log(jsonArray);
				document.querySelector('.menuText2').innerHTML = 
											`<div>나의 입찰 매물 정보</div>
											<div>입찰한 경매수 : 총 ${jsonArray.length}개</div>`;
				let count = 0;
				let html ='';
				
				if (jsonArray.length==0) {
					document.querySelector('.carouselMyauction').innerHTML += `<div class="notext">등록된 매물이 없습니다.</div>`;
					myWishlistView();
					return;
				}
				
				jsonArray.forEach( (p,i)=>{
					//console.log(p);
					//console.log('카이미지리스트 : '+Object.values(p.car.imglist)[0]);
					//Object.values() : 주어진 객체의 속성 값들을 배열로 반환
					//console.log('도는거여?');
					count++;
					// 제품1개 html 마크업
					html += `<div class="col"> <!-- 제품1개 -->
								<div class="card">
									<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
										<img src="/nichanaecha/auction/img/seltosImgSample.jpg" class="card-img-top" alt="Image 1">
										<div class="card-body">
											<h5 class="card-title">${p.atitle}</h5>
											<div class="card-text">경매 등록번호 : ${p.ano}</div>
											<div class="card-text">최소 입찰 금액 : ${p.aprice.toLocaleString()}원</div>
											<div class="card-text">경매 종료일 : ${p.aenddate}</div>
										</div>
									</a>
								</div>
							</div>`;
					// <a href="/nichanaecha/auction/carinfo.jsp?ano=${p.ano}"> 클릭시 carinfo 의 url로 ano 보냄
						if (jsonArray.length == 1 || count%2==0) { // 매물 2개씩 담기위해 나눔 /1개일때도 일단출력은되어야함
							//console.log('이프문도나');
							document.querySelector('.carouselMyauction').innerHTML += 
							`<div class="carousel-item carouselth2"> <!-- 제품 2개세트 -->
								<div class="abox2 row row-cols-md-2 g-4 px-5">
								</div>
							</div>`;
							// html 추가해주기 제품1개씩 
							document.querySelectorAll('.abox2')[Math.floor(i/2)].innerHTML = html; // Math.floor(i/2) 인덱스를 2로 나누고 소수점을 버림
							html=''; // 제품 2개씩 넣어줘야하기 때문에 넣고나면 초기화
						} // if end
				}); // forEach end 
				// 첫번째 carousel-item 에 active 클래스를 넣어줘야 실행됨
				document.querySelectorAll('.carouselth2')[0].className += ' active'; // 띄어쓰기 안하면 실행X
				document.querySelector('#imageCarousel2').innerHTML += `<button class="carousel-control-prev" type="button"
							data-bs-target="#imageCarousel" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						
						<button class="carousel-control-next" type="button"
							data-bs-target="#imageCarousel" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>`;
				myWishlistView();
			} , 
			error : e => {console.log("실패"+e);}
		});
}


// 규리 찜한 매물정보(캐러셀) 출력
function myWishlistView(){
	

	$.ajax({
			url : "/nichanaecha/MypageController" , 
			method : "get" ,
			async : false,
			data : { type : "myWishlistView" } ,
			success : jsonArray => { //console.log(jsonArray);
				document.querySelector('.menuText3').innerHTML = 
											`<div>나의 찜한 매물 정보</div>
											<div>찜한 경매수 : 총 ${jsonArray.length}개</div>`;
				let count = 0;
				let html ='';
				
				if (jsonArray.length==0) {
					document.querySelector('.carouselMywishlist').innerHTML += `<div class="notext">찜한 매물이 없습니다.</div>`;
					return;
				}
				
				jsonArray.forEach( (p,i)=>{
					//console.log(p);
					//console.log('카이미지리스트 : '+Object.values(p.car.imglist)[0]);
					//Object.values() : 주어진 객체의 속성 값들을 배열로 반환
					//console.log('도는거여?');
					count++;
					// 제품1개 html 마크업
					html += `<div class="col"> <!-- 제품1개 -->
								<div class="card">
									<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
										<img src="/nichanaecha/auction/img/seltosImgSample.jpg" class="card-img-top" alt="Image 1">
										<div class="card-body">
											<h5 class="card-title">${p.atitle}</h5>
											<div class="card-text">경매 등록번호 : ${p.ano}</div>
											<div class="card-text">최소 입찰 금액 : ${p.aprice.toLocaleString()}원</div>
											<div class="card-text">경매 종료일 : ${p.aenddate}</div>
										</div>
									</a>
								</div>
							</div>`;
					// <a href="/nichanaecha/auction/carinfo.jsp?ano=${p.ano}"> 클릭시 carinfo 의 url로 ano 보냄
						if (jsonArray.length == 1 || count%2==0) { // 매물 2개씩 담기위해 나눔 /1개일때도 일단출력은되어야함
							//console.log('이프문도나');
							document.querySelector('.carouselMywishlist').innerHTML += 
							`<div class="carousel-item carouselth3"> <!-- 제품 2개세트 -->
								<div class="abox3 row row-cols-md-2 g-4 px-5">
								</div>
							</div>`;
							// html 추가해주기 제품1개씩 
							document.querySelectorAll('.abox3')[Math.floor(i/2)].innerHTML = html; // Math.floor(i/2) 인덱스를 2로 나누고 소수점을 버림
							html=''; // 제품 2개씩 넣어줘야하기 때문에 넣고나면 초기화
						} // if end
				}); // forEach end 
				// 첫번째 carousel-item 에 active 클래스를 넣어줘야 실행됨
				document.querySelectorAll('.carouselth3')[0].className += ' active'; // 띄어쓰기 안하면 실행X
				document.querySelector('#imageCarousel3').innerHTML += `<button class="carousel-control-prev" type="button"
							data-bs-target="#imageCarousel" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						
						<button class="carousel-control-next" type="button"
							data-bs-target="#imageCarousel" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>`;
			} , 
			error : e => {console.log("실패"+e);}
		});
}





//console.log('마이페이지');
//console.log(loginMid);

// 최종 입찰자 회원 아이디, 연락처 변수저장
let finalmno = -1;
let finalmid = '';
let finalmphone = '';

// 판매자 회원 아이디, 연락처 변수 저장
let sellermno = -1;
let sellermid = '';
let sellermphone = '';


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


// 규리 1. 등록매물정보(캐러셀) 출력
function mySubmitcarView(){

	$.ajax({
			url : "/nichanaecha/MypageController" , 
			method : "get" ,
			async : false,
			data : { type : "mySubmitcarView" } ,
			success : jsonArray => { 
				//console.log(jsonArray);
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
					console.log(p);
					//console.log('카이미지리스트 : '+Object.values(p.car.imglist)[0]);
					//Object.values() : 주어진 객체의 속성 값들을 배열로 반환
					// 제품1개 html 마크업
					count++; // 카운트를 쓰지 않으면 캐러셀공간이 추가생성됨 화살표버튼 누르면 아무것도 없는데 넘어감
					if (p.astate >= 1){ // 경매중인 상태가 아니면 (경매완료면)
						// 전화번호 가져오는 함수실행
							//console.log("p.car.cno : "+p.car.cno);
								findphone(p.car.cno); // 차량번호로 최종입찰자 찾기함수
								findseller(p.car.cno); // 차량번호로 판매자 찾기 함수
						if (finalmid == loginMid || sellermid == loginMid){ // 로그인한 사람이 판매자이거나 최종 입찰자이면
						html += `<div class="col"> <!-- 제품1개 -->
									<div class="card">
										<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
											<img src="/nichanaecha/auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="Image 1">
											<div class="card-body">
												<h5 class="card-title">${p.atitle}</h5>
												<div class="card-text">경매 등록번호 : ${p.ano}</div>
												<div class="card-text">최소 입찰 금액 : ${p.aprice.toLocaleString()}원</div>
												<div class="card-text">이미 종료된 경매입니다.</div>
												<div class="card-text">판매자 아이디 : ${sellermid}</div>
												<div class="card-text">판매자 연락처 : ${sellermphone}</div>
												<div class="card-text">구매자 아이디 : ${finalmid}</div>
												<div class="card-text">구매자 연락처 : ${finalmphone}</div>
											</div>
										</a>
									</div>
								</div>`;
						}else { // 로그인 한 사람이나 , 판매자나 최종입찰자가 아니면 경매종료된 상태면 경매종료된것만 알림
							html += `<div class="col"> <!-- 제품1개 -->
										<div class="card">
											<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
												<img src="/nichanaecha/auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="Image 1">
												<div class="card-body">
													<h5 class="card-title">${p.atitle}</h5>
													<div class="card-text">경매 등록번호 : ${p.ano}</div>
													<div class="card-text">이미 종료된 경매입니다.</div>
												</div>
											</a>
										</div>
									</div>`;	
						}
					} else { // 경매중인 상태면
						html += `<div class="col"> <!-- 제품1개 -->
										<div class="card">
											<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
												<img src="/nichanaecha/auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="Image 1">
												<div class="card-body">
													<h5 class="card-title">${p.atitle}</h5>
													<div class="card-text">경매 등록번호 : ${p.ano}</div>
													<div class="card-text">최소 입찰 금액 : ${p.aprice.toLocaleString()}원</div>
													<div class="card-text">경매 종료 : ${p.aenddate}</div>
												</div>
											</a>
										</div>
									</div>`;
					}
							//console.log("html : "+html);
					// <a href="/nichanaecha/auction/carinfo.jsp?ano=${p.ano}"> 클릭시 carinfo 의 url로 ano 보냄
					if ( jsonArray.length ==1 || count%2==0) { // 매물 2개씩 담기위해 나눔 /1개일때도 일단출력은되어야함
						//console.log(html);
						//console.log("몇번도니");
						document.querySelector('.carousel-inner').innerHTML += 
						`<div class="carousel-item"> <!-- 제품 2개세트 -->
							<div class="abox row row-cols-md-2 g-4 px-5">
							</div>
						</div>`;
						// html 추가해주기 제품1개씩 
						document.querySelectorAll('.abox')[Math.floor(i/2)].innerHTML = html; // Math.floor(i/2) 인덱스를 2로 나누고 소수점을 버림
						if (i%2==1){// 제품 2개씩 넣어줘야하기 때문에 넣고나면 초기화
							html=''; 
						}
					
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

// 최종 입찰자 찾기 함수
function findphone(cno){
	$.ajax({
		url : "/nichanaecha/MypageController" , 
			method : "get" ,
			async : false,
			data : { type : "findphone" , cno: cno } ,
			success : jsonArray => {
				//console.log('findphone 여연결된');
				//console.log(jsonArray);
				//console.log(jsonArray.mid);
				//console.log(jsonArray.mphone);
				finalmid = jsonArray.mid;
				finalmphone = jsonArray.mphone;
				finalmno = jsonArray.mno;
			} ,
			error : e=>{console.log(e);}
	});
}

// 판매자 아이디, 연락처 찾기 함수
function findseller(cno){
	$.ajax({
		url : "/nichanaecha/MypageController" , 
			method : "get" ,
			async : false,
			data : { type : "findSeller" , cno: cno } ,
			success : jsonArray => {
				//console.log('findphone 여연결된');
				//console.log(jsonArray);
				//console.log(jsonArray.mid);
				//console.log(jsonArray.mphone);
				sellermid = jsonArray.mid;
				sellermphone = jsonArray.mphone;
				sellermno = jsonArray.mno;
			} ,
			error : e=>{console.log(e);}
	});
}

// 규리 2. 입찰한 매물정보(캐러셀) 출력
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
					if (p.astate >= 1){// 경매중인 상태가 아니면 (경매완료면)
						findphone(p.car.cno); // 차량번호로 최종입찰자 찾기함수
						findseller(p.car.cno); // 차량번호로 판매자 찾기 함수
						console.log("finalmid : "+finalmid);
						console.log("sellermid : "+sellermid);
						console.log("loginMid : "+loginMid);
						if (finalmid == loginMid || sellermid == loginMid){ // 로그인한 사람이 판매자이거나 최종 입찰자이면
							console.log("도니");
							// 제품1개 html 마크업
							html += `<div class="col"> <!-- 제품1개 -->
										<div class="card">
											<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
												<img src="/nichanaecha/auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="Image 1">
												<div class="card-body">
													<h5 class="card-title">${p.atitle}</h5>
													<div class="card-text">경매 등록번호 : ${p.ano}</div>
													<div class="card-text">최소 입찰 금액 : ${p.aprice.toLocaleString()}원</div>
													<div class="card-text">이미 종료된 경매입니다.</div>
													<div class="card-text">판매자 아이디 : ${sellermid}</div>
													<div class="card-text">판매자 연락처 : ${sellermphone}</div>
													<div class="card-text">구매자 아이디 : ${finalmid}</div>
													<div class="card-text">구매자 연락처 : ${finalmphone}</div>
												</div>
											</a>
										</div>
									</div>`;
						}else { // 로그인 했으나 , 판매자나 최종입찰자가 아니면 경매종료된 상태면 경매종료된것만 알림
							console.log("두번째도니");
							html += `<div class="col"> <!-- 제품1개 -->
										<div class="card">
											<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
												<img src="/nichanaecha/auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="Image 1">
												<div class="card-body">
													<h5 class="card-title">${p.atitle}</h5>
													<div class="card-text">경매 등록번호 : ${p.ano}</div>
													<div class="card-text">최소 입찰 금액 : ${p.aprice.toLocaleString()}원</div>
													<div class="card-text">이미 종료된 경매입니다.</div>
												</div>
											</a>
										</div>
									</div>`;
						}
					} else { // 경매중인 상태면
						html += `<div class="col"> <!-- 제품1개 -->
									<div class="card">
										<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
											<img src="/nichanaecha/auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="Image 1">
											<div class="card-body">
												<h5 class="card-title">${p.atitle}</h5>
												<div class="card-text">경매 등록번호 : ${p.ano}</div>
												<div class="card-text">최소 입찰 금액 : ${p.aprice.toLocaleString()}원</div>
												<div class="card-text">경매 종료일 : ${p.aenddate}</div>
											</div>
										</a>
									</div>
								</div>`;
					}
					// <a href="/nichanaecha/auction/carinfo.jsp?ano=${p.ano}"> 클릭시 carinfo 의 url로 ano 보냄
					if (jsonArray.length ==1 || count%2==0) { // 매물 2개씩 담기위해 나눔 /1개일때도 일단출력은되어야함
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


// 규리 3. 찜한 매물정보(캐러셀) 출력
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
				//console.log("jsonArray.length"+jsonArray.length);
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
					if (p.astate >= 1){// 경매중인 상태가 아니면 (경매완료면)
						findphone(p.car.cno); // 차량번호로 최종입찰자 찾기함수
						findseller(p.car.cno); // 차량번호로 판매자 찾기 함수
						if (finalmid == loginMid || sellermid == loginMid){ // 로그인한 사람이 판매자이거나 최종 입찰자이면
						// 제품1개 html 마크업
						html += `<div class="col"> <!-- 제품1개 -->
									<div class="card">
										<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
											<img src="/nichanaecha/auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="Image 1">
											<div class="card-body">
												<h5 class="card-title">${p.atitle}</h5>
												<div class="card-text">경매 등록번호 : ${p.ano}</div>
												<div class="card-text">최소 입찰 금액 : ${p.aprice.toLocaleString()}원</div>
												<div class="card-text">이미 종료된 경매입니다.</div>
												<div class="card-text">판매자 아이디 : ${sellermid}</div>
												<div class="card-text">판매자 연락처 : ${sellermphone}</div>
												<div class="card-text">구매자 아이디 : ${finalmid}</div>
												<div class="card-text">구매자 연락처 : ${finalmphone}</div>
											</div>
										</a>
									</div>
								</div>`;
						}else { // 로그인 한 사람이나 , 판매자나 최종입찰자가 아니면 경매종료된 상태면 경매종료된것만 알림
							html += `<div class="col"> <!-- 제품1개 -->
										<div class="card">
											<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
												<img src="/nichanaecha/auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="Image 1">
												<div class="card-body">
													<h5 class="card-title">${p.atitle}</h5>
													<div class="card-text">경매 등록번호 : ${p.ano}</div>
													<div class="card-text">최소 입찰 금액 : ${p.aprice.toLocaleString()}원</div>
													<div class="card-text">이미 종료된 경매입니다.</div>
												</div>
											</a>
										</div>
									</div>`;
						}
					} else { // 경매중인 상태면
						html += `<div class="col"> <!-- 제품1개 -->
									<div class="card">
										<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
											<img src="/nichanaecha/auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="Image 1">
											<div class="card-body">
												<h5 class="card-title">${p.atitle}</h5>
												<div class="card-text">경매 등록번호 : ${p.ano}</div>
												<div class="card-text">최소 입찰 금액 : ${p.aprice.toLocaleString()}원</div>
												<div class="card-text">경매 종료일 : ${p.aenddate}</div>
											</div>
										</a>
									</div>
								</div>`;
					}
					// <a href="/nichanaecha/auction/carinfo.jsp?ano=${p.ano}"> 클릭시 carinfo 의 url로 ano 보냄
					if (jsonArray.length ==1 || count%2==0) { // 매물 2개씩 담기위해 나눔 /1개일때도 일단출력은되어야함
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

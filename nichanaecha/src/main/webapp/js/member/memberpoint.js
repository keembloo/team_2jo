//console.log('포인트실행');
let listsize = 10;
let page = 1; 
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
				//console.log('r.mcash : '+r.mcash); 보유포인트확인
				//console.log('r: '+r);
				//console.log('r[0].mno : '+r[0].mno);
				
				let cashInfo = document.querySelector('.cashInfo');

				cashInfo.innerHTML = `<div class="textId">'${r.mname}' 님</div>
									<div class="textPoint">보유 포인트 : <span>${r.mcash.toLocaleString()}원</span></div>
									<button class="btn btn-primary" onclick="inputPoint()" type=button>입금</button>
									<button class="btn btn-danger" onclick="outputPoint(${r.mcash})" type=button>출금</button>`;
				PointAllView(page); // 포인트내역 전체출력 함수 호출
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
				data : { type : "사용자 입금" , gold : gold } ,
				success : r => { //console.log("js연결성공");
					alert('입금 성공');
					mpointView(); // 포인트출력함수 호출
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
				data : { type : "사용자 출금" , gold : gold } , 
				success : r => { //console.log("js연결성공");
					alert('출금 성공');
					mpointView(); // 포인트출력함수 호출
				} , 
				error : e => {console.log("실패"+e);}
		})
	}
}



// 규리 포인트내역 전체 출력 
function PointAllView(page){
	//console.log(listsize);
	//console.log(page);
		$.ajax({
			url : "/nichanaecha/MemberPointController" , 
			async : false ,
			method : "get" ,
			data : { type : "PointAllView" , listsize : listsize , page : page } ,  
			success : jason => { //console.log("js연결성공");
				console.log(jason);
				let pointbox = document.querySelector('.pointbox');
				let html = ``;
				
				jason.pointList.forEach(p =>{
					//console.log(p);
					//console.log(p.pointhistory);
					html += `<tr>
						   		<td class="text-center">${p.mpoint.toLocaleString()}원</td>
						   		<td class="text-center">${p.pointdate}</td>
						   		<td class="text-center">${p.pointhistory}</td>
							</tr>`;
				});
				pointbox.innerHTML = html;
				pageboxView(page , jason);
			} , 
			error : e => {console.log("실패"+e);}
		})
}

// 규리 입금내역만보기
function PointInputView(page){
		$.ajax({
			url : "/nichanaecha/MemberPointController" , 
			async : false ,
			method : "get" ,
			data : { type : "PointInput" , listsize : listsize , page : page } ,  
			success : jasonArray => { //console.log("js연결성공");
				//console.log(jasonArray);
				let pointbox = document.querySelector('.pointbox');
				let html = ``;
				
				jasonArray.pointList.forEach(p =>{
					html += `<tr>
						   		<td class="text-center">${p.mpoint.toLocaleString()}원</td>
						   		<td class="text-center">${p.pointdate}</td>
						   		<td class="text-center">${p.pointhistory}</td>
							</tr>`;
					
				});
				pointbox.innerHTML = html;
				pageboxView(page , jasonArray);
			} , 
			error : e => {console.log("실패"+e);}
		})
}

// 규리 출금내역만보기
function PointOutputView(page){
		$.ajax({
			url : "/nichanaecha/MemberPointController" , 
			async : false ,
			method : "get" ,
			data : { type : "PointOutput" , listsize : listsize , page : page } ,  
			success : jasonArray => { //console.log("js연결성공");
				console.log(jasonArray);
				let pointbox = document.querySelector('.pointbox');
				let html = ``;
				
				jasonArray.pointList.forEach(p =>{
						html += `<tr>
							   		<td class="text-center">${p.mpoint.toLocaleString()}원</td>
							   		<td class="text-center">${p.pointdate}</td>
							   		<td class="text-center">${p.pointhistory}</td>
								</tr>`;
				});
				pointbox.innerHTML = html;
				pageboxView(page , jasonArray);
			} , 
			error : e => {console.log("실패"+e);}
		})
}


// 규리 페이지 번호 출력 함수 
function pageboxView(page , jason){
	//console.log('실행된다');
	//page : 조회한 페이지 번호 [ 현재 보고 잇는 페이지 번호 ]
	// 이전버튼 [page<=1? page: page-1] 만약에 1페이지에서 이전버튼 클릭시 1페이지로 고정하고 아니면 1차감
	let pagebox = document.querySelector('.pagebox');
	let html = ``;
	let before = `<button onclick="onView(${page<=1? page: page-1})" type="button"> < </button>`;
	// 페이지 번호 버튼 [ * 페이지 개수만큼 반복 ]
	for (let i= jason.startbtn; i <= jason.endbtn ; i++){
		// 만약에 현재페이지와 i번째 페이지가 일치하면 버튼태그에 class="selectpage" 추가
		//console.log("포문도나");
		html += `<button class="${page==i ? 'selectpage' : '' }" onclick="PointAllView(${i})" type="button"> ${i} </button>`;
	}
	// 다음버튼 [ page >= r.totalpage ? page : page+1 ] 만약에 현재페이지가 마지막페이지면 고정 아니면 1증가
	let after = `<button onclick="PointAllView(${page >= jason.totalpage ? page : page+1})" type="button"> > </button>`;
	
	//console.log("html + "+html);
	pagebox.innerHTML = before + html + after;

}


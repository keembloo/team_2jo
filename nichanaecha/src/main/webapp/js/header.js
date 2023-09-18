
// 1. 현재 로그인된 회원정보 요청
let loginMid = '';	// 로그인 성공된 아이디를 가지고 있는 변수 
getMemberInfo();
function getMemberInfo(){
	//ajax 이용한 서블릿세션 정보 가져오기
	$.ajax({
		url : "/nichanaecha/MemberFindController",
		method : "get",
		asyno : false,		// 비동기화(기본값=true), 동기화(false) 설정하는 속성 (우선적인 ajax실행 응답이 필요할때)
		data : { type : "info" },
		success : r => { console.log(r); 
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
					`<li class="nav-item"><a onclick="logout()" class="nav-link" href="#">로그아웃</a></li>
	                 <li class="nav-item"><a class="nav-link" href="/nichanaecha/member/memberinfo.jsp">마이페이지</a></li>`;
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

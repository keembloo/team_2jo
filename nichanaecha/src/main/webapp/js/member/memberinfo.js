console.log('마이페이지');
mview();


function mview(){
	// 로그인된 회원번호 전달 하거나 컨트롤러에서 세션으로 불러와야함 ! 
	if (loginMid > 0) { // 로그인된 상태면
		$.ajax({
			url : "/nichanaecha/MemberController" , 
			method : "get" ,
			data : {} ,
			success : r => { console.log("성공");
			} , 
			error : e => {console.log("실패");}		
		})
	} else {
		alert('로그인된 회원만 볼수 있습니다. 로그인페이지로 돌아갑니다.');
		location.href='/nichanaecha/member/memberlogin.jsp';
	}
}
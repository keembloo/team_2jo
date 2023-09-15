
//1. 로그인 함수 
function login(){
	//1. 입력받은 아이디, 비밀번호 가져오기
	let mid = document.querySelector('.mid').value
	let mpwd = document.querySelector('.mpwd').value
	
	//2. ajax에게 전달해서 회원이 존재하는지 반환 받기
	$.ajax({
		url :"/nichanaecha/MemberFindController",
		method : "post",
		async : { mid : mid , mpwd : mpwd },
		success : r => {
			if(r){location.href="/nichanaecha/index.jsp";}
			else{
				document.querySelector('.logincheckbox')
					.innerHTML = '동일한 회원정보가 없습니다.';
			}
		},
		error : e => { console.log(e);}
	});
};
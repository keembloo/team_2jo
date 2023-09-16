console.log('회원가입페이지열림');

/*

﻿ /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,20}$/ : 영대소문자(1개이상필수) + 숫자(1개이상필수) 조합 5~20글자 패턴 ﻿

 */

//아이디 유효성검사: 중복아이디 (규리님과 상의 필요)----------------------------------------------------
let mid= document.querySelector('.mid').value;

$.ajax({
      	url : "/nichanaecha/MemberController",     
     	method : "get",   
     	data : {},      
      	success : r=>{console.log('통신성공'+r)} ,       
      	error : e=>{console.log(e)} ,         
   });

//비밀번호 유효성 검사[9월16일 고연진]: 1.정규표현식 2.동일한비밀번호------------------------------------------
function pwcheck(){
	let mpw = document.querySelector('.mpw').value;
	let checkPw= document.querySelector('.checkPw').value;
	let html=document.querySelector('.valBox');
	let valPw= /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{4,20}$/
	
	if(valPw.test(mpw)){//정규표현식 통과
		if(valPw.test(checkPw)){
			if(mpw==checkPw){
				html.innerHTML=`사용 가능한 비밀번호입니다`
			}else {html.innerHTML=`비밀번호가 일치하지 않습니다`}
		}else{html.innerHTML=`영대소문자 + 숫자 조합 4글자 이상으로 입력하세요`}
	}else{//정규표현식 불통
		html.innerHTML=`영대소문자 + 숫자 조합 4글자 이상으로 입력하세요`}
}//f()


//회원가입 버튼 눌렀을 때 실행되는 함수 [9월15일 고연진]-------------------------------------------------
function signup(){
	let signupForm = document.querySelectorAll('.signupForm')[0]; console.log(signupForm);
	let signupData = new FormData(signupForm); console.log('가입 정보 FormData로 묶음'+ signupData);
	
	$.ajax({
      	url : "/nichanaecha/MemberController",     
     	method : "post",   
     	data : signupData,      
    	contentType:false,
    	processData:false,
      	success : r=>{console.log('통신성공'+r)
      		if(r){console.log('회원가입성공'); alert('가입성공')}
      		else{console.log('회원가입실패')}
      	
      	} ,       
      	error : e=>{console.log('통신실패'+e)} ,         
   });

	
}//f()
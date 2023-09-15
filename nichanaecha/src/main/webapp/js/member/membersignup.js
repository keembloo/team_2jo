console.log('회원가입페이지열림');


//회원가입 버튼 눌렀을 때 실행되는 함수 [9월15일 고연진]
function signup(){
	let signupForm = document.quaquerySelectorAll[0]('signupForm');
	let signupData = new FormData(signupForm); console.log('가입 정보 FormData로 묶음'+ signupData);
	
	$.ajax({
      	url : "nichanaecha/MemberController",     
     	method : "post",   
     	data : {},      
    	contentType:false,
    	processData:false,
      	success : r=>{console.log('통신성공'+r)
      	
      	
      	
      	} ,       
      	error : e=>{console.log(e)} ,         
   });

	
}//f()
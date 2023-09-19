
function bcarsubmit(){
	//1. form 가져오기
	let form = document.querySelectorAll('.carsubmitForm')[0];
	
	//2. form 객체화 하기
	let formData = new FormData(form);
	$.ajax({
		url : "/nichanaecha/AuctionController" , 
		method: "post" , 
		data : formData ,
		contentType : false , 
		processData : false ,
		success : r => {
			
			if( r ){
				alert('글등록 성공');
				location.href="/nichanaecha/src/main/webapp/index.jsp";
			}else{
				alert('글등록 실패 ');
			}
			
		} , 
		error : e => { } 
	})
}
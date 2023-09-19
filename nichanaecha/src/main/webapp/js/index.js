
function Vehicleregistration(){
	$.ajax({
		url : "/nichanaecha/AuctionController" , 
		method: "post" , 
		data : formData ,
		contentType : false , 
		processData : false ,
		success : r => {
			
			if( r ){
				alert('차량등록 페이지로 넘어가기 성공');
				location.href="/nichanaecha/src/main/webapp/auction/carsubmit.jsp";
			}else{
				alert('차량등록 페이지로 넘어가기 실패 ');
			}
			
		} , 
		error : e => { } 
	})
}
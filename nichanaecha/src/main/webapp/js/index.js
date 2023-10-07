console.log('index.js실행')

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
}//f()



//알람 관련 소켓 통신 [10월7일 고연진]------------------------------------------------------
loginMid 

let clientSocket= new WebSocket('ws://localhost:80/nichanaecha/AlarmSocket');
console.log('알람관련 클라이언트 소켓 생성');
clientSocket.onopen=e=>{console.log('클라이언트소켓열림');}
clientSocket.onclose=e=>{console.log('서버소켓과 통신이 끝났음. 로그아웃 시 출력되야됨 ,,')};
clientSocket.onmessage=e=>newAlarm(e)

/*
function newAlarm(e){
	
}//f()
*/
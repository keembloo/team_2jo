//console.log('연결됨'); 
editview();

let pwstate = false; // 비밀번호일치 상태 확인 변수


// 기본정보 출력 
function editview(){
	if (loginMid == '') {
		alert('로그인된 회원만 볼수 있습니다. 로그인페이지로 돌아갑니다.');
		location.href='/nichanaecha/member/memberlogin.jsp';
		return;
	 }// 로그인된 상태면
	$.ajax({
		url : "/nichanaecha/MemberController" , 
		async : false ,
		method : "get" ,
		data : {type : "editview"} ,
		success : r => { //console.log("js연결성공");
			//console.log('r.mno : '+r.mno);
			//console.log(r);
			//console.log('r[0].mno : '+r[0].mno);
			
			let infobox = document.querySelector('.infobox');

			infobox.innerHTML = `<div class="basictitle">기본정보</div>
								<div class="linedeco"></div>
								<div class="nameinfo">'${r.mname}' 님</div>
								<div class="idinfo">${r.mid}</div> 
								<div class="linedeco"></div>
								<div class="pointinfo">보유 포인트 : <span>${r.mcash.toLocaleString()}원</span></div>
								<div class="linedeco"></div>
								<div class="infoflexbox">
									<div class="infotitle">주소 : ${r.mads}</div>
									<button class="btn editbtn" onclick="memberEdit(1)" data-toggle="adressmodal" data-target="adressmodal" type="button">수정</button>
								</div>
								<div class="linedeco"></div>
								<div class="infoflexbox">
									<div class="infotitle">연락처 : ${r.mphone}</div>
									<button class="btn editbtn" onclick="memberEdit(2)" type="button">수정</button>
								</div>
								<div class="linedeco"></div>
								<div class="infoflexbox">
									<div class="infotitle">비밀번호</div>
									<button class="btn editbtn" onclick="memberEdit(3)" type="button">수정</button>
								</div>
								`;
		} , 
		error : e => {console.log("실패");}		
	})
}



// 버튼 클릭시 모달창 열기
function memberEdit(num){
	//console.log("주소수정");
	if (num ==1){
		$("#adressmodal").modal("show");
	}else if (num ==2){
		$("#adressmodal2").modal("show");
	}else if (num ==3){
		$("#adressmodal3").modal("show");
	}
}


// 주소 , 연락처 수정 서블릿연결
function editDataSend(num){
	//console.log("입력값");
	let data = document.querySelectorAll('.newDataInput')[num];
	let pw = document.querySelectorAll('.pwcheck')[num];
	let type="";
	let newDataInputCheck = document.querySelector('.newDataInputCheck');

	if (num==0){ // 주소수정인경우
		if(data.value ==""||pw.value==""){
			alert('비어있는 내용이 있습니다');
			return;
		} else {
			type="adressSend";
		}
	} else if (num==1){ // 연락처수정인경우
		if(data.value ==""||pw.value==""){
			alert('비어있는 내용이 있습니다');
			return;
		} else {
			type="phoneSend";
		}
	} else if (num==2){ // 비번수정인경우
		if (data.value ==""||pw.value==""||newDataInputCheck.value==""){
			alert('비어있는 내용이 있습니다');
			return;
		}
		if(pwstate==false){ // 비밀번호일치 상태체크
			alert("비밀번호가 일치하지 않습니다. 다시입력해주세요.");
			return;
		}else{
			type="newpwSend";
		}
	}
	//console.log('g확인 : '+  data.value + pw.value);
	
	$.ajax({
			url : "/nichanaecha/MemberController" , 
			async : false ,
			method : "put" ,
			data : { type : type , data : data.value , pw : pw.value } ,
			success : r => { 
				data.value="";
				pw.value="";
				newDataInputCheck.value="";
				$("#adressmodal").modal("hide");
				$("#adressmodal2").modal("hide");
				$("#adressmodal3").modal("hide");
				
				if(type=="newpwSend"){ // 비번 변경 성공이면
					alert('비밀번호가 변경되었습니다. 다시 로그인해주세요.');
					logout();
					location.href = "/nichanaecha/index.jsp";
					pwstate=false;						 
					return;
				}
				alert('정보가 변경되었습니다.');
				editview();
			
			} , 
			error : e => {console.log("실패");}		
	})
}

// 비밀번호, 비밀번호확인 체크 기능
function keyCheck(){
	let checkhtml = document.querySelector('.checkhtml');
	let pwCheck = document.querySelector('.newDataInputCheck');
	let pw = document.querySelectorAll('.newDataInput')[2];
	let html =``;
	//console.log("값들어오나"+pwCheck.value+pw.value);
	if (pwCheck.value != pw.value){
		html = `비밀번호가 일치하지 않습니다. 다시 입력해주세요.`;
		pwstate=false;
	} else{
		html = `비밀번호가 일치합니다.`;
		pwstate=true;
	}
		checkhtml.innerHTML =html;
}

// 모달 닫기 버튼 눌렀을때 인풋값 초기화
function resetInput(num){
	let data = document.querySelectorAll('.newDataInput')[num];
	let pw = document.querySelectorAll('.pwcheck')[num];
	let newDataInputCheck = document.querySelector('.newDataInputCheck');
	data.value="";
	pw.value="";
	newDataInputCheck.value="";
}

//console.log('연결됨');
editview();

function editview(){
	$.ajax({
		url : "/nichanaecha/MemberController" , 
		async : false ,
		method : "get" ,
		data : {type : "editview"} ,
		success : r => { //console.log("js연결성공");
			//console.log('r.mno : '+r.mno);
			console.log(r);
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


	
function memberEdit(num){
	console.log("주소수정");
	if (num ==1){
		$("#adressmodal").modal("show");
	}else if (num ==2){
		$("#adressmodal2").modal("show");
	}else if (num ==3){
		$("#adressmodal3").modal("show");
	}
}

function phoneEdit(){
	//console.log("전화수정");
	
}

// 규리 주소 , 연락처 수정
function editDataSend(num){
	//console.log("입력값");
	let data = document.querySelector('.newDataInput');
	let pw = document.querySelector('.pwcheck');
	let type="";
	if (num==1){ // 주소수정
		type="adressSend";
	} else if (num==2){ // 연락처수정
		type="phoneSend";
	} else if (num==3){ // 비번수정
		type="newpwSend";
	}
	//console.log('g확인 : '+  data.value + pw.value);
	if (data.value ==""){
		alert('입력한 내용이 없습니다');
		return;
	}
	$.ajax({
			url : "/nichanaecha/MemberController" , 
			async : false ,
			method : "put" ,
			data : { type : type , data : data.value , pw : pw.value } ,
			success : r => { 
				data.value="";
				pw.value="";
				$("#adressmodal").modal("hide");
				if(r){
					alert('정보가 변경되었습니다');
					editview();
				}else{
					alert('정보 수정 실패');
				}
			} , 
			error : e => {console.log("실패");}		
	})
}

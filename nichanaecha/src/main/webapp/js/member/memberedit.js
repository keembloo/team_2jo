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
									<button class="btn editbtn" onclick="adressEdit()" data-toggle="adressmodal" data-target="adressmodal" type="button">수정</button>
								</div>
								<div class="linedeco"></div>
								<div class="infoflexbox">
									<div class="infotitle">연락처 : ${r.mphone}</div>
									<button class="btn editbtn" onclick="phoneEdit()" type="button">수정</button>
								</div>
								<div class="linedeco"></div>
								<div class="infoflexbox">
									<div class="infotitle">비밀번호</div>
									<button class="btn editbtn" onclick="pwEdit()" type="button">수정</button>
								</div>
								`;
			
		} , 
		error : e => {console.log("실패");}		
	})
}


	
function adressEdit(){
	console.log("주소수정");
	$("#adressmodal").modal("show");

}

function phoneEdit(){
	//console.log("전화수정");
	
}

// 규리 주소 수정
function adressSend(){
	console.log("입력값");
	let newAdress = document.querySelector('.newadressinput');
	let pw = document.querySelector('.pwcheck');
	console.log('g확인 : '+  newAdress.value + pw.value);
	$.ajax({
			url : "/nichanaecha/MemberController" , 
			async : false ,
			method : "put" ,
			data : { type : "adressSend" , data: newAdress.value , pw : pw.value } ,
			success : r => { 
				newAdress.value="";
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

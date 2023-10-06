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
								<div class="nameinfo">'${r.mname}' 님</div>
								<div class="idinfo">${r.mid}</div> 
								<div class="linedeco"></div>
								<div class="pointinfo">보유 포인트 : <span>${r.mcash.toLocaleString()}원</span></div>
								<div class="linedeco"></div>
								<div class="infoflexbox">
									<div class="infotitle">주소 : ${r.mads}</div>
									<button class="btn editbtn" type="button">수정</button>
								</div>
								<div class="linedeco"></div>
								<div class="infoflexbox">
									<div class="infotitle">연락처 : ${r.mphone}</div>
									<button class="btn editbtn" type="button">수정</button>
								</div>
								`;
			
		} , 
		error : e => {console.log("실패");}		
	})
}
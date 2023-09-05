console.log('인덱스js시작');



//최초실행
onView();


// 출력 함수 규리
function onView(){
	console.log('함수실행');
	let wrap = document.querySelector('.wrap');
	let html = '';
		
	$.ajax({
			url : "/team_2jo/SnsController" , 
			method : "get" ,
			data : { },
			success : r => { console.log(r);
				// 배열명.forEach
				r.forEach( b => {
					html+= `<div class="contentbox">
								<div class="img_area">
									<img alt="이미지" src="img/${b.bfile}">
								</div>
								<div class="content">
									<div class="time">${b.bdate}</div>		
									<div class="infotext">${b.bcontent}</div>		
								</div>
								<button onclick="updata(bno)" class="btn_update" type="button">수정</button>
								<button onclick="ondelet()" class="btn_delete" type="button">삭제</button>
							</div>`;
				});
				// 3. 구성된 html내용 출력 
				wrap.innerHTML = html;
				console.log("html :"+html);
				
			} , 
			error : e => {console.log(e);}		
		})
} // onView end













// 게시물 삭제 (이성호)
function ondelete(bno){
	$.ajax({
		url : "/team_2jo/SnsController",
		method : "delete",
		date : {bno : bno},
		success : r => {console.log(r);
			if(r){
				alert('삭제성공');
				location.href="/team_2jo/sns_project/index.jsp";
			}else{alert('삭제실패');}
		},
		error : e => { console.log(e)}
	})
}

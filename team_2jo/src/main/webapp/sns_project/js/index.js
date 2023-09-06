console.log('인덱스js시작');

let keyobj = {key : ''};

//최초실행
onView();

// 출력 함수 규리
function onView(){
	//console.log('함수실행');
	let wrap = document.querySelector('.wrap');
	let html = '';
		
	$.ajax({
			url : "/team_2jo/SnsController" , 
			method : "get" ,
			data : { key : keyobj.key },
			success : r => { //console.log(r);
				// 배열명.forEach
				r.list.forEach( b => {console.log(b.bno)
					html+= `<div class="contentbox">
								<div class="img_area">
									<img alt="이미지" src="img/${b.bfile}">
								</div>
								<div class="content">
									<div class="time">${b.bdate}</div>		
									<div class="infotext">${b.bcontent}</div>		
								</div>
								<button onclick="update(${b.bno})" class="btn_update" type="button">수정</button>
								<button onclick="ondelete(${b.bno})" class="btn_delete" type="button">삭제</button>
								<button onclick="oncoment(${b.bno})" class="btn_coment" type="button">답글</button>
									<div class="reply">
										<div class="reply_bccontent">${b.bccontent}</div>
										<div class="reply_bcdate">${b.bcdate}</div>
										<button onclick"">X</button>
									</div>
							</div>`;
				});
				document.querySelector('.feed').innerHTML = `<h4>총 피드 수 : ${r.feedCnt}</h4>`
				// 3. 구성된 html내용 출력 
				wrap.innerHTML = html;
				//console.log("html :"+html);
				
			} , 
			error : e => {console.log(e);}		
		})
} // onView end


//검색 함수 
function onSerch(){
	let key = document.querySelector('.serchInput').value;
	keyobj.key = key;
	onView()
}

function update(bno){
	let bpwd = prompt('비밀번호를 입력하세요.');
	$.ajax({
		url : "/team_2jo/SnsController" , 
		method: "put" ,
		data : {type : 'get', bno : bno, bpwd : bpwd},
		success : r => { 
			if(r){
				location.href=`/team_2jo/sns_project/update.jsp?bno=${bno}`;
			}else{
				alert('비밀번호가 일치하지 않습니다.')
			}
			
		} ,
		error : e => { console.log(e) }
		})
}





// 게시물 삭제 (이성호)
function ondelete(bno){
	let bpwd = prompt("비밀번호를 입력하세요.");
	$.ajax({
		url : "/team_2jo/SnsController",
		method : "delete",
		data : {bno : bno , bpwd : bpwd},
		success : r => {console.log(r);
			if(r){			
				alert('삭제성공');
			}else{alert('비밀번호가 다릅니다.');}
			onView();
		},
		error : e => { alert('삭제실패');}
	})
}

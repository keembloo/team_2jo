console.log('인덱스js시작');



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
			data : { },
			success : r => { //console.log(r);
				// 배열명.forEach
				r.forEach( b => {console.log(b.bno)
					html+= `
					
					<div class="contentbox">
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
							
							
								<button onclick="cdelte()" class="btn_cdelete" type="button">X</button>
							
							</div>`;
				});
				// 3. 구성된 html내용 출력 
				wrap.innerHTML = html;
				//console.log("html :"+html);
				
			} , 
			error : e => {console.log(e);}		
		})
} // onView end




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

// <button onclick="cdelte()" class="btn_cdelete" type="button">X</button>
//댓글 삭제[고연진]---------------------------------------------------------------------
function cdelte(cno){
	//1. 요청
	let cpwd= prompt('비밀번호 입력'); 
	
	//2. ajax 통신
	 $.ajax({
      	url : "/team_2jo/CommentController",     
     	method : "get",   
     	data : {cno:cno,cpwd:cpwd},      
      	success : r=>{console.log('통신성공')
      	
      	
      	
      	} ,       
      	error : e=>{console.log(e)} ,         
   });

	
}//f()

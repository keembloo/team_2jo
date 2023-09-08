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
					console.log("r.list : "+r.list)
					
					
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
								<a href="/team_2jo/FileController?filename=${b.bfile}"><button class="btn_download" type="button">다운로드</button></a>
								
									<div class="reply">`
									
									for(let i=0; i<r.clist.length; i++){
										console.log("r.clist : "+r.clist[i].ccontent);
										if(b.bno==r.clist[i].bno){
											html +=`<div class="exid"><div class="reply_bccontent">${r.clist[i].ccontent}</div>
											<div class="reply_bcdate">${r.clist[i].cdate}</div>
											<button onclick="cdelte(${r.clist[i].cno})" class="btn_cdelete" type="button">X</button>
											</div>`
										}

									}
									
								html+=	`</div></div>`
							
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


// 답글 등록 규리
function oncoment(bno){
	let ccontent = prompt("댓글내용을 입력하세요.최대 30글자");
	let cpwd = prompt("비밀번호를 입력하세요. 8글자이상");
	//console.log(cpwd.length);
	if (ccontent.length<=30 && ccontent.length>0){
		if(20>=cpwd.length && cpwd.length>=8){
			$.ajax({
			url : "/team_2jo/CommentController",
			method : "post",
			data : {ccontent : ccontent , cpwd : cpwd , bno : bno},
			success : r => {console.log(r);
				if(r){			
					alert("댓글등록성공!");
					onView();
				}else{alert("경고) 댓글등록실패");}
				onView();
			},
			error : e => { alert('삭제실패');}
		})
		}else { // 비번이 20글자이상 8글자이하이면
			alert("경고) 비밀번호는 8글자 이상이어야합니다.");
		}
	}else { // 내용이 30글자이상이면
		alert("경고) 댓글내용은 30글자 이하이어야합니다.");
	}
	
	
}

// <button onclick="cdelte()" class="btn_cdelete" type="button">X</button>
//댓글 삭제[고연진]---------------------------------------------------------------------
function cdelte(cno){
	//1. 요청
	let cpwd= prompt('비밀번호 입력'); 
	console.log(cpwd);
	console.log(cno);

	//2. ajax 통신
	 $.ajax({
      	url : "/team_2jo/CommentController",     
     	method : "delete",   
     	data : {cno:cno,cpwd:cpwd},      
      	success : r=>{
      		if(r){alert('댓글삭제성공했습니다');
      			onView();}
      		else{alert('비밀번호를 일치하지 않습니다')}
      	
      	} ,       
      	error : e=>{
			  console.log(e);
			  alert('댓글삭제에 실패했습니다')
			 } ,         
   });
	
	
}//f()

// 다운로드[고연진]
/*
function ondownload(bno){ console.log('인수전달받음:'+bno)
	$.ajax({
      	url : "/team_2jo/FileController",     
     	method : "get",   
     	data : {bno:bno},      
    	contentType:false,
    	processData:false,
      	success : r=>{console.log('통신성공')} ,       
      	error : e=>{console.log(e)} ,         
   });
*/

	


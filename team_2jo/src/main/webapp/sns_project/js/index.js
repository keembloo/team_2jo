console.log('인덱스js시작');



//최초실행
onView();


// 출력 함수
function onView(){
	let contentbox = document.querySelector('.contentbox');
	let html = '';
	
	



}






















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

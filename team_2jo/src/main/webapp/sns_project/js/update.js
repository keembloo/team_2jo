


function update(){

	let bpwd = prompt('비밀번호를 입력하세요.');

	let submitForm = document.querySelectorAll('.updateform')[0];
	
	let boardData = new FormData(writeForm);
	
	let urlParams = new URL(location.href).searchParams
	let dno = urlParams.get('dno');
	
	boardData.append('type','update');
	
	
	$.ajax({
		url : "/team_2jo/SnsController" , 
		method: "put" ,
		data : boardData ,
		contentType : false ,
		processData : false ,
		success : r => { 
			if(r){
				alert('수정 완료')
				location.href="/team_2jo/sns_project/index.jsp";
				
			}else{
				alert('수정 실패')
			}
			
		} ,
		error : e => { console.log(e) } ,
	})


	
	
	
	
	
	
}
console.log('업데이트js')
uprint()

function uprint(){
	
	let urlParams = new URL(location.href).searchParams
	let bno = urlParams.get('bno');
	
	$.ajax({
		url : "/team_2jo/SnsController" , 
		method: "put" ,
		data : {type : 'print', bno : bno } ,
		success : r => { console.log('성공 결과 '+r)
				document.querySelector('.preimg').src = `/team_2jo/sns_project/img/${r.bfile};`;
				document.querySelector('.bcontent').value = r.bcontent;
				console.log('bfile'+r.bfile)
		} ,
		error : e => { console.log('에러'+e) } ,
	})
}

function prechange(o){
	
	console.log(o.files[0])
	
	let file = new FileReader();
	
	file.readAsDataURL(o.files[0]);
	
	file.onload = e => {
		document.querySelector('.preimg').src = e.target.result;
	}
	
}
	
	


function update2(){
	console.log("update2()")
	let updateform = document.querySelectorAll('.updateform')[0];
	
	let boardData = new FormData(updateform);
	
	let urlParams = new URL(location.href).searchParams
	let bno = urlParams.get('bno');
	
	boardData.set('type1','update');
	boardData.set('bno',bno);
	
	console.log( boardData );
	
	
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
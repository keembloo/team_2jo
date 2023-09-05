console.log('글쓰기페이지열림')


//사진 미리보기[고연진]=======================================================
function preimg(o){console.log('onchange 실행')
	let file= new FileReader();
	file.readAsDataURL(o.files[0]);
	console.log(file);
	file.onload =e=>{
		document.querySelector('.preimg').src=e.target.result;}//img src속성에 대입
	}



//글등록 [고연진] : 버튼 눌렀을 때=======================================
function bwrite(){
	let form= document.querySelectorAll('.writeform')[0];
	console.log('form: '+form);
	let formData= new FormData(form);
	console.log('formData: '+formData)
	let bfile=document.querySelector('.bfile').value; console.log('파일들어옴'+bfile)
	let bpwd=document.querySelector('.bpwd').value; console.log('패스워드받음')
	let bcontent=document.querySelector('.bcontent').value; console.log('내용받음')
	if(bfile!=''&&bpwd!=''&&bcontent!=''){
		$.ajax({
	      	url : "/team_2jo/SnsController",     
	     	method : "post",   
	     	data : formData ,      
	    	contentType:false,
	    	processData:false,
	      	success : r=>{console.log('통신성공'+formData)
		      	if(r){
					  alert('글등록성공');
					  location.href="/team_2jo/sns_project/index.jsp"}
		      	else{alert('글등록실패')}
	      	//onView()
	      	
	      	} ,       
	      	error : e=>{console.log('통신실패')} ,         
	   });
		
		
	}else{alert('공란 없이 입력해주세요')}
	
	


}//f()



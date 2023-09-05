console.log('글쓰기페이지열림')

let inputState=[false,false,false]

//사진 미리보기=======================================================
function preimg(o){console.log('onchange 실행')
	let file= new FileReader();
	file.readAsDataURL(o.files[0]);
	console.log(file);
	file.onload =e=>{
		document.querySelector('.preimg').src=e.target.result;}//img src속성에 대입
	}

//유효성=========================================================
function inputcheck(){
	let checkbox =document.querySelector('.checkbox');
	let btitle = document.querySelector('.btitle').value;
	let bpwd = document.querySelector('.bpwd').value;
	let bcontent = document.querySelector('.bcontent').value;
	if(btitle==''){inputState[0]==false;}
	else {inputState[0]==true}
	
	if(bpwd==''){inputState[1]==false;}
	else {inputState[1]==true}
	
	if(bcontent==''){inputState[2]==false;}
	else {inputState[2]==true}	
	
	if()
	}//f()



/* 무시하셈!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//제목 공백======================================================
function titlecheck(){
	let btitle= document.querySelector('.btitle').value;
	if(btitle!=''){inputState[0]==true}
	else{inputState[0]==false}
	}//f()
	
//비밀번호 공백=====================================================
function pwdcheck(){
	let bpwd= document.querySelector('.bpwd').value;
	if(bpwd!=''){inputState[1]==true}
	else{inputState[1]==false}
	}
	
//내용 공백=========================================================
function contentcheck(){
	let bcontent= document.querySelector('.bcontent').value;
	if(bcontent!=''){inputState[2]==true}
	else{inputState[2]==false}
	}
*/



//글등록 [고연진] : 버튼 눌렀을 때=======================================
function bwrite(){
	let form= document.querySelectorAll('.writeform')[0];
	console.log('form: '+form);
	let formData= new FormData(form);
	console.log('formData: '+formData)
	
	
	if(inputState[0]&&inputState[1]&&inputState[2]){
		$.ajax({
	      	url : "/team_2jo/SnsController",     
	     	method : "post",   
	     	data : formData ,      
	    	contentType:false,
	    	processData:false,
	      	success : r=>{console.log('통신성공'+formData)
		      	if(r){alert('글등록성공')}
		      	else{alert('글등록실패')}
	      	
	      	
	      	} ,       
	      	error : e=>{console.log('통신실패')} ,         
	   });
	}else{//버튼 활성화 x
	}


}//f()
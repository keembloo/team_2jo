console.log('글쓰기페이지열림')




//글등록 [고연진] : 버튼 눌렀을 때
function bwrite(){
	let form= document.querySelectorAll('.writeform')[0];
	console.log('form: '+form);
	let formData= new FormData(form);
	console.log('formData: '+formData)
	
	
	
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



}//f()
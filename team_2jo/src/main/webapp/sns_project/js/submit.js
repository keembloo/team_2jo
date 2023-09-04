console.log('글쓰기페이지열림')

//글등록 [고연진]
function bwrite(){
	let form= document.querySelectorAll('.writeform')[0];
	let formData= new formData();
	
	$.ajax({
      	url : "/team_2jo/SnsController",     
     	method : "post",   
     	data : formData ,      
    	contentType:false,
    	processData:false,
      	success : r=>{console.log('통신성공'+formData)
	      	
      	
      	
      	} ,       
      	error : e=>{console.log('통신실패')} ,         
   });



}//f()
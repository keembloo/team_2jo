console.log('상세페이지출력')

let ano=new URL(location.href).searchParams.get("ano"); //경매게시글번호

//(개별)상세페이지 출력 [9월19일 고연진]
auctionPrint(ano);
function auctionPrint(ano){
	
	$.ajax({
      	url : "/nichanaecha/AuctionController",     
     	method : "get",   
     	data : {ano:ano},      
      	success : r=>{console.log('통신성공'+r)
      	
      	
      	} ,       
      	error : e=>{console.log('통신실패'+e)} ,         
   });

	
	
}//f()
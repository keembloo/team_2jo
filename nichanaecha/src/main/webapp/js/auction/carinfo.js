console.log('상세페이지출력')

let ano=new URL(location.href).searchParams.get("ano"); //경매게시글번호

//(개별)상세페이지 출력 [9월19일 고연진]
auctionPrint(2);
function auctionPrint(ano){
	
	$.ajax({
      	url : "/nichanaecha/AuctionController",     
     	method : "get",   
     	data : {ano:ano},      
      	success : r=>{console.log('통신성공'+r)
			// 제목
      		document.querySelector('.atitle').innerHTML=`${r.atitle}`

      		//캐러셀(여러개이미지)
      		let imgbox=document.querySelector('.imgbox');
      		let html=``;
      		Object.values(r.imglist).forEach((img,i)=>{
				  //첫번째 이미지만 active
				  html+=`<div class="carousel-item ${ i==0 ? 'active' : '' }">
					     	 <img src="/jspweb/auction/img/${img}" class="d-block w-100" alt="...">
					      </div>`
			  })
      		imgbox.innerHTML=html;
      		
      		// 경매정보
      		
      		
      		//차량정보
      		document.querySelector('.ccompany').innerHTML=`${r.ccompany}`
      		document.querySelector('.csize').innerHTML=`${r.csize}`
      		document.querySelector('.cname').innerHTML=`${r.cname}`
      		document.querySelector('.coil').innerHTML=`${r.coil}`
      		document.querySelector('.cc').innerHTML=`${r.cc}`
      		document.querySelector('.cdate').innerHTML=`${r.cdate}`
      		document.querySelector('.ckm').innerHTML=`${r.ckm}`
      		document.querySelector('.cads').innerHTML=`${r.cads}`
      		document.querySelector('.acontent').innerHTML=`${r.acontent}`
      		
      		
      	} ,       
      	error : e=>{console.log('통신실패'+e)} ,         
   });

	
	
}//f()
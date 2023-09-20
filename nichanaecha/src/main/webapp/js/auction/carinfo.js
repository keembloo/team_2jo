console.log('상세페이지출력')

let ano=new URL(location.href).searchParams.get("ano"); //경매게시글번호

//(개별)상세페이지 출력 [9월19일 고연진]
auctionPrint(2);
function auctionPrint(ano){
	
	$.ajax({
      	url : "/nichanaecha/AuctionController",     
     	method : "get",   
     	data : {ano:ano},      
      	success : r=>{
			  console.log('통신성공')
			  console.log(r)
					
			// 제목
      		document.querySelector('.atitle').innerHTML=`${r[0].atitle}`

      		//캐러셀(여러개이미지)
      		let imgbox=document.querySelector('.imgbox');
      		let html=``;
      		Object.values(r[0].imglist).forEach((img,i)=>{
				  //첫번째 이미지만 active
				  html+=`<div class="carousel-item ${ i==0 ? 'active' : '' }">
					     	 <img src="/nichanaecha/auction/img/${img}" class="d-block w-100" alt="...">
					      </div>`
			  })
      		imgbox.innerHTML=html;
      		
      		// 경매정보
      		
      		
      		//차량정보
      		document.querySelector('.ccompany').innerHTML=`${r[0].ccompany}`
      		document.querySelector('.csize').innerHTML=`${r[0].csize}`
      		document.querySelector('.cname').innerHTML=`${r[0].cname}`
      		document.querySelector('.coil').innerHTML=`${r[0].coil}`
      		document.querySelector('.cc').innerHTML=`${r[0].cc}`
      		document.querySelector('.cdate').innerHTML=`${r[0].cdate}`
      		document.querySelector('.ckm').innerHTML=`${r[0].ckm}`
      		document.querySelector('.cads').innerHTML=`${r[0].cads}`
      		document.querySelector('.acontent').innerHTML=`${r[1].acontent}`
      		
      		
      	} ,       
      	error : e=>{console.log('통신실패'+e)} ,         
   });

	
	
}//f()
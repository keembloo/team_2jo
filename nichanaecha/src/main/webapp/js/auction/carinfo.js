console.log('상세페이지출력')

let ano=new URL(location.href).searchParams.get("ano"); //경매게시글번호


//(개별)상세페이지 출력 [9월19일 고연진]--------------------------------------------------------
auctionPrint(401);
function auctionPrint(ano){
	
	$.ajax({
      	url : "/nichanaecha/AuctionController",     
     	method : "get",   
     	data : {ano:ano},      
      	success : r=>{
			  console.log('통신성공')
			  console.log(r);
					
			// 제목
      		document.querySelector('.atitle').innerHTML=`${r.atitle}`

      		//캐러셀(여러개이미지)
      		let imgbox=document.querySelector('.imgbox');
      		let html=``;
      		//객체를 배열로 바꾸기
      		Object.values(r.car.imglist).forEach((img,i)=>{
				  //첫번째 이미지만 active
				  html+=`<div class="carousel-item ${ i==0 ? 'active' : '' }">
					     	 <img src="/nichanaecha/auction/img/${img}" class="d-block w-100" alt="...">
					      </div>`
			  })
      		imgbox.innerHTML=html;
      		
      		// 경매정보
      		
      		
      		//차량정보
      		document.querySelector('.ccompany').innerHTML=`${r.car.ccompany}`
      		document.querySelector('.csize').innerHTML=`${r.car.csize}`
      		document.querySelector('.cname').innerHTML=`${r.car.cname}`
      		document.querySelector('.coil').innerHTML=`${r.car.coil}`
      		document.querySelector('.cc').innerHTML=`${r.car.cc}`
      		document.querySelector('.cdate').innerHTML=`${r.car.cdate}`
      		document.querySelector('.ckm').innerHTML=`${r.car.ckm}`
      		document.querySelector('.cads').innerHTML=`${r.car.cads}`
      		document.querySelector('.acontent').innerHTML=`${r.acontent}`
      		
      		
      	} ,       
      	error : e=>{console.log('통신실패'+e)} ,         
   });

	
	
}//f()


//스크랩 기능 [9월21일 고연진] -onclick()--------------------------------------------------------
/* 
	기능: 버튼 누를 시 wishlist 테이블에 추가되고 스크립트 모양 변경
	필요한거: 회원번호, 게시물번호
*/
function clipping(){
	console.log('onclick함수 실행')
	//회원에 한해 사용 가능하도록
	if(loginMid==''){location.href='../member/memberlogin.jsp'}
	$.ajax({
      	url : "/nichanaecha/WishListController",     
     	method : "post",  
     	async: false, 
     	data : {ano:ano}, //회원번호는 세션에 저장(전달x)      
      	success : r=>{console.log('통신성공');console.log(r)
      		if(r){console.log('스크랩성공')
      		
      		}
      	} ,       
      	error : e=>{console.log(e)} ,         
   });


}//f()

// 스크랩 성공 시 출력되는 함수 [9월21일 고연진]-------------------------------------------------------
/*
	clipping() 함수 성공 시 실행 . 스크랩 성공 시 아이콘 변경.
	비로그인시, 로그인 시 
 */
function clipState(){
	
}//f()

// 입찰 버튼 클릭 시 [9월22일 고연진]--------------------------------------------------------------
/*
	새창띄움.
	유효성검사(회원에 한해 진행, 기본 금액보다 높은 금액, 보유 금액보다 적은 금액)
	form전송 전에 입찰 수정 불가임을 보여 줄 알림창. 
	등록 성공하면 보유 금액에서 차감. 
 */

 function batting(){console.log('batting()함수 실행')
	 let batPay = document.querySelector('.batPay').value;
	 
 }//f()
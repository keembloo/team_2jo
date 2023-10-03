console.log('경매내역출력.js')
let ano=new URL(location.href).searchParams.get("ano"); //경매게시글번호

buyView(ano)
function buyView(ano){
	console.log('전체출력')
			$.ajax({
			url : "/nichanaecha/BattingController" , 
			async : false ,
			method : "get" ,
			data : {type:'all', ano:ano} ,
			success : r => { 
				console.log('경매내역출력통신성공')
				//console.log(r)
				let buyno = document.querySelector('.buyno');
				r.forEach(b=>{
					console.log(`${b.bprice}원`)
					buyno.innerHTML += 
					` <tr>
						<th scope="row"></th>
		   			   	<td>${b.bprice}원</td>
		   			   	<td>${b.bDate}</td>
		   			   	<td>${b.mid}</td>
		   			  </tr>
					`
					
					
				})
				
				
				
				
				
			} , 
			error : e => {console.log("경매내역출력통신실패");}		
		})
}//f()

// 6. ---------------- 카카오 지도  표시 --------------------------- //

	// 1.현재 접속한 클라이언트[브라우저]의 위치 좌표 구하기 
navigator.geolocation.getCurrentPosition( e => {

	console.log( e );
	console.log( e.coords );	
	console.log( e.coords.latitude ); // 위도 
	console.log( e.coords.longitude ); // 경도 
	let currentlat = e.coords.latitude;
	let currentlng = e.coords.longitude;
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng( currentlat , currentlng ), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
	    
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	// 지도를 클릭한 위치에 표출할 마커입니다
	var marker = new kakao.maps.Marker({ 
	    // 지도 중심좌표에 마커를 생성합니다 
	    position: map.getCenter() 
	}); 
	
	// 지도에 마커를 표시합니다
	marker.setMap(map);
	
	// 지도에 클릭 이벤트를 등록합니다
	// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {     
	    // 클릭한 위도, 경도 정보를 가져옵니다 
	    var latlng = mouseEvent.latLng; 
	    // 마커 위치를 클릭한 위치로 옮깁니다
	    marker.setPosition(latlng);
	    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
	    message += '경도는 ' + latlng.getLng() + ' 입니다';
	    var resultDiv = document.getElementById('clickLatlng'); 
	    resultDiv.innerHTML = message;
	    
	    plat = latlng.getLat(); // 위도와 경도를 전역변수로 이동후 제품등록시 사용.
	    plng = latlng.getLng();
	    
	});
	
}); // getCurrentPosition end 

let plat = 0;		// 현재 카카오지도에서 선택한 좌표.. 초기값은 0 / 
let plng = 0;

// -------------------------------------------------------------- //

function bcarsubmit(){
	//1. form 가져오기
	let form = document.querySelectorAll('.carsubmitForm')[0];	console.log( carsubmitForm );
	
	//2. form 객체화 하기
	let formData = new FormData(form);		console.log(formData);
	
		// *** 카카오지도에서 선택된 좌표 사용했을때.
		if( plat == 0 || plng == 0 ){ 
			alert('제품위치를 선택해주세요.'); 
			return; 
		}
		formData.set( 'plat' , plat );
		formData.set( 'plng' , plng );
	
		// *** 드랍앤드랍을 사용했을때. 
			// 현재 드랍된 파일들을 form 같이 추가하기 [ 왜? 드랍된 파일은 input태그 드랍된 파일이 아니므로] 
		if( fileList.length >= 1 ){ // 드랍된 파일 1개 이상이면  
			fileList.forEach( f =>{
				//formData.set( 'filelList' , f ); 
				formData.append( 'filelList' , f );
					// -폼데이터객체에 데이터를 추가하는 방법.
					// 폼데이터객체.set( '키/필드명/속성명' , 값 ); 		
						// : 만약에 기존의 동일한 키 가 있으면 덮어쓰기 되므로 기존 사라짐 
					// 폼데이터객체.append( '키/필드명/속성명' , 값 ); 	
						// : 만약에 기존의 동일한 키 가 있으면 해당 키에 데이터 추가 
					// 폼데이터객체.delete( '키' );	: 폼데이터객체내 데이터 삭제
			});
		}
		
	$.ajax({
		url : "/nichanaecha/AuctionController" , 
		method: "post" , 
		data : formData ,
		contentType : false , 
		processData : false ,
		success : r => {
			
			if( r ){
				alert('차량등록 성공');
				location.href="/nichanaecha/src/main/webapp/index.jsp";
			}else{
				alert('차량등록 실패 ');
			}
			
		} , 
		error : e => { } 
	})
}

let fileList = [] ; // 현재 드래그앤드랍으로 등록된 파일들 

// 3. 현재 드랍된 파일들의 정보를 구역에 표시하는 함수 
function fileListPrint(){
	// 1. [어디에]fileDropBox 에  [ fileDropBox dom객체가 함수밖에 존재하므로 재호출 X ]
	// 2. [무엇을] HTML 구성 
	let html = ``;
		fileList.forEach( ( f , i ) => { 
			let fname = f.name; // 파일의 이름 
			let fsize = ( f.size/1024 ).toFixed(1) // 파일의 용량 [ 바이트 단위 ] kb 변환 
						// .toFixed(소수점단위) : 소수점 자르기 
			html += `<div>
						<span> ${fname} </span>
						<span> ${fsize} </span>
						<span> <button onclick="fileDelete(${ i })" type="button">삭제</button> </span>
					</div>`;
		})
	// 3. [ 대입 ]
	fileDropBox.innerHTML = html;
} // f end 
// 4. 현재 드랍된 파일들 중에 삭제버튼 클릭시 해당 파일 제거 
function fileDelete( i ){  fileList.splice( i , 1 ); fileListPrint(); }

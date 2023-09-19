
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
	let form = document.querySelectorAll('.carsubmitForm')[0];
	
	//2. form 객체화 하기
	let formData = new FormData(form);
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
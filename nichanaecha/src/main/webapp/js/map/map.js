let boardList = {}; 


// 카카오 지도 api
let getTexts = '';

  var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(37.312345, 126.8256878), // 지도의 중심좌표
        level : 8 // 지도의 확대 레벨
    });

    // 마커 클러스터러를 생성합니다
    // 마커 클러스터러를 생성할 때 disableClickZoom 값을 true로 지정하지 않은 경우
    // 클러스터 마커를 클릭했을 때 클러스터 객체가 포함하는 마커들이 모두 잘 보이도록 지도의 레벨과 영역을 변경합니다
    // 이 예제에서는 disableClickZoom 값을 true로 설정하여 기본 클릭 동작을 막고
    // 클러스터 마커를 클릭했을 때 클릭된 클러스터 마커의 위치를 기준으로 지도를 1레벨씩 확대합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 1, // 클러스터 할 최소 지도 레벨
        disableClickZoom: true, // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
        styles: [{	// 마커 클러스터리 스타일 지정
                backgroundColor: 'rgba(0, 123, 255, 0.6)',
			    color: '#fff',
			    border: '1px solid #007bff',
			    borderRadius: '100%',
			    padding: '10px',
			    textAlign: 'center',
			    width: '48px'
			    //width: '100%'
            }]
        
    });
    clusterer.setMinClusterSize(2); // 클러스터 최소 개수
    getInfo()

    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다

    // 마커 클러스터러에 클릭이벤트를 등록합니다
    // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우
    // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
 

/* --------------------------- 이벤트 영역 --------------------------- */

// 드래그 이벤트
kakao.maps.event.addListener(map, 'dragend', function() {
    getInfo();
});

//확대, 축소 이벤트
kakao.maps.event.addListener(map, 'zoom_changed', function() {        
	getInfo();    
});

// 클러스터 클릭 이벤트
kakao.maps.event.addListener(clusterer, 'clusterclick', function(cluster) {
	
	
	
	let markerList = [];
		
	cluster.getMarkers().forEach( p => { // 클러스터안의 마커 정보 배열로 반환 후 markerList에 cno 저장 후 배열로 반환
			
		markerList.push(p.cno)
				
	})
		
	clusterPrint(markerList)
});



function getInfo() {
    // 지도의 현재 중심좌표를 얻어옵니다 
    var center = map.getCenter(); 
    
    // 지도의 현재 레벨을 얻어옵니다
    var level = map.getLevel();
    
    // 지도타입을 얻어옵니다
    var mapTypeId = map.getMapTypeId(); 
    
    // 지도의 현재 영역을 얻어옵니다 
    var bounds = map.getBounds();
    
    // 영역의 남서쪽 좌표를 얻어옵니다 
    var swLatLng = bounds.getSouthWest(); 
    
    // 영역의 북동쪽 좌표를 얻어옵니다 
    var neLatLng = bounds.getNorthEast(); 
    
    // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
    var boundsStr = bounds.toString();
    
    let east = neLatLng.getLat();
	let west = swLatLng.getLat();
	let south = swLatLng.getLng();
	let north = neLatLng.getLng();
	
	var level = map.getLevel();
	
	mapAreaPrint(east, west, south, north, level);
    
}   

function mapAreaPrint(east, west, south, north, level){
		
	clusterer.clear();
	
	if(level>4){
		clusterer.setMinLevel(20);
	}else{
		clusterer.setMinLevel(0);
	}
	$.ajax({
		url : "/nichanaecha/MapController",
		method : "get",
		async : false,
		data : {type : "mapAreaPrint", east : east, west : west, south : south, north : north, level : level},
		success : r => {
			console.log("level : "+level);
			if(level > 4){ // 확대 레벨 4 초과시 지역별로 묶어서 출력
				var customOverlay = r.map( p => { 
					var content = `
									<div onclick="listPrint('${ p.areaName }', ${level} )" class="customoverlay">
									    <span class="marker-title">${p.areaName}</span>
									    <span class="marker-count">${p.count}</span>
									</div>`
								    
					return new kakao.maps.CustomOverlay({
						map: map,
						position: new kakao.maps.LatLng(p.calat, p.calng),
						content: content,
						xAnchor: 1,
						yAnchor: 1 
					});
				});
			}else{	// 확대 레벨 3 이하시 개별 출력, 근접 위치는 클러스터 기능 사용
				var customOverlay = r.map( p => {
					
					
					var content = `
									<div onclick="clusterPrint(${p.cno})" class="customoverlay">
									    <span class="marker-title">${p.cname}</span>
									</div>`	
					
					let kamap = new kakao.maps.CustomOverlay({
						map: map,
						position: new kakao.maps.LatLng(p.calat, p.calng),
						content: content,
						xAnchor: 1,
						yAnchor: 1,
						
					});
					kamap.cno = p.cno;
					
					return kamap;
					
				});
			}
			// 클러스터러에 마커들을 추가합니다
			clusterer.addMarkers(customOverlay);
			/* 마커 생성 후 클러스터 저장 */
			
		},
		error : e => {
			console.log('에러 : '+e)
		}
	})
}

// 지역명 클러스터 클릭시 작동하는 함수
function listPrint(areaName, level){
	
	if(level>8){ // 확대 레벨 8 초과이면 클릭 시 확대
		map.setLevel(level-1);
		return;
	}
	
	let auctionList = document.querySelector('.auctionList');
    
    auctionList.style.display = 'block';
	
	setTimeout(function () {
        auctionList.style.opacity = 1;
    }, 100); // 100ms 후에 투명도를 1로 변경
    
    
	 
	$.ajax({
		url : "/nichanaecha/MapController",
		method: "get",
		async: false,
		data: {type : "listPrint", areaName: areaName, level : level },
		success: r =>{
			r.areaName = areaName;
			
			boardList = r; // 결과 전역변수에 저장
			
			boardPrint(r) // 게시물 출력 공통 함수 호출
			
		},
		error: e =>{
			console.log('에러 발생 : '+e)
			
			
		}
	
		
	})
	
	
	
} // f n

// 카카오 api로 클러스터된 영역 클릭 시 리스트 출력 함수
function clusterPrint( cnoList ){
	
	let auctionList = document.querySelector('.auctionList');
    
    auctionList.style.display = 'block';
	
	setTimeout(function () {
        auctionList.style.opacity = 1;
    }, 100); // 100ms 후에 투명도를 1로 변경
	
	
	
	// 리스트를 json 형태로 변환
	jsonList = JSON.stringify(cnoList);
	
	$.ajax({
		url : "/nichanaecha/MapController",
		method: "get",
		async: false,
		data: {type : "clusterPrint", cnoList : jsonList },
		success: r =>{
			boardList = r; // 결과 전역변수에 저장
			
			boardPrint(r) // 게시물 출력 공통 함수 호출
				

		},
		error: e =>{
			console.log('에러 발생 : '+e)
			
			
		}
	
		
	})
	
	
	
}

// 리스트 닫기 함수
function listClose() {
	let auctionList = document.querySelector('.auctionList');
	
	document.querySelector('.listTitle').innerHTML = ``; 
	document.querySelector('.cardList').innerHTML = ``;
	
	auctionList.style.display = 'none';
	auctionList.style.opacity = '0';
	
	
}

// 리스트 출력 공통 함수
function boardPrint(object){
	
	const currentDate = new Date(); // 현재 시간 반환
			
	let html = ``;
	
	object.forEach(p => {
				// 현재시간, 반환 시간 비교하여 차이일수로 반환, 0이면 '오늘' 출력
				let inputDate = new Date(p.aenddate);
				
				let timeDifference = Math.floor((inputDate - currentDate) / (1000 * 60 * 60 * 24))
				
				timeDifference = 0 ? timeDifference = '오늘' : timeDifference = timeDifference+"일 전";
				
				html += `
						<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
							<div class="card mb-1 p-2" style="width: 100%;">
							  <img src="../auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="...">
							  <div class="card-body">
								   <h5 class="card-title">${p.atitle}</h5>
							    <p class="card-text">${p.acontent}</p>
							  </div>
							  <ul class="list-group list-group-flush">
							    <li class="list-group-item">경매 시작일 : ${p.astartdate.slice(0,10)} </li>
							    <li class="list-group-item">경매 종료일 : ${timeDifference} </li>
							    <li class="list-group-item">입찰 시작가 : ${p.aprice.toLocaleString()}원 </li>
							  </ul>
							</div>
						</a>
				`
				
			})
			
	
	
	let cardList = document.querySelector('.cardList');

	cardList.innerHTML = ``;

	cardList.scrollTo({ top: 0, behavior: 'smooth' })
	
	if(object.areaName != undefined ) { // 반환 속성에 지역명이 없을 경우 '' 출력
		$('.listTitle').fadeOut(100, function() {
			// 내용을 페이드 아웃한 후에 내용을 업데이트하고 다시 페이드 인
			$(this).html(document.querySelector('.listTitle').innerHTML = object.areaName).fadeIn(100);
		});
	}else{
		
		document.querySelector('.listTitle').innerHTML = '';
		
	}
	
	$('.cardList').fadeOut(100, function() {
		$(this).html(cardList.innerHTML = html).fadeIn(100);
	});


}


// 경매 제목, 내용 검색 함수
function auctionSearch(){
	
	let keyword = document.querySelector('.aucSerchValue').value;
	
	const currentDate = new Date(); // 현재 시간 반환
			
	let html = ``;
	
	for(let p of boardList){
		
		console.log('title : '+p.atitle.indexOf(keyword))
		console.log('content : '+p.acontent.indexOf(keyword))
		
		if (p.atitle.indexOf(keyword) == -1 && p.acontent.indexOf(keyword) == -1) {
			continue;
		}

		// 현재시간, 반환 시간 비교하여 차이일수로 반환, 0이면 '오늘' 출력
		let inputDate = new Date(p.aenddate);

		let timeDifference = Math.floor((inputDate - currentDate) / (1000 * 60 * 60 * 24))

		timeDifference = 0 ? timeDifference = '오늘' : timeDifference = timeDifference + "일 전";

		html += `
						<a href="/nichanaecha/auction/carinfo.jsp?cno=${p.cno}">
							<div class="card mb-1 p-2" style="width 100%;">
							  <img src="../auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="...">
							  <div class="card-body">
								   <h5 class="card-title">${p.atitle}</h5>
							    <p class="card-text">${p.acontent}</p>
							  </div>
							  <ul class="list-group list-group-flush">
							    <li class="list-group-item">경매 시작일 : ${p.astartdate.slice(0, 10)} </li>
							    <li class="list-group-item">경매 종료일 : ${timeDifference} </li>
							    <li class="list-group-item">입찰 시작가 : ${p.aprice.toLocaleString()}원 </li>
							  </ul>
							</div>
						</a>
				`

	}
	
	document.querySelector('.cardList').innerHTML = html
	
}








 
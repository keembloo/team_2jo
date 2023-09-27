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
	boardList = {};
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
					
					let cno = [p.cno];
					
					var content = `
									<div onclick="${clusterPrint(cno)}" class="customoverlay">
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


function listPrint(areaName, level){
	 
	$.ajax({
		url : "/nichanaecha/MapController",
		method: "get",
		data: {type : "listPrint", areaName: areaName, level : level },
		success: r =>{
			let cardList = document.querySelector('.cardList');
			
			let html = ``;
			
			document.querySelector('.listTitle').innerHTML = areaName;
			
			cardList.scrollTo({ top: 0 })
			
			r.forEach(p => {
				html += `
							<div class="card mb-1 p-2" style="width:width: 100%;">
							  <img src="../auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="...">
							  <div class="card-body">
								   <h5 class="card-title">${p.atitle}</h5>
							    <p class="card-text">${p.acontent}</p>
							  </div>
							  <ul class="list-group list-group-flush">
							    <li class="list-group-item">경매 시작일 : 2023-09-26</li>
							    <li class="list-group-item">경매 종료일 : 2023-09-26</li>
							    <li class="list-group-item">현재 입찰가 : 14,000,000원</li>
							  </ul>
							</div>					
				`
				
			})
				
				cardList.innerHTML = html;
			
			
		},
		error: e =>{
			console.log('에러 발생 : '+e)
			
			
		}
	
		
	})
	
	
	
} // f n


function clusterPrint( cnoList ){
	
	// 리스트를 json 형태로 변환
	jsonList = JSON.stringify(cnoList);
	
	$.ajax({
		url : "/nichanaecha/MapController",
		method: "get",
		data: {type : "clusterPrint", cnoList : jsonList },
		success: r =>{
			let cardList = document.querySelector('.cardList');
			
			let html = ``;
			
			document.querySelector('.listTitle').innerHTML = r[0].car.carAddress.cacodename;
			
			cardList.scrollTo({ top: 0 })
			
			r.forEach(p => {
				html += `
							<div class="card mb-1 p-2" style="width:width: 100%;">
							  <img src="../auction/img/${Object.values(p.car.imglist)[0]}" class="card-img-top" alt="...">
							  <div class="card-body">
								   <h5 class="card-title">${p.atitle}</h5>
							    <p class="card-text">${p.acontent}</p>
							  </div>
							  <ul class="list-group list-group-flush">
							    <li class="list-group-item">경매 시작일 : 2023-09-26</li>
							    <li class="list-group-item">경매 종료일 : 2023-09-26</li>
							    <li class="list-group-item">현재 입찰가 : 14,000,000원</li>
							  </ul>
							</div>					
				`
				
			})
				
				cardList.innerHTML = html;
			
			
		},
		error: e =>{
			console.log('에러 발생 : '+e)
			
			
		}
	
		
	})
	
	
	
}


 
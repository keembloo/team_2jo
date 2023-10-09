let boardList = {}; 
var adsMarkers = []; // 주소 마커 초기화용 배열
let startMonth = '1980-01' // 검색 시작 년도
let currentMonth = ''; // 현재 달 저장용 변수

defaultMonth()
//옵션 검색용 전역 객체
var optionsData = {
	carType: {
		manufacturer: [
			"현대",
			"기아",
			"르노삼성",
			"쉐보레",
			"쌍용"
		],
		carClass: [
			"대형",
			"중형",
			"준중형",
			"소형"
		],
	},
	year: {
		minYear: startMonth,
		maxYear: currentMonth,
	},
	mileage: {
		minMileage: 0,
		maxMileage: 10000000,
	},
	
	price: {
		minPrice: 0,
		maxPrice: 2000000000,
	},
	
	fuelType: [
		"휘발유",
		"디젤",
		"LPG",
		"하이브리드",
		"전기",
		"수소"
	],
};

// 카카오 지도 api
let getTexts = '';


//주소 검색에서 엔터 입력 시 검색 함수 실행 이벤트
var adsSearchEvent = document.querySelector('.adsSearch');


var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
	center: new kakao.maps.LatLng(37.312345, 126.8256878), // 지도의 중심좌표
	level: 8 // 지도의 확대 레벨
});


// 옵션 적용 함수
function applyOptions() {
	// 제조사 데이터 적용
	optionsData.carType.manufacturer = [];
	var manufacturerCheckboxes = document.querySelectorAll('.manufacturer');
	manufacturerCheckboxes.forEach(function(checkbox) {
		if (checkbox.checked) {
			optionsData.carType.manufacturer.push(checkbox.value);
		}
	});

	// 차종 데이터 적용
	optionsData.carType.carClass = [];
	var carClassCheckboxes = document.querySelectorAll('.carClass');
	carClassCheckboxes.forEach(function(checkbox) {
		if (checkbox.checked) {
			optionsData.carType.carClass.push(checkbox.value);
		}
	});

	// 연식 데이터 적용
	optionsData.year.minYear = document.getElementById('customRange1-start').value;
	optionsData.year.maxYear = document.getElementById('customRange1-end').value;

	// 주행거리 데이터 적용
	let minKm = document.querySelector('.minKm').value
	let maxKm = document.querySelector('.maxKm').value
	
	if(maxKm == 200000){
		maxKm = 10000000;
	}
	
	optionsData.mileage.minMileage = minKm;
	optionsData.mileage.maxMileage = maxKm;

	// 연료 데이터 적용
	optionsData.fuelType = [];
	var fuelTypeCheckboxes = document.querySelectorAll('.fuelType');
	fuelTypeCheckboxes.forEach(function(checkbox) {
		if (checkbox.checked) {
			optionsData.fuelType.push(checkbox.value);
		}
	});

	// 가격 데이터 적용
	let minPriceValue = document.querySelector('.minPriceValue').value
	let maxPriceValue = document.querySelector('.maxPriceValue').value
	
	if(minPriceValue == ''){
		minPriceValue = 0;
	}
	if(maxPriceValue == ''){
		maxPriceValue = 100000000000;
	}
	
	optionsData.price.minPrice = minPriceValue;
	optionsData.price.maxPrice = maxPriceValue;

	console.log(optionsData);
}

// 옵션 초기화
function defaultOption() {
	
	// 제조사 초기화
	var manufacturerCheckboxes = document.querySelectorAll('.manufacturer');
	for (var i = 0; i < manufacturerCheckboxes.length; i++) {
		manufacturerCheckboxes[i].checked = true; // 체크값 순회하면서 true로 변경
	}
	
	// 차종 초기화
	var carClassCheckboxes = document.querySelectorAll('.carClass');
	for (var i = 0; i < carClassCheckboxes.length; i++) {
		carClassCheckboxes[i].checked = true; // 체크값 순회하면서 true로 변경
	}

	// 연식 초기화
	optionsData.year.minYear = document.getElementById('customRange1-start').value = '2000-01';
	optionsData.year.maxYear = document.getElementById('customRange1-end').value = currentMonth;

	// 주행거리 초기화
	document.querySelector('.minKm').value = 0;
	document.querySelector('.maxKm').value = 200000;

	// 연료 초기화
	var fuelCheckboxes = document.querySelectorAll('.fuelType');
	for (var i = 0; i < fuelCheckboxes.length; i++) {
		fuelCheckboxes[i].checked = true; // 체크값 순회하면서 true로 변경
	}

	// 가격 초기화
	document.querySelector('.minPriceValue').value = '';
	document.querySelector('.maxPriceValue').value = '';

	document.querySelector('.minPricePrint').innerHTML = '0원';
	document.querySelector('.maxPricePrint').innerHTML = '무제한';
	document.querySelector('.minKmPrint').innerHTML = '0km';
	document.querySelector('.maxKmPrint').innerHTML = '무제한';

	optionsData = {
		carType: {
			manufacturer: [
				"현대",
				"기아",
				"르노삼성",
				"쉐보레",
				"쌍용"
			],
			carClass: [
				"대형",
				"중형",
				"준중형",
				"소형"
			],
		},
		year: {
			minYear: startMonth,
			maxYear: currentMonth,
		},
		mileage: {
			minMileage: 0,
			maxMileage: 10000000,
		},
		fuelType: [
			"휘발유",
			"디젤",
			"LPG",
			"하이브리드",
			"전기",
			"수소"
		],
		price: {
			minPrice: 0,
			maxPrice: 100000000000,
		},
	}
}


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
    
    // 지도의 현재 레벨을 얻어옵니다
    var level = map.getLevel();
    
    // 지도의 현재 영역을 얻어옵니다 
    var bounds = map.getBounds();
    
    // 영역의 남서쪽 좌표를 얻어옵니다 
    var swLatLng = bounds.getSouthWest(); 
    
    // 영역의 북동쪽 좌표를 얻어옵니다 
    var neLatLng = bounds.getNorthEast(); 
    
    
    let east = neLatLng.getLat();
	let west = swLatLng.getLat();
	let south = swLatLng.getLng();
	let north = neLatLng.getLng();
	
	var level = map.getLevel();
	
	mapAreaPrint(east, west, south, north, level);
    
}   

function mapAreaPrint(east, west, south, north, level){
		
	clusterer.clear();
	
	jsonObject = JSON.stringify(optionsData);
	
	if(level>4){
		clusterer.setMinLevel(20);
	}else{
		clusterer.setMinLevel(0);
	}
	$.ajax({
		url : "/nichanaecha/MapController",
		method : "get",
		async : false,
		data : {type : "mapAreaPrint", east : east, west : west, south : south, north : north, level : level, jsonObject : jsonObject},
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

// 주소 검색후 해당 위치로 이동 함수
function adsSearch(){
	
var geocoder = new kakao.maps.services.Geocoder();

let inputValue = document.querySelector('.adsSearch').value

// 주소로 좌표를 검색합니다
geocoder.addressSearch(inputValue, function(result, status) {
	
	// 마커 배열에 담긴 마커들 순회하며 null 대입하여 초기화
	adsMarkers.forEach(p => {
		p.setMap(null);
	});
	
    // 정상적으로 검색이 완료됐으면 
    if (status === kakao.maps.services.Status.OK) {
		 
		var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		 var callback = function(result, status) {
           if (status === kakao.maps.services.Status.OK) {
			   map.setLevel(5);

			   var address = result[0].address.region_2depth_name;

			   var content = `
                    <div class="adsOverlay">
                        <span class="marker-title">${address}</span>
                    </div>`;

			   var customOverlay = new kakao.maps.CustomOverlay({
				   map: map,
				   position: coords,
				   content: content,
				   xAnchor: 1,
				   yAnchor: 1
			   });

               customOverlay.setZIndex(3);

               // 커스텀 오버레이를 지도에 표시
               customOverlay.setMap(map);
               
               adsMarkers.push(customOverlay);

               // 지도의 중심을 결과값으로 받은 위치로 이동
               map.setCenter(coords);

               getInfo();
               
               adsSearchEvent.value = '';
           }
           else{
				alert('정확한 주소를 입력해 주세요.');
				return;
			}
        }

        geocoder.coord2Address(result[0].x, result[0].y, callback);
    }
	 else {
		 alert('정확한 주소를 입력해 주세요.');
		 return;
	 }
});

	
}



// 주소 검색 엔터키 이벤트 등록
adsSearchEvent.addEventListener('keydown', function(event) {
	
	//엔터키 입력 시 주소 검색 함수 실행
    if (event.keyCode === 13) {
		adsSearch()
    }
});


//---------------- 옵션 그룹 이벤트 영역---------------------------

// 주행거리 표시 함수 그룹

// 최소 주행거리 표시
function minKmPrint(){
	let minKmValue = document.querySelector('.minKm').value;
	let minKmPrint = document.querySelector('.minKmPrint');
	
	minKmPrint.innerHTML = parseInt(minKmValue, 10).toLocaleString()+"km";
	
}

// 최대 주행거리 표시
function maxKmPrint(){
	let maxKmValue = document.querySelector('.maxKm').value;
	let maxKmPrint = document.querySelector('.maxKmPrint');
	
	if(maxKmValue==200000){
		maxKmPrint.innerHTML = "무제한";
		return;
	}
	maxKmPrint.innerHTML = parseInt(maxKmValue, 10).toLocaleString()+"km";
	
}

let minKmInput = document.querySelector('.minKm');
minKmInput.addEventListener('input', function () {
    let minKmValue = parseInt(this.value, 10);
    let maxKmInput = document.querySelector('.maxKm');
    let maxKmValue = parseInt(maxKmInput.value, 10);

    if (minKmValue > maxKmValue) {
        maxKmInput.value = minKmValue;
        maxKmInput.dispatchEvent(new Event('input')); // 최대 주행거리에 대한 input 이벤트 다시 발생
    }

    updateMinKmPrint(minKmValue);
});

// 최대 주행거리(input 요소)에 대한 이벤트 처리
let maxKmInput = document.querySelector('.maxKm');
maxKmInput.addEventListener('input', function () {
    let maxKmValue = parseInt(this.value, 10);
    let minKmInput = document.querySelector('.minKm');
    let minKmValue = parseInt(minKmInput.value, 10);

    if (maxKmValue < minKmValue) {
        minKmInput.value = maxKmValue;
        minKmInput.dispatchEvent(new Event('input')); // 최소 주행거리에 대한 input 이벤트 다시 발생
    }

    updateMaxKmPrint(maxKmValue);
});

// 최소 주행거리 출력 업데이트
function updateMinKmPrint(value) {
    let minKmPrint = document.querySelector('.minKmPrint');
    minKmPrint.textContent = value.toLocaleString() + "km";
}

// 최대 주행거리 출력 업데이트
function updateMaxKmPrint(value) {
    let maxKmPrint = document.querySelector('.maxKmPrint');
    
    maxKmPrint.textContent = value.toLocaleString() + "km";
}


 
 // 경매가 출력 함수 그룹
 
// 최소 경매 가격 출력 함수
function minPricePrint(e) {
	let minPricePrint = document.querySelector('.minPricePrint');
	
	if (e.value == '') {
		minPricePrint.innerHTML = '0원';
		return;
	}
	
	if (e.value.length > e.maxLength) {
		e.value = e.value.slice(0, e.maxLength);
	}
	
	minPricePrint.innerHTML = parseInt(e.value, 10).toLocaleString() + "원";
	
}

// 최소 경매 가격 출력 함수
function maxPricePrint(e) {
	let maxPricePrint = document.querySelector('.maxPricePrint');
	
	if (e.value == '') {
		maxPricePrint.innerHTML = '무제한';
		return;
	}
	
	if (e.value.length > e.maxLength) {
		e.value = e.value.slice(0, e.maxLength);
	}
	
	
	
	maxPricePrint.innerHTML = parseInt(e.value, 10).toLocaleString() + "원";
	
}


// 기본 날짜 설정
function defaultMonth() {
	var curDate = new Date();
	var curYear = curDate.getFullYear();
	var curMonth = (curDate.getMonth() + 1).toString().padStart(2, '0');
	currentMonth = curYear + '-' + curMonth;
	
	document.getElementById('customRange1-start').value = "2000-01";
	document.getElementById('customRange1-end').value = currentMonth;

}
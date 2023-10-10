package controller.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.AuctionDao;
import model.dto.AuctionDto;
import model.dto.CarAddressDto;
import model.dto.OptionDto;

@WebServlet("/MapController")
public class MapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MapController() {
   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		ObjectMapper mapper = new ObjectMapper();
		
		String optionsData = request.getParameter("jsonObject");
		
		// 옵션 객체 저장용 함수 호출
		OptionDto optionDto = optionDto(optionsData);
		
		response.setContentType("application/json;charset=UTF-8");
		// 동,서,남,북 좌표로 결과 반환해주는 함수
		if(type.equals("mapAreaPrint")) {
			int level = Integer.parseInt(request.getParameter("level"));
			String east = request.getParameter("east");
			String west = request.getParameter("west");
			String south = request.getParameter("south");
			String north = request.getParameter("north");
			List<CarAddressDto> list = new ArrayList<>();
			
			//level(확대 레벨) 4이하 좌표 내 모든 매물 반환, level 5 행정동 기준 클러스터, level 5 초과시 광역시 또는 지방 자치구 별로 반환
			if( level <= 4 ) {
				list = AuctionDao.getInstence().mapAreaPrint1(east, west, south, north, optionDto); 
			}
			else if( level <= 5 ) {
				list = AuctionDao.getInstence().mapAreaPrint2(east, west, south, north, optionDto); 
			}
			else if( level > 5 ) {
				list = AuctionDao.getInstence().mapAreaPrint3(east, west, south, north, level, optionDto); 
			}
			
			String jsonObject = mapper.writeValueAsString(list);
			
			response.getWriter().print(jsonObject);
			
			// 커스텀 클러스터 클릭시 리스트 출력
		}else if(type.equals("listPrint")) {
			int level = Integer.parseInt(request.getParameter("level"));
			String areaName = request.getParameter("areaName");
			
			List<AuctionDto> list = AuctionDao.getInstence().listPrint(areaName, level, optionDto);
			String jsonObject = mapper.writeValueAsString(list);
			
			response.getWriter().print(jsonObject);
			
			// 클러스터 또는 개별 매물 클릭시 출력
		}else if(type.equals("clusterPrint")) { 
			String cnoList = request.getParameter("cnoList");
			
			List<Integer> list = new ArrayList<>();
			
			// 파라미터값이 한개 ( 쉼표가 없으면 ) 리스트 한개 추가, 그 외 배열 값 정수 리스트로 변환
			if(cnoList.indexOf(",") == -1) {
				
				list.add(Integer.parseInt(cnoList));
			}
			else {
				list = mapper.readValue(cnoList,mapper.getTypeFactory().constructCollectionType(List.class, Integer.class));
			}
			
			
			List<AuctionDto> AuctionDtolist = AuctionDao.getInstence().clusterPrint(list, optionDto);
			String jsonObject = mapper.writeValueAsString(AuctionDtolist);
			
		
			response.getWriter().print(jsonObject);
		
		}
		
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	// 옵션 저장용 함수
	protected OptionDto optionDto (String str) {
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonNode jsonNode =  mapper.readTree( str );
			
			OptionDto dto = new OptionDto();
			
			Map<String, Map<String, List<String>>> jsonData = mapper.readValue(str, Map.class);
			Map<String, Map<String, String>> jsonData2 = mapper.readValue(str, Map.class);
			Map<String, Map<String, Integer>> jsonData3 = mapper.readValue(str, Map.class);
			Map<String, List<String>> jsonData5 = mapper.readValue(str, Map.class);
			
			Map<String, List<String>> carType = jsonData.get("carType");
			Map<String, String> year = jsonData2.get("year");
			Map<String, Integer> mileage = jsonData3.get("mileage");
			
			dto.setManufacturer(carType.get("manufacturer"));
			dto.setCarClass(carType.get("carClass"));
			dto.setMinYear(year.get("minYear"));
			dto.setMaxYear(year.get("maxYear"));
			dto.setMinMileage(mileage.get("minMileage"));
			dto.setMaxMileage(mileage.get("maxMileage"));
			dto.setFuelType(jsonData5.get("fuelType"));
			// readValue로 Long 형변환이 불가하여 readTree 구조 사용 
			dto.setMinPrice( Long.parseLong( jsonNode.get("price").get("minPrice").toString()) );
			dto.setMaxPrice( Long.parseLong( jsonNode.get("price").get("maxPrice").toString()));
		
			return dto;
		
		} catch (Exception e) {
			System.out.println("옵션 저장 함수 오류 : "+e);
			return null;
		}
	}

}

package controller.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.AuctionDao;
import model.dto.CarAddressDto;

@WebServlet("/MapController")
public class MapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MapController() {
   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 동,서,남,북 좌표로 결과 반환해주는 함수
		
		String east = request.getParameter("east");
		String west = request.getParameter("west");
		String south = request.getParameter("south");
		String north = request.getParameter("north");
		int level = Integer.parseInt(request.getParameter("level"));
		List<CarAddressDto> list = new ArrayList<>();
		
		//level(확대 레벨) 4이하 좌표 내 모든 매물 반환, level 5 행정동 기준 클러스터, level 5 초과시 광역시 또는 지방 자치구 별로 반환
		if( level <= 3 ) {
			list = AuctionDao.getInstence().mapAreaPrint1(east, west, south, north); 
		}
		else if( level <= 5 ) {
			list = AuctionDao.getInstence().mapAreaPrint2(east, west, south, north); 
		}
		else if( level > 5 ) {
			list = AuctionDao.getInstence().mapAreaPrint3(east, west, south, north, level); 
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonObject = mapper.writeValueAsString(list);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(jsonObject);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}

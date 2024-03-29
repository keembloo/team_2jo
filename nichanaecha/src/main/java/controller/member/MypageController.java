package controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.MemberPointDao;
import model.dao.MypageDao;
import model.dto.AuctionDto;
import model.dto.MemberDto;

/**
 * Servlet implementation class MypageController
 */
@WebServlet("/MypageController")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MypageController() {
        super();
    }

    // 규리 마이페이지 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		// System.out.println("`연결");
		//int loginDto = 3; //임시테스트
		//request.getSession().setAttribute("loginDto", loginDto ); //임시테스트 세션에 로그인회원번호넣어놓기
		//System.out.println("loginDto : "+request.getSession().getAttribute("loginDto"));
		int mno = ((MemberDto)request.getSession().getAttribute("loginDto")).getMno();
		String json = "";
		if ( type.equals("mview")) { // mview() 멤버 회원정보출력
			MemberDto result = MypageDao.getInstence().mview( mno );
			//System.out.println("컨트롤러 result : "+result);
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(result);
			
	    	
		}else if (type.equals("mySubmitcarView")){	// 마이페이지 등록 매물 출력
			//System.out.println("컨트롤러연결");
			ArrayList<AuctionDto> result = MypageDao.getInstence().mySubmitcarView( mno , type);
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(result);
			
	    	
		} else if (type.equals("myAuctionView")) { // 마이페이지 입찰 매물 출력 
			ArrayList<AuctionDto> result = MypageDao.getInstence().myAuctionView( mno , type);
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(result);
			
	    	
		} else if (type.equals("myWishlistView")) { // 마이페이지 찜한 매물 출력 
			ArrayList<AuctionDto> result = MypageDao.getInstence().myWishlistView( mno , type);
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(result);
		
	    	
		}else if (type.equals("findphone")) {
			int cno = Integer.parseInt(request.getParameter("cno"));
			
			MemberDto result = MypageDao.getInstence().findphone(cno);
			//System.out.println("컨트롤러 result"+result);
			
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(result);
			
		} else if (type.equals("findSeller")) {
			int cno = Integer.parseInt(request.getParameter("cno"));
			
			MemberDto result = MypageDao.getInstence().findSeller(cno);
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(result);
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(json);
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

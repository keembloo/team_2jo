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
		System.out.println("컨트롤러 mno 나옴? "+mno);
		if ( type.equals("mview")) { // mview() 멤버 회원정보출력
			MemberDto result = MypageDao.getInstence().mview( mno );
			//System.out.println("컨트롤러 result : "+result);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(result);
			response.setContentType("application/json;charset=UTF-8");
	    	response.getWriter().print(json);
	    	
		}else {	// 마이페이지 매물관련 출력
			//System.out.println("컨트롤러연결");
			ArrayList<AuctionDto> result = MypageDao.getInstence().myPageAuctionView( mno , type);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(result);
			response.setContentType("application/json;charset=UTF-8");
	    	response.getWriter().print(json);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	   // 규리 마이페이지 입금,출금
	   protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      //System.out.println(" 컨트롤러실행");
	      String type = request.getParameter("type");
	      int mno = ((MemberDto)request.getSession().getAttribute("loginDto")).getMno();
	      long gold = Integer.parseInt(request.getParameter("gold"));
	      String mpno = (UUID.randomUUID().toString())+"_"+mno; 
	      //System.out.println("컨트롤ㄹ러 uuid : "+mpno);
	      
	      if (type.equals("사용자 출금") ||type.equals("입찰참여출금")){
	         gold = -gold;
	      }
	      
	      boolean result1 = MemberPointDao.getInstence().PointUpdate( type , mno , gold , mpno);
	      boolean result2 = MemberPointDao.getInstence().setPoint( type , mno , gold , mpno);
	      boolean allrs = false;
	      
	      if (result1 == true && result2 == true) { // 포인트업데이트, 포인트저장 둘다 성공이면
	         allrs =true;
	      }
	      response.setContentType("application/json;charset=UTF-8");
	       response.getWriter().print(allrs);
	   }

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

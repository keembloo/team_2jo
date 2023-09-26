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
import model.dto.MemberDto;
import model.dto.MemberPointDto;

/**
 * Servlet implementation class MemberPointController
 */
@WebServlet("/MemberPointController")
public class MemberPointController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberPointController() {
        super();
    }

    // 규리 회원포인트 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		int mno = ((MemberDto)request.getSession().getAttribute("loginDto")).getMno();
		//System.out.println("실행됨 mno : "+mno);
		// MypageDao 에 있는 mview()함수로 중복 호출
		if (type.equals("mpointView")) { // 현재포인트상태 출력
			MemberDto result = MypageDao.getInstence().mview(mno); // 마이페이지Dao에서 가져옵니다...
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(json);
			
		} else if (type.equals("PointAllView")) { // 전체 포인트입출금내역 출력
			ArrayList<MemberPointDto> result = MemberPointDao.getInstence().PointAllView(mno);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(json);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	// 규리 마이페이지 입금,출금
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(" 컨트롤러실행");
		String type = request.getParameter("type");
		int mno = ((MemberDto)request.getSession().getAttribute("loginDto")).getMno();
		int gold = Integer.parseInt(request.getParameter("gold"));
		String mpno = (UUID.randomUUID().toString())+"_"+mno; 
		//System.out.println("컨트롤ㄹ러 uuid : "+mpno);
		if(type.equals("1")) {
			type="입금";
		} else {
			type="출금";
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

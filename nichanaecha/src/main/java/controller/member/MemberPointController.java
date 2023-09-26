package controller.member;

import java.io.IOException;
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
		if (type.equals("mpointView")) {
			MemberDto result = MypageDao.getInstence().mview(mno);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(json);
		} else if (type.equals("PointAllView")) {
			MemberDto result = MypageDao.getInstence().mview(mno);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(json);
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
		boolean result = MemberPointDao.getInstence().PointUpdate( type , mno , gold , mpno);
			
		response.setContentType("application/json;charset=UTF-8");
    	response.getWriter().print(result);
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

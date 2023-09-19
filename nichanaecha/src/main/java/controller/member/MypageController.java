package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.MemberDao;
import model.dao.MypageDao;
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
		// System.out.println("컨트롤러연결");
		//int loginDto = 3; //임시테스트
		//request.getSession().setAttribute("loginDto", loginDto ); //임시테스트 세션에 로그인회원번호넣어놓기
		//System.out.println("loginDto : "+loginDto);
		MemberDto dto = (MemberDto) request.getSession().getAttribute("loginDto");
		int mno = dto.getMno();
		//System.out.println("로그인 세션 상태 : "+dto);
		MemberDto result = MypageDao.getInstence().mview( mno );
		
		System.out.println("컨트롤러 result : "+result);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(result);
		response.setContentType("application/json;charset=UTF-8");
    	response.getWriter().print(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// 마이페이지 입금,출금
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type"));
		int mno = Integer.parseInt(request.getParameter("mno"));
		int gold = Integer.parseInt(request.getParameter("gold"));
			
		boolean result = MypageDao.getInstence().PointUpdate( type , mno , gold);
			
		response.setContentType("application/json;charset=UTF-8");
    	response.getWriter().print(result);
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
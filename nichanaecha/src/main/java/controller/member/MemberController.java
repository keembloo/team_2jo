package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.MemberDao;
import model.dto.MemberDto;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public MemberController() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 규리 마이페이지 출력
		// System.out.println("컨트롤러연결");
		int loginDto = 2; //임시테스트
		request.getSession().setAttribute("loginDto", loginDto ); //임시테스트 세션에 로그인회원번호넣어놓기
		//System.out.println("loginDto : "+loginDto);
		int dto = (int) request.getSession().getAttribute("loginDto");
		//System.out.println("로그인 세션 상태 : "+dto);
		MemberDto result = MemberDao.getInstence().mview( dto );
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(result);
		response.setContentType("application/json;charset=UTF-8");
    	response.getWriter().print(json);
	}

// 회원가입[9월15일 고연진]
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest multi= new MultipartRequest(request, //요청방식 
				request.getServletContext().getRealPath("/member/img"),//저장경로
				1024*1024*1024,//업로드허용용량
				"UTF-8",//인코딩타입
				new DefaultFileRenamePolicy()//제목 자동 변경
				);
		String signupId= multi.getParameter(".signupId");
		String signupPw= multi.getParameter(".signupPw");
		String signupPhone= multi.getParameter(".signupPhone");
		
		
		
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

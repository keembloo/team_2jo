package controller.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.MemberDao;
import model.dto.MemberDto;
import model.dto.MypageDto;

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
		//int loginDto = 3; //임시테스트
		//request.getSession().setAttribute("loginDto", loginDto ); //임시테스트 세션에 로그인회원번호넣어놓기
		//System.out.println("loginDto : "+loginDto);
		MemberDto dto = (MemberDto) request.getSession().getAttribute("loginDto");
		int mno = dto.getMno();
		//System.out.println("로그인 세션 상태 : "+dto);
		ArrayList<MypageDto> result = MemberDao.getInstence().mview( mno );
		
		//System.out.println("컨트롤러 result : "+result);
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
		String mid= multi.getParameter("mid"); System.out.println("아이디: "+mid);
		String mpw= multi.getParameter("mpw"); System.out.println("비밀번호: "+mpw);
		String mphone= multi.getParameter("mphone"); System.out.println("폰번호: "+mphone);
		String mname= multi.getParameter("mname"); System.out.println("이름: "+mname);
		String mads= multi.getParameter("mads"); System.out.println("주소: "+mads);
		
		MemberDto dto = new MemberDto(mid, mpw, mphone, mname, mads); System.out.println("회원가입정보: "+dto);
		boolean result= MemberDao.getInstence().signup(dto);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
		
		
	}

	// 마이페이지 입금,출금
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type"));
		int mno = Integer.parseInt(request.getParameter("mno"));
		int gold = Integer.parseInt(request.getParameter("gold"));
			
		boolean result = MemberDao.getInstence().PointUpdate( type , mno , gold);
			
		response.setContentType("application/json;charset=UTF-8");
    	response.getWriter().print(result);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

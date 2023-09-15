package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDao;
import model.dto.MemberDto;


@WebServlet("/MemberFindController")
public class MemberFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MemberFindController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	// 로그인 ( 세션저장 )
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 매개변수 요청
		String mid = request.getParameter("mid");
		String mpwd = request.getParameter("mpwd");
		//2. (객체화/유효성검사)
		//3. DAO에게 전달후 결과 받기
		boolean result = MemberDao.getInstence().login(mid, mpwd);
		// - 만약에 로그인 성공하면 세션에 로그인한 정보를 담기
		if( result == true ) {
			//1. 세션에 저장할 데이터를 요청
			MemberDto loginDto = MemberDao.getInstence().info(mid);
			//2. 세션에 [로그인 성공한 회원의 정보(loginDto) 저장한다.
			request.getSession().setAttribute("loginDto", loginDto);
			// [테스트] 세션 상태 확인
				MemberDto dto = (MemberDto)request.getSession().getAttribute("loginDto");
				System.out.println("세션 상태 : "+dto);
		}
		// 4. 결과를 응답한다.
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(result); 
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

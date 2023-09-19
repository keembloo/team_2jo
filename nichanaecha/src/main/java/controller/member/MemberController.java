package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

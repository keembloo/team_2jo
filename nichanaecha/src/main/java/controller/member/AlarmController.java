package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AlarmDao;
import model.dto.MemberDto;

@WebServlet("/AlarmController")
public class AlarmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public AlarmController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
//알람등록[10월7일 고연진]	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String type= request.getParameter("type");
	System.out.println("알람등록컨트롤러들어옴 > type: "+type);
	// 입찰등록 성공 시 입찰자에게 알림
	if(type.equals("입찰")||type.equals("환급")) {
		int mno=((MemberDto)(request.getSession().getAttribute("loginDto"))).getMno();
		long gold=Long.parseLong(request.getParameter("gold"));
		System.out.println("출금된 사람 > "+mno);
		System.out.println("출금금액> "+gold);
		boolean result = AlarmDao.getInstence().buyAlarm(mno,gold,type);
		System.out.println("Dao에서 전달 받은 값 > "+result);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
		
		
	}
	
	
	
	
	
	}

	
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

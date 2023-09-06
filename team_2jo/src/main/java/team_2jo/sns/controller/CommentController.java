package team_2jo.sns.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team_2jo.sns.model.dao.CommentDao;
import team_2jo.sns.model.dto.SnsDto;
@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	// 답글등록 규리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ccontent = request.getParameter("ccontent");
		String cpwd = request.getParameter("cpwd");
		int bno = Integer.parseInt(request.getParameter("bno"));
		//System.out.println("ccontent : "+ccontent);
		//System.out.println("cpwd : "+cpwd);
		//System.out.println("bno : "+bno);
		SnsDto comentdto = new SnsDto(bno, ccontent, cpwd);
		
		boolean result = CommentDao.getInstence().oncoment(comentdto);
		
		response.setContentType("application/json;charset=UTF-8");
    	response.getWriter().print(result);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//1. 요청
		String cpwd= request.getParameter("cpwd");
		//2. dao처리
		boolean result=CommentDao.getInstence().cdelte(cpwd);
		//3. 응답
	}

}

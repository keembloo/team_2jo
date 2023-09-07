package team_2jo.sns.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team_2jo.sns.model.dao.SnsDao;

@WebServlet("/SerchController")
public class SerchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SerchController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		
		String type = request.getParameter("type");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String uip = request.getRemoteAddr();
		System.out.println("bno : "+bno);
		System.out.println("uip : "+uip);
		
		
		if(type.equals("likeup")) {
			response.getWriter().print(SnsDao.getInstence().likeup(uip, bno));
		}else if(type.equals("dislikeup")) {
			response.getWriter().print(SnsDao.getInstence().dislikeup(uip, bno));
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}

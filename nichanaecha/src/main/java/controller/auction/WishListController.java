package controller.auction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AuctionDao;
import model.dto.MemberDto;

/**
 * Servlet implementation class WishListController
 */
@WebServlet("/WishListController")
public class WishListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WishListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
//스크랩 게시물 추가 [9월21일 고연진]	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno= ((MemberDto)(request.getSession().getAttribute("loginDto"))).getMno();System.out.println("스크랩누른사람: "+mno);
		int ano = Integer.parseInt(request.getParameter("ano")); 
		System.out.println("게시물번호전달됨? " +ano);
		String mid=((MemberDto)(request.getSession().getAttribute("loginDto"))).getMid(); System.out.println("회원아이디: "+mid);
		boolean result= AuctionDao.getInstence().clipping(mno,ano);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
	
	
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

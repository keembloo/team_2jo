package controller.auction;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.BattingDao;
import model.dto.BattingDto;
import model.dto.MemberDto;

/**
 * Servlet implementation class BattingController
 */
@WebServlet("/BattingController")
public class BattingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BattingController() {
        super();
    }

  //경매내용 출력   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("배팅컨트롤러출력진입> ");
    String type = request.getParameter("type");
    System.out.println("타입확인> "+type);
    ObjectMapper mapper = new ObjectMapper();
    
    if(type.equals("topByBatting")) {
    	System.out.println("타입에 맞춰서 if문 들어옴> ");
       int ano =Integer.parseInt(request.getParameter("ano"));
       System.out.println("배팅컨트롤러(ano)> "+ano);
       List<BattingDto> result= BattingDao.getInstence().batView(ano); 
       System.out.println("mapper 전>"+result);
       String jsonArray=mapper.writeValueAsString(result); 
       System.out.println("mapper> "+jsonArray);
       response.setContentType("application/json;charset=UTF-8");
       response.getWriter().print(jsonArray);
    }
 
       
       
       
    
    

    }//f()
	
 // 입찰 등록 [9월26일 고연진]   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("입찰등록컨트롤러들어옴>");
    	long bprice=Long.parseLong(request.getParameter("bprice")); // 금액
    	int ano=Integer.parseInt(request.getParameter("ano"));
    	int mno=((MemberDto)(request.getSession().getAttribute("loginDto"))).getMno();
    	BattingDto dto = new BattingDto(mno, ano, bprice);
    	boolean result = BattingDao.getInstence().batting(dto);
    	response.setContentType("application/json;charset=UTF-8");
    	response.getWriter().print(result);
	
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

package controller.auction;

import java.io.IOException;
import java.util.ArrayList;
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
    	//System.out.println("타입에 맞춰서 if문 들어옴> ");
    	int count=Integer.parseInt(request.getParameter("count"));
       int ano =Integer.parseInt(request.getParameter("ano"));
       //System.out.println("배팅컨트롤러(ano)> "+ano);
       List<BattingDto> result= BattingDao.getInstence().batView(ano,count); 
       //System.out.println("mapper 전>"+result);
       String jsonArray=mapper.writeValueAsString(result); 
       //System.out.println("mapper> "+jsonArray);
       response.setContentType("application/json;charset=UTF-8");
       response.getWriter().print(jsonArray);
    }//if
    else if(type.equals("price")) {
    	   //System.out.println("type확인> "+type);
		   int ano = Integer.parseInt(request.getParameter("ano"));
		   int count=Integer.parseInt(request.getParameter("count"));
		   //System.out.println("count> "+count);
		   ArrayList<BattingDto> result= BattingDao.getInstence().batView(ano,count);
		   String jsonArray=mapper.writeValueAsString(result); 
	       //System.out.println("mapper> "+jsonArray);
	       response.setContentType("application/json;charset=UTF-8");
	       response.getWriter().print(jsonArray);
	   }//if
    else if(type.equals("all")) {
    	System.out.println("all 들어옴? ");
    	 int ano = Integer.parseInt(request.getParameter("ano"));
    	 System.out.println("ano> "+ano);
    	 ArrayList<BattingDto> result= BattingDao.getInstence().buyView(ano);
		 String jsonArray=mapper.writeValueAsString(result); 
		 System.out.println("전체출력> "+jsonArray);
	     response.setContentType("application/json;charset=UTF-8");
	     response.getWriter().print(jsonArray);
    	
    }
    else if(type.equals("getBuyTop")) {
    	System.out.println("배팅컨트롤러 입찰참여자 get 들어옴>");
    	int ano = Integer.parseInt(request.getParameter("ano"));
    	BattingDto dto= BattingDao.getInstence().getBuyTop(ano);
    	String json = mapper.writeValueAsString(dto);
    	System.out.println("json전환> "+dto);
    	response.setContentType("application/json;charset=UTF-8");
 	    response.getWriter().print(json);
    	
    }
 
       
       
       
    
    

    }//f()
	
 // 입찰 등록 [9월26일 고연진]   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//System.out.println("입찰등록컨트롤러들어옴>");
    	long bprice=Long.parseLong(request.getParameter("bprice")); // 금액
    	//System.out.println("controller받은금액> "+bprice);
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

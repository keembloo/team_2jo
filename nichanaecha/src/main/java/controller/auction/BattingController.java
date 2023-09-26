package controller.auction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
/*
    String type = request.getParameter("type");
    ObjectMapper mapper = new ObjectMapper();
    
    if(type.equals("topByBatting")) {
       int ano =Integer.parseInt(request.getParameter("ano"));
       System.out.println("배팅컨트롤러(ano)> "+ano);
       List<BattingDto> result= BattingDao.getInstence().topByBatting(ano); 
       System.out.println("mapper 전>"+result);
       String jsonArray=mapper.writeValueAsString(result); 
       System.out.println("mapper> "+jsonArray);
       response.setContentType("application/json;charset=UTF-8");
       response.getWriter().print(jsonArray);
    }
    else if(type.equals("mcashVal")) {
       int ano =Integer.parseInt(request.getParameter("ano"));
       int bprice =Integer.parseInt(request.getParameter("bprice"));
       BattingDto result= BattingDao.getInstence().priceVal(ano,bprice);
       boolean a;
       if(result.getbPrice()<bprice) {
          a=true;
          response.setContentType("application/json;charset=UTF-8");
          response.getWriter().print(a);
       
          
       };
       
       
       
       
    }
    
*/
    }//f()
	
 // 입찰 등록 [9월26일 고연진]   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

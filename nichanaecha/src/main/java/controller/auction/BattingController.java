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
    

    }//f()
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

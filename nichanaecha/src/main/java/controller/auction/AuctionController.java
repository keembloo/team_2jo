package controller.auction;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.AuctionDao;
import model.dto.AuctionDto;


@WebServlet("/AuctionController")
public class AuctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public AuctionController() {
        // TODO Auto-generated constructor stub
    }

	
    
    
    
    //상세페이지조회 [9월19일 고연진]   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ano= Integer.parseInt(request.getParameter("ano"));
		List<AuctionDto> result= AuctionDao.getInstence().auctionPrint(ano);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
	}

	
	//등록페이지 성호
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 첨부파일 업로드 [ cos.jar -> MultipartRequest 클래스  ]
				MultipartRequest multi = new MultipartRequest(
						request,			// 요청방식 
						request.getServletContext().getRealPath("/auction/img"),	//저장경로				// 저장경로 
						1024*1024*1024, // 업로드허용용량[바이트] 1GB 
						"UTF-8",		// 인코딩타입 
						new DefaultFileRenamePolicy() // 만약에 업로드파일명이 서버내 존재하면(중복) 자동으로 파일명뒤에 숫자 붙이기
						);
				// * 업로드가 잘 되었는지 확인하기 위한 업로드 서버경로 확인 
				System.out.println( request.getServletContext().getRealPath("/auction/img") );
				
				//1. (입력받은 매개변수)요청
				String btitle = multi.getParameter("btitle");
				String bprice = multi.getParameter("bprice");
				String bfile = multi.getFilesystemName("bfile");
				int mno = ( (AuctionDto)request.getSession().getAttribute("loginDto") ).getMno();
				int bcno = Integer.parseInt( multi.getParameter("bcno") );
	
				//2. 유효성 검사/객체화
				AuctionDto auctionDto = new AuctionDto(btitle,bprice,bfile,mno,bcno);
				//3. Dao 처리
				boolean result = AuctionDao.getInstence().bcarsubmit(auctionDto);
				//4. (Dao 결과) 응답
				response.setContentType("application/json; charset=UTF-8"); 
				response.getWriter().print(result);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

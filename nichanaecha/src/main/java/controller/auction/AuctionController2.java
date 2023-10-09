package controller.auction;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.AuctionDao;
import model.dto.AuctionDto;
import model.dto.CarAddressDto;
import model.dto.CarDto;
import model.dto.MemberDto;

@WebServlet("/AuctionController")
public class AuctionController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public AuctionController2() {
        // TODO Auto-generated constructor stub
    }

	
    
    
    
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	

	//경매등록페이지 성호
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //1. 저장경로 [ 첨부파일이 저장될 폴더 위치]
	      String uploadPath = request.getServletContext().getRealPath("/auction/img");
	      
	      // 2. 파일아이템저장소 객체 : 업로드할 옵션  [ import org.apache.commons.fileupload.FileItem; ]
	      DiskFileItemFactory itemFactory = new DiskFileItemFactory();
	      itemFactory.setRepository(new File(uploadPath));   //  2.저장위치 [ File타입 ] 
	      itemFactory.setSizeThreshold(1024*1024*1024);      //  3.용량
	      itemFactory.setDefaultCharset("UTF-8");            // 4.한글인코딩
	      
	      // 3. 파일 업로드 객체 [ import org.apache.commons.fileupload.servlet.ServletFileUpload; ] 
	      ServletFileUpload fileUpload = new ServletFileUpload(  itemFactory );
		
	   // 4. 파일 업로드 요청 [ 요청방식 : request ]
		try {	
			// ?????? array 대신에 map 사용하기 된 이유 : 차량 이미지가 여러개라서 하나의 키의 값를 불러오기위해
	    	  Map< Integer, String > imgList = new HashMap<>(); // 업로드된 파일명 들을 저장하기 위한 map컬렉션
	         
	         // form전송시 input/select/textarea 등 태그의 모든 데이터 한번에 요청해서 결과를 List 반환 
	      List< FileItem > fileList = fileUpload.parseRequest(request);
	         
	      // 5. 업로드 실행 
	      int i = 0;
	      for( FileItem item : fileList ) { // 요청한 input 들의 데이터를 반복문으로 하나씩 꺼내기.
	         // 1. 일반 필드 [ isFormField() : 만약에 일반폼필드이면 true / 아니고 첨부파일필드이면 false  ] 
	         if( item.isFormField() ) { System.out.println( item.getString() ); } // .getString() : 해당 요청 input의 value 호출 
	         else { // 2. file 필드
	            // 만약에 파일 필드이면 업로드 진행
	               System.out.println( "업로드할 파일명 : " + item.getName() ); // .getName()
	            // 6.업로드 경로 + 파일명 [ 조합 ] 
	                                          
	               // 파일명에 중복이 있을때 식별 생성                   
	               UUID uuid = UUID.randomUUID();
	                  // UUID 클래스 : 고유성을 보장하는 ID를 만들기 위한 식별자 표준 규약  [ - 하이픈 4개 구역 ]
	               String filename = uuid+"-"+ item.getName().replaceAll("-", "_");
	                                 // 만약에 파일명에 - 하이픈 존재하면 _언더바로 변경 
	                                 // 왜?? 파일명과 UUID 간의 식별하기 위해 구분 -하이픈 사용하기 때문에.
	                                 // 추후에 파일명만 추출시 사용자가 파일명에 - 이 있으면 파일명 추출시 쪼개기가 힘듬.
	               // UUID[ - - -  ] - 파일명 : 추후에 파일명만 추출시 -하이픈 기준으로 쪼개기 
	               
	            File fileUploadPath = new File( uploadPath +"/"+ filename ) ;
	            
	               System.out.println( "업로드경로와 파일명이 조합된 경로 : " + fileUploadPath );
	            item.write( fileUploadPath ); // .write("저장할경로[파일명포함]") 파일 업로드할 경로를 file타입으로 제공 
	            // 7. 업로드 된 파일명을 Map에 저장 [ -DB에 저장할려고  ]
	            i++;   // i는 임의의 값 
	            imgList.put(i, filename); // 저장시 에는 이미지번호가 필요 없음
	            // MAP 컬렉션은 키 와 값으로 구성된 엔트리 [ * 키는 중복 불가능 ]
	         }
	      }
		
		String atitle = fileList.get(0).getString();			//경매 제목
			System.out.println(atitle);
		String acontent = fileList.get(1).getString();			//경매 내용
			System.out.println(acontent);
		String aenddate  = fileList.get(2).getString();			//차량종류
			System.out.println(aenddate);
		long aprice  = Integer.parseInt(fileList.get(3).getString());//가격
			System.out.println(aprice);
			
		// 위에서 만든 변수들 4개를 하나의 DTO로 만들기
			
		AuctionDto auctionDto = new AuctionDto(0, atitle, acontent, acontent, aenddate, aprice, 0, 0)
		System.out.println(AuctionDto);	
		
		//3. Dao 처리
	    boolean result = AuctionDao.getInstence().Auctionregistration(AuctionDto);
	    //4. (Dao 결과) 응답
	    response.setContentType("application/json; charset=UTF-8"); 
	    response.getWriter().print(result);
		
		}catch (Exception e) {}	 
	
	}
 
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

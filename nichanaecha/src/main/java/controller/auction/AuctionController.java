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
public class AuctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public AuctionController() {
    }

	
    
    
    
    //상세페이지조회 [9월19일 고연진]   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type= request.getParameter("type");
		System.out.println("타입확인 > "+type);
		ObjectMapper mapper=new ObjectMapper(); 
		if(type.equals("거래종료유효성")) {
			int ano = Integer.parseInt(request.getParameter("ano"));
			int astate = AuctionDao.getInstence().astate(ano);
			//System.out.println("컨트롤러에서 출력되는 astate > "+astate);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(astate);
		}
		else if(type.equals("상세페이지조회")) {
			int cno = Integer.parseInt(request.getParameter("cno"));
			//System.out.println("cno : "+cno);
			AuctionDto result= AuctionDao.getInstence().auctionPrint(cno);
			String jsonObject= mapper.writeValueAsString(result);
			//System.out.println("json 전환> "+jsonObject);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(jsonObject);
			
		}
		else if(type.equals("본인글유효성")) {
			int ano = Integer.parseInt(request.getParameter("ano"));
			
		}/*
		else if(type.equals("경매글작성자")) {
			System.out.println("[컨트롤러들어옴] 경매글작성자 타입 확인> "+type);
			int mno= Integer.parseInt(request.getParameter("mno"));
			System.out.println("경매글 작성자> "+mno);
			AuctionDto result = AuctionDao.getInstence().findAuctionMid(mno);
			String json=mapper.writeValueAsString(result);
			System.out.println("dao에서 전달된 dto> "+result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(json);
			
		}
		*/
		
	}//f()

	

	//차량등록페이지 성호
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
	      // ------------------------------------- 업로드 끝 --> DB처리 --------------------- //

			// FileItem 으로 가져온 데이터들을 각 필드에 맞춰서 제품Dto 에 저장하기 
			
			// 차 등록한 회원번호 [ 서블릿 세션 ] 
	      		
	      		String ccompany = fileList.get(0).getString();		//제조사
	      			System.out.println(ccompany);
	      		String cnum = fileList.get(1).getString();			//차량번호
	      			System.out.println(cnum);
	      		String csize  = fileList.get(2).getString();			//차량종류
	      			System.out.println(csize);
	      		int cc  = Integer.parseInt(fileList.get(3).getString());				//베기량
	      			System.out.println(cc);
	      		String coil  = fileList.get(4).getString();			//연료
	      			System.out.println(coil);
	      		String cname  = fileList.get(5).getString();			//차량명
	      			System.out.println(cname);	
	      		String cdate  = fileList.get(6).getString();			//제조년월
	      			System.out.println(cdate);
	      		int ckm  = Integer.parseInt(fileList.get(7).getString());			//KM
	      		// 로그인 회원번호 없음 : 어디서 구해올까.
		      		Object object = 
		      				request.getSession().getAttribute("loginDto");
		      		MemberDto loginDto = (MemberDto)object ;
	      		int mno = loginDto.getMno();
	      		
	      			System.out.println(ckm);
	      		String calat  = fileList.get(8).getString();;			//위도
	      			System.out.println(calat);
	      		String calng  = fileList.get(9).getString();;			//경도
	      			System.out.println(calng);
	      			
	      		String cads  = fileList.get(10).getString();;			//주소
	      			System.out.println(cads);
	      			
	      		String cacode  = fileList.get(11).getString();;			//법정동코드
	      			System.out.println(cacode);
	      			
	      		String cacodename  = fileList.get(12).getString();;			//법정동이름
	      			System.out.println(cacodename);
	      		
	      		CarAddressDto carAddressDto = new CarAddressDto();
	      			carAddressDto.setCalat(calat);
	      			carAddressDto.setCalng(calng);
	      			carAddressDto.setCads(cads);
	      			carAddressDto.setCacode(cacode);
	      			carAddressDto.setCacodename(cacodename);
	      		
	      		System.out.println( imgList ); // 이미지 목록
	      		
	      		
	      		//위에서 만든 변수들 12개를 하나의 DTO로 만들기
	      		CarDto carDto = new CarDto(0, ccompany, cnum, csize, cc, coil, cname,
	      				cdate, ckm, mno, carAddressDto, imgList);
	      		System.out.println(carDto);
	      		
	      		//3. Dao 처리
			    int result = AuctionDao.getInstence().bcarsubmit(carDto);
			    //4. (Dao 결과) 응답
			    request.getSession().setAttribute("cno", result);
			    response.setContentType("application/json; charset=UTF-8"); 
			    response.getWriter().print(result);
	      	
	        }catch (Exception e) { }	
		
	}
 
	
	
//경매상태변경	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ano= Integer.parseInt(request.getParameter("ano"));
		System.out.println("경매상태변경 ano > "+ano);
		boolean result= AuctionDao.getInstence().astateChage(ano);
		System.out.println("경매상태변경 성공 여부: "+result);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
	
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

package controller.auction;

import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.AuctionDao;
import model.dto.AuctionDto;
import model.dto.CarDto;

@WebServlet("/AuctionController")
public class AuctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public AuctionController() {
        // TODO Auto-generated constructor stub
    }

	
    
    
    
    //상세페이지조회 [9월19일 고연진]   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ano = Integer.parseInt(request.getParameter("ano"));
		System.out.println("controller 들어옴 > ano:  "+ano);
		
		AuctionDto result= AuctionDao.getInstence().auctionPrint(ano);
		System.out.println("경매Dto: "+result);
		System.out.println("경매Dto 안에 CarDto: "+result.getCar());
		System.out.println("CarDto 안에 imglist: "+result.getCar().getimglist());
		
		ObjectMapper mapper=new ObjectMapper(); 
		String jsonObject= mapper.writeValueAsString(result);
		System.out.println("jackson사용: "+jsonObject);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(jsonObject);
	}

	

	//등록페이지 성호
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
	    	  Map<  String > imgList = new HashMap<>(); // 업로드된 파일명 들을 저장하기 위한 map컬렉션
	         
	         // form전송시 input/select/textarea 등 태그의 모든 데이터 한번에 요청해서 결과를 List 반환 
	      List< FileItem > fileList = fileUpload.parseRequest( request );
	         
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
	            imgList.add( filename  ); // 저장시 에는 이미지번호가 필요 없음
	            // MAP 컬렉션은 키 와 값으로 구성된 엔트리 [ * 키는 중복 불가능 ]
	         }
	      }
	      // ------------------------------------- 업로드 끝 --> DB처리 --------------------- //

		// ------------------------------------- 업로드 끝 --> DB처리 --------------------- //
		
		// FileItem 으로 가져온 데이터들을 각 필드에 맞춰서 제품Dto 에 저장하기 
		
		// 제품 등록한 회원번호 [ 서블릿 세션 ] 
		Object object = request.getSession().getAttribute("loginDto");
		CarDto carDto = (CarDto)object;
		int cmo = carDto.getCno();
		  CarDto caDto = new CarDto(
		            0 ,                                       //cno 호출(x) : 차 등록시에는 매물 번호 는 자동부여 되므로 가지고 있을 필요가 없음
		            fileList.get(0).getString(),                  //제조사(0)
		            fileList.get(1).getString(),                  //차량번호(1)
		            fileList.get(2).getString(),                  //차량종류(2)
		            Integer.parseInt(fileList.get(3).getString()),      //배기량(3)
		            fileList.get(4).getString(),                  //연료(4)
		            fileList.get(5).getString(),                  //차량명(5)
		            fileList.get(6).getString(),                  //제조년월(6)
		            Integer.parseInt(fileList.get(7).getString()),      //주행거리(7)
		            null,                                       // 주소
		            fileList.get(8).getString(),                  //위도(8)
		            fileList.get(9).getString(),                        //경도(9)
		            imgList );                                 // 업로드 된 이미지들
		      
		      System.out.println( carDto );


		
	
		
		      //3. Dao 처리
		      boolean result = AuctionDao.getInstence().carDto(carDto);
		      //4. (Dao 결과) 응답
		      response.setContentType("application/json; charset=UTF-8"); 
		      response.getWriter().print(result);
		      
		      }catch (Exception e) {}	
		/*
				//1. (입력받은 매개변수)요청
				String ccompany = multi.getParameter("ccompany");			//제조사(1)
					System.out.println(ccompany);
				String cnum = multi.getParameter("cnum");					//차량번호(2)
					System.out.println(cnum);
				String csize = multi.getParameter("csize");					//차량종류(3)
					System.out.println(csize);
				int cc = Integer.parseInt( multi.getParameter("cc") );		//배기량(4)
					System.out.println(cc);
				String coil = multi.getParameter("coil");					//연료(5)
					System.out.println(coil);
				String cname = multi.getParameter("cname");					//차량명(6)
					System.out.println(cname);
				String cdate = multi.getParameter("cdate");					//제조년월(7)
					System.out.println(cdate);
				int ckm = Integer.parseInt( multi.getParameter("ckm") );	//주행거리(8)
					System.out.println(ckm);
				//차량 등록 주소(9)
				String clat = multi.getParameter("clat");
					System.out.println(clat);
				String clng = multi.getParameter("clng");
					System.out.println(clng);
				
	*/		
				
				
	
				//2. 유효성 검사/객체화
				//CarDto carDto = new CarDto();
				
				//3. Dao 처리
				//boolean result = AuctionDao.getInstence().bcarsubmit(CarDto);
				//4. (Dao 결과) 응답
				//response.setContentType("application/json; charset=UTF-8"); 
				//response.getWriter().print(result);
	}
 
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

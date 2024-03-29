package team_2jo.sns.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import team_2jo.sns.model.dao.SnsDao;
import team_2jo.sns.model.dto.SnsDto;
import team_2jo.sns.service.FileService;



@WebServlet("/SnsController")
public class SnsController extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SnsController() {    }

    // 게시물출력 규리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		
		ArrayList<SnsDto> clist = SnsDao.getInstence().onreply();
		ArrayList<SnsDto> list = SnsDao.getInstence().onView(key);
		int feedCnt = SnsDao.getInstence().onCount(key);
		
		SnsDto snsDto = new SnsDto();
		snsDto.setList(list);
		snsDto.setFeedCnt(feedCnt);
		snsDto.setClist(clist);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonArray = objectMapper.writeValueAsString(snsDto);
		
	  	response.setContentType("application/json;charset=UTF-8");
    	response.getWriter().print(jsonArray);
	}

	
	//게시글 등록[고연진]
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 첨부파일 업로드[COS.jar]
		MultipartRequest multi= new MultipartRequest(
											request, //요청방식 
											request.getServletContext().getRealPath("/sns_project/img"),
											1024*1024*1024,//업로드허용용량
											"UTF-8",//인코딩타입
											new DefaultFileRenamePolicy()//제목 자동 변경
											);
		 //업로드 경로 확인
		System.out.println(request.getServletContext().getRealPath("/board/upload"));
		
		//1.(입력받은 매개변수)요청
		String bfile=multi.getFilesystemName("bfile");	System.out.println("서블릿에서요청받은거: "+bfile);
		String bcontent=multi.getParameter("bcontent");
		String bpwd = multi.getParameter("bpwd");
		//2. 객체화
		SnsDto dto = new SnsDto(bfile, bcontent, bpwd);
		System.out.println("객체화됨? : "+dto);
		//3.dao
		boolean result= SnsDao.getInstence().bwrite(dto);
		//4.응답
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
		
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		String filePath = request.getSession().getServletContext().getRealPath("/sns_project/img");
		String type = request.getParameter("type");
		
		if(type==null) { // 게시글 수정
			MultipartRequest multi = new MultipartRequest(
					request, 				//1. 요청 방식
					filePath, 				// 첨부파일을 저장할 폴더 경로
					1024*1024*100,			// 100메가 바이트 최대 허용 용량 [ 바이트 단위 ]
					"UTF-8",				// 인코딩 타입
					new DefaultFileRenamePolicy()	// 첨부 파일 이름 중복 시 이름 끝에 숫자 붙여줌
					);
			System.out.println(filePath);
			
			SnsDto dto = new SnsDto();
			String bfile = multi.getFilesystemName("bfile");
			int bno = Integer.parseInt(multi.getParameter("bno"));
			
			if(bfile==null) {
				bfile = SnsDao.getInstence().uprint(bno).getBfile();
			}
			
			
			dto.setBno(bno);
			dto.setBfile(bfile);
			dto.setBcontent(multi.getParameter("bcontent"));
			dto.setBpwd(multi.getParameter("bpwd"));
			
			response.getWriter().print(SnsDao.getInstence().update(dto));
		} //게시글 수정 종료
		
		
		else if(type.equals("get")) { // 비밀번호 일치여부 확인
			System.out.println("get 실행");
			String bpwd = request.getParameter("bpwd");
			int bno = Integer.parseInt(request.getParameter("bno"));
			
			
			response.getWriter().print(SnsDao.getInstence().pwdCheck(bno, bpwd));
			
		}else if(type.equals("print")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			
			
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(SnsDao.getInstence().uprint(bno));
			response.getWriter().print(json);
		}
	}
	


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청
		int bno = Integer.parseInt(request.getParameter("bno"));
		String bpwd = request.getParameter("bpwd");
		String filename = request.getParameter("filename");
		
		System.out.println(bno);
		//2. DAO
		boolean result = SnsDao.getInstence().ondelete(bno, bpwd);
			if(result) {
				filename = request.getServletContext().getRealPath("파일경로임 물어보고 하셈")+"/"+filename;
				FileService.fileDelete(filename);
			}
		//3. 응답
		response.setContentType("application/json; charset=UTF-8"); 
		response.getWriter().print(result);
	}

}

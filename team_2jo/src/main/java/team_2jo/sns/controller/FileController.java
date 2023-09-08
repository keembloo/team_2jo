package team_2jo.sns.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileController
 */
@WebServlet("/FileController")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileController() {
        super();
    }

    
 //파일 다운로드 받기 [고연진]
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//------------------------1. 다운로드 할 파일 경로 찾기------------------------//	

		String filename=request.getParameter("filename"); System.out.println(" 서블릿이동 성공->파일명: "+filename);
		String uploadpath = request.getServletContext().getRealPath("/sns_project/img");
		String filepath = uploadpath+"/"+filename;
		System.out.println("이름뭐임"+filepath);
	

//--------------------------2. 응답 (파일 다운로드 시 옵션 설정)-------------------------------------//
		response.setHeader("Content-Disposition", //다운로드형식 HTTP 옵션 
				"attachmemt;filename="+URLEncoder.encode(filename,"UTF-8"));// 다운로드파일명 옵션[생략시 서블릿 이름]
								//URLEncoder 클래스.encode(다운로드파일명,"UTF-8") -> 한글지원
		
//--------------------------3. 파일 내보내기-----------------------------------------------//
		File file= new File(filepath);//File로 객체화 함으로써 여러가지 함수 사용 가능
		//System.out.println("File로 객체화하기 : "+file);
		//FileInputStream: 파일입력 스트림 객체=> 파일 읽어오기
			//1.입력객체 
//????????????????????????여기가 안됨 ???????????????????????????????????
		FileInputStream fin = new FileInputStream(file);
		System.out.println("이넉ㄴ되니......");
		System.out.println("파일 읽어오기 됨 ? : "+fin);
			//2. 바이트단위로 읽어온 내용을 저장할 배열 선언 (효율적인 관리를 위해 length() 사용)
		byte[] bytes = new byte[(int)file.length()];
			//3. 읽어온 바이트를 배열에 저장 <= FileInputStream이 가진 메소드 read())
		fin.read(bytes);

		//BufferedOutputStream : 파일출력 스트림 객체
			//1. 출력객체			
						//response.getOutputStream() : 자바에서 지원해주는 형식으로 보냄
		BufferedOutputStream oin = new BufferedOutputStream(response.getOutputStream());
			//2. 파일 내보내기
		fin.close();oin.flush();oin.close();
		
		// 스트림 닫기 <= 대용량 전송 시 안전하게 닫아 줄 수 있도록
		fin.close(); oin.flush(); oin.close();
	
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

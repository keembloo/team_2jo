package team_2jo.sns.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/SnsController")
public class SnsController extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SnsController() {    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	//게시글 등록[고연진]
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 첨부파일 업로드[COS.jar]
		MultipartRequest multi= new MultipartRequest(
											request, //요청방식 
											request.getServletContext().getRealPath("/board/upload"),
											1024*1024*1024,//업로드허용용량
											"UTF-8",//인코딩타입
											new DefaultFileRenamePolicy()//제목 자동 변경
											);
		 //업로드 경로 확인
		System.out.println(request.getServletContext().getRealPath("/board/upload"));
	
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

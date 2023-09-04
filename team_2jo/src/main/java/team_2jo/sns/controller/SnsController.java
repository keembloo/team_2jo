package team_2jo.sns.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.BoardDao;
import model.dto.BoardDto;
import model.dto.MemberDto;
import team_2jo.sns.model.dao.SnsDao;
import team_2jo.sns.model.dto.SnsDto;


@WebServlet("/SnsController")
public class SnsController extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SnsController() {    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = request.getSession().getServletContext().getRealPath("/sns_project/img");
		
		System.out.println(filePath);
		MultipartRequest multi = new MultipartRequest(
				request, 				//1. 요청 방식
				filePath, 				// 첨부파일을 저장할 폴더 경로
				1024*1024*100,			// 100메가 바이트 최대 허용 용량 [ 바이트 단위 ]
				"UTF-8",				// 인코딩 타입
				new DefaultFileRenamePolicy()	// 첨부 파일 이름 중복 시 이름 끝에 숫자 붙여줌
		);
		
		SnsDto dto = new SnsDto();
		
		dto.setBno(Integer.parseInt(multi.getParameter("bno")));
		dto.setBfile(multi.getFilesystemName("bfile"));
		dto.setBcontent(multi.getParameter("bcontent"));
		dto.setBpwd(multi.getParameter("bpwd"));
		
		String bfile = multi.getFilesystemName("bfile");
		
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(SnsDao.getInstence().update(dto));
	}
	


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

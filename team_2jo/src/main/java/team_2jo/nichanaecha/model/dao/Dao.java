package team_2jo.nichanaecha.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
	// 1. 필드 
	public Connection conn; 	 // DB연동 객체
	public PreparedStatement ps; // 연동된 DB SQL조작( SQL매개변수대입, SQL실행/취소 )하는 객체
	public ResultSet rs; 		 // SQL 조작 결과(검색결과)를 가져오는 객체
	// 2. 생성자 : 객체 생성시 초기화 담당
		// 기본생성자에 DB연동 코드 작성 *dao객체 생성시 바로 DB연동
	public Dao() { // 자식객체가 생성되면 부모객체도 같이 생성 !! [ 자식객체 생성되면 부모클래스의 생성자 호출된다 ] 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nichanaecha", "root" , "1234");
			
			System.out.println("안내 DB연동성공");
		} catch (Exception e) {System.out.println("경고 DB연동실패"+e);}
	}

}

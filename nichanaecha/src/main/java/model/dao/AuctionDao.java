package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dto.AuctionDto;
import model.dto.CarAddressDto;
import model.dto.CarDto;

public class AuctionDao extends Dao {
	private static AuctionDao auctionDao = new AuctionDao();
	public static AuctionDao getInstence() {return auctionDao;}
	private AuctionDao() {}
	
	// 자동차 정보 dto 반환 함수
	public CarDto carDto(int cno) {
		try {
			String sql = "select * from car where cno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			CarDto carDto = new CarDto(
					rs.getInt("cno"),
					rs.getString("ccompany"),
					rs.getString("cnum"),
					rs.getString("csize"),
					rs.getInt("cc"),
					rs.getString("coil"),
					rs.getString("cname"),
					rs.getString("cdate"),
					rs.getInt("ckm"),
					rs.getInt("mno")
			);
			
			return carDto;
			
		} catch (Exception e) {
			System.out.println("carDto 반환 sql 예외 : "+e);
			return null;
		}
		
	}
	
	// 경매 정보 dto 반환 함수
	public AuctionDto auctionDto(int cno) {
	
		
		try {
			String sql = "select * from auctionInfo where cno = ? ";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			AuctionDto auctionDto = new AuctionDto(
					rs.getInt("ano"),
					rs.getString("atitle"),
					rs.getString("acontent"),
					rs.getString("astartdate"),
					rs.getString("aenddate"),
					rs.getLong("aprice"),
					rs.getInt("astate"),
					rs.getInt("cno")
					
			);
			
			return auctionDto;
			
			
		} catch (Exception e) {
			System.out.println("auctionDto 반환 sql문 예외 : "+e);
			return null;
		}
	}
	
	// 자동차 이미지 Map 반환 함수
	public Map<Integer,String> imglist(int cno){
		try {
			String sql = "select * from carimg where cno = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs = ps.executeQuery();
			
			Map<Integer,String> imglist = new HashMap<>();
			
			while(rs.next()) {
				imglist.put(rs.getInt("cino"), rs.getString("ciimg"));
			}
			
			return imglist;
			
		} catch (Exception e) {
			System.out.println("자동차 img Map 반환 함수 sql 예외");
			return null;
		}
		
		
	}
	
	// 자동차 주소 테이블 반환 공통 함수
	public CarAddressDto carAddressDto(int cno) {
		try {
			String sql = "select * from caraddress where cno = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			CarAddressDto dto = new CarAddressDto(
					rs.getString("cads"),
					rs.getString("calat"),
					rs.getString("calng"),
					rs.getString("cacode"),
					rs.getString("cacodename"),
					rs.getInt("cno")
			);
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("자동차 주소 테이블 반환 공통 함수 예외 : "+e);
			return null;
		}
		
		
	}
	
	
	// 1.차 등록 성호
	  public boolean bcarsubmit(CarDto dto) {
	      try {
	         // ---------------------------------------------- 차량 등록 ---------------------------------------- // 
	         String sql ="insert into car( ccompany , cnum , csize , cc , coil , cname , cdate ,  ckm , mno )"
	               + "values(?,?,?,?,?,?,?,?,?)";
	         ps =conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS ); // 실행후 식별키[PK] 를 반환할 예정
	         ps.setString( 1 , dto.getCcompany() );		// 제조사
	         ps.setString( 2 , dto.getCnum() );			// 차량번호
	         ps.setString( 3 , dto.getCsize() );		// 차량종류
	         ps.setInt( 4 , dto.getCc() );				// 베기량
	         ps.setString( 5 , dto.getCoil() );			// 연료
	         ps.setString( 6 , dto.getCname() );		// 차량명
	         ps.setString( 7 , dto.getCdate() );		// 제조년월
	         ps.setInt( 8 , dto.getCkm() );				// KM
	         ps.setInt( 9 , dto.getMno() );				// 회원번호
	         int count = ps.executeUpdate();
	         // ********** insert 후 생성된 pk번호 가져오기 
	         rs = ps.getGeneratedKeys();
	         rs.next();
	         int cno = rs.getInt(1);
	         // ***************************************
	         
	         if( count == 1 ) { // Car 등록 성공시 
	            // --------------------------- 차량 주소 등록 -------------------------------------------------// 
	            sql = "insert into caraddress(cads,calat,calng,cacode,cacodename,cno)"
	                  + "values( ?,?,?,?,?,?) ";
	            ps =conn.prepareStatement(sql);
	            ps.setString( 1 , null );
	            ps.setString( 2 , dto.getCarAddress().getCalat() );				// 차량 위치 위도
	            ps.setString( 3 , dto.getCarAddress().getCalng() );				// 차량 위치 경도
	            ps.setString( 4 , null );
	            ps.setString( 5 , null );
	            ps.setInt( 6 , cno );
	            count = ps.executeUpdate();
	            if( count == 1 ) {  // Car 주소 등록 성공시 
	               // --------------------------- 차량 이미지 등록 -------------------------------------------------//
	               //Map<키,값> imglist;
	               //Map<이미지pk,이미지명> imglist;
	                  // 모든 키 호출 : dto.getImglist().keySet()
	                  // 모든 값 호출 : dto.getImglist().values()
	               dto.getImglist().values().forEach( (img)->{ // 반복문 사용하는 이유.. map컬렉션에는 여러개 사진이 있으므로
	                  try {
	                     String sql2 = "insert into carimg(ciimg ,cno)values(?,?) ";
	                     ps =conn.prepareStatement( sql2 );
	                     ps.setString( 1 , img );
	                     ps.setInt( 2 , cno );
	                     ps.executeUpdate();
	                  }catch (Exception e) {System.out.println(e);}
	               });
	               return true;
	            }
	         }
	      }catch (Exception e) {System.out.println(e);}
	      return false;
	  }
	
//게시물 상세조회 [9월19일 고연진]------------------------------------------------------------
	public AuctionDto auctionPrint(int cno) {
		System.out.println("Dao들어옴> "+cno);
		AuctionDto auctionDto = new AuctionDto();
		
		try {
			String sql = "select*from auctioninfo where cno=? order by astartdate desc limit 1";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
			rs=ps.executeQuery(); 
			//경매 번호에 맞는 차량 번호 찾기
			if(rs.next()) {
				CarDto carDto = carDto(rs.getInt("cno"));//차량번호에 맞는 CarDto 객체 가져옴.
				Map<Integer, String> imglist= imglist(rs.getInt("cno"));//cno에 맞는 차량이미지 저장한거 가져옴
				carDto.setImglist(imglist);
				
				auctionDto =auctionDto(rs.getInt("cno"));
				auctionDto.setCar(carDto);
	
				return auctionDto;
			}
			
		} catch (Exception e) {System.out.println("auctionPrint() 오류: "+e);}
		
		
		
		return null;
	
	}//f()
	
	
// 입찰 등록 시 유효성 검사를 위한 함수 [10월6일 고연진] 
	public int astate(int ano) {		
	
		try {
			String sql = "select astate from auctioninfo where ano=? ";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ano);
			rs=ps.executeQuery(); 
			if(rs.next()) {
			return rs.getInt("astate");
		}
		
	} catch (Exception e) {System.out.println("auctionPrint() 오류: "+e);}
	return 4;
	}
	
//스크랩(찜) 테이블 추가[9월21일 고연진]----------------------------------------------------------------	
	public boolean clipping(int mno, int ano) {
		try {//스크랩 항목 추가하기
			//스크랩 눌린 상태면 true로 아니면 false.( 현재 상태 확인을 하기 위한 함수 필요)
			//System.out.println("dao로 전달된 mno와 ano> : "+ mno+ano);
			String sql=	clipState(mno,ano)?
						"delete from wishlist where mno=? and ano=?":
						"insert into wishlist values (?,?)" ;
			
			ps=conn.prepareStatement(sql); 
			ps.setInt(1, mno);
			ps.setInt(2, ano);
			int count= ps.executeUpdate();
			if(count==1) {return true;}
		} catch (Exception e) {System.out.println("clipping() 오류: "+e);}
		System.out.println("테이블추가 실패함");return false;
	}//f()
	

//현재 회원과 글의 상태를 확인하는 함수[9월21일 고연진]---------------------------------
	public boolean clipState(int mno, int ano) {
		try {//테이블에 있으면 true, 없으면 false
			//System.out.println("회원과글상태확인하는함수 들어옴 =성공");
			String sql="select*from wishlist where mno=? and ano=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, mno);
			ps.setInt(2, ano);
			rs=ps.executeQuery();
			if(rs.next()) {return true;}
		} catch (Exception e) {System.out.println("clipState() 오류: "+e);}
		return false;
	}//f()
	
	
// 경매상태 변경 함수 [고연진]-------------------------------------------------------------
	 public boolean astateChage(int ano) {
		try {//타이머가 종료되면 경매상태를 종료(1)로 변경
			String sql= "update auctionInfo set astate=1 where ano=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ano);
			int count = ps.executeUpdate();
			if(count==1) {return true;}
		} catch (Exception e) {System.out.println("astateChage()오류"+e);}
		 return false;
	}//f()
	
	
	
	
	
	
	
	// 좌표 영역 내 옥션,자동차 정보 반환 [ 정용상 ]
	public List<CarAddressDto> mapAreaPrint1(String east, String west, String south, String north) {
		List<CarAddressDto> list = new ArrayList<>();
		
		try {
			
			String sql = "select calat, calng, cads, cname, car.cno from caraddress as cads inner join car on cads.cno = car.cno where calat between ? and ? and calng between ? and ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, west);
			ps.setString(2, east);
			ps.setString(3, south);
			ps.setString(4, north);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				CarAddressDto carAddressDto = new CarAddressDto();
				
				carAddressDto.setCalat(rs.getString("calat"));
				carAddressDto.setCalng(rs.getString("calng"));
				carAddressDto.setCads(rs.getString("cads"));
				carAddressDto.setCname(rs.getString("cname"));
				carAddressDto.setCno(rs.getInt("cno"));
				
				list.add(carAddressDto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("좌표 내 제품 검색 sql문 예외1 : "+e);
			return null;
		}
	}
	
	// 행정동 기준 클러스터 반환
	public List<CarAddressDto> mapAreaPrint2(String east, String west, String south, String north) {
		List<CarAddressDto> list = new ArrayList<>();
		
		try {
			String sql = "select cacodename as areaName, count(cacodename) as count, avg(calat) as calat, avg(calng) as calng from caraddress\n"
						+ "where calat between ? and ? and calng between ? and ?\n"
						+ "group by cacodename;";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, west);
			ps.setString(2, east);
			ps.setString(3, south);
			ps.setString(4, north);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				CarAddressDto carAddressDto = new CarAddressDto();
				carAddressDto.setCalat(rs.getString("calat"));
				carAddressDto.setCalng(rs.getString("calng"));
				carAddressDto.setAreaName(rs.getString("areaName"));
				carAddressDto.setCount(rs.getInt("count"));
				
				list.add(carAddressDto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("좌표 내 제품 검색 sql문 예외2 : "+e);
			return null;
		}
	}
	
	// 광역시 또는 자치구 기준 클러스터 반환
	public List<CarAddressDto> mapAreaPrint3(String east, String west, String south, String north, int level) {
		List<CarAddressDto> list = new ArrayList<>();
		
		try { // 확대 레벨 8 초과 시 광역 자치, 8 이하시 지방 자치 클러스터 반환
			String condition = level > 8 ? condition = "substring_index(cads,' ',1)" 
					: "substring_index(substring_index(cads,' ',2),' ',-1)"; 
			
			String sql = "select "+condition+" as areaName, count(cads) as count, avg(calat) as calat, avg(calng) as calng from caraddress\n"
						+ "group by "+condition;
				
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
				
			while(rs.next()) {
					
				CarAddressDto carAddressDto = new CarAddressDto();
				carAddressDto.setCalat(rs.getString("calat"));
				carAddressDto.setCalng(rs.getString("calng"));
				carAddressDto.setAreaName(rs.getString("areaName"));
				carAddressDto.setCount(rs.getInt("count"));
					
				list.add(carAddressDto);
			}
			return list;
			
		} catch (Exception e) {
			System.out.println("좌표 내 제품 검색 sql문 예외3 : "+e);
			return null;
		}
	}

	// 지도 클러스터, 마커 클릭 시 경매 정보 리스트 반환 [ 정용상 ]
	public List<AuctionDto> listPrint(String areaName, int level){
		List<AuctionDto> list = new ArrayList<>();
		System.out.println("level : "+level);
		try {
			// caraddress과 auctioninfo테이블 inner join 후 주소에서 공백으로 구분된 두번째 위치가 매개변수의
			// 지역명과 일치하는지 확인 후 auctioninfo의 등록 번호 내림차순으로 정렬 후 cno 출력 후 공통 함수 이용 List 객체에 담아 반환
			String sql = "select au.cno from caraddress ca inner join auctioninfo as au\n"
						+ "on ca.cno = au.cno\n";
			
			if(level >= 6 && level <= 8) {
				sql += "where substring_index(substring_index(ca.cads,' ',2),' ',-1) = '"+areaName+"'\n"
					+ "order by au.ano desc;";
			}
			else if(level == 5) {
				sql += "where cacodename = '"+areaName+"'\n"
						+ "order by au.ano desc;";
			}
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int cno = rs.getInt("cno");
				CarDto carDto = carDto(cno);
				carDto.setImglist(imglist(cno));
				
				AuctionDto auctionDto = auctionDto(cno);
				auctionDto.setCar(carDto);
				
				list.add(auctionDto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("지도 경매 정보 리스트 반환 sql 예외 : "+e);
			return null;
		}
		
	}
	
	
	// 클러스터 or 개별 마커 클릭 시 결과 반환
	public List<AuctionDto> clusterPrint( List<Integer> list ){
		List<AuctionDto> auctionDtoList = new ArrayList<>();
		
		try {
			
			for(Integer cno : list) {
				
				CarDto carDto = carDto(cno);
				carDto.setImglist(imglist(cno));
				carDto.setCarAddress(carAddressDto(cno));
				
				AuctionDto auctionDto = auctionDto(cno);
				auctionDto.setCar(carDto);
				
				auctionDtoList.add(auctionDto);
			}
			
			return auctionDtoList;
			
			
		} catch (Exception e) {
			System.out.println("클러스터 or 마커 결과 반환 sql문 예외 : "+e);
			return null;
		}
		
	}

	
} // 클래스 종료

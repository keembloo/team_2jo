package model.dto;

public class CarAddressDto {
	
	private String cads;		// #차량 등록 주소 
	private String calat;		//# 차량 위치 위도
	private String calng;		//# 차량 위치 경도
	private String cacode;		// 법정동 코드
	private String cacodename;	// 법정동명
	private int cno;
	
	
	public CarAddressDto() {
		super();
	}


	public CarAddressDto(String cads, String calat, String calng, String cacode, String cacodename, int cno) {
		super();
		this.cads = cads;
		this.calat = calat;
		this.calng = calng;
		this.cacode = cacode;
		this.cacodename = cacodename;
		this.cno = cno;
	}


	public String getCads() {
		return cads;
	}


	public void setCads(String cads) {
		this.cads = cads;
	}


	public String getCalat() {
		return calat;
	}


	public void setCalat(String calat) {
		this.calat = calat;
	}


	public String getCalng() {
		return calng;
	}


	public void setCalng(String calng) {
		this.calng = calng;
	}


	public String getCacode() {
		return cacode;
	}


	public void setCacode(String cacode) {
		this.cacode = cacode;
	}


	public String getCacodename() {
		return cacodename;
	}


	public void setCacodename(String cacodename) {
		this.cacodename = cacodename;
	}


	public int getCno() {
		return cno;
	}


	public void setCno(int cno) {
		this.cno = cno;
	}
	
	
	
	
	
}

package model.dto;

public class CarAddressDto {
	
	private String cads;		// #차량 등록 주소 
	private String clat;		//# 차량 위치 위도
	private String clng;		//# 차량 위치 경도
	
	
	
	public CarAddressDto() {
		super();
	}
	
	
	public CarAddressDto(String cads, String clat, String clng) {
		super();
		this.cads = cads;
		this.clat = clat;
		this.clng = clng;
	}


	public String getCads() {
		return cads;
	}
	public void setCads(String cads) {
		this.cads = cads;
	}
	public String getClat() {
		return clat;
	}
	public void setClat(String clat) {
		this.clat = clat;
	}
	public String getClng() {
		return clng;
	}
	public void setClng(String clng) {
		this.clng = clng;
	}
	
	
	
	
}

package model.dto;

import java.util.HashMap;
import java.util.Map;

public class CarDto {
	private int cno;			//#매물 번호 
	private String ccompany;	//#제조사 
	private String cnum;		//  #차량 번호
	private String csize;		// #차량 종류
	private int cc;				// #배기량
	private String coil;		//#연료
	private String cname;		//#차량명
	private String cdate;		// #제조년월
	private int ckm;			// #주행거리
	private int mno;			// #회원번호
	private CarAddressDto carAddress;
	private Map<Integer,String> imglist;
	
	
	public CarDto() {}
	
	


	//풀생성자
	public CarDto(int cno, String ccompany, String cnum, String csize, int cc, String coil, String cname, String cdate,
			int ckm, int mno, CarAddressDto carAddress, Map<Integer, String> imglist) {
		super();
		this.cno = cno;
		this.ccompany = ccompany;
		this.cnum = cnum;
		this.csize = csize;
		this.cc = cc;
		this.coil = coil;
		this.cname = cname;
		this.cdate = cdate;
		this.ckm = ckm;
		this.mno = mno;
		this.carAddress = carAddress;
		this.imglist = imglist;
	}





	public CarDto(int cno, String ccompany, String cnum, String csize, int cc, String coil, String cname, String cdate,
			int ckm, String cads, String clat, String clng) {
		super();
		this.cno = cno;
		this.ccompany = ccompany;
		this.cnum = cnum;
		this.csize = csize;
		this.cc = cc;
		this.coil = coil;
		this.cname = cname;
		this.cdate = cdate;
		this.ckm = ckm;
	}
	
	public CarDto(int cno, String ccompany, String cnum, String csize, int cc, String coil, String cname, String cdate,
			int ckm, int mno) {
		super();
		this.cno = cno;
		this.ccompany = ccompany;
		this.cnum = cnum;
		this.csize = csize;
		this.cc = cc;
		this.coil = coil;
		this.cname = cname;
		this.cdate = cdate;
		this.ckm = ckm;
		this.mno = mno;
	}
	
	
	
	


	public CarAddressDto getCarAddress() {
		return carAddress;
	}



	public void setCarAddress(CarAddressDto carAddress) {
		this.carAddress = carAddress;
	}





	public Map<Integer, String> getImglist() {
		return imglist;
	}


	public void setImglist(Map<Integer, String> imglist) {
		this.imglist = imglist;
	}


	public int getMno() {
		return mno;
	}



	public void setMno(int mno) {
		this.mno = mno;
	}


	public int getCno() {
		return cno;
	}


	public void setCno(int cno) {
		this.cno = cno;
	}


	public String getCcompany() {
		return ccompany;
	}


	public void setCcompany(String ccompany) {
		this.ccompany = ccompany;
	}


	public String getCnum() {
		return cnum;
	}


	public void setCnum(String cnum) {
		this.cnum = cnum;
	}


	public String getCsize() {
		return csize;
	}


	public void setCsize(String csize) {
		this.csize = csize;
	}


	public int getCc() {
		return cc;
	}


	public void setCc(int cc) {
		this.cc = cc;
	}


	public String getCoil() {
		return coil;
	}


	public void setCoil(String coil) {
		this.coil = coil;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public String getCdate() {
		return cdate;
	}


	public void setCdate(String cdate) {
		this.cdate = cdate;
	}


	public int getCkm() {
		return ckm;
	}


	public void setCkm(int ckm) {
		this.ckm = ckm;
	}




	@Override
	public String toString() {
		return "CarDto [cno=" + cno + ", ccompany=" + ccompany + ", cnum=" + cnum + ", csize=" + csize + ", cc=" + cc
				+ ", coil=" + coil + ", cname=" + cname + ", cdate=" + cdate + ", ckm=" + ckm + ", mno=" + mno
				+ ", imglist=" + imglist + "]";
	}
	
	
}

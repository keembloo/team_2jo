package model.dto;

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
	private String cads;		// #차량 등록 주소 
	private String clat;		//# 차량 위치 위도
	private String clng;		//# 차량 위치 경도
	
	
	public CarDto() {}


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
		this.cads = cads;
		this.clat = clat;
		this.clng = clng;
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


	@Override
	public String toString() {
		return "CarDto [cno=" + cno + ", ccompany=" + ccompany + ", cnum=" + cnum + ", csize=" + csize + ", cc=" + cc
				+ ", coil=" + coil + ", cname=" + cname + ", cdate=" + cdate + ", ckm=" + ckm + ", cads=" + cads
				+ ", clat=" + clat + ", clng=" + clng + "]";
	}
	
	
}

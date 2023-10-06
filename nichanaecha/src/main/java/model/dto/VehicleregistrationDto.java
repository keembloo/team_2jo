package model.dto;

//차량등록시 필요한 차,차주소,차이미지 담을 Dto
public class VehicleregistrationDto {
	//필드
		//차
	private  String ccompany;		//제조사
	private String cnum;			//차량 번호
	private String csize;			//차량 종류
	private int cc;					//배기량
	private String coil;			//연료
	private String cname;			//차량명
	private String cdate;			//제조년월
	private int ckm;				//주행거리
	private int mno;				//회원번호
		//차주소
	private String calat;			//차량 위치 위도
	private String calng;			//차량 위치 경도
		//차이미지
	private String img;				//차량 이미지
	
	
	
	
	//생성자
		// 빈생성자
		public VehicleregistrationDto() {
			super();
		}
		// 풀생성자
	public VehicleregistrationDto(String ccompany, String cnum, String csize, int cc, String coil, String cname,
			String cdate, int ckm, int mno, String calat, String calng, String img) {
		super();
		this.ccompany = ccompany;
		this.cnum = cnum;
		this.csize = csize;
		this.cc = cc;
		this.coil = coil;
		this.cname = cname;
		this.cdate = cdate;
		this.ckm = ckm;
		this.mno = mno;
		this.calat = calat;
		this.calng = calng;
		this.img = img;
	}
	
	//메소드
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
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "VehicleregistrationDto [ccompany=" + ccompany + ", cnum=" + cnum + ", csize=" + csize + ", cc=" + cc
				+ ", coil=" + coil + ", cname=" + cname + ", cdate=" + cdate + ", ckm=" + ckm + ", mno=" + mno
				+ ", calat=" + calat + ", calng=" + calng + ", img=" + img + "]";
	}
		
	
}

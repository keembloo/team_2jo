package model.dto;

public class MemberDto {
	private int mno;		//회원 번호 
	private String mid;		//회원 아이디
	private String mpw;		//회원 비밀번호 
	private String mphone;	//회원 전화번호
	private String mname;	//회원 이름 
	private String mads;	//회원 주소 
	private int mcash;		//회원 보유 금액 
	
	public MemberDto() {
		super();
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMads() {
		return mads;
	}

	public void setMads(String mads) {
		this.mads = mads;
	}

	public int getMcash() {
		return mcash;
	}

	public void setMcash(int mcash) {
		this.mcash = mcash;
	}

	@Override
	public String toString() {
		return "MemberDto [mno=" + mno + ", mid=" + mid + ", mpw=" + mpw + ", mphone=" + mphone + ", mname=" + mname
				+ ", mads=" + mads + ", mcash=" + mcash + "]";
	}
	
	
	
}
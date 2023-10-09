package model.dto;

public class MemberDto {
	private int mno;		//회원 번호 
	private String mid;		//회원 아이디
	private String mpw;		//회원 비밀번호 
	private String mphone;	//회원 전화번호
	private String mname;	//회원 이름 
	private String mads;	//회원 주소 
	private long mcash;		//회원 보유 금액 
	
	

	
	public MemberDto() {
		super();
	}
	
	public MemberDto(int mno, String mid, String mpw, String mphone, String mname, String mads, long mcash) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mphone = mphone;
		this.mname = mname;
		this.mads = mads;
		this.mcash = mcash;
	}
	
	
	//내정보 호출 생성자
		public MemberDto(int mno, String mid) {
			super();
			this.mno = mno;
			this.mid = mid;
		}
		
	//회원가입 시 사용 [9월15일 고연진]
	
	public MemberDto(String mid, String mpw, String mphone, String mname, String mads) {
		super();
		this.mid = mid;
		this.mpw = mpw;
		this.mphone = mphone;
		this.mname = mname;
		this.mads = mads;
	}


	// 규리 마이페이지 출력시 사용 [회원번호, 이름 , 포인트 ]
	public MemberDto(int mno, String mname, long mcash) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.mcash = mcash;
	}
	


	// 규리 내정보 수정 출력시 사용 [ 회원번호, 이름 , 아이디 , 주소, 연락처 , 포인트 ]
	public MemberDto(int mno, String mid, String mphone, String mname, String mads, long mcash) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mphone = mphone;
		this.mname = mname;
		this.mads = mads;
		this.mcash = mcash;
	}
	
	// 규리 회원 , 전화번호 출력시 사용  [ 회원번호, 아이디 , 연락처 ]
	public MemberDto(int mno, String mid, String mphone) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mphone = mphone;
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

	public long getMcash() {
		return mcash;
	}

	public void setMcash(long mcash) {
		this.mcash = mcash;
	}

	@Override
	public String toString() {
		return "MemberDto [mno=" + mno + ", mid=" + mid + ", mpw=" + mpw + ", mphone=" + mphone + ", mname=" + mname
				+ ", mads=" + mads + ", mcash=" + mcash + "]";
	}
	
	
	
}

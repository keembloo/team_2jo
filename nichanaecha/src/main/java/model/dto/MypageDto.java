package model.dto;
//마이페이지 호출을 위한 DTO
public class MypageDto {
	
    private MemberDto memberDto;
    private AuctionDto auctionDto;
    private CarDto carDto;
	
	
	public MypageDto() {}


	public MypageDto(MemberDto memberDto, AuctionDto auctionDto, CarDto carDto) {
		super();
		this.memberDto = memberDto;
		this.auctionDto = auctionDto;
		this.carDto = carDto;
	}


	public MemberDto getMemberDto() {
		return memberDto;
	}


	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}


	public AuctionDto getAuctionDto() {
		return auctionDto;
	}


	public void setAuctionDto(AuctionDto auctionDto) {
		this.auctionDto = auctionDto;
	}


	public CarDto getCarDto() {
		return carDto;
	}


	public void setCarDto(CarDto carDto) {
		this.carDto = carDto;
	}


	@Override
	public String toString() {
		return "MypageDto [memberDto=" + memberDto + ", auctionDto=" + auctionDto + ", carDto=" + carDto + "]";
	}
	
	
	
	/*
	// 멤버
	private int mno;			//회원번호
	private String mid;			//회원 아이디
	private String mpw;			// 회원 비밀번호
	private String mphone;		// 회원 전화번호
	private String mname;		// 회원 이름
	private String mads;		// 회원 주소
	private int mcash;			// 회원 보유 금액 
	
	// 차량매물
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
	
	// 경매글
	private int ano;			//#경매 번호
	private String atitle;		//#경매 제목 
	private String acontent;	// #경매 내용 
	private String astartdate;	// #경매 등록 날짜 
	private String aenddate;	// #경매 종료 날짜 
	private int aprice;			// #경매 등록 가격 
	private int astate;			//  #경매 상태 [ 0: 경매중, 1:거래중(낙찰 후 판매자와 거래중) 2:경매 종료
	
	
	public MypageDto() { }


	public MypageDto(int mno, String mid, String mpw, String mphone, String mname, String mads, int mcash, int cno,
			String ccompany, String cnum, String csize, int cc, String coil, String cname, String cdate, int ckm,
			String cads, String clat, String clng, int ano, String atitle, String acontent, String astartdate,
			String aenddate, int aprice, int astate) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mphone = mphone;
		this.mname = mname;
		this.mads = mads;
		this.mcash = mcash;
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
		this.ano = ano;
		this.atitle = atitle;
		this.acontent = acontent;
		this.astartdate = astartdate;
		this.aenddate = aenddate;
		this.aprice = aprice;
		this.astate = astate;
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


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}


	public String getAtitle() {
		return atitle;
	}


	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}


	public String getAcontent() {
		return acontent;
	}


	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}


	public String getAstartdate() {
		return astartdate;
	}


	public void setAstartdate(String astartdate) {
		this.astartdate = astartdate;
	}


	public String getAenddate() {
		return aenddate;
	}


	public void setAenddate(String aenddate) {
		this.aenddate = aenddate;
	}


	public int getAprice() {
		return aprice;
	}


	public void setAprice(int aprice) {
		this.aprice = aprice;
	}


	public int getAstate() {
		return astate;
	}


	public void setAstate(int astate) {
		this.astate = astate;
	}


	@Override
	public String toString() {
		return "MypageDto [mno=" + mno + ", mid=" + mid + ", mpw=" + mpw + ", mphone=" + mphone + ", mname=" + mname
				+ ", mads=" + mads + ", mcash=" + mcash + ", cno=" + cno + ", ccompany=" + ccompany + ", cnum=" + cnum
				+ ", csize=" + csize + ", cc=" + cc + ", coil=" + coil + ", cname=" + cname + ", cdate=" + cdate
				+ ", ckm=" + ckm + ", cads=" + cads + ", clat=" + clat + ", clng=" + clng + ", ano=" + ano + ", atitle="
				+ atitle + ", acontent=" + acontent + ", astartdate=" + astartdate + ", aenddate=" + aenddate
				+ ", aprice=" + aprice + ", astate=" + astate + "]";
	}
	*/
}

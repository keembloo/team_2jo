package model.dto;


public class AuctionDto {
	private int ano;			//#경매 번호
	private String atitle;		//#경매 제목 
	private String acontent;	// #경매 내용 
	private String astartdate;	// #경매 등록 날짜 
	private String aenddate;	// #경매 종료 날짜 
	private long aprice;			// #경매 등록 가격 
	private int astate;			//  #경매 상태 [ 0: 경매중, 1:거래중(낙찰 후 판매자와 거래중) 2:경매 종료
	private int cno;
	private CarDto car;
	
//입찰내역 담기 [고연진]--------------------------------------------------------//
	private BattingDto batting; // 필드 추가
	
	//getter/setter
	public BattingDto getBatting() {return batting;}
	public void setBatting(BattingDto batting) {this.batting = batting;}
//--------------------------------------------------------------------------//

	public AuctionDto() {}
	
	
	public AuctionDto(int ano, String atitle, String acontent, String astartdate, String aenddate, int aprice,
			int astate) {
		super();
		this.ano = ano;
		this.atitle = atitle;
		this.acontent = acontent;
		this.astartdate = astartdate;
		this.aenddate = aenddate;
		this.aprice = aprice;
		this.astate = astate;
	}
	
	
	// 규리 마이페이지에서 등록매물정보 출력을 위한 생성자
	// 풀생성자에서 경매 내용, 경매 등록날짜만 빠짐 
	public AuctionDto(int ano, String atitle, String aenddate, int aprice, int astate) {
		super();
		this.ano = ano;
		this.atitle = atitle;
		this.aenddate = aenddate;
		this.aprice = aprice;
		this.astate = astate;
	}


	public AuctionDto(int ano, String atitle, String acontent, String astartdate, String aenddate, long aprice,
			int astate, int cno) {
		super();
		this.ano = ano;
		this.atitle = atitle;
		this.acontent = acontent;
		this.astartdate = astartdate;
		this.aenddate = aenddate;
		this.aprice = aprice;
		this.astate = astate;
		this.cno = cno;
	}

	
	public CarDto getCar() {
		return car;
	}


	public void setCar(CarDto car) {
		this.car = car;
	}


	public long getAprice() {
		return aprice;
	}


	public int getCno() {
		return cno;
	}


	public void setCno(int cno) {
		this.cno = cno;
	}



	public void setAprice(long aprice) {
		this.aprice = aprice;
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


	public int getAstate() {
		return astate;
	}


	public void setAstate(int astate) {
		this.astate = astate;
	}


	@Override
	public String toString() {
		return "AuctionDto [ano=" + ano + ", atitle=" + atitle + ", acontent=" + acontent + ", astartdate=" + astartdate
				+ ", aenddate=" + aenddate + ", aprice=" + aprice + ", astate=" + astate + "]";
	}
	
	
}

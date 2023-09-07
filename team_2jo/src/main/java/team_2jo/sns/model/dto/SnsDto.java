package team_2jo.sns.model.dto;

import java.util.ArrayList;

public class SnsDto {

		// 필드
	private int bno;
	private int cno;
	private String bfile;
	private String bdate;
	private String bcontent;
	private String bpwd;
	private String ccontent;
	private String cdate;
	private String cpwd;
	private ArrayList<SnsDto> list;
	private ArrayList<SnsDto> clist;
	private int blike;
	private int bdislike;
	private int feedCnt;
	
	
	public int getBlike() {
		return blike;
	}

	public void setBlike(int blike) {
		this.blike = blike;
	}

	public int getBdislike() {
		return bdislike;
	}

	public void setBdislike(int bdislike) {
		this.bdislike = bdislike;
	}

	public ArrayList<SnsDto> getClist() {
		return clist;
	}

	public void setClist(ArrayList<SnsDto> clist) {
		this.clist = clist;
	}

	public ArrayList<SnsDto> getList() {
		return list;
	}

	public void setList(ArrayList<SnsDto> list) {
		this.list = list;
	}

	public int getFeedCnt() {
		return feedCnt;
	}

	public void setFeedCnt(int feedCnt) {
		this.feedCnt = feedCnt;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getCpwd() {
		return cpwd;
	}

	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}

	// 생성자
	public SnsDto() {	}
	
	public SnsDto(int bno, String bfile, String bdate, String bcontent, String bpwd, int blike, int bdislike) {
		super();
		this.bno = bno;
		this.bfile = bfile;
		this.bdate = bdate;
		this.bcontent = bcontent;
		this.bpwd = bpwd;
		this.blike = blike;
		this.bdislike = bdislike;
	}
	
	
	// + 필요시 추가
	

	//글등록[고연진] 생성자
	public SnsDto(String bfile, String bcontent, String bpwd) {
		super();
		this.bfile = bfile;
		this.bcontent = bcontent;
		this.bpwd = bpwd;
	}
	
	//댓글등록생성자 규리
	public SnsDto(int bno, String ccontent, String cpwd) {
		super();
		this.bno = bno;
		this.ccontent = ccontent;
		this.cpwd = cpwd;
	}
	
	
	
	
	//댓글삭제[고연진] 생성자

	public SnsDto(int bno, int cno, String cpwd) {
		super();
		this.bno = bno;
		this.cno = cno;
		this.cpwd = cpwd;
	}
	
	
	// 메소드
	public int getBno() {
		return bno;
	}


	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBfile() {
		return bfile;
	}

	public void setBfile(String bfile) {
		this.bfile = bfile;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBpwd() {
		return bpwd;
	}

	public void setBpwd(String bpwd) {
		this.bpwd = bpwd;
	}

	@Override
	public String toString() {
		return "SnsDto [bno=" + bno + ", cno=" + cno + ", bfile=" + bfile + ", bdate=" + bdate + ", bcontent="
				+ bcontent + ", bpwd=" + bpwd + ", ccontent=" + ccontent + ", cdate=" + cdate + ", cpwd=" + cpwd + "]";
	}

	
	
	
}

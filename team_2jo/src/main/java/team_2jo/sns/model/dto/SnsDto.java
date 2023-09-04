package team_2jo.sns.model.dto;

public class SnsDto {

		// 필드
	private int bno;
	private String bfile;
	private String bdate;
	private String bcontent;
	private String bpwd;
	
	
	// 생성자
	public SnsDto() {	}
	
	public SnsDto(int bno, String bfile, String bdate, String bcontent, String bpwd) {
		super();
		this.bno = bno;
		this.bfile = bfile;
		this.bdate = bdate;
		this.bcontent = bcontent;
		this.bpwd = bpwd;
	}
	
	
	// + 필요시 추가
	
	
	
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
		return "SnsDto [bno=" + bno + ", bfile=" + bfile + ", bdate=" + bdate + ", bcontent=" + bcontent + ", bpwd="
				+ bpwd + "]";
	}
	

	
	
}

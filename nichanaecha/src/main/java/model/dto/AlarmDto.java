package model.dto;

public class AlarmDto {

	private String mid;//클라이언트소켓 사용하는 회원 아이디
	private String alarm; //클라이언트소켓

	
	public AlarmDto() {}


	public AlarmDto(String mid, String alarm) {
		super();
		this.mid = mid;
		this.alarm = alarm;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getAlarm() {
		return alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}

	
	@Override
	public String toString() {
		return "AlarmDto [mid=" + mid + ", alarm=" + alarm + "]";
	}
	
	
}

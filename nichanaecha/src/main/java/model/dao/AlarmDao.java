package model.dao;



public class AlarmDao extends Dao{
	 
		private static AlarmDao alarmDao = new AlarmDao();
		public static AlarmDao getInstence() {return alarmDao;}
		private AlarmDao() {}

		
		
		//1.입찰등록 성공 시 입찰자들에게 입출금 알람[10월7일 고연진]-------------------------
		public boolean buyAlarm(int mno,long gold,String type) {
			try {
				String sql = "insert into alarm (mno,alarmcontent)values( "+mno+", ' 경매 관련해 "+gold+"원 "+type+"')";
				System.out.println("sql 들어옴> "+sql);
				ps=conn.prepareStatement(sql);
				int count =ps.executeUpdate();
				if(count==1) {
					System.out.println("알람등록성공");
					return true;
				}
				
			} catch (Exception e) {System.out.println("buyPay() 오류");}
			return false;
		}
		
		
		
		
}//c

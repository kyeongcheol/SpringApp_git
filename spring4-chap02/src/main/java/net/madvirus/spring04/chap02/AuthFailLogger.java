package net.madvirus.spring04.chap02;

//인증에 실패한 기록을 남기기 위한 클래스
public class AuthFailLogger 
{
	private int threshold;
	private int failCounts;
	
	//비밀번호 입력을 잘못 하였을 때 처리하는 메서드
	public void insertBadPw(String userId, String inputPw)
	{
		System.out.printf("AuthFail [type=badpw, userid=%s, pw=%s]\n", 
				userId, inputPw);
		
		failCounts++;
		if(threshold > 0 && failCounts > threshold)
		{
			notifyTooManyFail();
			failCounts=0;
		}
	}
	
		private void notifyTooManyFail()
		{
			System.out.println("너무 많은 로그인 시도 실패");
		}
		
		//프로퍼티 설정(set 메서드)으로 의존 객체(변수)를 받음
		//외부에서 꼭 객체 뿐만 아니라 기본형 타입도 외부에서 설정 후 사용 가능
		public void setThreshold(int threshold)
		{
			this.threshold = threshold;
		}


}

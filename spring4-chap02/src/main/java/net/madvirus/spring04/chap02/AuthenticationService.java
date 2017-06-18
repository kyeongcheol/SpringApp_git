package net.madvirus.spring04.chap02;

public class AuthenticationService 
{
	//객체 선언
	private UserRepository userRepository;
	private AuthFailLogger failLogger;
	
	//AuthInfo 클래 스 객체를 이용하여 메서드 생성
	public AuthInfo authenticate(String id, String password)
	{
		User user = userRepository.findUserById(id);
		
		//회원 가입 정보가 없다면..
		if(user == null)
		{
			throw new UserNotFoundException();
		}
		
		//등록된 사용자 비밀번호가 맞지 않으면..
		if(!user.matchPassword(password))
		{
			//AuthFailLogger 클래스 안에 있는 메서드로 처리
			failLogger.insertBadPw(id,password);
			throw new AuthException();
		}
		
		return new AuthInfo(user.getId());		
	}
	
	//DI - 프로퍼티 설정
	public void setUserRepository(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	public void setFailLogger(AuthFailLogger failLogger)
	{
		this.failLogger = failLogger;
	}

}

package net.madvirus.spring04.chap02;

public class PasswordChangeService
{
	//객체 선언
	private UserRepository userRepository;
	
	//생성자를 통해 의존 객체 받음
	public PasswordChangeService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	//비밀번호 바꾸기
    public void changePassword(String userId, String oldPw, String newPw)
    {
    	User user = userRepository.findUserById(userId);
    	
    	//회원 가입 정보가 없으면..(ID가 없으면..)
    	if(user==null)
    	{
    		throw new UserNotFoundException();
    	}
    	
    	//ID가 있으면..
    	user.changePassword(oldPw, newPw);
    	
    }

}

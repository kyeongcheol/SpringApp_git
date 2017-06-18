package net.madvirus.spring04.chap02;

//javabean

public class User 
{
	private String id;
	private String password;
	
	//생성자를 통해 의존 객체(or 변수)를 전달받음
	public User(String id, String password)
	{
		this.id = id;
		this.password = password;
	}
	
	public String getId()
	{
		return id;
	}
	
	//입력된 비밀번호와 맞는지 여부
	public boolean matchPassword(String inputPasswd)
	{
		return password.equals(inputPasswd);
	}
	
	//비밀번호 바꾸기
	public void changePassword(String oldPassword, String newPassword)
	{
		//비밀번호 매칭 시 기존에 비밀번호와 같지 않으면..
		if(!matchPassword(oldPassword))
		{
			//익셉션 처리
			throw new IllegalArgumentException("illegal password");
		}
		
		//else : 기존에 비밀번호와 같으면..
		password = newPassword;
		
	}

}

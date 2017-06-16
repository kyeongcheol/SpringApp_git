package net.madvirus.spring04.chap02;

//인증 할 정보를 담은 클래스
public class AuthInfo 
{
	private String id;
	
	//생성자를 통해 의존 객체를 받음
	public AuthInfo(String id)
	{
		this.id = id;
	}
	
	public String getId()
	{
		return id;
	}

}

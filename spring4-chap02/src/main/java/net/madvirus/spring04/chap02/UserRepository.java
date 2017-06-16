package net.madvirus.spring04.chap02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//User 객체를 보관 : List로..

public class UserRepository 
{
	//HashMap 생성
    private Map<String, User> userMap = new HashMap<String, User>();
    
    //Id 찾기
    public User findUserById(String id)
    {
    	return userMap.get(id);
    }
    
    //프로퍼티 설정 방식으로 의존 객체를 전달 받음, List로..
    //외부에서 객체에 대한 설정(의존주입)이 되면 객체 생성하여 사용
    public void setUsers(List<User> users)
    {
    	//users List를 User u 객체에 대입하여 값을 출력
    	for(User u : users)
    		userMap.put(u.getId(), u); //u 객체 안에 getId 값을 Hash Map으로..   	
    }

}

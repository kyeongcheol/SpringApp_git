package first.common.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//request에 담겨 있는 파라미터를 Map에 담아주는 역할을 하는 클래스
public class CommandMap 
{
	//Hashmap 생성
	Map<String,Object> map = new HashMap<String,Object>();
	
	public Object get(String key)
	{
		return map.get(key);
	}
	
	//map에 key,value 삽입
	public void put(String key, Object value)
	{
		map.put(key, value);
	}
	
	//map 안에 key를 가져와 삭제
	public Object remove(String key)
	{
		return map.remove(key);
	}
	
	//map에 해당 키를 폼하하고 있는지 체크
	public boolean containsKey(String key)
	{
		return map.containsKey(key);
	}
	
	public boolean containsValue(Object value)
	{
		return map.containsValue(value);
	}
	
	//map에 모든 객체 다 지우기
	public void clear()
	{
		map.clear();
	}
	
	//entryset : key와 value를 하나의 entry로 묶어줌
	public Set<Entry<String,Object>> entrySet()
	{
		return map.entrySet();
	}
	
    //keyset : map 안에 있는 key를 set으로 가져옴
	public Set<String> keySet()
	{
		return map.keySet();
	}
	
	public boolean isEmpty()
	{
		return map.isEmpty();
	}
	
	//String과 Object를 기준으로 자신을 포함한 하위 클래스를 제네릭 타입으로 지정
	public void putAll(Map<? extends String, ? extends Object> m)
	{
		map.putAll(m);
	}
	
	//다른 곳에서도 CommandMap을 map과 같이 사용할 수 있도록 getMap 메서드를 추가
	public Map<String, Object> getMap()
	{
		return map;
	}
	

}

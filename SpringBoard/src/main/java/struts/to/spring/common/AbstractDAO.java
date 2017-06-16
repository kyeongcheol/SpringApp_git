package struts.to.spring.common;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDAO 
{
	
	@Autowired //xml에 선언했던 의존관게를 자동으로 주입
	private SqlSessionTemplate sqlSession;
	
	//글 등록
	public Object insert(String queryId, Object params)
	{
		return sqlSession.insert(queryId, params);
	}
	
	//글 수정
	public Object update(String queryId, Object params)
	{
		return sqlSession.update(queryId, params);
	}
	
	//글 삭제
	public Object delete(String queryId, Object params)
	{
		return sqlSession.delete(queryId, params);
	}
	
	//글 목록
	//글 상세보기
	public Object selectOne(String queryId)
	{
		return sqlSession.selectOne(queryId);
	}
	
	public Object selectOne(String queryId, Object params)
	{
		return sqlSession.selectOne(queryId, params);
	}
	
	//여러 글
	@SuppressWarnings("rawtypes") //컴파일러가 일반적으로 경고하는 내용 중 제외시킴
	//rawtypes : 제네릭을 사용하는 클래스 매개변수가 불특정일 때의 경고 억제
	public List selectList(String queryId)
	{
		return sqlSession.selectList(queryId);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectList(String queryId, Object params)
	{
		return sqlSession.selectList(queryId, params);
	}
	
	

}

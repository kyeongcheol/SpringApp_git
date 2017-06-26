package first.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class AbstractDAO 
{
	protected Log log = LogFactory.getLog(AbstractDAO.class);
	
	@Autowired //xml에 선언했던 의존관게를 자동으로 주입
	private SqlSessionTemplate sqlSession;
	
	//console 로그를 남기기 위해 각각의 메서드 재정의
	protected void printQueryId(String queryId)
	{
		if(log.isDebugEnabled())
		{
			log.debug("\t QueryId \t: " +queryId);
		}
	}
	
	//글 등록
	public Object insert(String queryId, Object params)
	{
		printQueryId(queryId);
		return sqlSession.insert(queryId, params);
	}
	
	//글 수정
	public Object update(String queryId, Object params)
	{
		printQueryId(queryId);
		return sqlSession.update(queryId, params);
	}
	
	//글 삭제
	public Object delete(String queryId, Object params)
	{
		printQueryId(queryId);
		return sqlSession.delete(queryId, params);
	}
	
	//글 목록
	//글 상세보기
	public Object selectOne(String queryId)
	{
		printQueryId(queryId);
		return sqlSession.selectOne(queryId);
	}
	
	public Object selectOne(String queryId, Object params)
	{
		printQueryId(queryId);
		return sqlSession.selectOne(queryId, params);
	}
	
	//여러 글
	@SuppressWarnings("rawtypes") //컴파일러가 일반적으로 경고하는 내용 중 제외시킴
	//rawtypes : 제네릭을 사용하는 클래스 매개변수가 불특정일 때의 경고 억제
	public List selectList(String queryId)
	{
		printQueryId(queryId);
		return sqlSession.selectList(queryId);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectList(String queryId, Object params)
	{
		printQueryId(queryId);
		return sqlSession.selectList(queryId, params);
	}
	
	//페이징 처리 로직
	@SuppressWarnings("unchecked")
	public Object selectPagingList(String queryId, Object params)
	{
		printQueryId(queryId);
		//map 객체 생성, params를 map에 저장
		Map<String, Object> map = (Map<String,Object>) params;
		
		//현재 페이지 번호(currentPage) 와 
		//한 페이지에 보여줄 행의 개수(pagePerCount) 계산
		
		String strPageIndex = (String)map.get("PAGE_INDEX");
		String strPageRow = (String)map.get("PAGE_ROW");
		
	    //혹시 모를 예외 상황에 대비하여 해당 값을 지정
		int nPageIndex = 0;
		int nPageRow = 20;
		
		//StringUtils : String 클래스가 제공하는 문자열 관련 기능
		//대부분의 문자열 처리로 인해 파라미터 값으로 null을 주더라도 nullPointException을 발생 x
		if(StringUtils.isEmpty(strPageIndex) == false)
		{
			nPageIndex = Integer.parseInt(strPageIndex) - 1;
		}
		
		if(StringUtils.isEmpty(strPageRow) == false)
		{
			nPageRow = Integer.parseInt(strPageRow);
		}
		
		//페이징 쿼리의 시작과 끝 값을 계산
		// (0*0) + 1 = 1 , 시작 페이지 
		map.put("START", (nPageIndex * nPageRow) + 1);
		// (0*20) + 20 = 20, 끝 페이지
		map.put("END", (nPageIndex * nPageRow) + nPageRow);
		
		return sqlSession.selectList(queryId, map);
	}
	
	
}

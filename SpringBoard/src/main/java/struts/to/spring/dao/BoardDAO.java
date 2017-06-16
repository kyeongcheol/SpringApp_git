package struts.to.spring.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import struts.to.spring.common.AbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends AbstractDAO
{
	@SuppressWarnings("unchecked")
	//검증되지 않은 연산자 관련 경고 억제
	
    //게시글 목록
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map)
    throws Exception
	{
		//첫번째 파라미터 : 쿼리 id , 두번째 파라미터 : 쿼리가 실행되는데 필요한 변수
		return(List<Map<String, Object>>)selectList("board.selectBoardList", map);
	}
	
	@SuppressWarnings("unchecked")
	
	//게시글 상세보기
	public Map<String, Object> selectBoardView(Map<String, Object> map)
	throws Exception
	{
		return (Map<String, Object>)selectOne("board.selectBoardView", map);
	}
	
	//조회수 증가
	public void updateHitCnt(Map<String, Object> map) throws Exception
	{
		update("board.updateReadHit", map);
	}
	
	@SuppressWarnings("unchecked")
	//비밀번호 체크
	public Map<String, Object> selectPassword(Map<String, Object> map)
		throws Exception
	{
		return (Map<String, Object>)selectOne("board.selectPassword", map);
	}

}

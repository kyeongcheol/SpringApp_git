package first.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import first.common.dao.AbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO
{
	@SuppressWarnings("unchecked")
	//검증되지 않은 연산자 관련 경고 억제
    //글 목록
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception
	{
		//첫번째 파라미터 : 쿼리 이름, 두번째는 쿼리가 실행되는데 필요한 변수
		return (List<Map<String, Object>>)selectList("sample.selectBoardList", map);
	}
	
	//조회수 증가
	public void updateHitCnt(Map<String, Object> map) throws Exception
	{
		update("sample.updateHitCnt", map);
	}
	
	//게시글 상세보기
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception
	{
		return (Map<String, Object>)selectOne("sample.selectBoardDetail", map);
	}
	
	//글 등록
	public void insertBoard(Map<String, Object> map) throws Exception
	{
		insert("sample.insertBoard", map);
	}
	
	//글 수정
	public void updateBoard(Map<String, Object> map) throws Exception
	{
		update("sample.updateBoard", map);
	}
	
	//글 삭제
	public void deleteBoard(Map<String, Object> map) throws Exception
	{
		update("sample.deleteBoard", map);
	}
	
	//파일 업로드
	public void insertFile(Map<String, Object> map) throws Exception
	{
		insert("sample.insertFile", map);
	}


}

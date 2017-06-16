package first.sample.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SampleService 
{
	//글 목록
	List<Map<String, Object>> selectBoardList(Map<String, Object> map)
	throws Exception;
	
	//글 등록
	//파일 업로드 위해 HttServletRequest request 추가
	void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	//글 상세보기
	Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception;
	
	//글 수정
	void updateBoard(Map<String, Object> map) throws Exception;
	
	//글 삭제
	void deleteBoard(Map<String, Object> map) throws Exception;

}

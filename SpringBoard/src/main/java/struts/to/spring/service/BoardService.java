package struts.to.spring.service;

import java.util.List;
import java.util.Map;

public interface BoardService 
{
    //게시글 목록
    List<Map<String, Object>> selectBoardList(Map<String, Object> map)
    throws Exception;
    
    //게시글 상세보기
    Map<String, Object> selectBoardView(Map<String, Object> map)
    throws Exception;
    
    //비밀번호 체크
    Map<String, Object> selectPassword(Map<String, Object> map)
    throws Exception;
    	    
	
}

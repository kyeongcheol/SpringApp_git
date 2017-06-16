package struts.to.spring.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import struts.to.spring.dao.BoardDAO;

@Service("boardService")
public class BoardServiceImpl implements BoardService
{
	@Resource(name="boardDAO")
	private BoardDAO boardDAO;

	//게시글 목록
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) 
			throws Exception 
	{
		return boardDAO.selectBoardList(map);
	}

	//게시글 상세보기
	@Override
	public Map<String, Object> selectBoardView(Map<String, Object> map) 
			throws Exception 
	{
		boardDAO.updateHitCnt(map);
		Map<String, Object> view = boardDAO.selectBoardView(map);
		
		return view;
	}

	@Override
	public Map<String, Object> selectPassword(Map<String, Object> map) 
	throws Exception 
	{
		return boardDAO.selectPassword(map);
	}
	
	
	

}

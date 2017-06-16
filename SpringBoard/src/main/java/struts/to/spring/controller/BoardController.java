package struts.to.spring.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import struts.to.spring.common.BoardCommonMap;
import struts.to.spring.service.BoardService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BoardController 
{
	@Resource(name="boardService")
	private BoardService boardService;
	
	//게시글 목록
	@RequestMapping(value="/board/listAction")
	public ModelAndView boardListAll(BoardCommonMap bcm) throws Exception
	{
		//화면에 보여줄 view name
		ModelAndView mav = new ModelAndView("/board/boardList");
		
	    //게시판 목록 쿼리문
		List<Map<String, Object>> list = boardService.selectBoardList(bcm.getMap());
	
		//서비스 클래스에서 처리한 selectBoardList 결과를 "list"라는 영역에 저장
		mav.addObject("list", list);
		
		return mav;
	}
	
	//게시글 상세보기
	@RequestMapping(value="/board/viewAction")
	public ModelAndView boardDetail(BoardCommonMap bcm) throws Exception
	{
		//화면에 보여줄 view name
		ModelAndView mav = new ModelAndView("/board/boardView");
		
		//게시판 상세보기 쿼리문
		Map<String, Object> view = boardService.selectBoardView(bcm.getMap());
		mav.addObject("view", view);
		
		return mav;
	}


	//비밀번호 check form
	@RequestMapping(value="/board/checkForm")
	public ModelAndView pwCheckForm(BoardCommonMap bcm) throws Exception
	{
		ModelAndView mav = new ModelAndView("/board/checkPassword");
        mav.addObject("NO", bcm.get("NO"));
		return mav;
	}
	
	//비밀번호 check 처리
	@RequestMapping(value="/board/checkAction")
	public ModelAndView pwCheckAction(BoardCommonMap bcm) 
			throws Exception
	{
		ModelAndView mav = new ModelAndView();
        
		Map<String, Object> password = boardService.selectPassword(bcm.getMap());

		if(password == null)
		{
			mav.setViewName("/board/checkError");
			return mav;
		}

		   mav.addObject("password", password);
		   mav.setViewName("/board/checkSuccess");		   
		   return mav;
	}

}

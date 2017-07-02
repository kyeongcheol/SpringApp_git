package first.sample.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.common.common.CommandMap;
import first.sample.service.SampleService;

@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());
	
	//컨트롤러에서 서비스 영역 접근
    //Resource 어노테이션을 통해 필요한 빈을 수동으로 등록
	@Resource(name="sampleService")
	private SampleService sampleService;
	
	//게시글 목록
	@RequestMapping(value="/sample/openBoardList.do")
    public ModelAndView openBoardList(CommandMap commandMap) throws Exception
	{
    	ModelAndView mv = new ModelAndView("/sample/boardList");
    	
    	return mv;
    }
	
	@RequestMapping(value="/sample/selectBoardList.do")
    public ModelAndView selectBoardList(CommandMap commandMap) throws Exception
	{
    	ModelAndView mv = new ModelAndView("jsonView");
    	
    	List<Map<String,Object>> list = sampleService.selectBoardList(commandMap.getMap());
    	
    	mv.addObject("list", list);
    	
    	//list에 값이 있으면..
    	if(list.size() > 0)
    	{
    		//TOTAL_COUNT 는 Common_SQL에서 select한 모든 행의 개수
    		mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
    	}
    	
    	else
    	{
    		mv.addObject("TOTAL", 0);
    	}
    	
    	return mv;
    }
	
	//게시글 작성
	//게시글 작성 폼
	@RequestMapping(value="/sample/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception
	{
			ModelAndView mv = new ModelAndView("/sample/boardWrite");
			
			return mv;
	}
	
	//게시글 작성 처리
	@RequestMapping(value="/sample/insertBoard.do")
		
	//HttpServletRequest : 화면에 전송한 모든 데이터는 담겨서 전송
	//모든 텍스트 데이터 뿐만 아니라 화면에서 전송한 파일정보도 함께 담겨있음.
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception
				
	{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
			
		sampleService.insertBoard(commandMap.getMap(), request);
			
		return mv;
	}
	
	//게시글 상세보기
	@RequestMapping(value="/sample/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception
	{
		ModelAndView mv = new ModelAndView("/sample/boardDetail");
			
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
			
		mv.addObject("map", map);		
		return mv;
	}
	
	//게시글 수정
	//게시글 수정 폼
	@RequestMapping(value="/sample/openBoardUpdate.do")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception
	{
			ModelAndView mv = new ModelAndView("/sample/boardUpdate");
			
			Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
			
			mv.addObject("map", map);
			
			return mv;
	}
		
	//게시글 수정 처리
	@RequestMapping(value="/sample/updateBoard.do")
	public ModelAndView updateBoard(CommandMap commandMap) throws Exception
	{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail.do");
			
		sampleService.updateBoard(commandMap.getMap());
			
		mv.addObject("IDX", commandMap.get("IDX"));
		return mv;
	}
	
	//게시글 삭제
	@RequestMapping(value="/sample/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception
	{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		
		sampleService.deleteBoard(commandMap.getMap());
		
		return mv;
	}
}

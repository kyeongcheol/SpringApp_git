package first.sample.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import first.common.util.FileUtils;
import first.sample.dao.SampleDAO;

@Service("sampleService")
public class SampleServiceImpl implements SampleService 
{
	Logger log = Logger.getLogger(this.getClass());
	
	//서비스에서 DAO 영역접근
	@Resource(name="sampleDAO")
	private SampleDAO sampleDAO;
	
	//서비스에서 파일 업로드 영역 접근
	@Resource(name="fileUtils")
	private FileUtils fileUtils;

	//게시글 목록
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) 
			throws Exception 
	{
       return sampleDAO.selectBoardList(map);       
	}

	//게시글 상세보기
	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) 
			throws Exception
	{
		sampleDAO.updateHitCnt(map);
		Map<String, Object> resultMap = sampleDAO.selectBoardDetail(map);
		return resultMap;
	}
	
	//게시글 작성
	@Override
	public void insertBoard(Map<String, Object> map, HttpServletRequest request) 
			throws Exception 
	{
		sampleDAO.insertBoard(map);
		
		//HttpServletRequest 자체로는 데이터를 조작하는데 어려움이 있기 때문에 
		//request를 MultipartHttpServletRequest 형식으로 변환
		MultipartHttpServletRequest multipartHttpServletRequest
		  = (MultipartHttpServletRequest) request;
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		
		while(iterator.hasNext())
		{
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			//전송된 파일이 있으면..
			if(multipartFile.isEmpty() == false)
			{
				 log.debug("------------- file start -------------");
				 log.debug("name : " +multipartFile.getName());
				 log.debug("filename : " +multipartFile.getOriginalFilename());
				 log.debug("size : " +multipartFile.getSize());
				 log.debug("-------------- file end --------------\n");

			}
		}
		//파일 클래스에서 반환되는 list를 list에 저장
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);
		//반환되는 list를 sampleDAO insertFile 메서드에 저장
		for(int i=0, size=list.size(); i<size; i++)
		{
			sampleDAO.insertFile(list.get(i));
		}
	}

	//게시글 수정
	@Override
	public void updateBoard(Map<String, Object> map) throws Exception 
	{
		sampleDAO.updateBoard(map);
		
	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception 
	{
       sampleDAO.deleteBoard(map);
		
	}
	
	
	
	
	
	
	

}

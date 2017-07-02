package first.common.util;

//공통적으로 가져다 쓸 파일 업로드 클래스
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//component 자동 스캔 시 fileUtils 이름으로 빈 객체 등록
@Component("fileUtils")
public class FileUtils 
{
	//파일 경로 설정
	private static final String filePath = "C:\\java\\upload\\";
	
	public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map,
			HttpServletRequest request) throws Exception
	{
		MultipartHttpServletRequest multipartHttpServletRequest
		 = (MultipartHttpServletRequest) request;
		
		//request 이용하여 fileName 가져오기
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null; //원래 파일 이름
		String originalFileExtension = null; //원래 파일 확장자
		String storedFileName = null; //저장할 파일 이름
		
		//사용자가 전송한 파일 정보를 담을 list
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> listMap = null;
		
		//map에서 글 번호를 가져와 boardIdx에..
		//ServiceImpl 영역에서 전달해준 map에서 신규 생성되는 게시글의 번호를 받아옴
		String boardIdx = (String)map.get("IDX");
		
		File file = new File(filePath);
		//파일을 저장할 경로에 해당폴더가 없으면..
		if(file.exists() == false)
		{
			//파일 디렉토리 생성
			file.mkdirs();
		}
		
		//전송된 파일이름들이 계속 잇으면...
		while(iterator.hasNext())
		{
			//계속해서 파일들을 multipartFile 객체에 넣어줌..
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			
			//그 파일들이 존재하면..
			if(multipartFile.isEmpty() == false)
			{
				//원래 파일 이름을 가져와 객체에 넣어줌
				originalFileName = multipartFile.getOriginalFilename();
				//원래 파일 확장자(.이후)를 가져와 객체에 넣어줌
				originalFileExtension = 
						originalFileName.substring(originalFileName.lastIndexOf("."));
				
				//저장할 파일 이름 정의
				storedFileName = CommonUtils.getRandomString() + originalFileExtension;
				
				file = new File(filePath + storedFileName);
				//새로 저장할 파일을 서버에 저장
				multipartFile.transferTo(file);
				
				listMap = new HashMap<String, Object>();
				
				//listMap에 글번호, 원래 파일 이름, 저장된 파일이름, 파일 사이즈 저장
				listMap.put("BOARD_IDX", boardIdx);
				listMap.put("ORIGINAL_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
			}
		} //end of while
		
		return list;
	}

}

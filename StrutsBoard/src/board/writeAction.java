package board;

import com.opensymphony.xwork2.ActionSupport;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.util.*;
import java.io.Reader;
import java.io.IOException;

import java.io.File;
import org.apache.commons.io.FileUtils;

public class writeAction extends ActionSupport
{
	public static Reader reader; //파일 스트림을 위한 reader
    public static SqlMapClient sqlMapper; //SqlMapClient API를 사용하기 위한 sqlMapper 객체
    
    private boardVO paramClass; //파라미터를 저장할 객체
    private boardVO resultClass; //쿼리 결과 값을 저장할 객체
    
    private int currentPage; //현재 페이지
    
    private int no;
    private String subject;
    private String name;
    private String password;
    private String content;
    private String file_orgname; //업로드 파일의 원래 이름
    private String file_savname; //서버에 저장할 업로드 파일의 이름. 고유 번호로 구분
    Calendar today = Calendar.getInstance(); //오늘 날짜 구하기
    
    private File upload; //파일 객체
    private String uploadContentType; //컨텐츠 타입
    private String uploadFileName; //파일 이름
    private String fileUploadPath="C:\\Java\\upload\\"; //업로드 경로
    
    //답변 관련 변수
    private int ref;
    private int re_step;
    private int re_level;
    
    boolean reply = false;
    
    //생성자 
    public writeAction() throws IOException
    {
    	//sqlMapConfig.xml 파일의 설정내용을 가져온다.
    	reader = Resources.getResourceAsReader("sqlMapConfig.xml");
    	//sqlMapConfig.xml의 내용을 적용한 sqlMapper 객체 생성
    	sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader); 
    	reader.close();
    }
    
    //등록 폼
    public String form() throws Exception
    {
    	return SUCCESS;
    }
    
    //답변 글 reply() 메서드
    public String reply() throws Exception
    {
    	reply = true;
    	resultClass = new boardVO();
    	
    	resultClass = (boardVO)sqlMapper.queryForObject("selectOne", getNo());
    	resultClass.setSubject("[답변]" + resultClass.getSubject());
    	resultClass.setPassword("");
    	resultClass.setName("");
    	resultClass.setContent("");
    	resultClass.setFile_orgname(null);
    	resultClass.setFile_savname(null);
    	
    	return SUCCESS;
    }
    
    //게시판 WRITE 액션
    public String execute() throws Exception
    {
    	//파라미터와 리절트 객체 생성
    	paramClass = new boardVO();
    	resultClass = new boardVO();
    	
    	
    	//일반 글이면
    	if(ref==0)
    	{
    		paramClass.setRe_step(0);
    		paramClass.setRe_level(0);
    	}
    	
    	//답변글이면
    	else
    	{
    		
    		paramClass.setRef(getRef());
    		paramClass.setRe_step(getRe_step());
    		sqlMapper.update("updateReplyStep", paramClass);
    		
    		paramClass.setRe_step(getRe_step() + 1);
    		paramClass.setRe_level(getRe_level()+ 1);
    		paramClass.setRef(getRef());
    	}
    	
    	//등록할 항목 설정(업로드 안할 경우)
    	paramClass.setSubject(getSubject());
    	paramClass.setName(getName());
    	paramClass.setPassword(getPassword());
    	paramClass.setContent(getContent());
    	paramClass.setRegdate(today.getTime());
    	
    	if(ref==0)
    	//등록 쿼리 수행 , 일반글일 경우
    	sqlMapper.insert("insertBoard", paramClass);
    	
    	else
    	  sqlMapper.insert("insertBoardReply", paramClass);
    	
    	
    	//첨부파일을 선택했다면 파일을 업로드
    	if(getUpload() != null)
    	{
    		//등록한 글 번호 가져오기. 한 줄 가져올때 queryForObject
    		//여러 줄 가져올 때 queryForList
    		resultClass = (boardVO)sqlMapper.queryForObject("selectLastNo");
    		
    		//실제 서버에 저장될 파일 이름과 확장자 설정.
    		String file_name = "file_" + resultClass.getNo();
            String file_ext = getUploadFileName().substring(
            		getUploadFileName().lastIndexOf('.') + 1,
            		getUploadFileName().length());
            
            //서버에 파일 저장
            File destFile = new File(fileUploadPath + file_name + "." + file_ext);
            FileUtils.copyFile(getUpload(), destFile);
            
            //파일 정보 파라미터 설정 (업로드 할 경우 - 컬럼 3개 추가)
            paramClass.setNo(resultClass.getNo());
            paramClass.setFile_orgname(getUploadFileName()); //원래 파일 이름
            paramClass.setFile_savname(file_name + "." + file_ext); //서버에 저장한 파일 이름
            
            //파일 정보 업데이트. update 쿼리 설정 부분 id 값 : updateFile
            sqlMapper.update("updateFile", paramClass);
            
    	}
    	
    	return SUCCESS;
    	
    }
    public Calendar getToday() {
		return today;
	}

	public void setToday(Calendar today) {
		this.today = today;
	}

	public boardVO getParamClass() {
		return paramClass;
	}

	public void setParamClass(boardVO paramClass) {
		this.paramClass = paramClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getFile_orgName() {
		return file_orgname;
	}

	public void setFile_orgName(String file_orgname) {
		this.file_orgname = file_orgname;
	}

	public String getFile_savName() {
		return file_savname;
	}

	public void setFile_savName(String file_savname) {
		this.file_savname = file_savname;
	}

	public boardVO getResultClass() {
		return resultClass;
	}

	public void setResultClass(boardVO resultClass) {
		this.resultClass = resultClass;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getRe_step() {
		return re_step;
	}

	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

	public boolean isReply() {
		return reply;
	}

	public void setReply(boolean reply) {
		this.reply = reply;
	}
	
	
}

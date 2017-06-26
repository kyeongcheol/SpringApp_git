<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>게시글 작성</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/common/css.css'/>" />

<script type="text/javascript">
function validation()
{
  var frm = document.forms(0);
  
  if(frm.subject.value == "")
  {
	  alert("제목을 입력해주세요.");
	  return false;
  }
  
  else if(frm.name.value == "")
  {
	  alert("이름을 입력해주세요.");
	  return false;
  }
  
  else if(frm.content.value == "")
  {
	  alert("내용을 입력해주세요.");
	  return false;
  }
  
  return true;
	
}
</script>
</head>
<body>
 <table width="600" border="0" cellspacing="0" cellpadding="2">
 <tr>
  <td align="center"><h2>스트럿츠 2 게시판</h2>
  </td>
  </tr>
 </table>

  <form action="/spring/board/modifyAction.action" method="post">
  <input type="hidden" id="NO" name="NO" value="${view.NO}"/>

       <table width="600" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right" colspan="2"><font color="#FF0000">*</font>는 필수 입력사항입니다.</td>
        </tr>
        
        <tr bgcolor="#777777">
          <td height="1" colspan="2"></td>
        </tr>
				
        <tr>
          <td width="100" bgcolor="#F4F4F4"><font color="#FF0000">*</font>  제목</td>
          <td width="500" bgcolor="#FFFFFF">
            <input type="text" id="SUBJECT" name="SUBJECT" theme="simple" value="${view.SUBJECT}" cssStyle="width:370px" maxlength="50"/>
          </td>
        </tr>
        							
        <tr bgcolor="#777777">
          <td height="1" colspan="2"></td>
        </tr>
        
        <tr>
          <td bgcolor="#F4F4F4"><font color="#FF0000">*</font>  이름 </td>
          <td bgcolor="#FFFFFF">
            <input type="text" id="NAME" name="NAME" theme="simple" value="${view.NAME}" cssStyle="width:100px" maxlength="20"/>
          </td>
        </tr>
        <tr bgcolor="#777777">
          <td height="1" colspan="2"></td>	
        </tr>
 
        <tr>
          <td bgcolor="#F4F4F4"><font color="#FF0000">*</font>  비밀번호 </td>
          <td bgcolor="#FFFFFF">
            <input type="text" id="PASSWORD" name="PASSWORD" theme="simple" value="${view.PASSWORD}" cssStyle="width:100px" maxlength="20"/>
          </td>
        </tr>
        <tr bgcolor="#777777">
          <td height="1" colspan="2"></td>	
        </tr>
        
	  <tr>
          <td bgcolor="#F4F4F4"><font color="#FF0000">*</font>  내용 </td>
          <td bgcolor="#FFFFFF">
           <textarea rows="10" cols="50" title="내용" id="CONTENT" name="CONTENT">${view.CONTENT}</textarea>
          </td>
        </tr>
        <tr bgcolor="#777777">
          <td height="1" colspan="2"></td>
        </tr>
        
      <tr bgcolor="#777777">
          <td height="1" colspan="2"></td>	
        </tr>
        
        <tr>
          <td height="10" colspan="2"></td>
        </tr>
        
        
        <tr>
          <td align="right" colspan="2">
          	<input name="submit" type="submit" value="작성완료" class="inputb">
            <input name="list" type="button" value="목록" class="inputb" 
			onClick="javascript:location.href='/spring/board/listAction.action'">
          </td>
        </tr>

    </table>
    </form>

  </body>
</html>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<title>비밀번호 확인</title>
<link rel="stylesheet" href="/StrutsBoard/board/common/css.css" type="text/css">
<script type="text/javascript">

function locationURL()
{
  if(window.name=='modify')
	  window.opener.parent.location.href='modifyForm.action?no=<s:property value="no"/>&currentPage=<s:property value="currentPage"/>';

  else if(window.name=='delete')
  {
	  alert("삭제되었습니다.");
	  window.opener.parent.location.href='deleteAction.action?no=<s:property value="no"/>&currentPage=<s:property value="currentPage"/>';
		  
  }
  
  window.close();
}
</script>
<body>

<script>locationURL()</script>
</body>
</html>
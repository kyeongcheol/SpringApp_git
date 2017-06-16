<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<title>비밀번호 확인</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/common/css.css'/>" />
<script type="text/javascript">

function locationURL()
{
  if(window.name=='modify')
	  window.opener.parent.location.href='modifyForm.action?NO=${password.NO}';

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
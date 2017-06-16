<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<title>비밀번호 확인</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/common/css.css'/>" />
</head>

<body>
<h2>비밀번호 확인</h2>
<form action="/spring/board/checkAction.action" method="post">
<input type="hidden" id="NO" name="NO" value="${NO}">
<table width="250" border="0" cellspacing="0" cellpadding="0">

 <tr bgcolor="#777777">
        <td height="1" colspan="2"></td>
      </tr>
  
  <tr>
    <td width="100" bgcolor="#F4F4F4">비밀번호 입력</td>
    <td width="150" bgcolor="#FFFFFF">
    &nbsp;&nbsp;<input type="text" id="PASSWORD" name="PASSWORD"
    theme="simple" cssStyle="width:100px" maxlength="20"/>
    &nbsp;&nbsp;<input name="submit" type="submit" value="확인" class="inputb">
    </td>
    </tr>
  
  <tr bgcolor="#777777">
        <td height="1" colspan="2"></td>
      </tr>
</table>
</form>
</body>
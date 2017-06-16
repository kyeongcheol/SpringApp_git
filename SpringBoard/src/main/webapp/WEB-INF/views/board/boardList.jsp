<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>Struts2 to Spring Board</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/common/css.css'/>" />
</head>
<body>
  	<table width="600" border="0" cellspacing="0" cellpadding="2">
  		<tr>
  			<td align="center"><h2>스트럿츠2 게시판</h2></td>
  		</tr>
  		<tr>
  			<td height="20"></td>
  		</tr>
  	</table>
  	
  	<table width="600" border="0" cellspacing="0" cellpadding="2">
	    <tr align="center" bgcolor="#F3F3F3">
      		<td width="50">번호</td>
		    <td width="350">제목</td>
            <td width="70">글쓴이</td>
            <td width="80">날짜</td>
		    <td width="50">조회</td>
      	</tr>
      	<tr bgcolor="#777777">
           <td height="1" colspan="5"></td>
      	</tr>

        <%--list의 길이가 0 이상이면 --%>
        <c:if test="${fn:length(list) > 0}">
        
        <c:forEach items="${list}" var="board">
        <tr bgcolor="#FFFFFF"  align="center">
         <td>${board.NO}</td>
         <td align="center">
         <a href="viewAction.action?NO=${board.NO}">${board.SUBJECT}</a></td>
         <td align="center">${board.NAME}</td>
         <td align="center">${board.REGDATE}</td>
         <td>${board.READHIT}</td>
         <tr bgcolor="#777777">
           <td height="1" colspan="5"></td>
      	 </tr>
        </c:forEach>
        </c:if>

        <%--list의 길이가 0 이하이면 --%>
        <c:if test="${fn:length(list) <= 0}">
        <tr bgcolor="#FFFFFF"  align="center">
		<td colspan="5">등록된 게시물이 없습니다.</td>
        </tr>						
	      <tr bgcolor="#777777">
      		<td height="1" colspan="5"></td>
    	   </tr>
        </c:if>
    
    <tr align="right">
       <td colspan="5">
       <input type="button" value="글쓰기" class="inputb" 
	   onClick="javascript:location.href='writeForm.action';">
	   </td>
    </tr>
    </table>
</body>
</html>
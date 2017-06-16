<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>상세보기</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/common/css.css'/>" />

<script type="text/javascript">
	function open_win_noresizable(url, name)
	{
		var oWin = window.open(url, name, "scrollbars=no, status=no, resizable=no, width=300, height=150");
		
	}
</script>

</head>
<body>
	<table width="600" border="0" cellspacing="0" cellpadding="2">
	<tr>
		<td align="center"><h2>스트럿츠 게시판</h2></td>
	</tr>
	</table>

	<table width="600" border="0" cellspacing="0" cellpadding="0">
		<tr bgcolor="#777777">
			<td colspan="2" height="1"></td>
		</tr>
		<tr>
			<td width="100">번호</td>
			<td width="500">
				${view.NO}
			</td>
		</tr>
		<tr>
			<td width="100">제목</td>
			<td width="500">
				${view.SUBJECT}
			</td>
		</tr>
		<tr>
			<td width="100">이름</td>
			<td width="500">
				${view.NAME}
			</td>
		</tr>
		
		<tr>
			<td width="100">내용</td>
			<td width="500">
				${view.CONTENT}
			</td>
		</tr>
		<tr>
			<td width="100">조회수</td>
			<td width="500">
				${view.READHIT}
			</td>
		</tr>
		<tr>
			<td width="100">등록날짜</td>
			<td width="500">
				${view.REGDATE}
			</td>
		</tr>
		<tr>
			<td width="100">첨부파일</td>
			<td width="500">
				&nbsp;&nbsp;
			${view.FILE_ORGNAME}
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td colspan="2" height="1"></td>
		</tr>
		<tr>
			<td colspan="2" height="10"></td>
		</tr>
		
		<input name="list" type="button" value="목록" class="inputb" 
		onClick="javascript:location.href='listAction.action'">
		
		<input name="list" type="button" value="수정하기" class="inputb" 
		onClick="javascript:open_win_noresizable('checkForm.action?NO=${view.NO}','modify')">
		
		<input name="list" type="button" value="삭제하기" class="inputb" 
		onClick="javascript:open_win_noresizable('checkForm.action?NO=${view.NO}','delete')">
		
		
</table>
</body>
</html>
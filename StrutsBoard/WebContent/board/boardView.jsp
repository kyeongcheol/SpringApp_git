<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>��Ʈ���� 2 �Խ���</title>
<link rel="stylesheet" href="/struts2_board/board/common/css/css.css" type="text/css">

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
		<td align="center"><h2>��Ʈ���� �Խ���</h2></td>
	</tr>
	</table>

	<table width="600" border="0" cellspacing="0" cellpadding="0">
		<tr bgcolor="#777777">
			<td colspan="2" height="1"></td>
		</tr>
		<tr>
			<td width="100">��ȣ</td>
			<td width="500">
				<s:property value="resultClass.no" />
			</td>
		</tr>
		<tr>
			<td width="100">����</td>
			<td width="500">
				<s:property value="resultClass.subject" />
			</td>
		</tr>
		<tr>
			<td width="100">�̸�</td>
			<td width="500">
				<s:property value="resultClass.name" />
			</td>
		</tr>
		
		<tr>
			<td width="100">����</td>
			<td width="500">
				${resultClass.content }
			</td>
		</tr>
		<tr>
			<td width="100">��ȸ��</td>
			<td width="500">
				<s:property value="resultClass.readhit" />
			</td>
		</tr>
		<tr>
			<td width="100">��ϳ�¥</td>
			<td width="500">
				<s:property value="resultClass.regdate" />
			</td>
		</tr>
		<tr>
			<td width="100">÷������</td>
			<td width="500">
				&nbsp;&nbsp;
				<s:url id="download" action="fileDownloadAction">
					<s:param name="no">
						<s:property value="no" />
					</s:param>
				</s:url>
				
				<s:a href="%{download}">
					<s:property value="resultClass.file_orgname" />
				</s:a>
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td colspan="2" height="1"></td>
		</tr>
		<tr>
			<td colspan="2" height="10"></td>
		</tr>

		<%-- <!-- ------��� ���� -->
		<tr>
			<td colspan="2" height="10">
				<form action="writeCommentAction.action" method="post">
					<table>
						<tr>
							<td width="170">
								��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� <s:textfield name="name" theme="simple" value="" cssStyle="width:100px" maxlength="20" /><br>
								��й�ȣ <s:textfield name="password" theme="simple" value="" cssStyle="width:100px" maxlength="20" />
							</td>
							<s:hidden name="originno" value="%{resultClass.no}" />
					<!-- !!!!!!!!!!!!!!!!!!!!!!!!����!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
							<s:hidden name="no" value="%{resultClass.no}" />
							<s:hidden name="currentPage" value="%{currentPage}" />
					<!-- !!!!!!!!!!!!!!!!!!!!!!!!����!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
							<td align="left">
								<s:textarea name="content" theme="simple" value="" cols="60" rows="3" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input name="submit" type="submit" value="�ۼ��Ϸ�" class="inputb">
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	
		<tr bgcolor="#777777">
			<td colspan="2" height="1"></td>
		</tr>
	
	
		<s:iterator value="commentlist" status="stat">
		<tr>
			<td height="10" width="130" align="center">
				<s:property value="name" /><br>
				<s:property value="regdate" /><br><br>
			</td>
			<td>
			<!-- @@@@@@@@@@@@�ڸ�Ʈ ����@@@@@@@@@@@@ -->
				<s:property value="content" /> 
				<a href="javascript:open_win_noresizable('checkForm2.action?no=<s:property value="no" />&originno=<s:property value="originno" />&currentPage=<s:property value="currentPage" />','cdelete')">x</a>
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td colspan="2" height="1"></td>
		</tr>
		</s:iterator>
		<tr>
			<td colspan="2" height="10">
				<s:if test="commentlist.size() <= 0">
				<!-- ��۾��� -->
			</td>
		</tr>
				</s:if>	
	
		<tr bgcolor="#777777">
			<td colspan="2" height="1"></td>
		</tr>
		<tr>
			<td colspan="2" height="10"></td>
		</tr>
	
	    <!-- ��� �� --> --%>
		<tr>
			<td colspan="2" align="right">
				<s:url id="modifyURL" action="modifyForm">
					<s:param name="no">
						<s:property value="no" />
					</s:param>
				</s:url>
				<s:url id="deleteURL" action="deleteAction">
					<s:param name="no">
						<s:property value="no" />
					</s:param>
				</s:url>
				<input name="list" type="button" value="�亯�ޱ�" class="inputb" onClick="javascript:location.href='replyForm.action?no=<s:property value="no" />'">
				<input name="list" type="button" value="����" class="inputb" onClick="javascript:open_win_noresizable('checkForm.action?no=<s:property value="resultClass.no" />&currentPage=<s:property value="currentPage" />','modify')">
				<input name="list" type="button" value="����" class="inputb" onClick="javascript:open_win_noresizable('checkForm.action?no=<s:property value="resultClass.no" />&currentPage=<s:property value="currentPage" />','delete')">
				<input name="list" type="button" value="���" class="inputb" onClick="javascript:location.href='listAction.action?currentPage=<s:property value="currentPage" />'">
			</td>
		</tr>
	</table>
</body>
</html>
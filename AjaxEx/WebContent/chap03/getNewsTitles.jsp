<%@ page contentType = "text/plain; charset=euc-kr" %>
<%
	String[] titles = {
		"������ �Ϻ������� �Է��� 3�� ����",
		"��Ʋ ���� '������ 12�Ϻ��� ���� ����'",
		"����ȣ �輱�� �ù���� ���� ȣ��"
	};
	for (int i = 0 ; i < titles.length ; i++) {
%>
<% if (i == 0) { %><strong><% } %>
<%= titles[i] %>
<% if (i == 0) { %></strong><% } %>
<br/>
<%
	}
%>
<%@ page contentType = "text/plain; charset=euc-kr" %>
<%
	request.setCharacterEncoding("utf-8");
	String value = request.getParameter("value");
	String type = request.getParameter("type");
	
	if (value.equals("001")) {
%>
[
	{text: "���α�", value: "001001"},
	{text: "���Ǳ�", value: "001002"},
	{text: "���빮��", value: "001003"}
]
<%
	} else if (value.equals("002")) {
%>
[
	{text: "������", value: "002001"},
	{text: "����", value: "002002"},
	{text: "��籸", value: "002003"}
]
<%
	} else if (value.equals("003")) {
%>
[
	{text: "����", value: "003001"},
	{text: "������", value: "003002"},
	{text: "�����", value: "003003"}
]
<%
	}
%>
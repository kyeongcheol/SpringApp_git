<%@ page pageEncoding="euc-kr" %>
<%@ page import = "org.apache.commons.httpclient.HttpClient" %>
<%@ page import = "org.apache.commons.httpclient.methods.GetMethod" %>
<%@ page import = "org.apache.commons.httpclient.HttpStatus" %> 
<%
	request.setCharacterEncoding("utf-8");
	String url = "http://openapi.naver.com/search";
	String queryString = request.getQueryString(); 
	
	HttpClient client = new HttpClient();
	GetMethod method = new GetMethod(url);
	
	method.setQueryString(queryString);
	try {
		int statusCode = client.executeMethod(method);
		
		out.clearBuffer();
		response.reset();
		
		response.setStatus(statusCode);
		
		if (statusCode == HttpStatus.SC_OK) {
			String result = method.getResponseBodyAsString();
			response.setContentType("text/xml; charset=utf-8");
			out.println(result);
		}
	} finally {
		if (method != null) method.releaseConnection();
	}
%>
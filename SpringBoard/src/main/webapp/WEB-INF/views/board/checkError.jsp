<%@ page contentType="text/html; charset=utf-8" %>

<html>
<head>
<title>비밀번호 오류</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/common/css.css'/>" />
<script type="text/javascript">
function ErrorMessage()
{
	alert("비밀번호가 틀립니다.");
	history.go(-1);
}
</script>
</head>

<body>
<script>ErrorMessage()</script>
</body>
</html>
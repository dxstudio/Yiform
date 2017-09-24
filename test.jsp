<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cn.yiban.open.Authorize" %>
<%
		String appKey  = "应用的AppID";
		String appSecret = "应用的AppSecret";
		String callbackurl = " http://127.0.0.1:8080/yiban/test.jsp";
%>

<%
		Authorize au = new Authorize(appKey, appSecret);
	%>
<%
		String url = au.forwardurl(callbackurl, "test", Authorize.DISPLAY_TAG_T.WEB);
		response.sendRedirect(url);
	%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
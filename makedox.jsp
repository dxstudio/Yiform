<% request.setCharacterEncoding("UTF-8");%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page import="test.Xwpfchange" %>


<jsp:useBean id ="maker2" class="test.Xtest"  scope="session"></jsp:useBean>
<jsp:useBean id ="map1" class="test.makemap"  scope="session"></jsp:useBean>
<jsp:setProperty name ="maker2" property="*"/>
<!DOCTYPE html>

<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%      
    String path=request.getSession().getServletContext().getRealPath("/");
    System.out.println("现在操作的是----------"+request.getParameter("filename"));
    System.out.println(request.getSession().getServletContext().getRealPath("/"));
    String[] a=maker2.testTemplateWrite(map1.transToMAP(request.getParameterMap()),request.getParameter("filename"));
    
    session.setAttribute("name",a[0]);
    session.setAttribute("filepath",a[1]);
%>
 <jsp:forward page="out.jsp"></jsp:forward>
</body>
</html>
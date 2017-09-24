<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html> 
<head> 
<meta charset="UTF-8"> 
<title>Insert title here</title> 
</head> 
<body> 
<h1>通过链接下载文件</h1> 
<a href="/word/download/2.doc">压缩包</a> 
<a href="/word/download/1.png">图片</a> 
<h1>通过servlet程序下载文件</h1> 
<a href="/day06/ServletDownload?filename=cors.zip">压缩包</a> 
<a href="/word/ServletDownload?filepath=D:/Apache Software Foundation/Tomcat 8.0/webapps/word/download/2.docx&filename=安琪">图片</a> 
</body> 
</html> 

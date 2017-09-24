<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>   
<style>
   a{
      text-decoration: none;
      color: #222222;
   }
   
   button{
      font-size:20px;
      border-radius:5px;
      height:65px;
      width:200px;
   }
   
   button:hover{
      color:#ffffff;
      background-color: #333333;
   }
</style>
</head>
<body>
     <a href="/word/ServletDownload?filepath=D:/Apache Software Foundation/Tomcat 8.0/webapps/word/download/<% out.println(session.getAttribute("name")+"/"); %><% out.println(session.getAttribute("filepath")+".docx"); %>&filename=<% out.println(session.getAttribute("filepath")); %>">
         <button>点击下载<% out.println(session.getAttribute("filepath")+".docx"); %></button>
     </a> <br><br>
     <a href="index.html">
         <button>返回首页</button>
     </a>    
</body>
</html>
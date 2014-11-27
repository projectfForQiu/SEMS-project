<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	
	<style type="text/css" media="screen">
      html,body {
	    height: 100%;
        }

body {
	margin: 0;
	padding: 0;
	overflow: auto;
	text-align: center;
	background-color: #f6f9fe;
}

#flashContent {
	display: none;
}
</style>
	
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	


  </head>
  
  <body>
    <a1>工具测试</a1>
	<br/>
	<a1>请输入工具名</a1>
	<form method="get" action="runTool" target="_blank">
		工具名:<input type="text" name="toolId" id="toolId">* <br />
			 <input	type="submit" value="提交">
	</form>
  </body>
</html>

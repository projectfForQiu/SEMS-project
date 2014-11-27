<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css" media="screen">
		html,body{
			height:100%;
		}
		
		body{
			margin:0;
			padditn:0;
			overflow:0;
			text-align:center;
			backgroud-color:#f6f9fe;
		}
		
		#flashContent{
			display:none;
		}
	</style>
  </head>
  
  <body>
    <h1>测试工具</h1>
    <h1>请输入工具名</h1>
    <form method="get" action="runTool" target="_blank" >
    	工具名：<input type="text" name="toolId" id="toolId" />
    	<br/><input type="submit" value="提交" >    
    </form>
  </body>
</html>

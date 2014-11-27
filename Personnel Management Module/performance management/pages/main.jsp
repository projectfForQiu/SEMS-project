<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工绩效管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="tools/${toolId}/js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="tools/${toolId}/js/themes/icon.css">
	<script type="text/javascript" src="tools/${toolId}/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="tools/${toolId}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="tools/${toolId}/js/easyui-lang-zh_CN.js"></script>
	<!-- 引入外部自定义js文件 -->
    <script type="text/javascript" src="tools/${toolId}/js/control.js"></script>
    
    <script type="text/javascript">
		var toolId = '${toolId}';//定义全局变量，用于把toolId的值传给js文件
	</script>

	<style type="text/css">
	a {
		text-decoration: none
	}
	td , input {
	    font-family: "微软雅黑";
	    font-size: 13px;
	}
	</style>

  </head>
  
  <body class="easyui-layout" bgcolor="#EFF0FF" leftmargin="0"
  	topmargin="0" marginwidth="0" marginheight="0" ondrag="JavaScript:return false" >
    
    <!-- 顶部 -->
	<div region="north" id="north"
		style="height:70px; padding:5px 0 0 10px; background:#7389B5 url(tools/${toolId}/images/header.png) no-repeat; color:#fff; font-weight:bold;">
		<div style="margin-top: 20px;"></div>
		<font color="#2E84BA" size=5 style="margin-left: 460px;margin-top: 30px;">—— 员工绩效管理工具</font>
		<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-help"
			onclick="showhelp()" style="float:right;margin-top: 15px;">帮助说明</a>
	</div>
	
	<!-- 底部 -->
	<div region="south" id="south"
		style="height:25px;lind-height:25px;padding:3px 0 0 5px;background:#EFF0FF;overflow: hidden;">
		<div style="text-align: center; font-family: 微软雅黑">版权所有：广州市飞元信息科技有限公司</div>
	</div>
	
	<!--中部 -->
	<div region="center" id="center">
		<table id="tabs"></table>
	</div>
	
	<!-- 编辑dialog -->
	<div>
		<div id="EmployeePerformance" class="easyui-dialog" title="material"
			data-options="modal:true,iconCls:'icon-save'" closed="true"
			style="padding-top: 20px;">
			<form id="EmployeePerformanceForm" name="SmCostForm">
				<input name="orderid" id="orderid" type="hidden"/>
				<input name="contactid" id="contactid" type="hidden"/>
				<table align="center">
					<tr>
						<td align="right">员工编号</td>
						<td align="right">
						   <input id="evalue_no" name="evalue_no" />
						</td>
						<td align="right">评分日期</td>
						<td align="right">
						   <input id="evalue_date" name="evalue_date" />
						</td>
					</tr>
					<tr>
						<td align="right">业绩分数</td>
						<td align="right">
						   <input id="workPerformance" name="workPerformance" />
						</td>
						<td align="right">能力分数</td>
						<td align="right">
						   <input id="qualityOfWork" name="qualityOfWork" />
						   <!-- <select id="contentment" name="contentment" style="width: 153px;"></select> -->
						</td>					    
					</tr>
					
					<tr>
						<td align="right">态度分数</td>
						<td align="right">
						   <input id="workAttitude" name="workAttitude" />
						</td>	
						<td align="right">技巧分数</td>
						<td align="right">
						   <input id="workSkills" name="workSkills" >
						</td>				    
					</tr>
					
					<tr>
						<td align="right">其他分数</td>
						<td align="right">
						   <input id="itemFiveScore" name="itemFiveScore" />
						</td>					    
					</tr>
					<tr>
						<td align="right">绩效备注</td>
						<td align="right">
						  <textarea id="demo" name="demo" ></textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 查询工具栏 -->
	<div id="finditem" style="margin-top: 3px; margin-left: 20px;">
	<input id="kword" name="kword" class="easyui-searchbox" searcher="qq" prompt="  查询内容" menu="#mm"/>
	</div>
	<div id="mm" style="width:120px"> 
       <div name="employee_name" iconCls="icon-ok">员工编号</div> 
    </div> 
    
    <!-- 帮助工具栏 -->
    <div id="help" class="easyui-dialog" title="help"
			data-options="modal:true,iconCls:'icon-help'" closed="true" style="padding-top: 20px;">
        <div style="font-family: 微软雅黑; margin-left: 15px; margin-right: 15px;">
        <font size="3">员工绩效的详细情况。</font></div>
    </div>
    
  </body>
</html>

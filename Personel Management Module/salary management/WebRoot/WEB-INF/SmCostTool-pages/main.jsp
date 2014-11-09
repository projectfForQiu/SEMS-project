<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工工资管理</title>
<link rel="stylesheet" type="text/css" href="tools/${toolId}/js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="tools/${toolId}/js/themes/icon.css">
	<script type="text/javascript" src="tools/${toolId}/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="tools/${toolId}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="tools/${toolId}/js/easyui-lang-zh_CN.js"></script>
	<!-- 引入外部自定义js文件 -->
    <script type="text/javascript" src="tools/${toolId}/js/control.js"></script>
    <script type="text/javascript" src="tools/${toolId}/js/print.js"></script>
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



<!-- easyui布局 -->
<body class="easyui-layout" bgcolor="#EFF0FF" leftmargin="0"
	topmargin="0" marginwidth="0" marginheight="0"
	ondrag="JavaScript:return false" class="easyui-layout">

	<!-- 顶部 -->
	<div region="north" id="north"
		style="height:70px; padding:5px 0 0 10px; background:#7389B5 url(tools/${toolId}/images/header.png) no-repeat; color:#fff; font-weight:bold;">
		<div style="margin-top: 20px;"></div>
		<font color="#2E84BA" size=5 style="margin-left: 460px;margin-top: 30px;">—— 员工工资管理工具</font>
		<a href="#"
			class="easyui-linkbutton" plain="true" iconCls="icon-help"
			onclick="showhelp()"
			style="float:right;margin-top: 15px;"">帮助说明</a>
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
	
	<div>
		<div id="SmCost" class="easyui-dialog" title="material"
			data-options="modal:true,iconCls:'icon-save'" closed="true"
			style="padding-top: 20px;">
			<form id="SmCostForm" name="SmCostForm">
				<input name="orderid" id="orderid" type="hidden"/>
				<input name="contactid" id="contactid" type="hidden"/>
				<table align="center">
					<tr>
						<td align="right">员工编号</td>
						<td align="right">
						   <input id="employee_no" name="employee_no" onblur="zonjia()"/>
						</td>
						<td align="right">基础工资</td>
						<td align="right">
						   <input id="basic_salary" name="basic_salary" onblur="zonjia()"/>
						</td>
					</tr>

					<tr>
						<td align="right">工作表现工资</td>
						<td align="right">
						   <input id="performance_salary" name="performance_salary" onblur="zonjia()"/>
						</td>
						<td align="right">考勤工资</td>
						<td align="right">
						   <input id="attend_salary" name="attend_salary" onblur="zonjia()"/>
						</td>
					</tr>

					<tr>
						<td align="right">年度工资</td>
						<td align="right">
						  <input id="year_sarary" name="year_sarary" onblur="zonjia()"/>
						</td>
						<td align="right">其他工资</td>
						<td align="right">
						   <input id="other_sarary" name="other_sarary" />
						</td>					    
					</tr>
					
					<tr>
						<td align="right">备注</td>
						<td align="right">
						   <input id="remark" name="remark" />
						   <!-- <select id="contentment" name="contentment" style="width: 153px;"></select> -->					    
					</tr>	
				</table>
			</form>
		</div>
	</div>
	
	<div id="finditem" style="margin-top: 3px; margin-left: 20px;">
	<input id="kword" name="kword" class="                                                                                                                                                easyui-searchbox" searcher="qq" prompt="  查询内容" menu="#mm"/>
	</div>
	<div id="mm" style="width:120px"> 
       <div name="employee_no" iconCls="icon-ok">员工编号</div>  
    </div> 
    
    <div id="help" class="easyui-dialog" title="help"
			data-options="modal:true,iconCls:'icon-help'" closed="true"
			style="padding-top: 20px;">
        <div style="font-family: 微软雅黑; margin-left: 15px; margin-right: 15px;"><font size="3">该管理工具用于记录和管理销售人员外出费用支出的详细情况。</font></div>
    </div>
</body>
</html>
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
<title>员工出差管理</title>
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
<!-- easyui布局 -->
<body class="easyui-layout" bgcolor="#EFF0FF" leftmargin="0"
	topmargin="0" marginwidth="0" marginheight="0"
	ondrag="JavaScript:return false">

	<!-- 顶部 -->
	<div region="north" id="north"
		style="height:70px; padding:5px 0 0 10px; background:#7389B5 url(tools/${toolId}/images/header.png) no-repeat; color:#fff; font-weight:bold;">
		<div style="margin-top: 20px;"></div>
		<font color="#2E84BA" size=5 style="margin-left: 460px;margin-top: 30px;">—— 员工出差管理工具</font>
		<a
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
		<!-- 弹窗 -->
<!-- 新增编辑 -->
	<div id ="addLevection"  class="easyui-dialog" closed="true" closable="false">
 	<form id="addLevectionForm"  style="margin:10 auto">
		<table align="center" id="addLevectionTable">			
			<tr style="height:25px;">
				<td align="right"  width="60px">申请人</td>
				<td align="left">
					<input style="border-style: inset;border-width: large;width: 100px" type="text"  id="employeeNo" name="employeeNo" />					
				    <input type="button" onclick="chooseEmpl('empl')" value="选择" />
				</td>				
				<td align="right" width="60px">审批人</td>
				<td align="left" colspan="2">
					<input style="border-style: inset;border-width: large;width: 100px" type="text"   id="approverId" name="approverId" />
					<input type="button" onclick="chooseEmpl('appr')" value="选择" />
				</td>
			</tr>			
			<tr style="height:25px;" >
				<td align="right" width="60px">开始时间</td>
				<td align="left" >
					<input style="border-style: inset;border-width: large;width: 150px" type="text"  id="startDate" name="startDate"  />
				</td>
				<td align="right" width="60px">结束时间</td>
				<td align="left"  colspan="2">
					<input style="border-style: inset;border-width: large;width: 150px" type="text"  id="endDate" name="endDate" />
				</td>							
			</tr>			
			<tr style="height:25px;">
				<td align="right" width="60px">出差时间</td>
				<td><input style="border-style: inset;border-width: large;width: 150px" type="text"  id="leaveDay" name="leaveDay" /></td>
			
				<td align="right"  width="60px">出差类型</td>
				<td align="left"  colspan="2">
					<input style="border-style: inset;border-width: large;width: 150px" type="text"  id="leaveType" name="leaveType" />
				</td>													
			</tr>
			
			<tr style="height:25px;">
				<td align="right"  width="60px">出差时期</td>
				<td align="left">
					<input style="border-style: inset;border-width: large;width: 150px"  type="text" id="leaveReason" name="leaveReason"/>					
				</td>
				<td align="right"  width="60px">状态</td>
				<td align="left" colspan="2">
					<input style="border-style: inset;border-width: large;width: 150px"  type="text" id="status" name="status"/>					
				</td>
				
			</tr>
			<tr style="height:25px;">
				<td align="right"  width="60px">备注</td>
				<td align="left" colspan="4">
					<textarea rows="4" cols="52" id="remark" name="remark"></textarea>
				</td>									
			</tr>
		</table>
	</form>	
 </div>
	<!-- 搜索框 -->
	<div id="finditem" style="margin-top: 3px; margin-left: 20px;">
	<input id="kword" name="kword" class="easyui-searchbox" searcher="qq" prompt="  查询内容" menu="#mm"/>
	</div>
	<div id="mm" style="width:120px"> 
       <div name="leavetabId" >编号</div> 
       <div name="employeeName">申请人</div> 
       <div name="approverName">批准人</div> 
    </div> 
    <!-- 选择审核人、申请人的对话框 -->
 	<div id="chooseEmplDialog"  class="easyui-dialog" closed="true" closable="false">
 	  <div><input type="text" id="searchEmplText" style="margin-left:20px;width:130px"/>&nbsp;<input type="button" onclick="searchEmpl()" value="搜索" /></div><br/>
 	  <div><table id="chooseEmplList" style="margin-left:10px"></table></div>
 	</div>
 	<!-- 显示帮助信息的对话框 -->
    <div id="help" class="easyui-dialog" title="help"
			data-options="modal:true,iconCls:'icon-help'" closed="true"
			style="padding-top: 20px;">
        <div style="font-family: 微软雅黑; margin-left: 15px; margin-right: 15px;"><font size="3">该管理工具用于记录和管理员工出差的信息。</font></div>
    </div>
</body>
</html>
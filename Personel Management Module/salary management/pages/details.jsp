<%@ page language="java" pageEncoding="UTF-8"%>
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
<title></title>
    <link rel="stylesheet" type="text/css" href="tools/${toolId}/js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="tools/${toolId}/js/themes/icon.css">
	<script type="text/javascript" src="tools/${toolId}/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="tools/${toolId}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="tools/${toolId}/js/easyui-lang-zh_CN.js"></script>
	<!-- 引入外部自定义js文件 -->
    <script type="text/javascript" src="tools/${toolId}/js/control.js"></script>
<style type="text/css">
a {
	text-decoration: none
}
.style1{
    font-size: 10.5pt; 
    font-family:微软雅黑;
    padding-left: 15px;
}
.style2{
    font-size: 10.5pt; 
    font-family:微软雅黑;
}
input {
	border-style:inset; 
	border-width:medium;
}
select {
	width:157px; 
	border-style:inset; 
	border-width:medium;
}
</style>

<script type="text/javascript">
	var toolId = '${toolId}';//定义全局变量，用于把toolId的值传给js文件
</script>
</head>
<body>
   
	<div>
		<div id="SmCost" class="easyui-dialog" title="material"
			data-options="modal:true,iconCls:'icon-save'" closed="true"
			style="padding-top: 20px;">
			<form id="SmCostForm" name="SmCostForm">
				<input name="orderid" id="orderid" type="hidden"/>
				<input name="contactid" id="contactid" type="hidden"/>
				<table align="center">
					<tr>
						<td align="right">交通费</td>
						<td align="right">
						   <input id="tra_cost" name="tra_cost" onblur="zonjia()"/>
						</td>
						<td align="right">住宿费</td>
						<td align="right">
						   <input id="lod_cost" name="lod_cost" onblur="zonjia()"/>
						</td>
					</tr>

					<tr>
						<td align="right">过路费</td>
						<td align="right">
						   <input id="tol_cost" name="tol_cost" onblur="zonjia()"/>
						</td>
						<td align="right">就餐费</td>
						<td align="right">
						   <input id="eat_cost" name="eat_cost" onblur="zonjia()"/>
						</td>
					</tr>

					<tr>
						<td align="right">其他费用</td>
						<td align="right">
						  <input id="oth_cost" name="oth_cost" onblur="zonjia()"/>
						</td>
						<td align="right">总费用</td>
						<td align="right">
						   <input id="sum_cost" name="sum_cost" readonly="readonly"/>
						</td>					    
					</tr>
					
					<tr>
						<td align="right">费用所属客户</td>
						<td align="right">
						   <input id="sel_client" name="sel_client" />
						   <!-- <select id="contentment" name="contentment" style="width: 153px;"></select> -->
						</td>
						<td align="right">费用所属日期</td>
						<td align="right">
						   <input id="sel_date" name="sel_date" />
						</td>					    
					</tr>
					
					<tr>
						<td align="right">销售员</td>
						<td align="right">
						   <input id="sell" name="sell" >
						</td>
						<td align="right">登记日期</td>
						<td align="right">
						   <input id="re_date" name="re_date" />
						</td>					    
					</tr>
						
				</table>
			</form>
		</div>
	</div>
	
	<div id="finditem" style="margin-top: 2px; margin-left: 2px;">
	<input id="kword" name="kword" class="easyui-searchbox" searcher="qq" prompt="查询内容" menu="#mm"/>
	</div>
	<div id="mm" style="width:120px"> 
       <div name="id" iconCls="icon-ok">编号</div> 
       <div name="employee_name" iconCls="icon-ok">销售员</div> 
    </div> 
</body>
</html>

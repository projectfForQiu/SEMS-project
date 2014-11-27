<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调职管理(上级领导)</title>
  <link rel="stylesheet" type="text/css" href="<%= basePath %>/tools/${toolId}/js/themes/default/easyui.css"/>
  <link rel="stylesheet" type="text/css" href="<%= basePath %>/tools/${toolId}/js/themes/icon.css"/>
  <script type="text/javascript" src="<%= basePath %>/tools/${toolId}/js/jquery-1.8.0.min.js"></script>
  <script type="text/javascript" src="<%= basePath %>/tools/${toolId}/js/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%= basePath %>/tools/${toolId}/js/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript" src="<%= basePath %>/tools/${toolId}/js/easyui.plugin.js"></script>
    <script type="text/javascript">
    	var toolId  = '${toolId}';
    	$(function(){
  			initData();
  			
  		});
  		function initData(){
  	
				$('#tt').datagrid({
					title : '调职管理',
					fitColumns : true, //自动展开/合同列的大小
					striped : true,  //单元格显示条纹
					fit : true,
					singleSelect:true,
					collapsible : true,
					nowrap : false, //显示数据在同一行上
					url : '<%= basePath %>/execTool?toolId=${toolId}&action=listTransfer',
					loadMsg : '你的网速不给力，正努力加载中。。。。',
					//列的配置对象
					columns : [[
						{
							field : 'ckBox',
							checkbox : true
						},{
							field : 'transfer_id',
							title : '转职编号',
							width : 100,
							align : 'center'
						},{
							field : 'employee_no',
							title : '员工编号',
							width : 100,
							align : 'center'
						},{
							field : 'positionID',
							title : '岗位ID',
							width : 100,
							align : 'center'
						},{
							field : 'employee_name',
							title : '员工姓名',
							width : 100,
							align : 'center'
						},{
							field : 'orgposition',
							title : '目前岗位',
							width : 100,				
							align : 'center'
						},{
							field : 'nowpositionid',
							title : '转向岗位id',
							width : 100,				
							align : 'center'
						},{
							field : 'nowposition',
							title : '转向岗位',
							width : 100,				
							align : 'center'
						},{
							field : 'transfer_reason',
							title : '转职原因',
							width : 100,				
							align : 'center'
						}
					]],
					pagination : true,   //底部显示一分页工具栏
					pageSize : 15,  //每页显示记录数
					pageList : [ 5, 10, 15 ],  //可设置的每页记录数
					//工具栏	        
					toolbar : [ {
						text : '<font size=3px; style="color:red;">审核</font>',
						iconCls : 'icon-add',
						handler: function(){
							
							pass();
						}
						
					},{
						text : '<font size=3px; style="color:red;">刷新</font>',
						iconCls : 'icon-reload',
						handler : 'refresh'

					}],
					onLoadSuccess:function(data){
						$('.datagrid-toolbar table tr').append($('#finditem'));
					}
					
					
				});
				
  }
  	
	  
  //刷新dataGrid
			function refresh() {
				$('#tt').datagrid('load', {});
				
			}
			
			
			function pass(){
				var select = $('#tt').datagrid('getSelected');
				if(select==null){
					alert("请选择一项");
					return false;
				}
				
				$.ajax({
					type:'post',
					url:'<%= basePath %>/execTool?toolId=${toolId}&action=pass',
					data:'transfer_id='+select.transfer_id+'&employee_id='+select.employee_no+'&position_id='+select.nowpositionid,
					success:function(data){
						alert(data);
					}
				});
			
			
				refresh();
			}

			function qq(value, name) {
				if (value == "") {
					$.messager.alert('提示', '请输入搜索内容!');
				} else {
					$("#tt").datagrid('load',{
						value:value,
						name:name
					});
				}
			}
    </script>

 </head>
 <body class="easyui-layout">
  
  	<div region="north" id="north"
		style="height:70px; padding:5px 0 0 10px; background:#7389B5 url(tools/${toolId}/images/header.png) no-repeat; color:#fff; font-weight:bold;">
		<div style="margin-top: 20px;"></div>
		<font color="#2E84BA" size=5 style="margin-left: 460px;margin-top: 30px;">—— 调职审核工具</font>
		<a href="#"
			class="easyui-linkbutton" plain="true" iconCls="icon-help"
			onclick="showhelp()"
			style="float:right;margin-top: 15px;"">帮助说明</a>
	</div> 
	<div  region="center" id="center">
	    <table id="tt"></table>
		    <div style="display:none">
			 <div id="finditem" style="margin-top: 3px; margin-left: 20px;">
				<input id="kword" name="employee_no" class="easyui-searchbox" searcher="qq" prompt="  请输入员工编号" menu="#mm"/>
			</div>
	    </div>
   </div>
    <div region="south" id="south"
			style="height:25px;lind-height:25px;padding:3px 0 0 5px;background:#EFF0FF;overflow: hidden;">
			<div style="text-align: center; font-family: 微软雅黑">版权所有：广州市飞元信息科技有限公司</div>
	</div>   
  </body>
</html>

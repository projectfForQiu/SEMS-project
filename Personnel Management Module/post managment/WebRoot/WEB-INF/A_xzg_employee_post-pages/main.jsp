<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>岗位管理(人事专员)</title>
  <link rel="stylesheet" type="text/css" href="<%= basePath %>/tools/${toolId}/js/themes/default/easyui.css"/>
  <link rel="stylesheet" type="text/css" href="<%= basePath %>/tools/${toolId}/js/themes/icon.css"/>
  <script type="text/javascript" src="<%= basePath %>/tools/${toolId}/js/jquery-1.8.0.min.js"></script>
  <script type="text/javascript" src="<%= basePath %>/tools/${toolId}/js/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%= basePath %>/tools/${toolId}/js/easyui-lang-zh_CN.js"></script>
	
    <script type="text/javascript">
    	$(function(){
  			initData();
  			
  		});
  		function initData(){
  	
				$('#tt').datagrid({
					title : '岗位管理',
					fitColumns : true, //自动展开/合同列的大小
					striped : true,  //单元格显示条纹
					fit : true,
					collapsible : true,
					nowrap : false, //显示数据在同一行上
					url : '<%= basePath %>/execTool?toolId=${toolId}&action=listPosition',
					loadMsg : '你的网速不给力，正努力加载中。。。。',
					//列的配置对象
					columns : [[
						{
							field : 'ckBox',
							checkbox : true
						},{
							field : 'positionID',
							title : '岗位ID',
							width : 100,
							align : 'center'
						},{
							field : 'positionName',
							title : '岗位名称',
							width : 100,
							align : 'center'
						},{
							field : 'basicSarary',
							title : '岗位基本工资',
							width : 100,
							align : 'center'
						},{
							field : 'deptNo',
							title : '归属部门',
							width : 100,
							align : 'center'
						},{
							field : 'positionDesc',
							title : '岗位描述',
							width : 100,
							align : 'center'
						}
					]],
					pagination : true,   //底部显示一分页工具栏
					pageSize : 15,  //每页显示记录数
					pageList : [ 5, 10, 15 ],  //可设置的每页记录数
					//工具栏	        
					toolbar : [ {
						text : '<font size=3px; style="color:red" >添加</font>',
						iconCls : 'icon-add',
						handler: function(){
							getlistId();
							add();
						}
						
					}, {
						text : '<font size=3px; style="color:red" >删除</font>',
						iconCls : 'icon-remove',
						handler :function(){
							del();
						}
					}, {
						text : '<font size=3px; style="color:red;" >修改</font>',
						iconCls : 'icon-reload',
						handler :function(){
							if(jugdeSelect()){
								alert("直接双击行进行修改");
							}else{
								alert('请选择需要修改的岗位信息');
							}
							
						}

					},{
						text : '<font size=3px; style="color:red;">刷新</font>',
						iconCls : 'icon-reload',
						handler : 'refresh'

					}],
					onLoadSuccess:function(data){
						$('.datagrid-toolbar table tr').append($('#finditem'));
					},
					onDblClickRow : function(index, row) {
						getlistId();
						$('#positionIDchange').val(row.positionID);
						$('#positionNamechange').val(row.positionName);
						$('#basicSararychange').val(row.basicSarary);
						$('#ccchange').combobox('setValue',row.deptNo);
						$('#positionDescchange').val(row.positionDesc);
						change();
					}
					
				});
				
  }
  	
	  
  //刷新dataGrid
			function refresh() {
				$('#tt').datagrid('load', {});
				
			}
			function getlistId(){
				$('#cc').combobox({
				    url:'<%= basePath %>/execTool?toolId=${toolId}&action=getdepNo',
				    valueField:'id',
				    textField:'text'
				});
				$('#ccchange').combobox({
				    url:'<%= basePath %>/execTool?toolId=${toolId}&action=getdepNo',
				    valueField:'id',
				    textField:'text'
				});
			}
			function add(){
				$('#win').window({
				title : '岗位信息添加表'

				}
				);
				$('#win').window('open');
				$('#ff').form({
					
        			url:'<%= basePath %>/execTool?toolId=${toolId}&action=addPosition',
      			
        		success:function(data){
        			
	                	$('#win').window('close');
	                	$('#ff').form('clear');
	                	alert(data);
	                	refresh();
        			},
	        	onSubmit:function(){
	        			
	        		} 
				});
				
			}
			function del(){
				var select = $('#tt').datagrid('getSelections');
				var selectID="";
				for(var index=0;index<select.length;index++){
					selectID = selectID + select[index].positionName + ",";
				}
				$('#delid').val(selectID);
				
				$('#dd').dialog({
					title:'删除管理信息',
					buttons:[{
						text:'确定',
						handler:function(){
							delsubmit();
						}
					}]
				});
				
			
			}
			function delsubmit(){
				var select = $('#tt').datagrid('getSelections',{});
				var selectID="";
				for(var index=0;index<select.length;index++){
					selectID = selectID + select[index].positionID + "#";
				}
				
				$.ajax({
					type:'post',
					url:'<%= basePath %>/execTool?toolId=${toolId}&action=deletePosition',
					data:'positionID='+selectID,
					success:function(data){
						alert(data);
	                	$('#dd').window('close');
	                	refresh();
	        		}
	        		
				});
			
			}
			
		function change(){
		    
			$('#winchange').window({
				title : '岗位信息修改表'

				}
				);
				$('#winchange').window('open');
				$('#ffchange').form({
					
        			url:'<%= basePath %>/execTool?toolId=${toolId}&action=changePosition',
      			
        		success:function(data){
        			
	                	$('#winchange').window('close');
	                	$('#ffchange').form('clear');
	                	alert(data);
	                	refresh();
        			},
	        	onSubmit:function(){
	        			
	        		} 
				});
		}
				
		function jugdeSelect(){
			var select = $('#tt').datagrid('getSelections');
			$('#positionID').val(select.positionID);
			$('#positionName').val(select.positionName);
			$('#basicSarary').val(select.basicSarary);
			$('#cc').combobox('setValue',select.deptNo);
			$('#positionDesc').val(select.positionDesc);
			
			if(select!=null){
				return true;
			}else{
				return false;
			}
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
		<font color="#2E84BA" size=5 style="margin-left: 460px;margin-top: 30px;">—— 岗位管理工具</font>
		<a href="#"
			class="easyui-linkbutton" plain="true" iconCls="icon-help"
			onclick="showhelp()"
			style="float:right;margin-top: 15px;"">帮助说明</a>
	</div> 
	<div  region="center" id="center">
    <table id="tt"></table>
    <div style="display:none">
    <div id="win" iconCls="icon-save" title="My Window">
    <form id="ff" method="post">
	    <table>
	    	<input name="positionID" id="positionID" type="hidden"/>
			<tr>
	  			<td>岗位名称:</td><td><input name="positionName" id="positionName" type="text"/>
	  		</tr>
	  		<tr>
	  			<td>岗位基本工资:</td><td><input name="basicSarary" id="basicSarary" type="text"/></td>
	  		</tr>
	  		<tr>
	  		    <td>归属部门:</td><td><input id="cc" name="deptNo"  type="text"></input></td>
	  		</tr>
	  		<tr>
	  			<td>岗位描述:</td><td><input name="positionDesc" id="positionDesc" type="text"></input></td>
	  		</tr>
	  		 <tr>
	  		 	<td></td>
	  		 	<td align="center"><input type="submit" text="提交"/></td>
	  		 </tr>
	  		 </table>
  	</form>
  	</div>
  <div id="winchange" iconCls="icon-save" title="My Window">
  	<form id="ffchange" method="post">
	    <table>
	    	<input name="positionID" id="positionIDchange" type="hidden"/>
			<tr>
	  			<td>岗位名称:</td><td><input name="positionName" id="positionNamechange" type="text"/>
	  		</tr>
	  		<tr>
	  			<td>岗位基本工资:</td><td><input name="basicSarary" id="basicSararychange" type="text"/></td>
	  		</tr>
	  		<tr>
	  		    <td>归属部门:</td><td><input id="ccchange" name="deptNo"  type="text"></input></td>
	  		</tr>
	  		<tr>
	  			<td>岗位描述:</td><td><input name="positionDesc" id="positionDescchange" type="text"></input></td>
	  		</tr>
	  		 <tr>
	  		 	<td></td>
	  		 	<td align="center"><input type="submit" text="提交"/></td>
	  		 </tr>
	  		 </table>
  	</form>
	</div>
	
    <div id="dd" title="My Dialog" style="width:400px;height:200px;">
		你确定删除岗位名称为:<input name="ID" type="text" disabled="disabled" id="delid">的岗位信息吗?
	</div>
	 <div id="finditem" style="margin-top: 3px; margin-left: 20px;">
		<input id="kword" name="position_id" class="easyui-searchbox" searcher="qq" prompt="  请输入岗位ID" menu="#mm"/>
	</div>
    </div>
   </div>
     <div region="south" id="south"
			style="height:25px;lind-height:25px;padding:3px 0 0 5px;background:#EFF0FF;overflow: hidden;">
			<div style="text-align: center; font-family: 微软雅黑">版权所有：广州市飞元信息科技有限公司</div>
		</div>  
  </body>
</html>

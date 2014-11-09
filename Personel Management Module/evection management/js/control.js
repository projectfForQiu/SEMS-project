$(function() {
	initData();
	$('#startDate').datetimebox();
	$('#endDate').datetimebox();
	$('#leaveDay').numberbox({
		min:0
	});
});
// 初始化数据
function initData() {
	$('#tabs').datagrid({
		title : "员工出差管理",
		height : 'auto', 
		width : 'auto',
		striped : true,
		collapsible : false,
		fit : true,
		nowrap : true,
		singleSelect : true,
		fitColumns:true,
		url : 'execTool?toolId=' + toolId + '&action=list',
		loadMsg : '正努力加载 ~>_<~',
		columns : [ [ {
			checkbox : true
		}, {
			field : 'leavetabId',
			title : '编号',
			width : 20
		}, {
			field : 'approverName',
			title : '批准人',
			width : 10
		}, {
			field : 'approverId',
			hidden: true
		}, {
			field : 'employeeNo',
			hidden : true
		}, {
			field : 'employeeName',
			title : '申请人',
			width : 10
		}, {
			field : 'startDate',
			title : '开始时间',
			width : 20
		}, {
			field : 'endDate',
			title : '结束时间',
			width : 20
		}, {
			field : 'leaveDay',
			title : '出差时间',
			width : 10
		}, {
			field : 'leaveType',
			title : '出差类型',
			width : 10
		}, {
			field : 'leaveReason',
			title : '出差时期',
			width : 10
		}, {
			field : 'status',
			title : '状态',
			width : 10
		}, {
			field : 'remark',
			title : '备注',
			width : 30
		} ] ],
		pagination : true,
		pageSize : 15,
		pageList : [ 10, 15, 20 ,25],
		// 工具栏
		toolbar : [ {
			text : '刷新',
			iconCls : 'icon-reload',
			// 添加数据项
			handler : function() {
				$('#tabs').datagrid('load');
			}
		},'-', {
			text : '添加',
			iconCls : 'add',
			// 添加数据项
			handler : function() {
				addItem();
			}
		},'-', {
			text : '修改',
			iconCls : 'edit',
			handler : function() {
				var selected = $('#tabs').datagrid('getSelected');
				if (selected) {
					editrow(selected);
				} else {
					$.messager.alert('警告', '请选择数据！', 'warning');
				}
			}
		},'-', {
			text : '删除',
			iconCls : 'del',
			handler : function() {
				var selected = $('#tabs').datagrid('getSelected');
				if (selected) {
					delectItem(selected.leavetabId);
				} else {
					$.messager.alert('警告', '请选择要删除的数据！', 'warning');
				}
			}

		},'-'],
		onDblClickRow : function(index, rowData) {
			editrow(rowData);
		},
		//用来控制datagrid样式
		onLoadSuccess: function(data){   
		        var panel = $(this).datagrid('getPanel');
		        var headtr = panel.find('div.datagrid-header tr');
		        var unittr = panel.find('div.datagrid-body tr');
		        var toolbar = panel.find('div.datagrid-toolbar');
		        headtr.each(function(){   
		            var td = $(this).children('td');   
		            td.children("div").css({   
		            	"font-weight":"bold",
		                "font-size" : "14px",
		                "font-family":"微软雅黑",
		                "padding":"8px"
		            });   
		        });
		        unittr.each(function(){   
		            var td = $(this).children('td');   
		            td.children("div").css({   
		                "font-size" : "14px",
		                "font-family":"微软雅黑",
		                "padding":"8px"
		            });   
		        });
		        toolbar.css({
		        	"font-family":"微软雅黑",
		        	"font-weight":"bold",
		        	"font-size":"15px"
		        });
		        $('.datagrid-toolbar table tr').append($('#finditem'));
		    }
	});
}
// 添加信息
function addItem() {
	$('#addLevection').dialog(
			{
				width : 500,
				height : 300,
				title : '新增员工出差信息',
				iconCls : 'icon-add',
				buttons : [
						{
							text : '提交',
							iconCls : 'icon-ok',
							handler : function() {
									$.ajax({
										url : 'execTool?toolId=' + toolId+ '&action=add',
										data : $('#addLevectionForm').serialize(),
										type : 'post',
										success : function(result) {
											if (result == 'success') {
												$.messager.show({
													msg : '添加成功！',
													title : '恭喜'
												});
												$('#addLevectionForm').form('clear');
												$('#addLevection').dialog('close');
												refresh();
											}else{
												$.messager.alert('提示信息','添加失败，请检查输入是否有误！','info' );
											}
											
										}

									});
								}
						},
						{
							iconCls : 'icon-cancel',
							text : '取消',
							handler : function() {
								$('#addLevectionForm').form('clear');
								$('#addLevection').dialog('close');
							}
						} ]
			});

	$('#addLevection').dialog('open');
}
// 修改信息
function editrow(data) {
	$('#addLevection').dialog(
			{
				width : 520,
				height : 300,
				title : '修改员工出差信息',
				iconCls : 'icon-edit',
				buttons : [
						{
							text : '提交',
							iconCls : 'icon-ok',
							handler : function() {
									$.ajax({
										url : 'execTool?toolId=' + toolId
												+ '&action=update&leavetabId=' + data.leavetabId,
										data : $('#addLevectionForm').serialize(),
										type : 'post',
										success : function(result) {
											if (result == 'success') {
												$.messager.show({
													msg : '修改成功！',
													title : '恭喜'
												});
												$('#addLevectionForm').form('clear');
												$('#addLevection').dialog('close');
												refresh();
											}else{
												$.messager.alert('提示信息','修改失败，请检查输入是否有误！','info' );
											}
										}
									});
							}

						},

						{
							iconCls : 'icon-cancel',
							text : '取消',
							handler : function() {
								$('#addLevectionForm').form('clear');
								$('#addLevection').dialog('close');
							}
						} ],onOpen:function(){
							$('#approverId').val(data.approverId);
							$('#employeeNo').val(data.employeeNo);
							$('#leaveType').val(data.leaveType);
							$('#leaveReason').val(data.leaveReason);
							$('#remark').val(data.remark);
							$('#status').val(data.status);
							$('#startDate').datetimebox('setValue',data.startDate);
							$('#endDate').datetimebox('setValue',data.endDate);
							$('#leaveDay').numberbox('setValue',data.leaveDay);
						}

			});
	$('#addLevection').dialog('open');
}


// 刷新订单
function refresh() {
	$('#tabs').datagrid("reload")

}
// 删除
function delectItem(id) {

	$.messager.confirm('提示', '确定删除该条记录?', function(r) {
		if (r) {
			$.ajax({
				type : 'post',
				url : 'execTool?toolId=' + toolId
						+ '&action=delete',
				data : "id="+id,
				success : function(result) {
					if (result == 'success') {
						refresh();
						$.messager.show({
							msg : '删除成功',
							title : '提示'
						});
					}else{
						$.messager.alert('提示消息','删除失败，请重新尝试！','info');
					}
				}
			});
		}
	});
}

// 查询
function qq(value, name) {
		$("#tabs").datagrid('load',{
			value:value,
			name:name
		});
}
//选择审核人和申请人
function chooseEmpl(type){
	$('#chooseEmplDialog').dialog({
	      width:250,
	      height:300,
	      title:'请选择',
	      cache:false,
	      buttons:[{
	         text:'确定',
	         iconCls:'icon-ok',
	         handler:function(){
	            var radio = document.getElementsByName("radioEmplId");
	            for(var i=0;i<radio.length;i++){
	               if(radio[i].checked){
	            	   if(type=='empl'){
	            		   $('#employeeNo').val(radio[i].value);
	            	   }else if(type=='appr'){
	            		   $('#approverId').val(radio[i].value);
	            	   }
	                   break;
	               }
	            }
	            $('#searchEmplText').val("");
	            $('#chooseEmplDialog').dialog('close');
	         }
	         },{
				iconCls : 'icon-cancel',
				text : '取消',
				handler : function() {
					$('#searchEmplText').val("");
					$('#chooseEmplDialog').dialog('close');
				}
	        }],onOpen:function(){
	            searchEmpl();
	        }
	        });
	   $('#chooseEmplDialog').dialog('open');
}
//搜索申请人，审核人
function searchEmpl(){
      var text = document.getElementById("searchEmplText").value;
      var table = document.getElementById("chooseEmplList");
      while(table.hasChildNodes()){
         table.removeChild(table.lastChild);
      }
      $.ajax({
          type:'post',
          url:'execTool?toolId='+toolId+'&action=getEmployee',
          data:'query='+text,
          cache:false,
          success:function(result){
             var data = eval("("+result+")");
             var data2 = data.employee;
             for(var i=0;i<data2.length;i++){
                var str = '<tr><td><input type="radio" name="radioEmplId" id="radioEmplId'+i+'"value="'
                +data2[i].employeeNo+'"><label for="radioEmplId'+i+'">'+data2[i].employeeName+'('
                +data2[i].employeeNo+')</label></td></tr>';
                $('#chooseEmplList').append(str);
             }
          }
      });
}
//显示工具的帮助说明
function showhelp() {
	$('#help').dialog({
		title:'帮助说明',
		width : 300,
		height : 200
	});
	$('#help').dialog('open');
}

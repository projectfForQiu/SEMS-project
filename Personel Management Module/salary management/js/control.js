$(function() {
	initData();
	//$('#sDate').datebox();
	$('#basic_salary').numberbox({
		value : 0,
		min : 0,
		precision : 2
	});
	$('#performance_salary').numberbox({
		value : 0,
		min : 0,
		precision : 2
	});
	$('#attend_salary').numberbox({
		value : 0,
		min : 0,
		precision : 2
	});
	$('#year_sarary').numberbox({
		value : 0,
		min : 0,
		precision : 2
	});
	$('#other_sarary').numberbox({
		value : 0,
		min : 0,
		precision : 2
	});
	$('#employee_no').combobox({
		url:'execTool?toolId=' + toolId + '&action=getSaler',
		valueField: 'code',
        textField: 'employee_name',
        panelHeight: 'auto',
        editable: false,
        width: 139
	});
	//绑定select控件
//	$('#sell').combobox({
//		url:'execTool?toolId=' + toolId + '&action=getSaler',
//		valueField: 'code',
//        textField: 'employee_name',
//        panelHeight: 'auto',
//        editable: false,
//        width: 139
//	});
});
// 初始化数据
function initData() {
	$('#tabs').datagrid({
		height : 'auto', 
		width : 'auto',
		striped : true,
		collapsible : true,
		fit : true,
		nowrap : true,
		singleSelect : true,
		url : 'execTool?toolId=' + toolId + '&action=SmCostList',
		loadMsg : '正努力加载 ~>_<~',
		columns : [ [ {
			checkbox : true
		}, {
			field : 'salary_id',
			title : '编号'	
		}, {
			field : 'employee_no',
			title : '员工编号'
		}, {
			field : 'basic_salary',
			title : '基础工资(元)'
		}, {
			field : 'performance_salary',
			title : '工作表现工资（元）'
		}, {
			field : 'attend_salary',
			title : '考勤工资（元）'
		}, {
			field : 'year_sarary',
			title : '年度工资(元)'
		}, {
			field : 'other_sarary',
			title : '其他工资(元)'
		}, {
			field : 'remark',
			title : '备注'
		}] ],
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 15, 20 ],
		// 工具栏
		toolbar : [ {
			text : '<font style="font-size:14px;color:red;">添加</font>',
			iconCls : 'add',
			// 添加数据项
			handler : function() {
				addItem();
			}
		},'-', {
			text : '<font style="font-size:14px;color:red;">修改</font>',
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
			text : '<font style="font-size:14px;color:red;">删除</font>',
			iconCls : 'del',
			handler : function() {
				var selected = $('#tabs').datagrid('getSelected');

				if (selected) {
					delectItem(selected.salary_id);
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


// 验证订单表单
function checkForm() {
//	if ($('#employee_no').val() == '') {
//		$.messager.alert('警告', '请填写费用所属客户！');
//		return false;
//	}
//	if ($('#sel_date').datebox('getValue') == '') {
//		$.messager.alert('警告', '请填写费用所属日期！');
//		return false;
//	}
//	if ($('#re_date').datebox('getValue') == '') {
//		$.messager.alert('警告', '请填写登记日期！');
//		return false;
//	}
	if ($('#employee_no').combobox('getValue') == '') {
		$.messager.alert('警告', '请选择员工！');
		return false;
	}
	return true;
	
}
// 添加信息
function addItem() {
	$('#SmCostForm').form('clear');
	//getSaler();
	$('#SmCost').dialog(
			{
				width : 550,
				height : 280,
				title : '员工工资信息录入',
				buttons : [
						{
							text : '提交',
							iconCls : 'icon-ok',
							handler : function() {
								if (checkForm()) {
									$.ajax({
										url : 'execTool?toolId=' + toolId
												+ '&action=SmCostAdd',
										data : $('#SmCostForm').serialize(),
										type : 'post',
										success : function(result) {
											if (result == 'success') {
												$.messager.show({
													msg : '添加成功！',
													title : '恭喜'
												});
											}

											$('#SmCostForm').form('clear');
											$('#SmCost').dialog('close');
											refresh();
										}

									});
								}
							}

						},

						{
							iconCls : 'icon-cancel',
							text : '取消',
							handler : function() {
								$('#SmCost').dialog('close');
							}
						} ]
			});

	$('#SmCost').dialog('open');

}

// 修改信息
function editrow(data) {
	$('#SmCost').dialog(
			{
				width : 550,
				height : 280,
				title : '销售人员费用支出信息修改',
				iconCls : 'icon-edit',
				buttons : [
						{
							text : '提交',
							iconCls : 'icon-ok',
							handler : function() {
								if (checkForm()) {
									$.ajax({
										url : 'execTool?toolId=' + toolId
												+ '&action=updateSmCost&id=' + data.salary_id,
										data : $('#SmCostForm').serialize(),
										type : 'post',
										success : function(result) {
											if (result == 'success') {
												$.messager.show({
													msg : '修改成功！',
													title : '恭喜'
												});
											}
											$('#SmCostForm').form('clear');
											$('#SmCost').dialog('close');
											refresh();
										}
									});
								}
							}

						},

						{
							iconCls : 'icon-cancel',
							text : '取消',
							handler : function() {
								$('#SmCostForm').form('clear');
								$('#SmCost').dialog('close');
							}
						} ]

			});
	//getSaler();
	getSmCostById(data)
	$('#SmCost').dialog('open');
}

//根据id获取销售员费用支出情况
function getSmCostById(data){
//	$.ajax({
//		type : 'post',
//		url : 'execTool?toolId='+ toolId +'&action=getSmCostById&id='+id,
//	    success : function(result){
//	    	if(result=="{}"){
//	    		$('#SmCost').dialog('close');
//	    		$.messager.show({
//					msg : '该数据不存在，可能已被删除！',
//					title : '警告'
//				});
//	    		refresh();
//	    	}else{    		
//	    		var data=eval("("+result+")");
	    		$('#basic_salary').numberbox('setValue', data.basic_salary);
	    		$('#performance_salary').numberbox('setValue', data.performance_salary);
	    		$('#attend_salary').numberbox('setValue', data.attend_salary);
	    		$('#year_sarary').numberbox('setValue', data.year_sarary);
	    		$('#other_sarary').numberbox('setValue', data.other_sarary);
//	    		$('#sum_cost').val(data.sum_cost);
//	    		$('#sel_client').val(data.sel_client);
//	    		$('#sel_date').datebox('setValue', data.sel_date);
	    		$('#employee_no').combobox('setValue', data.employee_no);
	    		$('#remark').val(data.remark);
//	    		$('#re_date').datebox('setValue', data.re_date);
	
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
						+ '&action=SmCostDel',
				data : "id="+id,
				success : function(result) {
					if (result == 'success') {
						refresh();
						$.messager.show({
							msg : '删除成功',
							title : '提示'
						});
					}
				}
			});
		}
	});
}

// 查询
function qq(value, name) {
	if (value == "") {
		$.messager.alert('提示', '请输入搜索内容!');
	} else {
		$("#tabs").datagrid('load',{
			value:value,
			name:name
		});
	}
}

//计算总价
//function zonjia() {
//	var total = new Number($('#tra_cost').val())+new Number($('#lod_cost').
//			val())+new Number($('#tol_cost').val())+new Number($('#eat_cost').val())+
//			new Number($('#oth_cost').val());
//	$('#sum_cost').val(new Number(total));
//	}
//显示工具的帮助说明
function showhelp() {
	$('#help').dialog({
		title:'帮助说明',
		width : 300,
		height : 200
	});
	$('#help').dialog('open');
}

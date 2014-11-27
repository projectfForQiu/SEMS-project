$(function() {
	//aa();
	initData();
	$('#evalue_date').datebox();
	$('#workPerformance').numberbox({
		value : 0,
		min : 0,
		precision : 2
	});
	$('#qualityOfWork').numberbox({
		value : 0,
		min : 0,
		precision : 2
	});
	$('#workAttitude').numberbox({
		value : 0,
		min : 0,
		precision : 2
	});
	$('#workSkills').numberbox({
		value : 0,
		min : 0,
		precision : 2
	});
	$('#itemFiveScore').numberbox({
		value : 0,
		min : 0,
		precision : 2
	});
	$("#evalue_no").combobox({
		url:'execTool?toolId=' + toolId + '&action=getEmployeeNo',
		valueField: 'code',
        textField: 'employee_name',
        panelHeight: 'auto',
        editable: false,
        width: 139
	});
});

function aa(){
	$('#tabs').datagrid({
		title : "员工绩效管理",
		height : 'auto', 
		width : 'auto',
		striped : true,//当true时，单元格显示条纹。默认false.
		collapsible : true,//是否可折叠，默认false
		fit : true,//自定义适应
		nowrap : true,//当true时，显示数据在同一行上。默认true.
		singleSelect : false,//单选
		url : 'execTool?toolId=' + toolId + '&action=listEmployeePerformance',
		loadMsg : '正在加载中~>_<~',
		columns : [[
					{
						field : 'ckBox',
						checkbox : true
					},{
						field : 'evalue_id',
						title : '绩效编号',
						width : 150,
						align : 'center'
					},{
						field : 'evalue_no',
						title : '员工编号',
						width : 150,
						align : 'center'
					},{
						field : 'evalue_date',
						title : '日期',
						width : 150,
						align : 'center'
					},{
						field : 'totalScore',
						title : '总分',
						width : 150,
						align : 'center'
					},{
						field : 'demo',
						title : '备注',
						width : 150,
						align : 'center'
					},{
						field : 'workPerformance',
						title : '绩效分数',
						width : 150,
						align : 'center'
					},{
						field : 'qualityOfWork',
						title : '能力分数',
						width : 150,
						align : 'center'
					},{
						field : 'workAttitude',
						title : '态度分数',
						width : 150,
						align : 'center'
					},{
						field : 'workSkills',
						title : '技能分数',
						width : 150,
						align : 'center'
					},{
						field : 'itemFiveScore',
						title : '其余项分数',
						width : 150,
						align : 'center'
					}
				]]
	});
}

function initData() {
	$('#tabs').datagrid({
		height : 'auto', 
		width : 'auto',
		striped : true,//当true时，单元格显示条纹。默认false.
		collapsible : true,//是否可折叠，默认false
		fit : true,//自定义适应
		nowrap : true,//当true时，显示数据在同一行上。默认true.
		singleSelect : false,//单选
		url : 'execTool?toolId=' + toolId + '&action=listEmployeePerformance',
		loadMsg : '正在加载中~>_<~',
		columns : [[
					{
						field : 'ckBox',
						checkbox : true
					},{
						field : 'evalue_no',
						title : '员工编号',
						width : 150,
						align : 'center'
					},{
						field : 'evalue_date',
						title : '日期',
						width : 150,
						align : 'center'
					},{
						field : 'totalScore',
						title : '总分',
						width : 150,
						align : 'center'
					},{
						field : 'demo',
						title : '备注',
						width : 150,
						align : 'center'
					},{
						field : 'workPerformance',
						title : '绩效分数',
						width : 150,
						align : 'center'
					},{
						field : 'qualityOfWork',
						title : '能力分数',
						width : 150,
						align : 'center'
					},{
						field : 'workAttitude',
						title : '态度分数',
						width : 150,
						align : 'center'
					},{
						field : 'workSkills',
						title : '技能分数',
						width : 150,
						align : 'center'
					},{
						field : 'itemFiveScore',
						title : '其余项分数',
						width : 150,
						align : 'center'
					}
				]],
				pagination : true,//当true时在DataGrid底部显示一个分页工具栏.
				pageSize : 10,
				pageList : [ 10, 15, 20 ],
//				toolbar:['-'],
				// 工具栏
				toolbar : [ {
					text : '<font size=3px; style="color:red;">录入</font>',
					iconCls : 'icon-add',
					// 添加数据项
					handler : function() {
						addItem();
					}
				},'-', {
					text : '<font size=3px; style="color:red;">修改</font>',
					iconCls : 'icon-edit',
					handler : function() {
						var selected = $('#tabs').datagrid('getSelected');
						if (selected) {
							editrow(selected);
						} else {
							$.messager.alert('警告', '请选择数据！', 'warning');
						}
					}
				},'-', {
					text : '<font size=3px; style="color:red;">删除</font>',
					iconCls : 'icon-cut',
					handler : function() {
						var selections = $('#tabs').datagrid('getSelections');
						if (selections.length>0) {
							$.messager.confirm('提示', '确定删除该记录?', function(r) {
								if (r) {
									for(var i=0;i<selections.length;i++){
										delectItem(selections[i].evalue_id);
									}
									refresh();
									$.messager.show({
										msg : '删除成功',
										title : '提示'
									});
								}
							});
						} else {
							$.messager.alert('警告', '请选择要删除的数据！', 'warning');
						}
					}

				},'-',{
					text : '<font size=3px; style="color:red;">刷新</font>',
					iconCls : 'icon-reload',
					handler : refresh

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
//			if ($('#evalue_no').val() == '') {
//				$.messager.alert('警告', '请填写所属绩效员工！');
//				return false;
//			}
			if ($('#evalue_date').datebox('getValue') == '') {
				$.messager.alert('警告', '请填绩效考核日期！');
				return false;
			}
			if ($('#totalScore').val() == '') {
				$.messager.alert('警告', '请填写考核总分！');
				return false;
			}
			if ($('#demo').val() == '') {
				$.messager.alert('警告', '请填写考核备注！');
				return false;
			}
			if ($('#workPerformance').val() == '') {
				$.messager.alert('警告', '请填写业绩分数');
				return false;
			}
			if ($('#qualityOfWork').val() == '') {
				$.messager.alert('警告', '请填写能力分数');
				return false;
			}
			if ($('#workAttitude').val() == '') {
				$.messager.alert('警告', '请填写态度分数');
				return false;
			}
			if ($('#workSkills').val() == '') {
				$.messager.alert('警告', '请填写技能分数');
				return false;
			}
			if ($('#itemFiveScore').val() == '') {
				$.messager.alert('警告', '请填写其余项分数');
				return false;
			}
			return true;
			
		}
		// 添加信息
		function addItem() {
			$('#EmployeePerformanceForm').form('clear');
			//getSaler();
			$('#EmployeePerformance').dialog(
					{
						width : 550,
						height : 280,
						title : '绩效考核信息录入',
						buttons : [
								{
									text : '提交',
									iconCls : 'icon-ok',
									handler : function() {
										if (checkForm()) {
											$.ajax({
												url : 'execTool?toolId=' + toolId
														+ '&action=EmployeePerformanceAdd',
												data : $('#EmployeePerformanceForm').serialize(),
												type : 'post',
												success : function(result) {
													if (result == 'success') {
														$.messager.show({
															msg : '成功！',
															title : '恭喜'
														});
													}

													$('#EmployeePerformanceForm').form('clear');
													$('#EmployeePerformance').dialog('close');
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
										$('#EmployeePerformance').dialog('close');
									}
								} ]
					});

			$('#EmployeePerformance').dialog('open');

		}

		// 修改信息
		function editrow(data) {
			$('#EmployeePerformance').dialog(
					{
						width : 550,
						height : 280,
						title : '员工绩效信息修改',
						iconCls : 'icon-edit',
						buttons : [
								{
									text : '提交',
									iconCls : 'icon-ok',
									handler : function() {
										if (checkForm()) {
											$.ajax({
												url : 'execTool?toolId=' + toolId
														+ '&action=updateEmployeePerformance&id=' + data.evalue_id,
												data : $('#EmployeePerformanceForm').serialize(),
												type : 'post',
												success : function(result) {
													if (result == 'success') {
														$.messager.show({
															msg : '修改成功！',
															title : '恭喜'
														});
													}
													$('#EmployeePerformanceForm').form('clear');
													$('#EmployeePerformance').dialog('close');
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
										$('#EmployeePerformanceForm').form('clear');
										$('#EmployeePerformance').dialog('close');
									}
								} ]

					});
			//getSaler();
			getEmployeePerformanceById(data.evalue_id);
			$('#EmployeePerformance').dialog('open');
		}

		//根据id获取绩效信息
		function getEmployeePerformanceById(evalue_id){
			$.ajax({
				type : 'post',
				url : 'execTool?toolId='+ toolId +'&action=getEmployeePerformanceById&id='+evalue_id,
			    success : function(result){
			    	if(result=="{}"){
			    		$('#EmployeePerformance').dialog('close');
			    		$.messager.show({
							msg : '该数据不存在，可能已被删除！',
							title : '警告'
						});
			    		refresh();
			    	}else{    		
			    		var data=eval("("+result+")");
			    		$('#totalScore').numberbox('setValue', data.totalScore);
			    		$('#workPerformance').numberbox('setValue', data.workPerformance);
			    		$('#qualityOfWork').numberbox('setValue', data.qualityOfWork);
			    		$('#workAttitude').numberbox('setValue', data.workAttitude);
			    		$('#workSkills').numberbox('setValue', data.workSkills);
			    		$('#itemFiveScore').numberbox('setValue', data.itemFiveScore);
			    		$('#demo').val(data.demo);
			    		$('#evalue_no').combobox('setValue',data.evalue_no);
			    		$('#evalue_date').datebox('setValue', data.evalue_date);
			    		$('#evalue_id').val(evalue_id);
			    	}
			    }
			});
		}


		// 刷新订单
		function refresh() {
			$('#tabs').datagrid("load",{});

		}
		// 删除
		function delectItem(evalue_id) {

//			$.messager.confirm('提示', '确定删除该条记录?', function(r) {
//				if (r) {
//					$.ajax({
//						type : 'post',
//						url : 'execTool?toolId='+ toolId +'&action=EmployeePerformanceDel',
//						data : "id="+evalue_id,
//						success : function(result) {
//							if (result == 'success') {
//								refresh();
//								$.messager.show({
//									msg : '删除成功',
//									title : '提示'
//								});
//							}
//						}
//					});
//				}
//			});
				$.ajax({
					type:'post',
					url:'execTool?toolId='+ toolId +'&action=EmployeePerformanceDel',
					data:"id="+evalue_id,
					success:function(result){
						if(result=='success'){
							//refresh();
						}
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

		//显示工具的帮助说明
		function showhelp() {
			$('#help').dialog({
				title:'帮助说明',
				width : 300,
				height : 200
			});
			$('#help').dialog('open');
		}
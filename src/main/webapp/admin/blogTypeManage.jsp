<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客类别管理页面</title>

<script type="text/javascript">
	var url;

	$(function() {
		//博客类别表格初始化
		//datagrid初始化
		$('#blogTypeGrid').datagrid({
			//头部显示的标题文本
			title : "博客类别管理",
			//请求数据的url
			url : '${basePath}/admin/blogType/list.do',
			//请求方法类型
			method : 'GET',
			//大小将自适应父容器
			fit : true,
			//载入提示信息
			loadMsg : 'loading...',
			//水平自动展开，如果设置此属性，则不会有水平滚动条，演示冻结列时，该参数不要设置
			fitColumns : true,
			//数据多的时候不换行
			nowrap : true,
			//设置分页
			pagination : true,
			//设置每页显示的记录数，默认是10个
			pageSize : 15,
			//每页显示记录数项目
			pageList : [ 5, 10, 15, 20 ],
			//指定id为标识字段，在删除，更新的时候有用，如果配置此字段，在翻页时，换页不会影响选中的项
			//idField : 'id',  //删除缓存BUG
			//上方工具条 添加 修改 删除 刷新按钮
			toolbar : [ {
				iconCls : 'icon-add', //图标  
				text : '添加', //名称
				handler : function() { //回调函数
					$("#dlg").dialog("open").dialog("setTitle", "添加博客类别信息");
					//将url设置为添加
					url = "${basePath}/admin/blogType/save.do";
				}
			}, '-', {
				iconCls : 'icon-edit',
				text : '修改',
				handler : function() {
					//获取选中要修改的行
					var selectedRows = $("#blogTypeGrid").datagrid("getSelections");

					//确保被选中行只能为一行
					if (selectedRows.length != 1) {
						$.messager.alert("系统提示", "请选择一个要修改的博客类别", "info");
						return;
					}
					//获取选中行id
					var row = selectedRows[0];
					//打开对话框并且设置标题
					$("#dlg").dialog("open").dialog("setTitle", "修改博客类别信息");
					//将数组回显对话框中
					$("#fm").form("load", row);//会自动识别name属性，将row中对应的数据，填充到form表单对应的name属性中
					//在url中添加id 后台就能识别是更新操作
					url = "${basePath}/admin/blogType/save.do?id=" + row.id;
				}
			}, '-', {
				iconCls : 'icon-remove',
				text : '删除',
				handler : function() {
					//获取选中要删除的行
					var selectedRows = $("#blogTypeGrid").datagrid("getSelections");
					if (selectedRows.length == 0) {
						$.messager.alert("系统提示", "请选择要删除的数据！", "info");
						return;
					}
					var strIds = [];
					for (var i = 0; i < selectedRows.length; i++) {
						strIds.push(selectedRows[i].id);
					}
					var ids = strIds.join(",");
					$.messager.confirm("系统提示", "您确定要删除这<font color=red>" + selectedRows.length + "</font>条数据吗？", function(r) {
						if (r) {
							$.post("${pageContext.request.contextPath}/admin/blogType/delete.do", {
								ids : ids
							}, function(result) {
								if (result.success) {
									if (result.exist) {
										$.messager.alert("系统提示", result.exist, "info");
									} else {
										$.messager.alert("系统提示", "数据已成功删除！", "info");
									}
									$("#blogTypeGrid").datagrid("reload");
								} else {
									$.messager.alert("系统提示", "数据删除失败！", "info");
								}
							}, "json");
						}
					});
				}
			}, '-', {
				iconCls : 'icon-reload',
				text : '刷新',
				handler : function() {
					$("#blogTypeGrid").datagrid("reload");
				}
			} ],
			//同列属性，但是这些列将会冻结在左侧,z大小不会改变，当宽度大于250时，会显示滚动条，但是冻结的列不在滚动条内
			frozenColumns : [ [ {
				//复选框
				field : 'checkbox',
				checkbox : true
			}, {//id字段
				field : 'id',
				title : '编号',
				width : 200
			} ] ],
			columns : [ [ {
				//typeName 字段
				field : 'typeName',
				title : '博客分类名称',
				width : 300
			}, {
				//orderNum 字段
				field : 'orderNum',
				title : '博客类别排序',
				width : 300
			}, ] ],
		});
	});

	function saveBlogType() {
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$.messager.alert("系统提示", "保存成功！", "info");
					$("#fm").form("clear");
					$("#dlg").dialog("close");
					$("#blogTypeGrid").datagrid("reload");
				} else {
					$.messager.alert("系统提示", "保存失败！", "info");
					return;
				}
			}
		});
	}
	function closeBlogTypeDialog() {
		$("#dlg").dialog("close");
		$("#fm").form("clear");
	}
</script>
</head>
<body style = "margin: 10px">
	<table id="blogTypeGrid"></table>
	<div id="dlg" class="easyui-dialog" inline="true"
		style="width: 600px; height: 180px; padding: 10px 20px" closed="true" modal="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="8px" style="font-size: 13px;">
				<tr>
					<td>博客类别名称</td>
					<td><input id="typeName" name="typeName"
						style="border-radius: 5px;" class="easyui-validatebox"
						required="true"></td>
				</tr>
				<tr>
					<td>博客类别排序</td>
					<td><input id="orderNum" name="orderNum"
						class="easyui-numberbox" required="true" style="width: 60px">&nbsp;(博客类别会根据序号从小到大排列)
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<div>
			<a href="javascript:saveBlogType()" class="easyui-linkbutton"
				iconCls="icon-ok" plain="true">保存</a> <a
				href="javascript:closeBlogTypeDialog()" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true">关闭</a>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>友情链接管理页面</title>
<script type="text/javascript">
	var url;

	$(function() {
		$('#linkGrid').datagrid({
			//头部显示的标题文本
			title : "博客信息管理",
			//请求数据的url
			url : '${basePath}/admin/link/list.do',
			//请求方法类型
			method : 'GET',
			//大小将自适应父容器
			fit : true,
			//载入提示信息
			loadMsg : 'loading...',
			//水平自动展开，如果设置此属性，则不会有水平滚动条，演示冻结列时，该参数不要设置
			fitColumns : false,
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
			toolbar : '#tb',
			//同列属性，但是这些列将会冻结在左侧,z大小不会改变，当宽度大于250时，会显示滚动条，但是冻结的列不在滚动条内
			frozenColumns : [ [ {
				//复选框
				field : 'checkbox',
				checkbox : true
			}, ] ],
			columns : [ [ {//id字段
				field : 'id',
				title : '编号',
				align : 'center',
				width : 80
			}, {
				field : 'linkName',
				title : '友情链接名称',
				align : 'center',
				width : 400
			}, {
				field : 'linkUrl',
				title : '友情链接地址',
				align : 'center',
				width : 400
			}, {
				field : 'orderNo',
				title : '排序序号',
				align : 'center',
				width : 200
			} ] ]
		});
	});

	function deleteLink() {
		var selectedRows = $("#linkGrid").datagrid("getSelections");
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
				$.post("${basePath}/admin/link/delete.do", {
					ids : ids
				}, function(result) {
					if (result.success) {
						$.messager.alert("系统提示", "数据已成功删除！", "info");
						$("#linkGrid").datagrid("reload");
					} else {
						$.messager.alert("系统提示", "数据删除失败！", "info");
					}
				}, "json");
			}
		});
	}
	function openLinkAddDialog() {

		$("#dlg").dialog("open").dialog("setTitle", "添加友情链接信息");
		url = "${basePath}/admin/link/save.do";

	}
	function openLinkModifyDialog() {
		var selectedRows = $("#linkGrid").datagrid("getSelections");
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！", "info");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑友情链接信息");
		$("#fm").form("load", row);
		url = "${basePath}/admin/link/save.do?id=" + row.id;
	}
	function saveLink() {
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$.messager.alert("系统提示", "保存成功！", "info");
					$("#dlg").dialog("close");
					$("#fm").form("clear");
					$("#linkGrid").datagrid("reload");
				} else {
					$.messager.alert("系统提示", "保存失败！", "info");
					return;
				}
			}
		});
	}
	function closeLinkDialog() {
		$("#dlg").dialog("close");
		$("#fm").form("clear");
	}
</script>
</head>
<body style="margin: 10px">
	<table id="linkGrid"></table>
	<div id="tb">
		<div>
			<a href="javascript:openLinkAddDialog()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">添加</a> <span
				class="datagrid-btn-separator"
				style="vertical-align: middle; display: inline-block; float: none"></span>
			<a href="javascript:openLinkModifyDialog()" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true">修改</a> <span
				class="datagrid-btn-separator"
				style="vertical-align: middle; display: inline-block; float: none"></span>
			<a href="javascript:deleteLink()" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 600px; height: 210px; padding: 10px 20px" closed="true"
		modal="true" buttons="#dlg-buttons">

		<form id="fm" method="post">
			<table cellspacing="8px" style="font-size: 13px;">
				<tr>
					<td>友情链接名称：</td>
					<td><input type="text" style="border-radius: 5px;"
						id="linkName" name="linkName" class="easyui-validatebox"
						required="true" /></td>
				</tr>
				<tr>
					<td>友情链接地址：</td>
					<td><input type="text"
						style="border-radius: 5px; width: 250px" id="linkUrl"
						name="linkUrl" class="easyui-validatebox" validtype="url"
						required="true" /></td>
				</tr>
				<tr>
					<td>友情链接排序：</td>
					<td><input type="text" id="orderNo" name="orderNo"
						class="easyui-numberbox" required="true" style="width: 60px" />&nbsp;(友情链接根据排序序号从小到大排序)</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:saveLink()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeLinkDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>

</html>
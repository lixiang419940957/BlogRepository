<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>
<%@include file="./common/ueditor.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客修改页面</title>
<script type="text/javascript">
	$(function() {
		$('#blogType').combobox({
			url : '${basePath}/admin/blogType/comboboxlist.do',
			method : 'GET',
			textField : 'typeName',
			valueField : 'id',
			required : true,
			editable : false
		});
	});

	function submitData() {
		var title = $("#title").val();
		var blogTypeId = $("#blogType").combobox("getValue");
		var content = UE.getEditor('editor').getContent();
		var keyWord = $("#keyWord").textbox("getValue");

		if (title == null || title == '') {
			$.messager.alert('系统提示', '请输入博客标题！', 'info');
		} else if (blogTypeId == null || blogTypeId == '') {
			$.messager.alert('系统提示', '请选择所属类别！', 'info');
		} else if (content == null || content == '') {
			$.messager.alert('系统提示', '请输入博客内容！', 'info');
		} else {
			$.ajax({
				type : "POST",
				url : "${basePath}/admin/blog/save.do",
				data : {
					"id" : '${param.id}',
					"title" : title,
					"blogType.id" : blogTypeId,
					"content" : content,
					"contentNoTag" : UE.getEditor('editor').getContentTxt(),
					"summary" : UE.getEditor('editor').getContentTxt().substr(0, 155),
					"keyWord" : keyWord
				},
				dataType : "json",
				success : function(result) {
					if (result.success) {
						$.messager.alert('系统提示', '博客修改成功！', 'info');
					} else {
						$.messager.alert('系统提示', '博客修改失败！', 'info');
					}
				}
			});
		}
	}
</script>
</head>
<body style="margin: 10px">
	<div id="p" class="easyui-panel" title="编写博客" style="padding: 10px;">

		<table style="font-size: 13px;width: 100%" cellspacing="20px">

			<tr>
				<td width="80px">博客标题：</td>
				<td><input id="title" class="easyui-validatebox"
					data-options="required:true"
					style="width: 400px; border-radius: 5px;" /></td>
			</tr>

			<tr>
				<td valign="top">所属类别：</td>
				<td><input id="blogType" /></td>
			</tr>
			<tr>
				<td>博客内容：</td>
				<td><script id="editor" type="text/plain"
						style="width:100%;height:500px;"></script></td>
			</tr>
			<tr>
				<td>关键字：</td>
				<td><input id="keyWord" class="easyui-textbox"
					style="width: 400px"></td>
			</tr>
			<tr>
				<td></td>
				<td><a href="javascript:submitData()" class="easyui-linkbutton"
					data-options="iconCls:'icon-submit', width:'100px' ">发布博客</a></td>
			</tr>

		</table>

	</div>
	<script type="text/javascript">
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor');
	
		ue.addListener("ready", function() {
			//通过ajax请求数据
			UE.ajax.request("${basePath}/admin/blog/queryBlogById.do", {
				method : "GET",
				async : false,
				data : {
					"id" : "${param.id}"
				},
				onsuccess : function(result) {
					result = eval("(" + result.responseText + ")");
					$("#title").val(result.title);
					$("#blogType").combobox("setValue", result.blogType.id);
					$("#keyWord").textbox("setValue", result.keyWord);
					UE.getEditor('editor').setContent(result.content);
				}
			});
		});
	</script>
</body>
</html>
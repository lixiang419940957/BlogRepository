<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>
<%@include file="./common/ueditor.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息页面</title>

<script type="text/javascript">
	function submitData() {
		var proFile = UE.getEditor('proFile').getContent();
		if (proFile == null || proFile == '') {
			$.messager.alert("系统提示", "请输入个性简介！", "info");
			return;
		}
		$("#fm").form("submit", {
			url : "${basePath}/admin/blogger/save.do",
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$.messager.alert("系统提示", "保存成功！", "info");
				} else if (result.isLegal) {
					$.messager.alert("系统提示", "图片格式非法！", "info");
				} else {
					$.messager.alert("系统提示", "保存失败！", "info");
					return;
				}
			}
		});
	}
</script>

</head>
<body style="margin: 10px">
	<div id="p" class="easyui-panel" title="修改个人信息" style="padding: 10px">
		<form id="fm" method="post" enctype="multipart/form-data">
			<table cellspacing="20px" style="font-size: 13px;">
				<tr>
					<td width="80px">用户名：</td>
					<td><input type="hidden" id="id" name="id"
						value="${currentUser.id }" /> <input id="userName"
						class="easyui-validatebox" data-options="required:true"
						name="userName" style="width: 200px;"
						value="${currentUser.userName }" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>昵称：</td>
					<td><input id="nickName" name="nickName"
						class="easyui-validatebox" data-options="required:true"
						style="width: 200px;" /></td>
				</tr>
				<tr>
					<td>个性签名：</td>
					<td><input id="sign" name="sign" class="easyui-validatebox"
						data-options="required:true" style="width: 400px;" /></td>
				</tr>
				<tr>
					<td>个人头像：</td>
					<td><input class="easyui-filebox" id="imageFile"
						name="imageFile"
						data-options="required:true,buttonText:'选择个人头像',accept:'.bmp,.jpg,.jpeg,.gif,.png '"
						style="width: 400px;" /></td>
				</tr>
				<tr>
					<td valign="top">个人简介：</td>
					<td><script id="proFile" type="text/plain"
							style="width:100%;height:500px;"></script> <input type="hidden"
						id="pF" name="proFile" /></td>
				</tr>
				<tr>
					<td></td>
					<td><a href="javascript:submitData()"
						class="easyui-linkbutton" data-options="iconCls:'icon-submit'">提交</a>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript">
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('proFile');

		ue.addListener("ready", function() {
			//通过ajax请求数据
			UE.ajax.request("${basePath}/admin/blogger/find.do", {
				method : "get",
				async : false,
				data : {},
				onsuccess : function(result) {
					result = eval("(" + result.responseText + ")");
					$("#nickName").val(result.nickName);
					$("#sign").val(result.sign);
					UE.getEditor('proFile').setContent(result.proFile);
				}
			});
		});
	</script>
</body>
</html>
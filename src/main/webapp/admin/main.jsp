<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="./common/head.jsp"%>
<title>博客系统后台管理页面</title>
</head>

<script type="text/javascript">
	$(function() {
		//监听右键事件，创建右键菜单
		$("#tabs").tabs({
			onContextMenu : function(e, title, index) {
				e.preventDefault();
				if (index > 0) {
					$("#mm").menu('show', {
						left : e.pageX,
						top : e.pageY
					}).data("tabTitle", title);
				}
			}
		});
		//右键菜单click
		$("#mm").menu({
			onClick : function(item) {
				closeTab(this, item.name);
			}
		});
	});

	//删除Tabs
	function closeTab(menu, type) {
		var allTabs = $("#tabs").tabs('tabs');
		var allTabtitle = [];
		$.each(allTabs, function(i, n) {
			var opt = $(n).panel('options');
			if (opt.closable)
				allTabtitle.push(opt.title);
		});
		var curTabTitle = $(menu).data("tabTitle");
		var curTabIndex = $("#tabs").tabs("getTabIndex", $("#tabs").tabs("getTab", curTabTitle));
		switch (type) {
		case 1: //关闭
			$("#tabs").tabs("close", curTabIndex);
			return false;
			break;
		case 2: //全部关闭
			for (var i = 0; i < allTabtitle.length; i++) {
				$("#tabs").tabs('close', allTabtitle[i]);
			}
			break;
		case 3: //除此之外全部关闭
			for (var i = 0; i < allTabtitle.length; i++) {
				if (curTabTitle != allTabtitle[i])
					$("#tabs").tabs('close', allTabtitle[i]);
			}
			$("#tabs").tabs('select', curTabTitle);
			break;
		case 4: //当前页右侧全部关闭
			for (var i = curTabIndex; i < allTabtitle.length; i++) {
				$("#tabs").tabs('close', allTabtitle[i]);
			}
			$("#tabs").tabs('select', curTabTitle);
			break;
		case 5: //当前页左侧全部关闭
			for (var i = 0; i < curTabIndex - 1; i++) {
				$("#tabs").tabs('close', allTabtitle[i]);
			}
			$("#tabs").tabs('select', curTabTitle);
			break;
		case 6: //刷新
			var panel = $("#tabs").tabs("getTab", curTabTitle).panel("refresh");
			break;
		}
	}

	function openTab(text, url, iconCls) {
		if ($("#tabs").tabs("exists", text)) {
			$("#tabs").tabs("select", text);

			// 刷新面板
			var tab = $("#tabs").tabs('getTab', text); // 获取选择的面板
			var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${basePath}/admin/" + url + "'></iframe>";
			$("#tabs").tabs('update', {
				tab : tab,
				options : {
					content : content
				}
			});

		} else {
			var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${basePath}/admin/" + url + "'></iframe>";
			$("#tabs").tabs("add", {
				title : text,
				iconCls : iconCls,
				closable : true,
				content : content
			});
		}
	}
	function refreshSystem() {
		$.ajax({
			type : "POST",
			url : "${basePath}/admin/system/refreshSystem.do",
			data : {
			},
			dataType : "json",
			success : function(result) {
				if (result.success) {
					$.messager.alert('系统提示', '已成功刷新系统缓存！', 'info');
				} else {
					$.messager.alert('系统提示', '刷新系统缓存失败！', 'info');
				}
			}
		});
	}
	function openPasswordModifyDialog() {
		$("#newPassword").val("");
		$("#newPassword2").val("");
		$("#dlg").dialog("open").dialog("setTitle", "修改密码");
	}
	function modifyPassword() {
		$("#fm").form("submit", {
			url : "${basePath}/admin/blogger/modifyPassword.do?id=${currentUser.id}",
			onSubmit : function() {
				var newPassword = $("#newPassword").val();
				var newPassword2 = $("#newPassword2").val();
				if (!$(this).form("validate")) {
					return false;
				}
				if (newPassword != newPassword2) {
					$.messager.alert("系统提示", "输入的密码不一致！", "info");
					return false;
				}
				return true;
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$.messager.alert("系统提示", "密码修改成功，下一次登录生效！", "info");
					$("#newPassword").val("");
					$("#newPassword2").val("");
					$("#dlg").dialog("close");
				} else {
					$.messager.alert("系统提示", "密码修改失败！", "info");
					return;
				}
			}
		});
	}

	function closePasswordModifyDialog() {
		$("#newPassword").val("");
		$("#newPassword2").val("");
		$("#dlg").dialog("close");
	}
	function logout() {
		$.messager.confirm("系统提示", "您确定要退出系统吗？", function(r) {
			if (r) {
				window.location.href = '${basePath}/admin/blogger/logout.do';
			}
		});
	}
</script>

<body class="easyui-layout">
	<div region="north" style="height: 80px; background-color: #E0ECFF">

		<table>
			<tr>
				<td width="50%">
					<h1>博客后台系统</h1>
				</td>
				<td valign="bottom" align="right" width="50%"><font size="3">&nbsp;&nbsp;<strong>欢迎：</strong>${currentUser.userName }
				</font></td>
			</tr>
		</table>

	</div>
	<div region="west" title="导航菜单" split="true" style="width: 200px;">

		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="常用操作" data-options="selected:true,iconCls:'icon-item'"
				style="padding: 10px">
				<a href="javascript:openTab('写博客','writeBlog.jsp','icon-writeblog')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-writeblog',width:'150px'"
					style="text-align: left;">写博客</a> <a
					href="javascript:openTab('评论审核','commentReview.jsp','icon-review')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-review',width:'150px'"
					style="text-align: left;">评论审核</a>
			</div>
			<div title="博客管理" data-options="selected:true,iconCls:'icon-bkgl'"
				style="padding: 10px;">
				<a href="javascript:openTab('写博客','writeBlog.jsp','icon-writeblog')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-writeblog',width:'150px'"
					style="text-align: left;">写博客</a> <a
					href="javascript:openTab('博客信息管理','blogManage.jsp','icon-bkgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-bkgl',width:'150px'"
					style="text-align: left;">博客信息管理 </a>
			</div>
			<div title="博客类别管理" data-options="iconCls:'icon-bklb'"
				style="padding: 10px">
				<a
					href="javascript:openTab('博客类别信息管理','blogTypeManage.jsp','icon-bklb')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-bklb',width:'150px'"
					style="text-align: left;">博客类别信息管理</a>
			</div>
			<div title="评论管理" data-options="iconCls:'icon-plgl'"
				style="padding: 10px">
				<a
					href="javascript:openTab('评论审核','commentReview.jsp','icon-review')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-review',width:'150px'"
					style="text-align: left;">评论审核</a> <a
					href="javascript:openTab('评论信息管理','commentManage.jsp','icon-plgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-plgl',width:'150px'"
					style="text-align: left;">评论信息管理</a>
			</div>
			<div title="个人信息管理" data-options="iconCls:'icon-grxx'"
				style="padding: 10px">
				<a
					href="javascript:openTab('修改个人信息','modifyBlogger.jsp','icon-grxxxg')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-grxxxg',width:'150px'"
					style="text-align: left;">修改个人信息</a>
			</div>
			<div title="系统管理" data-options="iconCls:'icon-system'"
				style="padding: 10px">
				<a href="javascript:openTab('友情链接管理','linkManage.jsp','icon-link')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-link',width:'150px'"
					style="text-align: left;">友情链接管理</a> <a
					href="javascript:openPasswordModifyDialog()"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-modifyPassword',width:'150px'"
					style="text-align: left;">修改密码</a> <a
					href="javascript:refreshSystem()" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-refresh',width:'150px'"
					style="text-align: left;">刷新系统缓存</a> <a href="javascript:logout()"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-exit',width:'150px'"
					style="text-align: left;">安全退出</a>
			</div>
		</div>

	</div>
	<div region="center" style="padding: 5px; background: #eee;">

		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" data-options="iconCls:'icon-home'">
				<div align="center" style="padding-top: 100px">
					<font color="red" size="10">欢迎使用</font>
				</div>
			</div>
		</div>

	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:400px;height:200px;padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">

		<form id="fm" method="post">
			<table cellspacing="8px">
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="userName" name="userName"
						readonly="readonly" value="${currentUser.userName }"
						style="width: 200px" /></td>
				</tr>
				<tr>
					<td>新密码：</td>
					<td><input type="password" id="newPassword" name="newPassword"
						class="easyui-validatebox" required="true" style="width: 200px" /></td>
				</tr>
				<tr>
					<td>确认新密码：</td>
					<td><input type="password" id="newPassword2"
						name="newPassword2" class="easyui-validatebox" required="true"
						style="width: 200px" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:modifyPassword()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a
			href="javascript:closePasswordModifyDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	<div id="mm" class="easyui-menu" style="width: 120px;">
		<!-- 		<div id="mm-tabclosrefresh" data-options="name:6">刷新</div> -->
		<div id="mm-tabclose" data-options="name:1">关闭</div>
		<div id="mm-tabcloseall" data-options="name:2">全部关闭</div>
		<div id="mm-tabcloseother" data-options="name:3">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright" data-options="name:4">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft" data-options="name:5">当前页左侧全部关闭</div>
	</div>

	<div region="south" style="height: 25px; padding: 5px" align="center">
		© 2017-? 考拉的究极开发团队 博客系统 版权所有</div>

</body>
</html>
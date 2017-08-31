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
	function openTab(text, url, iconCls) {
		if ($("#tabs").tabs("exists", text)) {
			$("#tabs").tabs("select", text);
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
</script>

<body class="easyui-layout">
	<div region="north" style="height: 80px; background-color: #E0ECFF">

		<table>
			<tr>
				<td width="50%">
					<h1>博客后台系统</h1>
				</td>
				<td valign="bottom" align="right" width="50%"><font size="3">&nbsp;&nbsp;<strong>欢迎：</strong>XXX
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
					style="text-align: left;">博客信息管理</a>
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
				<a href="javascript:openTab('修改个人信息','','')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-grxxxg',width:'150px'"
					style="text-align: left;">修改个人信息</a>
			</div>
			<div title="系统管理" data-options="iconCls:'icon-system'"
				style="padding: 10px">
				<a href="javascript:openTab('友情链接管理','','')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-link',width:'150px'"
					style="text-align: left;">友情链接管理</a> <a
					href="javascript:openTab('修改密码','','')" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-modifyPassword',width:'150px'"
					style="text-align: left;">修改密码</a> <a
					href="javascript:openTab('刷新系统缓存','','')" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-refresh',width:'150px'"
					style="text-align: left;">刷新系统缓存</a> <a
					href="javascript:openTab('安全退出','','')" class="easyui-linkbutton"
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
	<div region="south" style="height: 25px; padding: 5px" align="center">
		© 2017-? 考拉的究极开发团队 博客系统 版权所有</div>

</body>
</html>
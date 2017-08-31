<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论审核管理页面</title>

<script type="text/javascript">
	$(function() {
		$('#commentGrid').datagrid({
			//头部显示的标题文本
			title : "博客信息管理",
			//请求数据的url
			// 			url : '${basePath}/admin/blog/list.do',
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
				field : 'blog',
				title : '标题',
				align : 'center',
				width : 300
			}, {
				field : 'userIp',
				title : '用户IP',
				align : 'center',
				width : 150
			}, {
				field : 'content',
				title : '评论内容',
				align : 'center',
				width : 400
			}, {
				field : 'commentDate',
				title : '评论日期',
				align : 'center',
				width : 150
			} ] ]
		});
	});
</script>

</head>
<body style="margin: 10px">
	<table id="commentGrid"></table>
</body>
</html>
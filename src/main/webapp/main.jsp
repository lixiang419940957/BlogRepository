<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle}</title>
<link rel="stylesheet"
	href="${basePath}/static/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${basePath}/static/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${basePath}/static/css/blog.css">
<!-- <link href="http://blog.java1234.com/favicon.ico" rel="SHORTCUT ICON"> -->
<script src="${basePath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${basePath}/static/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body style="padding-top: 10px; padding-bottom: 40px;">
	<div class="container">
		<jsp:include page="/foreground/common/head.jsp" />
		<jsp:include page="/foreground/common/menu.jsp" />

		<div class="row">

			<div class="col-md-9">
				<jsp:include page="${mainPage }"></jsp:include>
			</div>
			<div class="col-md-3">
				<div class="data_list">
					<div class="data_list_title">
						<img src="${basePath}/static/images/user_icon.png" /> 博主信息
					</div>
					<div class="user_image">
						<%-- 						<img src="${basePath}/static/userImages/${blogger.imageName }" /> --%>
						<img src="${basePath}/static/userImages/lbxn.jpg" />
					</div>
					<div class="nickName">${blogger.nickName }</div>
					<div class="userSign">(${blogger.sign })</div>
				</div>

				<div class="data_list">
					<div class="data_list_title">
						<img src="${basePath}/static/images/byType_icon.png" /> 按日志类别
					</div>
					<div class="datas">
						<ul>
							<c:forEach var="blogTypeCount" items="${blogTypeCountList }">
								<li><span><a
										href="${basePath}/index.html?typeId=${blogTypeCount.id }">${blogTypeCount.typeName }(${blogTypeCount.blogCount })</a></span></li>
							</c:forEach>
						</ul>
					</div>
				</div>

				<div class="data_list">
					<div class="data_list_title">
						<img src="${basePath}/static/images/byDate_icon.png" /> 按日志日期
					</div>
					<div class="datas">
						<ul>
							<c:forEach var="blogCount" items="${blogCountList }">
								<li><span><a
										href="${basePath}/index.html?releaseDateStr=${blogCount.releaseDateStr }">${blogCount.releaseDateStr }(${blogCount.blogCount })</a></span></li>
							</c:forEach>
						</ul>
					</div>
				</div>

				<div class="data_list">
					<div class="data_list_title">
						<img src="${basePath}/static/images/link_icon.png" /> 友情链接
					</div>
					<div class="datas">
						<ul>
							<c:forEach var="link" items="${linkCountList }">
								<li><span><a href="${link.linkUrl }" target="_blank">${link.linkName }</a></span></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="/foreground/common/foot.jsp" />
	</div>
	<%-- 
	 	color: 线条颜色, 默认: '0,0,0' ；三个数字分别为(R,G,B)，注意用,分割 
	 	opacity: 线条透明度（0~1）, 默认: 0.5 
	 	count: 线条的总数量, 默认: 150 
	 	zIndex: 背景的z-index属性，css属性用于控制所在层的位置, 默认: -1 
	--%>
	<script type="text/javascript" color="0,104,183" opacity='1' count="66"
		zIndex="-2" src="${basePath}/static/js/canvas-nest.js"></script>

</body>
</html>
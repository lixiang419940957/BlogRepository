<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="admin/common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function checkHtml(str) {
		//匹配非法字符如:< > & / ' |   
//		var reg = /<[^>]+>/g;
		var rag = "[^<>&/|'\]+";
		var flag = rag.test(str);
		alert(flag);
// 		return rag.test(str);
	}
	
	function a(){
		var val = $("#abc").val();
		var type =  typeof(val);
		alert(type);
// 		var rag = /[^<>&/|']+/;
// 		var rag = /[`~!@#$%^&*_+<>{}\/'[\]]/im;
		var rag =  />|</;
// 		var rag =  /[@#\$%\^&\*<>]+/g ;
// 		var rag = /[^%&',;=?$\x22]+/ ;
		var flag = rag.test(val);
		if(flag){
			alert(flag);
			alert("存在特殊字符");
		} else {
			alert("不存在");
		}
		
	}
</script>
<body>
<!-- 	<h1>Hello,myblog!</h1> -->
	<input type="text" id="abc"></input>
	<input type="button" onclick="a()" value="测试"></input>
</body>
</html>
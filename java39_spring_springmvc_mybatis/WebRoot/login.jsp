<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML >
<html>
<head>
<base href="<%=basePath%>">
<title>系统登录 - 超市账单管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css"></style>

<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body class="login_bg">
	<section class="loginBox">
		<header class="loginHeader">
			<h1>超市账单管理系统</h1>
		</header>
		<section class="loginCont">
			<form class="loginForm" action="user/login">
				<div class="inputbox">
					<label for="user">用户名：</label>
					<input id="user" type="text" name="username" placeholder="请输入用户名" required />
				</div>
				<div class="inputbox">
					<label for="mima">密码：</label>
					<input id="mima" type="password" name="password" placeholder="请输入密码" required />
				</div>
				<div class="subBtn">
					<input type="submit" value="登录" />
					<input type="reset" value="重置" />
				</div>
			</form>
		</section>
	</section>
</body>
</html>

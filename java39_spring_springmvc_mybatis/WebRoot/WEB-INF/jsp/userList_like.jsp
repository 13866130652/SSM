<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>超市账单管理系统</title>
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	  <header class="publicHeader">
        <h1>超市账单管理系统</h1>
        <div class="publicHeaderR">
            <p><span>下午好！</span><span style="color: #fff21b">  ${User_login.userName}</span> , 欢迎你！</p>
            <a href="user/loginout">退出</a>
        </div>
    </header>
<!--时间-->
    <section class="publicTime">
        <span id="time">2015年1月1日 11:11  星期一</span>
        <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
    </section>
<!--主体内容-->
    <section class="publicMian ">
        <div class="left">
            <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
            <nav>
                <ul class="list">
                    <li><a href="billList.html">账单管理</a></li>
                    <li><a href="providerList.html">供应商管理</a></li>
   <!-- 页面已经处理 --><li  id="active"><a href="user/userListLike">用户管理</a></li>
                    <li><a href="password.html">密码修改</a></li>
   <!-- 页面已经处理 --><li><a href="user/loginout">退出系统</a></li>
                </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理</span>
            </div>
            <div class="search">
            	<form action="user/userListLike" method="get" calss="search_form">
                	<span>用户名：</span>
                	<input type="text" placeholder="请输入用户名" name="userName" value="${USER_NAME}"/>
               	 	<input type="submit" value="查询" id="sub"/>
               	 	<a href="user/toUserAdd">添加用户</a>
                </form>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户编码</th>
                    <th width="20%">用户名</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户类型</th>
                    <th width="30%">操作</th>
                </tr>
               <c:forEach items="${USER_LIST_LIKE}" var="list" varStatus="i">
              	   	<tr>
                    <td>${list.userCode}</td>
                    <td>${list.userName}</td>
                    	<c:if test="${list.gender==1}">
                    	<td>男</td>
                    	</c:if>
                    	<c:if test="${list.gender==2}">
                    	<td>女</td>
                    	</c:if>
                    <td>${list.age}</td>
                    <td>${list.phone}</td>
                    <td>${list.role.roleName}</td>
                    <td>
                        <a href="user/toUserView?id=${list.id}"><img src="img/read.png" alt="查看" title="查看"/></a>
                        <a href="user/toUpdate?id=${list.id}"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                        <a href="user/deleteById?id=${list.id}" class="removeUser"><img src="img/schu.png" alt="删除" title="删除"/></a>
                    </td>
               	 </tr>
               </c:forEach>
            </table>
            <p>
            	<span>[第${PAGE_NUM}/共${TOTAL_PAGE}页]</span>&nbsp;&nbsp;
            	<a href="user/userListLike?userName=${USER_NAME}&pageNum=1">首页</a>&nbsp;&nbsp;
            	<a href="user/userListLike?userName=${USER_NAME}&pageNum=${PAGE_NUM-1}">上一页</a>&nbsp;&nbsp;
            	<a href="user/userListLike?userName=${USER_NAME}&pageNum=${PAGE_NUM+1}">下一页</a>&nbsp;&nbsp;
            	<a href="user/userListLike?userName=${USER_NAME}&pageNum=${TOTAL_PAGE}">末页</a>
            </p>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

    <footer class="footer">
        版权归北大青鸟
    </footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>
</body>
</html>

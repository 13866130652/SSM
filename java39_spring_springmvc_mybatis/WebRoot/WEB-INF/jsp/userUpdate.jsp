<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script src="laydate/laydate.js" ></script>
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>超市账单管理系统</h1>

    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"> ${User_login.userName}</span> , 欢迎你！</p>
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
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <form action="user/doUpdate" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <input type="hidden" name="id" value="${USER_UPDATE.id }"/>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" placeholder="${USER_UPDATE.userName }"/>
                    <span >*</span>
                </div>

                <div>
                    <label >用户性别：</label>
                    <select name="gender">
                        <option value="1">男</option>
                        <option value="2" selected>女</option>
                    </select>
                </div>
                <div>
                    <label for="data">出生日期：</label>
                    <fmt:formatDate value="${USER_UPDATE.birthday }" var="birth" pattern="yyyy-MM-dd HH:mm:ss"/>
                    <input type="text" name="birthday" id="data" value="${birth}" onclick="laydate()"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="phone" id="userphone" placeholder="${USER_UPDATE.phone }"/>
                    <span >*</span>
                </div>
                <div>
                   
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="address" id="userAddress" placeholder="${USER_UPDATE.address }"/>
                </div>
                <div>
                    <label >用户类别：</label>
                    <input type="radio" name="userRole" value="1"/>管理员
                    <input type="radio" name="userRole" checked value="2"/>经理
                    <input type="radio" name="userRole"/ value="3">普通用户
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="submit" value="保存" />
                    <input type="button" value="返回" />
                </div>
            </form>
        </div>
    </div>
</section>
<footer class="footer">
    版权归北大青鸟
</footer>
<script src="js/time.js"></script>

</body>
</html>
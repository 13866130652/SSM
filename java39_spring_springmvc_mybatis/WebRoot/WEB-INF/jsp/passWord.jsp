<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
</head>
<body>
<!--头部-->
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
   					<li  id="active"><a href="user/userListLike">用户管理</a></li>
                    <li><a href="user/topassword">密码修改</a></li>
   					<li><a href="user/loginout">退出系统</a></li>
            </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>密码修改页面</span>
            </div>
            <div class="providerAdd">
                <form action="user/doPassWord" id="my_form">
                    <!--div的class 为error是验证错误，ok是验证成功-->
                     <input type="hidden" name="id" value="${User_login.id}"/>
                    <div class="">
                        <label for="oldPassword">旧密码：</label>
                        <input type="password" name="oldPassword" id="oldPassword" required/>
                        <span>*请输入原密码</span>
                    </div>
                    <div>
                        <label for="newPassword">新密码：</label>
                        <input type="password" name="newPassword" id="newPassword" required/>
                        <span >*请输入新密码</span>
                    </div>
                    <div>
                        <label for="reNewPassword">确认新密码：</label>
                        <input type="password" name="userPassword" id="reNewPassword" required/>
                        <span >*请输入新确认密码，保证和新密码一致</span>
                    </div>
                    <div class="providerAddBtn">
                        <!--<a href="#">保存</a>-->
                        <input type="submit" value="保存"/>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <footer class="footer">
        	版权归北大青鸟
    </footer>
<script src="js/time.js"></script>
<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
<script type="text/javascript">
	$(function (){
		//判断密码是否正确
		function checkOreginalPass (){
			var sp = $("#oldPassword").next();
			sp.html("");
			var value = $("#oldPassword").val();
			var pass = "${User_login.userPassword}";
			if(pass==value){
				sp.html("&radic;").css("color","green");
				return true;
			}else{
				sp.html("你输入的密码错误").css("color","red");
				return false;
			}
		}
		
		//新密码是否符合要求
		function checkNewPass(){
			var reg =/^[a-zA-Z]{1,6}\w{5,9}$/;
			var newPassword = $("#newPassword");
			var sp = newPassword.next();
			var value = newPassword.val();
			sp.html("");
			if(value.length==0){
				sp.html("密码不能为空").css("color","red");
				return false;
			}else if(!reg.test(value)){
				sp.html("以字母开头长度12个字符！").css("color","red");
				return false;
			}else{
				sp.html("&radic;").css("color","green");
				return true;
			}
		}
		
		//判断两次密码的一致性
		function checkPass(){
			var v1 = $("#newPassword").val();
			var v2 = $("#reNewPassword").val();
			var sp = $("#reNewPassword").next();
			sp.html("");
			if(v2==v1){
				sp.html("&radic;").css("color","green");
				return true;
			}else{
				sp.html("两次密码不一致").css("color","red");
				return false;
			}
		}
		
		$("#oldPassword").blur(function(){
			checkOreginalPass();
		});
		
		$("#newPassword").blur(function(){
			checkNewPass();
		});
		
		$("#reNewPassword").blur(function(){
			checkPass();
		});
		
		//判断表单是否可以提交
		$("#my_form").submit(function(){
			var k = checkOreginalPass()&checkNewPass()&checkPass();
			return k==1?true:false;
		});
	});
</script>
</body>
</html>
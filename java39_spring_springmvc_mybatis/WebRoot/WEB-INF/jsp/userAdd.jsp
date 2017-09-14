<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>超市账单管理系统-用户添加</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
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
                <li><a href="billList.html">账单管理</a></li>
                <li ><a href="providerList.html">供应商管理</a></li>
                <li id="active"><a href="user/userListLike">用户管理</a></li>
                <li><a href="password.html">密码修改</a></li>
                <li><a href="user/loginout">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="user/doUserAdd" id="my_form" method="post" enctype="multipart/form-data">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="userId">用户编码：</label>
                    <input type="text" name="userCode" id="userCode"/>
                    <span>*请输入用户编码，且不能重复</span>
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName"/>
                    <span >*请输入用户名称</span>
                </div>
                <div>
                    <label for="userpassword">用户密码：</label>
                    <input type="password" name="userPassword" id="userPassword"/>
                    <span>*密码长度必须大于6位小于20位</span>

                </div>
                <div>
                    <label for="userRemi">确认密码：</label>
                    <input type="password" name="userRemi" id="userRemi"/>
                    <span>*请输入确认密码</span>
                </div>
                <div>
                    <label >用户性别：</label>
                    <select name="gender" id="sex"></select>
                    <span></span>
                </div>
                <div>
                    <label for="data">出生日期：</label>
                    <input type="text" name="birthday" id="birthday" onclick="laydate()"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="phone" id="phone"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="address" id="address"/>
                </div>
                <div>
                	<label for="userRole">用户类型：</label>
                   <select name="userRole" id="userRole"></select>
                </div>
                 <div>
                    <label for="a_idPicPath">上传头像：</label>
                    <input type="file" name="a_idPicPath" id="a_idPicPath" />
                    <span >*</span>
                </div>
                <div class="providerAddBtn">
                    <input type="submit" value="保存" id="sub" />
                    <input type="button" value="返回" onclick="history.back(-1)"/>
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
<script type="text/javascript" src="laydate/laydate.js"></script>
<script type="text/javascript">
	$(function(){
		//1、检查用户编码是否占用
		function checkUserCode(){
			var value= $("#userCode").val();
			$("#userCode").next().html();
			if(value==''||value.length==0){
				 $("#userCode").next().html("用户编码不能为空！").css("color","red");
			 	return false;
			}
			$.ajax({
				type:"GET",
				url:"user/userExits",
				data:{userCode:value},//"userCode="+value
				dataType:"json",
				success:function(data){
					if(data.userCode=="exits"){
						 $("#userCode").next().html("用户编码已经存在").css("color","red");
						 return false;
					}else{
						 $("#userCode").next().html("用户编码不存在，可以使用").css("color","green");
						 return true;
					}
				},
				error:function(){
					alert("程序出错了！");
					return false;
				}
			});//end of ajax
			return true;
		}
		//失去焦点时进行验证
		$("#userCode").blur(function(){
			checkUserCode();
		});
		
		//表单提交验证
		$("#my_form").submit(function(){
			var k = checkUserCode();
			return k==1?true:false;
		});
		
		//使用post获得用户角色和id
		$.post("role/getRoles",function(data){
			var v =JSON.parse(data);
			if(v!=null){
				var sl = $("#userRole");
				 for(var i=0;i<v.length;i++){
					var id = v[i].id;
					var roleName=v[i].roleName;
					var op = $("<option value='"+id+"'>"+roleName+"</option>");
					sl.append(op);
				} 
			}
		},"text");
		
		//获得用户的性别Written [[1,2]] as "text/html;charset=UTF-8" StringHttpMessageConverter
		$.get("user/userSex", function(data){
			var v =JSON.parse(data);
			if(v!=null){
				for(var i = 0 ;i<v.length;i++){
					var st =v[i]==1?"男":"女";
					$("#sex").append("<option value='"+v[i]+"'>"+st+"</option>");
				}
			}
		},"text");
	});
</script>
</body>
</html>
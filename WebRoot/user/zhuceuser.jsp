<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户注册</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="<%=basePath %>jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<style>
body{background: #fff url(<%=basePath%>shouye/image/shouye.jpg) 50% 0 no-repeat;margin:0;padding:0;}
#login{width:420px;height:420px;position:absolute;left:50%;top:30%;font-size:12px;line-height:24px;margin:-222px auto auto -210px;background:#fbfbfb;}
#login .logo{height:78px; text-align:center; font-size:30px; font-weight:bold; color:#4c91d1; margin-top:40px;}
#login .main{height:550px;background:#fff;overflow:hidden; border:#CCC 1px solid;box-shadow: 0 0 3px #ccc;}
#login .copyright{height:26px;line-height:20px;text-align:center;font-family:arial;color:#bbb;}
#login .copyright a{color:#bbb;text-decoration:none;}
#login .copyright a:hover{color:#f60;}

#login .main form{margin:50px 62px 0 62px;}
#login .main form ul{margin:0;padding:0;list-style:none;}
#login .main form ul li{font-size:14px;color:#555;margin-bottom:18px;padding:0;position:relative;}
#login .main form ul li img{vertical-align:middle;cursor:pointer;}
#login .main form ul li input{border:1px solid #e5e5e5;padding:2px;border-radius:10px;color:#444;vertical-align:middle;outline:none;}
#login .main form ul li select{border:1px solid #e5e5e5;padding:2px;border-radius:10px;color:#444;vertical-align:middle;outline:none;}
#login .main form ul li .wa{width:160px;}
#login .main form ul li .wb{width:70px;}
#login .main form ul li .bnt{width:292px;border:0;background:#4C91D1;color:#fff;border-radius:4px;padding:12px 0;margin:0;font-size:16px;font-family:microsoft yahei;cursor:pointer;}
#login .main form ul li .bnt:hover{background:#09A3DC;}

#login .main form i{width:20px;height:24px;display:block;position:absolute;top:10px;left:74px;border-right:1px solid #e5e5e5;padding-right:8px;font-size:18px;color:#999;}


#login .main .api{text-align:center;color:#999;margin-top:-5px;}
#login .main .api:hover{color:#4C91D1;}
#login .main .api a{color:#666;text-decoration:none;height:16px;line-height:16px;overflow:hidden;font-size:14px;}
#login .main .api a:hover{color:#4C91D1;}
.msg-wrap{margin-left:50px;}</style>
</head>
<body>
<div id="login">
        <div class="logo">用户注册</div>
        <div class="main">
            <form action="<%=basePath%>zhuceUser" name="form1" method="post" onSubmit="return check()">
            <ul>
                <li>登录名：<input class="wa" type="text" name="userName" id="userName"/></li>
                <li>密&nbsp;&nbsp;&nbsp;&nbsp;码：<input class="wa" type="password" name="userPassword" id="userPassword"/></li>
                <li>密&nbsp;&nbsp;&nbsp;&nbsp;码：<input class="wa" type="password" name="userPassword1" id="userPassword1"/></li>
                <li>姓&nbsp;&nbsp;&nbsp;&nbsp;名：<input class="wa" type="text" name="userXingming" id="userXingming"/></li>
                <li>年&nbsp;&nbsp;&nbsp;&nbsp;龄：<input class="wa" type="text" name="userAge" id="userAge"/></li>
                <li>性&nbsp;&nbsp;&nbsp;&nbsp;别：<select id="userSex" name="userSex" class="wa">
									<option value="0">男</option>
									<option value="1">女</option>
								</select>
				</li>
                <li>电&nbsp;&nbsp;&nbsp;&nbsp;话：<input class="wa" type="text" name="userPhone" id="userPhone"/></li>
                <li>地&nbsp;&nbsp;&nbsp;&nbsp;址：<input class="wa" type="text" name="userMark1" id="userMark1"/></li>
                <li><input type="submit" value="注 册" class="bnt" style="width:48%" />&nbsp;<input type="reset" value="重 置" class="bnt" style="width:48%" /></li>
                <li><font color="red">${error }</font></li>
            </ul>
            </form>
    	</div>
</div>
	<script type="text/javascript">
		function check() {
			var userName = document.form1.userName.value;
			var userPassword = document.form1.userPassword.value;
			var userPassword1 = document.form1.userPassword1.value;
			var userXingming = document.form1.userXingming.value;
			var userAge = document.form1.userAge.value;
			var userPhone = document.form1.userPhone.value;
			var reg = /^[a-zA-Z0-9_]{6,}$/;
			if (!reg.test(userName)) {
				document.form1.userName.focus();
				alert("用户名不符合规则，6位以上");
				return false;
			}
			if (!reg.test(userPassword)) {
				document.form1.userPassword.focus();
				alert("密码不符合规则，6位以上");
				return false;
			}

			if (document.form1.userPassword1.value == "") {
				alert("请输入确认密码");
				document.form1.userPassword1.focus();
				return false;
			}
			if (document.form1.userPassword.value != document.form1.userPassword1.value) {
				alert("两次输入密码不一致");
				document.form1.userPassword1.focus();
				return false;
			}
			if (document.form1.userXingming.value == "") {
				alert("姓名为必填");
				document.form1.userXingming.focus();
				return false;
			}
			
			//定义正则表达式部分    
			var reg1 = /^[0-9]{0,}$/;
			var reg2 = /^[1-9]{1}[0-9]{0,}$/;
			//alert(numberValue);
			if(userAge ==null || userAge.length==0 || userAge.length>2){
				alert('请输入有效的年龄！');
				document.form1.userAge.focus();
				return false;
			}
			if((!reg1.test(userAge))||(!reg2.test(userAge))){
				alert('请输入有效的年龄！');
				document.form1.userAge.focus();
				return false;
			}
			
			//定义正则表达式部分    手机号码和邮箱
			var regphone = /^[1][3,4,5,7,8][0-9]{9}$/;
			var regemail = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
			if (!regphone.test(userPhone)) {
				alert('请输入有效的手机号码！');
				document.form1.userPhone.focus();
				return false;
			}
		}
	</script>
</body>
</html>
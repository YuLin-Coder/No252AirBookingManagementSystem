<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.*" 
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	// 权限验证
	Yonghu yonghu = (Yonghu)session.getAttribute("yonghu");
	int yonghuId = yonghu.getYonghuId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>
<style>
body{background: #fff 50% 0 no-repeat;margin:0;padding:0;}
#login{width:420px;height:420px;position:absolute;left:50%;top:30%;font-size:12px;line-height:24px;margin:-222px auto auto -210px;background:#fbfbfb;}
#login .logo{height:78px; text-align:center; font-size:30px; font-weight:bold; color:#4c91d1; margin-top:40px;}
#login .main{height:314px;background:#fff;overflow:hidden; border:#CCC 1px solid;box-shadow: 0 0 3px #ccc;}
#login .copyright{height:26px;line-height:20px;text-align:center;font-family:arial;color:#bbb;}
#login .copyright a{color:#bbb;text-decoration:none;}
#login .copyright a:hover{color:#f60;}

#login .main form{margin:50px 62px 0 62px;}
#login .main form ul{margin:0;padding:0;list-style:none;}
#login .main form ul li{font-size:14px;color:#555;margin-bottom:18px;padding:0;position:relative;}
#login .main form ul li img{vertical-align:middle;cursor:pointer;}
#login .main form ul li input{border:1px solid #e5e5e5;padding:12px;border-radius:10px;color:#444;vertical-align:middle;outline:none;}
#login .main form ul li select{border:1px solid #e5e5e5;padding:12px;border-radius:10px;color:#444;vertical-align:middle;outline:none;}
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

	<script type="text/javascript">
		function check() {
			var yonghuPassword = document.form1.yonghuPassword.value;
			var yonghuPassword1 = document.form1.yonghuPassword1.value;
			var yonghuPassword2 = document.form1.yonghuPassword2.value;
			var reg = /^[a-zA-Z0-9_]{6,}$/;
			if (document.form1.yonghuPassword1.value == "") {
				alert("请输入确认密码");
				document.form1.yonghuPassword1.focus();
				return false;
			}
			if (document.form1.yonghuPassword1.value != document.form1.yonghuPassword2.value) {
				alert("两次输入密码不一致");
				document.form1.yonghuPassword2.focus();
				return false;
			}
		}
	</script>
<body>
<div id="login">
        <div class="logo">修改密码</div>
        <div class="main">
            <form class="formname" action="<%=basePath%>mimaYonghu" name="form1" method="post" onSubmit="return check()">
            <ul>
                <input id="yonghuId" name="yonghuId" type="hidden" value="<%=yonghuId%>"/>
                <li>原密码： <input class="wa" type="password" name="yonghuPassword" id="yonghuPassword"/></li>
                <li>新密码： <input class="wa" type="password" name="yonghuPassword1" id="yonghuPassword1"/></li>
                <li>再输入： <input class="wa" type="password" name="yonghuPassword2" id="yonghuPassword2"/></li>
                <li><input type="submit" value="修 改" class="bnt" style="width:48%" />&nbsp;<input type="reset" value="重 置" class="bnt" style="width:48%" /></li>
                <li><font color="red">${error }</font></li>
            </ul>
            </form>
            
    </div>
</div>
<script>

</script>

</body>
</html>
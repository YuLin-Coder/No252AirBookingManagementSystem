<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.*" 
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>找回密码</title>
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
<body>
<div id="login">
        <div class="logo">找回密码</div>
        <div class="main">
            <form class="formname" action="<%=basePath%>zhaohuiUser" name="form1" method="post">
            <ul>
            	<li>用 户 名： <input type="text" class="wa" id="userName" name="userName"/></li>
                <li>电话号码： <input type="text" class="wa" id="userPhone" name="userPhone"/></li>
                <li><input type="submit" value="找    回" class="bnt" style="width:48%" />&nbsp;
                	<a href="<%=basePath%>"><input type="button" value="返    回" class="bnt" style="width:48%" /></a>
                </li>
                <li><font color="red">${error }</font></li>
            </ul>
            </form>
            
    </div>
</div>
<script>

</script>

</body>
</html>
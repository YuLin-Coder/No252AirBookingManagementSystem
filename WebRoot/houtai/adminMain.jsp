<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.*,java.util.List"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<%
	// 权限验证
	Admin admin = (Admin)session.getAttribute("admin");
	if(admin==null){
		System.out.println("没有得到adminId");
		response.sendRedirect("shouye/index.jsp");
		return;
	}
	Jcpeizhi newJcpeizhi = (Jcpeizhi)session.getAttribute("jcpeizhi");
	List<Jcbiaoti> jcbiaotis = (List<Jcbiaoti>)session.getAttribute("jcbiaotis");
	List<List<Jcdaohang>> jcdaohangslist = (List<List<Jcdaohang>>)session.getAttribute("jcdaohangslist");
	String adminName = admin.getAdminName();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><%=newJcpeizhi.getJcpeizhiName()%></title>
    <link rel="stylesheet" href="<%=basePath%>houtai/muban7/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>houtai/muban7/admin.css">
    <script src="<%=basePath%>houtai/muban7/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="<%=basePath%>houtai/muban7/y.jpg" class="radius-circle rotate-hover" height="50" alt="" /><%=newJcpeizhi.getJcpeizhiName()%></h1>
  </div>
  <div class="head-l">
  	<a class="button button-little bg-green"><span class="icon-home"></span><%=adminName %></a> &nbsp;&nbsp;
  	<a target = "_blank" class="button button-little bg-red" href="<%=basePath%>"><span class="icon-home"></span> 返回网站</a>  &nbsp;&nbsp;
  	<a class="button button-little bg-red" href="<%=basePath%>shouye/tuichu.jsp"><span class="icon-power-off"></span> 退出登录</a> 
  </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>个人信息</h2>
  <ul style="display:block">
    <li><a href="<%=basePath%>admin/rizhi.jsp" target="right"><span class="icon-caret-right"></span>登录日志</a></li>
    <li><a href="<%=basePath%>admin/adminmima.jsp" target="right"><span class="icon-caret-right"></span>修改口令</a></li>
  </ul>
  <%for(int i = 0; i < jcbiaotis.size(); i++){ %>
  <h2><span class="icon-user"></span><%=jcbiaotis.get(i).getJcbiaotiName() %></h2>
  <ul>
  	<%for(int j = 0; j < jcdaohangslist.get(i).size(); j++){ %>
    <li><a href="<%=basePath%>admin/<%=jcdaohangslist.get(i).get(j).getJcdaohangNeirong() %>" target="right"><span class="icon-caret-right"></span><%=jcdaohangslist.get(i).get(j).getJcdaohangName() %></a></li>
    <%} %>
  </ul>
  <%} %>
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><%=newJcpeizhi.getJcpeizhiName()%></li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="<%=basePath%>shouye/neiye.jsp" name="right" width="100%" height="100%"></iframe>
</div>
<div style="text-align:center;">
<p><%=newJcpeizhi.getJcpeizhiName()%></p>
</div>
</body>
</html>
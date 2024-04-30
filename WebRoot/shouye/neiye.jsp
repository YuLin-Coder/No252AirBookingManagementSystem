<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.*,java.util.List"
	pageEncoding="utf-8"%>
<%
	Jcpeizhi newJcpeizhi = (Jcpeizhi)session.getAttribute("jcpeizhi");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=newJcpeizhi.getJcpeizhiName()%></title>
</head>
<head>
    <title><%=newJcpeizhi.getJcpeizhiName()%></title><meta charset="UTF-8" />
<body>
<div title="<%=newJcpeizhi.getJcpeizhiName()%>" >
	<div align="center" style="padding-top: 10px;"><font color="#4C91D1" size="8">欢迎使用<%=newJcpeizhi.getJcpeizhiName()%></font></div>
</div>
</body>
</html>
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
<title>系统设置</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="<%=basePath %>jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function(){
		// 数据
		var treeData=[{
			text:"系统设置",
			children:[{
				text:"基础设置",
				attributes:{
					url:"<%=basePath %>xitong/jcpeizhi.jsp"
				}
			},{
				text:"导航标题",
				attributes:{
					url:"<%=basePath %>xitong/jcbiaoti.jsp"
				}
			},{
				text:"添加导航",
				attributes:{
					url:"<%=basePath %>xitong/jcbiaotitojcdaohang.jsp"
				}
			},{
				text:"导航内容",
				attributes:{
					url:"<%=basePath %>xitong/jcdaohang.jsp"
				}
			}]
		}];
		
		// 实例化树菜单
		$("#tree").tree({
			data:treeData,
			lines:true,
			onClick:function(node){
				if(node.attributes){
					openTab(node.text,node.attributes.url);
				}
			}
		});
		
		// 新增Tab
		function openTab(text,url){
			if($("#tabs").tabs('exists',text)){
				$("#tabs").tabs('select',text);
			}else{
				var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
				$("#tabs").tabs('add',{
					title:text,
					closable:true,
					content:content
				});
			}
		}
	});
</script>
</head>
<body class="easyui-layout">
	<div region="north" style="height: 80px;background-color: #E0EDFF">
		<div align="left" style="width: 80%;float: left"><font color="#4C91D1" size="8">系统设置</font></div>
	</div>
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" >
				<div align="center" style="padding-top: 100px;"><font color="red" size="10">系统设置</font></div>
			</div>
		</div>
	</div>
	<div region="west" style="width: 150px;" title="导航菜单" split="true">
		<ul id="tree"></ul>
	</div>
</body>
</html>
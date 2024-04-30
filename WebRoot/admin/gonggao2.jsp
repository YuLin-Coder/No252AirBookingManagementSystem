<%@ page language="java" import="com.model.*"  pageEncoding="utf-8"%>
<% Jcpeizhi newJcpeizhi = (Jcpeizhi)session.getAttribute("jcpeizhi"); %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>联系我们</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="../fuwenben/themes/default/default.css" />
<link rel="stylesheet" href="../fuwenben/plugins/code/prettify.css" />
<script charset="utf-8" src="../fuwenben/kindeditor-all.js"></script>
<script charset="utf-8" src="../fuwenben/lang/zh-CN.js"></script>
<script charset="utf-8" src="../fuwenben/plugins/code/prettify.js"></script>

	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="gonggaoMark"]', {
				cssPath : '../fuwenben/plugins/code/prettify.css',
				uploadJson : '../upload/fileUpload',
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				},
				afterBlur: function(){this.sync();}
			});
			prettyPrint();
		});
	</script>
<script type="text/javascript">
var url;
	function searchGonggao(){
		$('#dg').datagrid('load',{
			gonggaoName:$('#s_gonggaoName').val(),
			ggtypeId:$('#s_ggtypeId').combobox("getValue"),
			sdate:$('#s_sdate').datebox("getValue"),
			edate:$('#s_edate').datebox("getValue")
		});
	}
	
	function saveGonggao(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
			
				var s=result;
				var result = eval('(' + result + ')');
			
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function openGonggaoAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加联系我们");
		url="../addGonggao";
	}
	
	function resetValue(){
	}
	
	function deleteGonggao(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].gonggaoId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../deleteGonggao",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].gonggaoName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function closeGonggaoDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openGonggaoModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑联系我们");
		$("#fm").form("load",row);
		url="../addGonggao?ggtypeId=0&gonggaoId="+row.gonggaoId;
	}
	
	function formatChakan(shujuImg, row) {
		if(shujuImg){
			return '<a target="_blank" style="color:red;" href="../' + shujuImg + '">查看' + '</a>'; 
		}else {
			return "未上传";
		}
	}
	
	function formatXiazai(shujuImgName, row) {
		if(shujuImgName){
			return '<a target="_blank" style="color:red;" href="../downFile?filename=' + shujuImgName + '">下载' + '</a>'; 
		}else {
			return "未上传";
		}
	}
	
	function datetostr(date, row) {
		if(date){
			var date = new Date(date.time);
        	var y=date.getFullYear();
        	var m=date.getMonth()+1;
        	var d=date.getDate();
        	var h=date.getHours();
        	var m1=date.getMinutes();
        	var s=date.getSeconds();
        	m = m<10?("0"+m):m;
        	d = d<10?("0"+d):d;
        	return y+"-"+m+"-"+d;
			var text = JsonDateValue.toLocaleString();
			return text;
		}else{
			return "未填写";
		}
	}
	
	function doPrint() {      
        bdhtml=window.document.body.innerHTML;      
        sprnstr="<!--startprint-->";      
        eprnstr="<!--endprint-->";      
        prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);      
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));      
        window.document.body.innerHTML=prnhtml;   
        window.print();      
	}
	
	function shangchuanGonggao(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#shangchuan").dialog("open").dialog("setTitle","上传联系我们");
		$("#shchfm").form("load",row);
		shchurl="../shangchuanGonggao?gonggaoId="+row.gonggaoId;
	}
	
	function closeShangchuanGonggao(){
		$("#shangchuan").dialog("close");
		resetValue();
	}
	
	function saveShangchuanGonggao(){
		$("#shchfm").form("submit",{
			url:shchurl,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
			
				var s=result;
				var result = eval('(' + result + ')');
			
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#shangchuan").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function chakanNeirong(gonggaoId, row) {
		return '<a style="color:red;"target="_blank" href="<%=basePath%>wangzhan/gonggaoshow.jsp?gonggaoId=' + gonggaoId + '">查看' + '</a>';  
	}
</script>
</head>
<body style="margin: 5px;">
<!--startprint-->
	<table id="dg" title="联系我们" class="easyui-datagrid" fitColumns="true"
	 pagination="true" url="../getGonggaos?gonggaoId=2" fit="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="gonggaoId" width="10" formatter="chakanNeirong">查看</th>
				<th field="gonggaoName" width="80">联系我们</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openGonggaoModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>
	</div>
<!--endprint-->
	
	<div id="dlg" class="easyui-dialog" style="width: 850px;height: 380px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form name="fm" id="fm" method="post">
			<table>
				<tr>
					<td valign="top"><%=newJcpeizhi.getGonggaoBieming()%>描述：</td>
					<td colspan="4"><textarea name="gonggaoMark" id="gonggaoMark" cols="100" rows="12" style="width:700px;height:250px;visibility:hidden;"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveGonggao()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeGonggaoDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
<!--上传-->	
	<div id="shangchuan" class="easyui-dialog" style="width: 320px;height: 140px;padding: 10px 20px"
		closed="true" buttons="#shangchuan-buttons">
		<form id="shchfm" method="post" enctype="multipart/form-data">
			<table cellspacing="5px;">
				<tr>
					<td><input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="shangchuan-buttons">
		<a href="javascript:saveShangchuanGonggao()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeShangchuanGonggao()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
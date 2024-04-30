<%@ page language="java" import="com.model.*"  pageEncoding="utf-8"%>
<% Jcpeizhi newJcpeizhi = (Jcpeizhi)session.getAttribute("jcpeizhi"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>权限信息管理</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	function searchRizhi(){
		$('#dg').datagrid('load',{
			rizhiName:$('#s_rizhiName').val()
		});
	}
	
	function deleteRizhi(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].rizhiId);
		}
		var ids=strIds.join(",");
		//输出选择的行
		//$.messager.alert("ids:" + ids);
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../deleteRizhi",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].rizhiName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function openRizhiAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加权限信息");
		url="../addRizhi";
	}
	
	function saveRizhi(){
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
					$.messager.alert("系统提示","保存成功！");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function resetValue(){
		$("#rizhiName").val("");
		$("#rizhiMark").val("");
	}
	
	function closeRizhiDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openRizhiModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑权限信息");
		$("#fm").form("load",row);
		url="../addRizhi?rizhiId="+row.rizhiId;
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
</script>
</head>
<body style="margin: 5px;">
	<table id="dg" title="日志信息" class="easyui-datagrid" fitColumns="true"
		pagination="true" url="../getRizhis" fit="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="rizhiId" width="50">编号</th>
				<th field="rizhiName" width="50">用户名</th>
				<th field="dengluIp" width="50">IP</th>
				<th field="date" width="50" formatter="datetostr">日期</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:deleteRizhi()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">删除</a>
		</div>
	</div>
</body>
</html>
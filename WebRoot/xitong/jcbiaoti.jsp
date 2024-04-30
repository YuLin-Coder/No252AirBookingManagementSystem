<%@ page language="java" import="com.model.Jcbiaoti"  pageEncoding="utf-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>jcbiaoti信息管理</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
var url;
	function searchJcbiaoti(){
		$('#dg').datagrid('load',{
			jcbiaotiName:$('#s_jcbiaotiName').val(),
			jcbiaotiType:$('#s_jcbiaotiType').combobox("getValue"),
			jcbiaotiType1:$('#s_jcbiaotiType1').combobox("getValue")
		});
	}
	
	function saveJcbiaoti(){
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
	
	function openJcbiaotiAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加jcbiaoti信息");
		url="../addJcbiaoti";
	}
	
	function resetValue(){
	}
	
	function deleteJcbiaoti(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].jcbiaotiId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../deleteJcbiaoti",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].jcbiaotiName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function closeJcbiaotiDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openJcbiaotiModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑jcbiaoti信息");
		$("#fm").form("load",row);
		url="../addJcbiaoti?jcbiaotiId="+row.jcbiaotiId;
	}
	
	function formatSex(jcbiaotiSex, row) {  
		if(jcbiaotiSex==0){
			return "男";
		}
		if(jcbiaotiSex==1){
			return "女";
		}
	}
	
	function formatType1(jcbiaotiType1, row) {  
		if(jcbiaotiType1==0){
			return "启用";
		}
		if(jcbiaotiType1==1){
			return "禁用";
		}
	}
	
	function formatType(jcbiaotiType, row) {  
		if(jcbiaotiType==0){
			return "网页";
		}
		if(jcbiaotiType==1){
			return "admin";
		}
		if(jcbiaotiType==2){
			return "user";
		}
		if(jcbiaotiType==3){
			return "yonghu";
		}
		if(jcbiaotiType==4){
			return "仓管";
		}
		if(jcbiaotiType==5){
			return "采购";
		}
		if(jcbiaotiType==6){
			return "销售";
		}
	}
	
	function formatChakan(jcbiaotiImg, row) {
		if(jcbiaotiImg){
			return '<a target="_blank" style="color:red;" href="../' + jcbiaotiImg + '">查看' + '</a>'; 
		}else {
			return "未上传";
		}
	}
	
	function formatXiazai(jcbiaotiImgName, row) {
		if(jcbiaotiImgName){
			return '<a target="_blank" style="color:red;" href="../downFile?filename=' + jcbiaotiImgName + '">下载' + '</a>'; 
		}else {
			return "未上传";
		}
	}
	
	function daochuJcbiaoti(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要导出的数据！");
			return;
		}
		var jcbiaotiIds=[];
		for(var i=0;i<selectedRows.length;i++){
			jcbiaotiIds.push(selectedRows[i].jcbiaotiId);
		}
		var ids=jcbiaotiIds.join(",");
		$.messager.confirm("系统提示","您确认要导出数据吗？",function(r){
			if(r){
				$.post("../daochuJcbiaoti",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功导出数据：D:！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].jcbiaotiName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function datetostr(dateTime, row) {
		if(dateTime){
			var JsonDateValue = new Date(dateTime.time);
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
	
	function daoruJcbiaotis(){
		$("#daoru").dialog("open").dialog("setTitle","导入jcbiaoti信息");
		daoruurl="../daoruJcbiaoti";
	}
	
	function closeDaoruJcbiaoti(){
		$("#daoru").dialog("close");
		resetValue();
	}
	
	function saveDaoruJcbiaoti(){
		$("#drfm").form("submit",{
			url:daoruurl,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
			
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#daoru").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function shangchuanJcbiaoti(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#shangchuan").dialog("open").dialog("setTitle","上传jcbiaoti信息");
		$("#shchfm").form("load",row);
		shchurl="../shangchuanJcbiaoti?jcbiaotiId="+row.jcbiaotiId;
	}
	
	function closeShangchuanJcbiaoti(){
		$("#shangchuan").dialog("close");
		resetValue();
	}
	
	function saveShangchuanJcbiaoti(){
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
	
	function qiyongJcbiaoti(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要执行的数据！");
			return;
		}
		var row=selectedRows[0];
		url="../addJcbiaoti?jcbiaotiId="+row.jcbiaotiId;
		$.messager.confirm("系统提示","您确认要执行吗？",function(r){
			if(r){
				$.post(url,{jcbiaotiType1:0},function(result){
					if(result.errorMsg){
						$.messager.alert("系统提示",result.errorMsg);
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","您已成功执行！");
						$("#dg").datagrid("reload");
					}
				},"json");
			}
		});
	}
	
	function jinyongJcbiaoti(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要执行的数据！");
			return;
		}
		var row=selectedRows[0];
		url="../addJcbiaoti?jcbiaotiId="+row.jcbiaotiId;
		$.messager.confirm("系统提示","您确认要执行吗？",function(r){
			if(r){
				$.post(url,{jcbiaotiType1:1},function(result){
					if(result.errorMsg){
						$.messager.alert("系统提示",result.errorMsg);
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","您已成功执行！");
						$("#dg").datagrid("reload");
					}
				},"json");
			}
		});
	}
</script>
</head>
<body style="margin: 5px;">
<!--startprint-->
	<table id="dg" title="jcbiaoti信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" url="../getJcbiaotis" fit="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="jcbiaotiId" width="10">编号</th>
				<th field="jcbiaotiType" width="20" formatter="formatType">类型</th>
				<th field="jcbiaotiName" width="40">名称</th>
				<th field="jcbiaotiPaixu" width="10">排序</th>
				<th field="jcbiaotiType1" width="20" formatter="formatType1">状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openJcbiaotiAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openJcbiaotiModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteJcbiaoti()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			<a href="javascript:qiyongJcbiaoti()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">启用</a>
			<a href="javascript:jinyongJcbiaoti()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">禁用</a>
		</div>
		<div>
		&nbsp;名称：&nbsp;<input type="text" name="s_jcbiaotiName" id="s_jcbiaotiName" size="10"/>
		&nbsp;类型：&nbsp;<select class="easyui-combobox" id="s_jcbiaotiType" name="s_jcbiaotiType" editable="false" panelHeight="auto">
		    <option value="">请选择...</option>
			<option value="0">网页</option>
			<option value="1">admin</option>
			<option value="2">user</option>
			<option value="3">yonghu</option>
		</select>
		&nbsp;状态：&nbsp;<select class="easyui-combobox" id="s_jcbiaotiType1" name="s_jcbiaotiType1" editable="false" panelHeight="auto">
		    <option value="">请选择...</option>
			<option value="0">启用</option>
			<option value="1">禁用</option>
		</select>
		<a href="javascript:searchJcbiaoti()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
<!--endprint-->
	<div id="dlg" class="easyui-dialog" style="width: 300px;height: 200px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
				<tr>
					<td>类型：</td>
					<td><select class="easyui-combobox" id="jcbiaotiType" name="jcbiaotiType" editable="false" panelHeight="auto" style="width: 155px">
					    <option value="">请选择...</option>
						<option value="0">网页</option>
						<option value="1">admin</option>
						<option value="2">user</option>
						<option value="3">yonghu</option>
					</select></td>
				</tr>
				<tr>
					<td>名称：</td>
					<td><input type="text" name="jcbiaotiName" id="jcbiaotiName" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>排序：</td>
					<td><input type="text" name="jcbiaotiPaixu" id="jcbiaotiPaixu" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveJcbiaoti()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeJcbiaotiDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
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
		<a href="javascript:saveShangchuanJcbiaoti()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeShangchuanJcbiaoti()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
<!--导入-->	
	<div id="daoru" class="easyui-dialog" style="width: 320px;height: 140px;padding: 10px 20px"
		closed="true" buttons="#daoru-buttons">
		<form id="drfm" method="post" enctype="multipart/form-data">
			<table cellspacing="5px;">
				<tr>
					<td><input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="daoru-buttons">
		<a href="javascript:saveDaoruJcbiaoti()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDaoruJcbiaoti()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
</body>
</html>
<%@ page language="java" import="com.model.Jcdaohang"  pageEncoding="utf-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>jcdaohang信息管理</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
var url;
	function searchJcdaohang(){
		$('#dg').datagrid('load',{
			jcdaohangName:$('#s_jcdaohangName').val(),
			jcbiaotiId:$('#s_jcbiaotiId').combobox("getValue"),
			jcdaohangType:$('#s_jcdaohangType').combobox("getValue"),
			jcdaohangType1:$('#s_jcdaohangType1').combobox("getValue")
		});
	}
	
	function saveJcdaohang(){
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
	
	function openJcdaohangAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加jcdaohang信息");
		url="../addJcdaohang";
	}
	
	function resetValue(){
	}
	
	function deleteJcdaohang(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].jcdaohangId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../deleteJcdaohang",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].jcdaohangName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function closeJcdaohangDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openJcdaohangModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑jcdaohang信息");
		$("#fm").form("load",row);
		url="../addJcdaohang?jcdaohangId="+row.jcdaohangId;
	}
	
	function formatSex(shujuSex, row) {  
		if(shujuSex==0){
			return "男";
		}
		if(shujuSex==1){
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
	
	function formatType(shujuType, row) {  
		if(shujuType==0){
			return "网页";
		}
		if(shujuType==1){
			return "admin";
		}
		if(shujuType==2){
			return "user";
		}
		if(shujuType==3){
			return "yonghu";
		}
		if(shujuType==4){
			return "仓管";
		}
		if(shujuType==5){
			return "采购";
		}
		if(shujuType==6){
			return "销售";
		}
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
	
	function daochuJcdaohang(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要导出的数据！");
			return;
		}
		var jcdaohangIds=[];
		for(var i=0;i<selectedRows.length;i++){
			jcdaohangIds.push(selectedRows[i].jcdaohangId);
		}
		var ids=jcdaohangIds.join(",");
		$.messager.confirm("系统提示","您确认要导出数据吗？",function(r){
			if(r){
				$.post("../daochuJcdaohang",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功导出数据：D:！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].jcdaohangName+'</font>'+result.errorMsg);
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
	
	function daoruJcdaohangs(){
		$("#daoru").dialog("open").dialog("setTitle","导入jcdaohang信息");
		daoruurl="../daoruJcdaohang";
	}
	
	function closeDaoruJcdaohang(){
		$("#daoru").dialog("close");
		resetValue();
	}
	
	function saveDaoruJcdaohang(){
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
	
	function shangchuanJcdaohang(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#shangchuan").dialog("open").dialog("setTitle","上传jcdaohang信息");
		$("#shchfm").form("load",row);
		shchurl="../shangchuanJcdaohang?jcdaohangId="+row.jcdaohangId;
	}
	
	function closeShangchuanJcdaohang(){
		$("#shangchuan").dialog("close");
		resetValue();
	}
	
	function saveShangchuanJcdaohang(){
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
	
	function qiyongJcdaohang(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要执行的数据！");
			return;
		}
		var row=selectedRows[0];
		url="../addJcdaohang?jcdaohangId="+row.jcdaohangId;
		$.messager.confirm("系统提示","您确认要执行吗？",function(r){
			if(r){
				$.post(url,{jcdaohangType1:0},function(result){
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
	
	function jinyongJcdaohang(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要执行的数据！");
			return;
		}
		var row=selectedRows[0];
		url="../addJcdaohang?jcdaohangId="+row.jcdaohangId;
		$.messager.confirm("系统提示","您确认要执行吗？",function(r){
			if(r){
				$.post(url,{jcdaohangType1:1},function(result){
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
	<table id="dg" title="jcdaohang信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" url="../getJcdaohangs" fit="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="jcdaohangId" width="10" hidden="true">编号</th>
				<th field="jcbiaotiId" width="20">标题ID</th>
				<th field="jcbiaotiName" width="40">标题</th>
				<th field="jcdaohangType" width="20" formatter="formatType">类型</th>
				<th field="jcdaohangName" width="40">名称</th>
				<th field="jcdaohangNeirong" width="40">Url</th>
				<th field="jcdaohangPaixu" width="10">排序</th>
				<th field="jcdaohangType1" width="20" formatter="formatType1">状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openJcdaohangModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteJcdaohang()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			<a href="javascript:qiyongJcdaohang()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">启用</a>
			<a href="javascript:jinyongJcdaohang()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">禁用</a>
		</div>
		<div>
		&nbsp;名称：&nbsp;<input type="text" name="s_jcdaohangName" id="s_jcdaohangName" size="10"/>
		&nbsp;类型：&nbsp;<select class="easyui-combobox" id="s_jcdaohangType" name="s_jcdaohangType" editable="false" panelHeight="auto">
		    <option value="">请选择...</option>
			<option value="0">网页</option>
			<option value="1">admin</option>
			<option value="2">user</option>
			<option value="3">yonghu</option>
			<option value="4">仓管</option>
			<option value="5">采购</option>
			<option value="6">销售</option>
		</select>
		&nbsp;状态：&nbsp;<select class="easyui-combobox" id="s_jcdaohangType1" name="s_jcdaohangType1" editable="false" panelHeight="auto">
		    <option value="">请选择...</option>
			<option value="0">启用</option>
			<option value="1">禁用</option>
		</select>
		&nbsp;标题：&nbsp;<input class="easyui-combobox" id="s_jcbiaotiId" name="s_jcbiaotiId"  data-options="panelHeight:'auto',editable:false,valueField:'jcbiaotiId',textField:'jcbiaotiName',url:'../jcbiaotiComboList'"/>
		<a href="javascript:searchJcdaohang()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
<!--endprint-->
	<div id="dlg" class="easyui-dialog" style="width: 300px;height: 200px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
				<tr>
					<td>名称：</td>
					<td><input type="text" name="jcdaohangName" id="jcdaohangName" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>内容：</td>
					<td><input type="text" name="jcdaohangNeirong" id="jcdaohangNeirong" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>排序：</td>
					<td><input type="text" name="jcdaohangPaixu" id="jcdaohangPaixu" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveJcdaohang()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeJcdaohangDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
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
		<a href="javascript:saveShangchuanJcdaohang()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeShangchuanJcdaohang()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
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
		<a href="javascript:saveDaoruJcdaohang()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDaoruJcdaohang()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
</body>
</html>
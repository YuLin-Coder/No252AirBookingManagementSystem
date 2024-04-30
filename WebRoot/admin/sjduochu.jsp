<%@ page language="java" import="com.model.*"  pageEncoding="utf-8"%>
<% Jcpeizhi newJcpeizhi = (Jcpeizhi)session.getAttribute("jcpeizhi"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=newJcpeizhi.getShujuBieming()%><%=newJcpeizhi.getSjduochuBieming()%></title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
var url;
	function searchSjduochu(){
		$('#dg').datagrid('load',{
			shujuId:$('#s_shujuId').combobox("getValue"),
			sjduochuName:$('#s_sjduochuName').datebox("getValue")
		});
	}
	
	function saveSjduochu(){
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
	
	function openSjduochuAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加<%=newJcpeizhi.getShujuBieming()%><%=newJcpeizhi.getSjduochuBieming()%>");
		url="../addSjduochu";
	}
	
	function resetValue(){
	}
	
	function deleteSjduochu(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].sjduochuId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../deleteSjduochu",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].sjduochuName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function closeSjduochuDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openSjduochuModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑<%=newJcpeizhi.getShujuBieming()%><%=newJcpeizhi.getSjduochuBieming()%>");
		$("#fm").form("load",row);
		url="../addSjduochu?sjduochuId="+row.sjduochuId;
	}
	
	function formatSex(shujuSex, row) {  
		if(shujuSex==0){
			return "男";
		}
		if(shujuSex==1){
			return "女";
		}
	}
	
	function formatType1(shujuType1, row) {  
		if(shujuType1==0){
			return "零";
		}
		if(shujuType1==1){
			return "一";
		}
	}
	
	function formatType(shujuType, row) {  
		if(shujuType==0){
			return "未起";
		}
		if(shujuType==1){
			return "起飞";
		}
		if(shujuType==2){
			return "到达";
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
	
	function daochuSjduochu(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要导出的数据！");
			return;
		}
		var sjduochuIds=[];
		for(var i=0;i<selectedRows.length;i++){
			sjduochuIds.push(selectedRows[i].sjduochuId);
		}
		var ids=sjduochuIds.join(",");
		$.messager.confirm("系统提示","您确认要导出数据吗？",function(r){
			if(r){
				$.post("../daochuSjduochu",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功导出数据：D:！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].sjduochuName+'</font>'+result.errorMsg);
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
	
	function daoruSjduochus(){
		$("#daoru").dialog("open").dialog("setTitle","导入<%=newJcpeizhi.getShujuBieming()%><%=newJcpeizhi.getSjduochuBieming()%>");
		daoruurl="../daoruSjduochu";
	}
	
	function closeDaoruSjduochu(){
		$("#daoru").dialog("close");
		resetValue();
	}
	
	function saveDaoruSjduochu(){
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
	
	function shangchuanSjduochu(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#shangchuan").dialog("open").dialog("setTitle","上传<%=newJcpeizhi.getShujuBieming()%><%=newJcpeizhi.getSjduochuBieming()%>");
		$("#shchfm").form("load",row);
		shchurl="../shangchuanSjduochu?sjduochuId="+row.sjduochuId;
	}
	
	function closeShangchuanSjduochu(){
		$("#shangchuan").dialog("close");
		resetValue();
	}
	
	function saveShangchuanSjduochu(){
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
	
	function shenheSjduochu(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要执行的数据！");
			return;
		}
		var row=selectedRows[0];
		url="../addSjduochu?sjduochuId="+row.sjduochuId;
		$.messager.confirm("系统提示","您确认要执行吗？",function(r){
			if(r){
				$.post(url,{sjduochuType:1},function(result){
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
	
	function daodaSjduochu(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要执行的数据！");
			return;
		}
		var row=selectedRows[0];
		url="../addSjduochu?sjduochuId="+row.sjduochuId;
		$.messager.confirm("系统提示","您确认要执行吗？",function(r){
			if(r){
				$.post(url,{sjduochuType:2},function(result){
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
	<table id="dg" title="<%=newJcpeizhi.getShujuBieming()%><%=newJcpeizhi.getSjduochuBieming()%>" class="easyui-datagrid" fitColumns="true"
	 pagination="true" url="../getSjduochus" fit="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="sjduochuId" width="10" hidden="true">编号</th>
				<th field="sjduochuName" width="20">日期</th>
				<th field="shujuId" width="20" hidden="true"><%=newJcpeizhi.getShujuBieming()%>ID</th>
				<th field="shujuName" width="20"><%=newJcpeizhi.getShujuBieming()%></th>
				<th field="sjduochuMark" width="20">机型</th>
				<th field="sjduochuDouble" width="10">售价</th>
				<th field="sjduochuZong" width="10">座位</th>
				<th field="sjduochuZong1" width="10">已售</th>
				<th field="sjduochuZong2" width="10">剩余</th>
				<th field="sjduochuDouble2" width="15">上座率</th>
				<th field="sjleixingId" width="20" hidden="true"><%=newJcpeizhi.getSjleixingBieming()%>ID</th>
				<th field="sjleixingName" width="20">起点</th>
				<th field="sjxingtaiId" width="20" hidden="true"><%=newJcpeizhi.getSjxingtaiBieming()%>ID</th>
				<th field="sjxingtaiName" width="20">终点</th>
				<th field="sjduochuMark1" width="40">起飞</th>
				<th field="sjduochuMark2" width="40">到达</th>
				<th field="sjduochuType" width="10" formatter="formatType">状态</th>
				<th field="sjduochuDate" width="20" formatter="datetostr">添加</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openSjduochuAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:shenheSjduochu()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">起飞</a>
			<a href="javascript:daodaSjduochu()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">到达</a>
		</div>
		<div>
		&nbsp;<%=newJcpeizhi.getShujuBieming()%>：&nbsp;<input class="easyui-combobox" id="s_shujuId" name="s_shujuId"  data-options="panelHeight:'auto',editable:false,valueField:'shujuId',textField:'shujuName',url:'../shujuComboList'"/>
		&nbsp;日期：&nbsp;<input class="easyui-datebox" name="s_sjduochuName" id="s_sjduochuName" editable="false" size="10"/>->
		<a href="javascript:searchSjduochu()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
<!--endprint-->
	<div id="dlg" class="easyui-dialog" style="width: 300px;height: 160px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
				<tr>
					<td><%=newJcpeizhi.getShujuBieming()%>：</td>
					<td><input class="easyui-combobox" id="shujuId" name="shujuId"  data-options="panelHeight:'auto',editable:false,valueField:'shujuId',textField:'shujuName',url:'../shujuComboList'"/></td>
				</tr>
				<tr>
					<td>预售：</td>
					<td><input type="text" name="sjduochuZong" id="sjduochuZong" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveSjduochu()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeSjduochuDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
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
		<a href="javascript:saveShangchuanSjduochu()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeShangchuanSjduochu()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
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
		<a href="javascript:saveDaoruSjduochu()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDaoruSjduochu()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
</body>
</html>
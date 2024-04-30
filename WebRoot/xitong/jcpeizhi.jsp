<%@ page language="java" import="com.model.Jcpeizhi"  pageEncoding="utf-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>jcpeizhi信息管理</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
var url;
	function searchJcpeizhi(){
		$('#dg').datagrid('load',{
			jcpeizhiName:$('#s_jcpeizhiName').val()
		});
	}
	
	function saveJcpeizhi(){
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
	
	function openJcpeizhiAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加jcpeizhi信息");
		url="../addJcpeizhi";
	}
	
	function resetValue(){
	}
	
	function deleteJcpeizhi(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].jcpeizhiId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../deleteJcpeizhi",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].jcpeizhiName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function closeJcpeizhiDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openJcpeizhiModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑jcpeizhi信息");
		$("#fm").form("load",row);
		url="../addJcpeizhi?jcpeizhiId="+row.jcpeizhiId;
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
			return "juese";
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
	
	function daochuJcpeizhi(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要导出的数据！");
			return;
		}
		var jcpeizhiIds=[];
		for(var i=0;i<selectedRows.length;i++){
			jcpeizhiIds.push(selectedRows[i].jcpeizhiId);
		}
		var ids=jcpeizhiIds.join(",");
		$.messager.confirm("系统提示","您确认要导出数据吗？",function(r){
			if(r){
				$.post("../daochuJcpeizhi",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功导出数据：D:！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].jcpeizhiName+'</font>'+result.errorMsg);
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
	
	function daoruJcpeizhis(){
		$("#daoru").dialog("open").dialog("setTitle","导入jcpeizhi信息");
		daoruurl="../daoruJcpeizhi";
	}
	
	function closeDaoruJcpeizhi(){
		$("#daoru").dialog("close");
		resetValue();
	}
	
	function saveDaoruJcpeizhi(){
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
	
	function shangchuanJcpeizhi(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#shangchuan").dialog("open").dialog("setTitle","上传jcpeizhi信息");
		$("#shchfm").form("load",row);
		shchurl="../shangchuanJcpeizhi?jcpeizhiId="+row.jcpeizhiId;
	}
	
	function closeShangchuanJcpeizhi(){
		$("#shangchuan").dialog("close");
		resetValue();
	}
	
	function saveShangchuanJcpeizhi(){
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
</script>
</head>
<body style="margin: 5px;">
<!--startprint-->
	<table id="dg" title="jcpeizhi信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" url="../getJcpeizhis" fit="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="jcpeizhiId" width="10" hidden="true">编号</th>
				<th field="jcpeizhiName" width="40">标题</th>
				<th field="jcpeizhiNeirong" width="40">项目</th>
				<th field="jcpeizhiMark" width="40">备注</th>
				<th field="buyuanBieming" width="40">buyuan</th>
				<th field="bumenBieming" width="40">bumen</th>
				<th field="buzhiBieming" width="40">buzhi</th>
				<th field="userBieming" width="40">user</th>
				<th field="uxtypeBieming" width="40">uxtype</th>
				<th field="uxinxiBieming" width="40">uxinxi</th>
				<th field="uyijianBieming" width="40">uyijian</th>
				<th field="roleBieming" width="40">role</th>
				<th field="byuyuanBieming" width="40">byuyuan</th>
				<th field="byumenBieming" width="40">byumen</th>
				<th field="byuzhiBieming" width="40">byuzhi</th>
				<th field="yonghuBieming" width="40">yonghu</th>
				<th field="yxtypeBieming" width="40">yxtype</th>
				<th field="yxinxiBieming" width="40">yxinxi</th>
				<th field="yyijianBieming" width="40">yyijian</th>
				<th field="yhroleBieming" width="40">yhrole</th>
				<th field="ggtypeBieming" width="40">ggtype</th>
				<th field="gonggaoBieming" width="40">gonggao</th>
				<th field="ggpinglunBieming" width="40">ggpinglun</th>
				<th field="sjleixingBieming" width="40">sjleixing</th>
				<th field="sjxingtaiBieming" width="40">sjxingtai</th>
				<th field="shujuBieming" width="40">shuju</th>
				<th field="sjlaiyuanBieming" width="40">sjlaiyuan</th>
				<th field="sjqitaBieming" width="40">sjqita</th>
				<th field="sjduochuBieming" width="40">sjduochu</th>
				<th field="sjshaochuBieming" width="40">sjshaochu</th>
				<th field="sjjianchuBieming" width="40">sjjianchu</th>
				<th field="sjpinglunBieming" width="40">sjpinglun</th>
				<th field="jcpeizhiType" width="10">状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openJcpeizhiModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>
		<div>
		&nbsp;名称：&nbsp;<input type="text" name="s_jcpeizhiName" id="s_jcpeizhiName" size="10"/>
		<a href="javascript:searchJcpeizhi()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
<!--endprint-->
	<div id="dlg" class="easyui-dialog" style="width: 1100px;height: 340px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
				<tr>
					<td>标题：</td>
					<td><input type="text" name="jcpeizhiName" id="jcpeizhiName" class="easyui-validatebox" required="true"/></td>
					<td>项目：</td>
					<td><input type="text" name="jcpeizhiNeirong" id="jcpeizhiNeirong" class="easyui-validatebox"/></td>
					<td>备注：</td>
					<td><input type="text" name="jcpeizhiMark" id="jcpeizhiMark" class="easyui-validatebox"/></td>
					<td>状态：</td>
					<td><input type="text" name="jcpeizhiType" id="jcpeizhiType" class="easyui-validatebox"/></td>
				</tr>
				<tr>
					<td>buyuan：</td>
					<td><input type="text" name="buyuanBieming" id="buyuanBieming" class="easyui-validatebox"/></td>
					<td>bumen：</td>
					<td><input type="text" name="bumenBieming" id="bumenBieming" class="easyui-validatebox"/></td>
					<td>buzhi：</td>
					<td><input type="text" name="buzhiBieming" id="buzhiBieming" class="easyui-validatebox"/></td>
					<td>user：</td>
					<td><input type="text" name="userBieming" id="userBieming" class="easyui-validatebox"/></td>
				</tr>
				<tr>
					<td>uxtype：</td>
					<td><input type="text" name="uxtypeBieming" id="uxtypeBieming" class="easyui-validatebox"/></td>
					<td>uxinxi：</td>
					<td><input type="text" name="uxinxiBieming" id="uxinxiBieming" class="easyui-validatebox"/></td>
					<td>uyijian：</td>
					<td><input type="text" name="uyijianBieming" id="uyijianBieming" class="easyui-validatebox"/></td>
					<td>role：</td>
					<td><input type="text" name="roleBieming" id="roleBieming" class="easyui-validatebox"/></td>
				</tr>
				<tr>
					<td>byuyuan：</td>
					<td><input type="text" name="byuyuanBieming" id="byuyuanBieming" class="easyui-validatebox"/></td>
					<td>byumen：</td>
					<td><input type="text" name="byumenBieming" id="byumenBieming" class="easyui-validatebox"/></td>
					<td>byuzhi：</td>
					<td><input type="text" name="byuzhiBieming" id="byuzhiBieming" class="easyui-validatebox"/></td>
					<td>yonghu：</td>
					<td><input type="text" name="yonghuBieming" id="yonghuBieming" class="easyui-validatebox"/></td>
				</tr>
				<tr>
					<td>yxtype：</td>
					<td><input type="text" name="yxtypeBieming" id="yxtypeBieming" class="easyui-validatebox"/></td>
					<td>yxinxi：</td>
					<td><input type="text" name="yxinxiBieming" id="yxinxiBieming" class="easyui-validatebox"/></td>
					<td>yyijian：</td>
					<td><input type="text" name="yyijianBieming" id="yyijianBieming" class="easyui-validatebox"/></td>
					<td>yhrole：</td>
					<td><input type="text" name="yhroleBieming" id="yhroleBieming" class="easyui-validatebox"/></td>
				</tr>
				<tr>
					<td>ggtype：</td>
					<td><input type="text" name="ggtypeBieming" id="ggtypeBieming" class="easyui-validatebox"/></td>
					<td>gonggao：</td>
					<td><input type="text" name="gonggaoBieming" id="gonggaoBieming" class="easyui-validatebox"/></td>
					<td>ggpinglun：</td>
					<td><input type="text" name="ggpinglunBieming" id="ggpinglunBieming" class="easyui-validatebox"/></td>
					<td>sjleixing：</td>
					<td><input type="text" name="sjleixingBieming" id="sjleixingBieming" class="easyui-validatebox"/></td>
				</tr>
				<tr>
					<td>sjxingtai：</td>
					<td><input type="text" name="sjxingtaiBieming" id="sjxingtaiBieming" class="easyui-validatebox"/></td>
					<td>shuju：</td>
					<td><input type="text" name="shujuBieming" id="shujuBieming" class="easyui-validatebox"/></td>
					<td>sjlaiyuan：</td>
					<td><input type="text" name="sjlaiyuanBieming" id="sjlaiyuanBieming" class="easyui-validatebox"/></td>
					<td>sjqita：</td>
					<td><input type="text" name="sjqitaBieming" id="sjqitaBieming" class="easyui-validatebox"/></td>
				</tr>
				<tr>
					<td>sjduochu：</td>
					<td><input type="text" name="sjduochuBieming" id="sjduochuBieming" class="easyui-validatebox"/></td>
					<td>sjshaochu：</td>
					<td><input type="text" name="sjshaochuBieming" id="sjshaochuBieming" class="easyui-validatebox"/></td>
					<td>sjjianchu：</td>
					<td><input type="text" name="sjjianchuBieming" id="sjjianchuBieming" class="easyui-validatebox"/></td>
					<td>sjpinglun：</td>
					<td><input type="text" name="sjpinglunBieming" id="sjpinglunBieming" class="easyui-validatebox"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveJcpeizhi()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeJcpeizhiDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
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
		<a href="javascript:saveShangchuanJcpeizhi()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeShangchuanJcpeizhi()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
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
		<a href="javascript:saveDaoruJcpeizhi()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDaoruJcpeizhi()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
</body>
</html>
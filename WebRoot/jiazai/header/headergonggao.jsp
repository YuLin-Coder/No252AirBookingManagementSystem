<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String gonggaopage = "1";
	String gonggaorows = "8";
	String gonggaoName = "";
	String gonggaoId = "";
	String ggtypeId = "1";
	
	StringBuffer gonggaoparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isNotEmpty(gonggaopage)) {
		gonggaoparam.append("&page=" + gonggaopage);
	}
	if (StringUtil.isNotEmpty(gonggaorows)) {
		gonggaoparam.append("&rows=" + gonggaorows);
	}
	if (StringUtil.isNotEmpty(gonggaoName)) {
		gonggaoparam.append("&gonggaoName=" + gonggaoName);
	}
	if (StringUtil.isNotEmpty(gonggaoId)) {
		gonggaoparam.append("&gonggaoId=" + gonggaoId);
	}
	if (StringUtil.isNotEmpty(ggtypeId)) {
		gonggaoparam.append("&ggtypeId=" + ggtypeId);
	}

 %>
<%
	List<Gonggao> gonggaos = new ArrayList<Gonggao>();
	Gonggao gonggao = null;
	int gonggaosshuliang = 0;
	String gonggaopath = request.getContextPath();
	String gonggaobasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+gonggaopath+"/";
	
	String gonggaourl = gonggaobasePath + "getGonggaos";
	JSONObject gonggaoresult = PostUtil.sendPost(gonggaourl, gonggaoparam.toString());
	if(gonggaoresult!=null){
		JSONArray gonggaojsonArray = (JSONArray)gonggaoresult.get("rows");
		//System.out.println(gonggaojsonArray);
		gonggaos = JSONArray.toList(gonggaojsonArray, new Gonggao(), new JsonConfig());
		if(gonggaos.size()>0){
			gonggao = gonggaos.get(0);
		}
		//for(int i = 0;i < gonggaos.size();i++){
		//	System.out.println(gonggaos.get(i).getGonggaoName());
		//}
		gonggaosshuliang = (Integer)gonggaoresult.get("total");
		//System.out.println(gonggaosshuliang);
	}
%>
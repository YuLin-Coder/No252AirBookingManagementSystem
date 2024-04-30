<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String sousuoGonggaopage = (String) request.getParameter("page");
	String sousuoGonggaorows = (String) request.getParameter("rows");
	String sousuoGonggaoName = (String) request.getParameter("gonggaoName");
	String sousuoGonggaoId = (String) request.getParameter("gonggaoId");
	String sousuoGgtypeId = (String) request.getParameter("ggtypeId");
	
	StringBuffer sousuoGonggaoparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isEmpty(sousuoGonggaopage)) {
		sousuoGonggaopage = "1";
	}
	sousuoGonggaoparam.append("&page=" + sousuoGonggaopage);
	if (StringUtil.isEmpty(sousuoGonggaorows)) {
		sousuoGonggaorows = "10";
	}
	sousuoGonggaoparam.append("&rows=" + sousuoGonggaorows);
	if (StringUtil.isNotEmpty(sousuoGonggaoName)) {
		sousuoGonggaoparam.append("&gonggaoName=" + sousuoGonggaoName);
	}
	if (StringUtil.isNotEmpty(sousuoGonggaoId)) {
		sousuoGonggaoparam.append("&gonggaoId=" + sousuoGonggaoId);
	}
	if (StringUtil.isNotEmpty(sousuoGgtypeId)) {
		sousuoGonggaoparam.append("&ggtypeId=" + sousuoGgtypeId);
	}


	List<Gonggao> sousuoGonggaos = new ArrayList<Gonggao>();
	Gonggao sousuoGonggao = null;
	int sousuoGonggaosshuliang = 0;
	String sousuoGonggaopath = request.getContextPath();
	String sousuoGonggaobasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+sousuoGonggaopath+"/";
	
	String sousuoGonggaourl = sousuoGonggaobasePath + "getGonggaos";
	JSONObject sousuoGonggaoresult = PostUtil.sendPost(sousuoGonggaourl, sousuoGonggaoparam.toString());
	if(sousuoGonggaoresult!=null){
		JSONArray sousuoGonggaojsonArray = (JSONArray)sousuoGonggaoresult.get("rows");
		//System.out.println(sousuoGonggaojsonArray);
		sousuoGonggaos = JSONArray.toList(sousuoGonggaojsonArray, new Gonggao(), new JsonConfig());
		if(sousuoGonggaos.size()>0){
			sousuoGonggao = sousuoGonggaos.get(0);
		}
		//for(int i = 0;i < gonggaos.size();i++){
		//	System.out.println(gonggaos.get(i).getGonggaoName());
		//}
		sousuoGonggaosshuliang = (Integer)sousuoGonggaoresult.get("total");
		//System.out.println(gonggaosshuliang);
	}
	int gonggaorowCount = 1;
	gonggaorowCount = Integer.parseInt(sousuoGonggaorows);
	int gonggaocurrPage = 0;
	gonggaocurrPage = Integer.parseInt(sousuoGonggaopage);
	int gonggaototalPage = 0;
	gonggaototalPage = (sousuoGonggaosshuliang + gonggaorowCount - 1)/gonggaorowCount;
%>
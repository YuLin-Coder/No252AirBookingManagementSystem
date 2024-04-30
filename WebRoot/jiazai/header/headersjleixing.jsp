<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String sjleixingpage = "";
	String sjleixingrows = "";
	
	StringBuffer sjleixingparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isNotEmpty(sjleixingpage)) {
		sjleixingparam.append("&page=" + sjleixingpage);
	}
	if (StringUtil.isNotEmpty(sjleixingrows)) {
		sjleixingparam.append("&rows=" + sjleixingrows);
	}

 %>
<%
	List<Sjleixing> sjleixings = new ArrayList<Sjleixing>();
	Sjleixing sjleixing = null;
	int sjleixingsshuliang = 0;
	String sjleixingpath = request.getContextPath();
	String sjleixingbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+sjleixingpath+"/";
	
	String sjleixingurl = sjleixingbasePath + "getSjleixings";
	JSONObject sjleixingresult = PostUtil.sendPost(sjleixingurl, sjleixingparam.toString());
	if(sjleixingresult!=null){
		JSONArray sjleixingjsonArray = (JSONArray)sjleixingresult.get("rows");
		//System.out.println(sjleixingjsonArray);
		sjleixings = JSONArray.toList(sjleixingjsonArray, new Sjleixing(), new JsonConfig());
		if(sjleixings.size()>0){
			sjleixing = sjleixings.get(0);
		}
		for(int i = 0;i < sjleixings.size();i++){
			System.out.println(sjleixings.get(i).getSjleixingName());
		}
		sjleixingsshuliang = (Integer)sjleixingresult.get("total");
		System.out.println(sjleixingsshuliang);
	}
%>
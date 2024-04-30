<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String sousuoSjduochupage = (String) request.getParameter("page");
	String sousuoSjduochurows = (String) request.getParameter("rows");
	String sousuoSjduochuName = (String) request.getParameter("sjduochuName");
	String sousuoSjduochuId = (String) request.getParameter("sjduochuId");
	String sousuosjduochuType = "0";
	
	StringBuffer sousuoSjduochuparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isEmpty(sousuoSjduochupage)) {
		sousuoSjduochupage = "1";
	}
	sousuoSjduochuparam.append("&page=" + sousuoSjduochupage);
	if (StringUtil.isEmpty(sousuoSjduochurows)) {
		sousuoSjduochurows = "9";
	}
	sousuoSjduochuparam.append("&rows=" + sousuoSjduochurows);
	if (StringUtil.isNotEmpty(sousuoSjduochuName)) {
		sousuoSjduochuparam.append("&sjduochuName=" + sousuoSjduochuName);
	}
	if (StringUtil.isNotEmpty(sousuoSjduochuId)) {
		sousuoSjduochuparam.append("&sjduochuId=" + sousuoSjduochuId);
	}
	if (StringUtil.isNotEmpty(sousuosjduochuType)) {
		sousuoSjduochuparam.append("&sjduochuType=" + sousuosjduochuType);
	}

	List<Sjduochu> sousuoSjduochus = new ArrayList<Sjduochu>();
	Sjduochu sousuoSjduochu = null;
	int sousuoSjduochusshuliang = 0;
	String sousuoSjduochupath = request.getContextPath();
	String sousuoSjduochubasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+sousuoSjduochupath+"/";
	
	String sousuoSjduochuurl = sousuoSjduochubasePath + "getSjduochus";
	JSONObject sousuoSjduochuresult = PostUtil.sendPost(sousuoSjduochuurl, sousuoSjduochuparam.toString());
	if(sousuoSjduochuresult!=null){
		JSONArray sousuoSjduochujsonArray = (JSONArray)sousuoSjduochuresult.get("rows");
		//System.out.println(sousuoSjduochujsonArray);
		sousuoSjduochus = JSONArray.toList(sousuoSjduochujsonArray, new Sjduochu(), new JsonConfig());
		if(sousuoSjduochus.size()>0){
			sousuoSjduochu = sousuoSjduochus.get(0);
		}
		//for(int i = 0;i < sjduochus.size();i++){
		//	System.out.println(sjduochus.get(i).getSjduochuName());
		//}
		sousuoSjduochusshuliang = (Integer)sousuoSjduochuresult.get("total");
		//System.out.println(sjduochusshuliang);
	}
	int sjduochurowCount = 1;
	sjduochurowCount = Integer.parseInt(sousuoSjduochurows);
	int sjduochucurrPage = 0;
	sjduochucurrPage = Integer.parseInt(sousuoSjduochupage);
	int sjduochutotalPage = 0;
	sjduochutotalPage = (sousuoSjduochusshuliang + sjduochurowCount - 1)/sjduochurowCount;
%>
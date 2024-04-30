<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	StringBuffer jcpeizhiparam = new StringBuffer("ceshiId=1");
	List<Jcpeizhi> jcpeizhis = new ArrayList<Jcpeizhi>();
	Jcpeizhi jcpeizhi = null;
	int jcpeizhisshuliang = 0;
	String jcpeizhipath = request.getContextPath();
	String jcpeizhibasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+jcpeizhipath+"/";
	
	String jcpeizhiurl = jcpeizhibasePath + "getJcpeizhis";
	JSONObject jcpeizhiresult = PostUtil.sendPost(jcpeizhiurl, jcpeizhiparam.toString());
	if(jcpeizhiresult!=null){
		JSONArray jcpeizhijsonArray = (JSONArray)jcpeizhiresult.get("rows");
		//System.out.println(jcpeizhijsonArray);
		jcpeizhis = JSONArray.toList(jcpeizhijsonArray, new Jcpeizhi(), new JsonConfig());
		if(jcpeizhis.size()>0){
			jcpeizhi = jcpeizhis.get(0);
		}
		//for(int i = 0;i < jcpeizhis.size();i++){
		//	System.out.println(jcpeizhis.get(i).getJcpeizhiName());
		//}
		jcpeizhisshuliang = (Integer)jcpeizhiresult.get("total");
	}
	session.setAttribute("jcpeizhi", jcpeizhi);
%>
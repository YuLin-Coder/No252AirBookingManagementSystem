<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.*"
	pageEncoding="utf-8"%>
<%@ include file="header.jsp"%>
<%@ include file="../jiazai/sousuobaohan.jsp"%>
<script language="javascript" type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function chaxunSjduochu(shujuId){
		var sjduochuName = document.form1.sjduochuName.value;
		if(sjduochuName==""){
			document.form1.sjduochuName.focus();
			alert("请选择日期");
			return false;
		}
		window.location.href = "<%=basePath %>wangzhan/sjduochushow.jsp?sjduochuName="+sjduochuName+"&shujuId="+shujuId;
	}
	
</script>
  <!-- 内容 -->
    <div class="container">
        <div class="row">

            <div class="col-xs-12 col-sm-8 col-md-9" id="rightBox">

                <div class="positionBox">
                    <div class="titleBar">
                        <h1>当前位置</h1>
                        <span></span>
                        <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>wangzhan/shujulist.jsp"><%=newJcpeizhi.getShujuBieming() %>信息</a>
                    </div>
                </div>

                <div class="col-sm-12 col-md-12 pad">
                    
                    <div class="col-sm-12 col-md-6 pad">
                        <div class="detailGlide" style="max-width:600px; margin:0 auto">
                            <div class="slider">
                                <ul class="slider__wrapper">
                                    <li data-responsive='' data-src='<%=basePath %><%=sousuoShuju.getShujuImg()%>' data-sub-html='' class='slider__item real'><a><img src='<%=basePath %><%=sousuoShuju.getShujuImg()%>'></a></li>
                                </ul>
                            </div>
                            <div id="detailNav">
                            </div>
                        </div>
                        <script type="text/javascript">
                            var glide = $('.slider').glide({ navigationImg: true, navigation: "#detailNav" });
                        </script>
                    </div>
                
                    <div class="col-sm-12 col-md-6 pad">
                    <form name="form1" method="post">
                        <div class="detailTitle">
                            <%=sousuoShuju.getShujuName()%>
                        </div>
                        <div class="detailParameter" style="line-height:1.8em;">
                        		起点：<%=sousuoShuju.getSjleixingName()%><br />
                        		终点：<%=sousuoShuju.getSjxingtaiName()%><br />
                            	机型：<%=sousuoShuju.getShujuMark()%><br />
                            	起飞：<%=sousuoShuju.getShujuMark1()%><br />
                           	 	到达：<%=sousuoShuju.getShujuMark2()%><br />
                           	 	座位：<%=sousuoShuju.getShujuZong()%><br />
                           	 	售价：￥<%=sousuoShuju.getShujuDouble()%>元<br />
                            	日期：<input readonly id="sjduochuName" name="sjduochuName" class="Wdate" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"><br />
                        </div>
                        <div class="detailUrl"><a href="javascript:chaxunSjduochu(<%=sousuoShuju.getShujuId()%>)">在线查询</a></div>
                    </form>
                    </div>
                </div>

            </div>

            <%@ include file="left.jsp"%>

        </div>
    </div>
<%@ include file="footer.jsp"%>
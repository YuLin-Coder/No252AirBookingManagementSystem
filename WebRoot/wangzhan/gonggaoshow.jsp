<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="header.jsp"%>
<%@ include file="../jiazai/sousuobaohan.jsp"%>
    
    <!-- 内容 -->
    <div class="container">
        <div class="row">

            <div class="col-xs-12 col-sm-8 col-md-9" id="rightBox">

                <div class="positionBox">
                    <div class="titleBar">
                        <h1>当前位置</h1>
                        <span></span>
                        <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>wangzhan/gonggaolist.jsp"><%=newJcpeizhi.getGonggaoBieming() %>信息</a>
                    </div>
                </div>

                <div class="col-sm-12 col-md-12 pad">
                    
                    <div class="detailTitle" style="border:0px; background:none; font-size:20px; color:#000;">
                        <%=sousuoGonggao.getGonggaoName()%>
                    </div>

                    <div class="detailTime">
                       	 发布人：管理员　发布时间：<%=DateUtil.formatDate(sousuoGonggao.getGonggaoDate(),"yyyy-MM-dd HH:mm:ss") %>
                    </div>
                
                    <div class="detailContent">
                
                        <span style="color:#505050;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:14px;line-height:22px;">
                        &nbsp;&nbsp;&nbsp;&nbsp;<%=sousuoGonggao.getGonggaoMark()%>
                        </span>

                    </div>

                </div>

            </div>

            <%@ include file="left.jsp"%>

        </div>
    </div>
<%@ include file="footer.jsp"%>
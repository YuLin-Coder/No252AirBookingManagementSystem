<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="header.jsp"%>
<%@ include file="../jiazai/sousuobaohan.jsp"%>
  <!-- 内容 -->
    <div class="container">
        <div class="row">

            <div class="col-xs-12 col-sm-9 col-md-9">
                <div class="aboutBox">
                    <a href="<%=basePath %>wangzhan/gonggaoshow.jsp?gonggaoId=1" class="aboutMore">MORE +</a>
                    <div class="aboutTitle">
                        <h1>关于我们</h1>
                        <span>ABOUT US</span></div>
                    <section>

                        关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们 ... ...

                    </section>
                </div>
            </div>

            <div class="col-xs-12 col-sm-3 col-md-3">
                <div class="searchGroup">
                    <div class="searchBox">
                    <form id="form" method="post" name="form" action="<%=basePath%>wangzhan/shujulist.jsp">
                        <div class="title"><%=newJcpeizhi.getShujuBieming() %>查询</div>
						<input style="width:80px" type="text" placeholder="起点" id="sjleixingName" name="sjleixingName"/>
						<input style="width:80px" type="text" placeholder="终点" id="sjxingtaiName" name="sjxingtaiName"/>
                        <a href="javascript:document.form.submit();" type="button">搜 索</a>
                    </form>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <div style="background:#f9f9f9; padding-top:30px; margin-top:30px; padding-bottom:10px">
        <div class="container">
            <div class="row">

                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div class="productBox">
                        <div class="titleBar">
                            <h1><%=newJcpeizhi.getShujuBieming() %><%=newJcpeizhi.getSjleixingBieming() %></h1>
                            <span></span>
                            <%if(sjleixings!=null){ %>
							<%for(int i = 0; i < sjleixings.size(); i++){ %>
                            <a href="<%=basePath %>wangzhan/shujulist.jsp?sjleixingId=<%=sjleixings.get(i).getSjleixingId() %>"><%=sjleixings.get(i).getSjleixingName() %></a>
                            <%} %>
                            <%} %>
                            <a class="rightMore" href="<%=basePath %>wangzhan/shujulist.jsp">>></a>
                        </div>
                        <div class="list">
                        <%if(shujus!=null){ %>
			            <%for(int i = 0; i < shujus.size(); i++){ %>
			            	<%Shuju newShuju = shujus.get(i); %>
                            <div class="col-xs-6 col-sm-3 col-md-2 col-mm-6 productImg">
                                <a title="PRO-001" href='<%=basePath %>wangzhan/shujushow.jsp?shujuId=<%=newShuju.getShujuId()%>'>
                                    <span class="imgLink-hover"><span class="hover-link"></span></span>
                                    <img src="<%=basePath %><%=newShuju.getShujuImg() %>" alt="PRO-001" />
                                </a>
                                <a class="productTitle" href="<%=basePath %>wangzhan/shujushow.jsp?shujuId=<%=newShuju.getShujuId()%>" title="<%=newShuju.getShujuName() %>">
                                    <%=newShuju.getShujuName() %>
                                </a>
                            </div>
                        <%} %>
                        <%} %>
                           
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
<%@ include file="footer.jsp"%>
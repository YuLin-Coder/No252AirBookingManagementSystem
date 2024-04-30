<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.*"
	pageEncoding="utf-8"%>
<%@ include file="header.jsp"%>
<%@ include file="../jiazai/sousuobaohan.jsp"%>
<script type="text/javascript">
var userId = <%=userId%>;
	function addSjshaochu(sjduochuId){
		var sjshaochuZong = document.form1.sjshaochuZong.value;
		if(sjshaochuZong==""){
			document.form1.sjshaochuZong.focus();
			alert("请填写数量");
			return false;
		}
		if (!(/(^[1-9]\d*$)/.test(sjshaochuZong))) {
			document.form1.sjshaochuZong.focus();
			alert("请填写正确数量");
			return false;
		}
		if (userId==0) {
			if(confirm("注册登录网站？")){
				window.location.href="<%=basePath %>shouye/index.jsp";
				return false;
			}else{
				return false;
			}
		}
		$.post("<%=basePath %>addSjshaochu?userId="+userId+"&sjshaochuZong="+sjshaochuZong,{sjshaochuType1:sjduochuId},function(result){
			if(result.errorMsg){
				alert(result.errorMsg);
			}else{
				alert("订购成功，请到会员中心查看详情！");
				location.reload();
			}
		},"json");
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
                        <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>wangzhan/sjduochulist.jsp"><%=newJcpeizhi.getSjduochuBieming() %>信息</a>
                    </div>
                </div>

                <div class="col-sm-12 col-md-12 pad">
                <%if(sousuoSjduochu!=null){ %>   
                    <div class="col-sm-12 col-md-6 pad">
                        <div class="detailGlide" style="max-width:600px; margin:0 auto">
                            <div class="slider">
                                <ul class="slider__wrapper">
                                    <li data-responsive='' data-src='<%=basePath %><%=sousuoSjduochu.getSjduochuImg()%>' data-sub-html='' class='slider__item real'><a><img src='<%=basePath %><%=sousuoSjduochu.getSjduochuImg()%>'></a></li>
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
                            <%=sousuoSjduochu.getShujuName()%>
                        </div>
                        <div class="detailParameter" style="line-height:1.8em;">
                        		日期：<%=sousuoSjduochu.getSjduochuName()%><br />
                        		起点：<%=sousuoSjduochu.getSjleixingName()%><br />
                        		终点：<%=sousuoSjduochu.getSjxingtaiName()%><br />
                            	机型：<%=sousuoSjduochu.getSjduochuMark()%><br />
                            	起飞：<%=sousuoSjduochu.getSjduochuMark1()%><br />
                           	 	到达：<%=sousuoSjduochu.getSjduochuMark2()%><br />
                           	 	剩余：<%=sousuoSjduochu.getSjduochuZong2()%><br />
                            	价格：￥<%=sousuoSjduochu.getSjduochuDouble()%>元<br />
                            	购买：<input type="text" placeholder="填写数量" id="sjshaochuZong" name="sjshaochuZong"/><br />
                        </div>
                        <div class="detailUrl"><a href="javascript:addSjshaochu(<%=sousuoSjduochu.getSjduochuId()%>)">在线订购</a></div>
                    </form>
                    </div>
                <%}else{ %>
                没有航班
                <%} %>    
                <% %>    
                <% %>    
                <% %> 
                </div>

            </div>

            <%@ include file="left.jsp"%>

        </div>
    </div>
<%@ include file="footer.jsp"%>
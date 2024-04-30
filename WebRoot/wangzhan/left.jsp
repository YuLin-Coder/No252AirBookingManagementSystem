<%@ page language="java" contentType="text/html; charset=utf-8"  import="com.model.*,java.util.List,java.util.ArrayList"
	pageEncoding="utf-8"%>
            <div class="col-xs-12 col-sm-4 col-md-3">

                <div class="navigationBox" id="classification">
                    <div class="titleBar">
                        <h1><%=newJcpeizhi.getShujuBieming() %><%=newJcpeizhi.getSjleixingBieming() %></h1>
                        <span></span>
                    </div>
                    <div class="list">
                        <ul id="firstpane">
                        <%if(sjleixings!=null){ %>
						<%for(int i = 0; i < sjleixings.size(); i++){ %>
                        <li>
                            <a class='' href="<%=basePath %>wangzhan/shujulist.jsp?sjleixingId=<%=sjleixings.get(i).getSjleixingId() %>"><%=sjleixings.get(i).getSjleixingName() %></a>
                        </li>
                        <%} %>
                        <%} %>
                        </ul>
                    </div>
                </div>

            </div>
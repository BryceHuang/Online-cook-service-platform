<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="span3">
          <div class="well sidebar-nav">
		  <ul class="nav nav-list">
                            <li id="order1"><a href="<%=request.getContextPath()%>/cook/index.jsp" class="index" style="padding:15px;font-size:15px" > <i class="icon-home" ></i>  首&nbsp;&nbsp;&nbsp;&nbsp;页</a><li>
                            <li id="order2"><a href="<%=request.getContextPath()%>/cook/OnlineOrder.jsp" style="padding:15px;font-size:15px" class="exam"><i class="icon-film"></i> 在线订单</a><li>
                            <li id="order3"><a href="<%=request.getContextPath()%>/cook/MyRecievedOrder.jsp" class="personal" style="padding:15px;font-size:15px"><i class="icon-user"></i>待服务订单</a><li>
                            <li id="order32"><a href="<%=request.getContextPath()%>/cook/MyServingOrder.jsp" class="personal" style="padding:15px;font-size:15px"><i class="icon-user"></i>正服务订单</a><li>
                            <li id="order30"><a href="<%=request.getContextPath()%>/cook/MyHistoryOrder.jsp" class="personal" style="padding:15px;font-size:15px"><i class="icon-user"></i>历史订单</a><li>
                            <li id="order31"><a href="#" class="personal" style="padding:15px;font-size:15px"><i class="icon-user"></i>我的信息</a><li>
                            <li id="order4"><a href="#" class="examlog" style="padding:15px;font-size:15px"><i class="icon-folder-open"></i> 系统消息</a><li>
                            <li id="order5"><a href="#" style="padding:15px;font-size:15px" class="logout"><i class="icon-remove"></i>厨师信息认证</a><li>
                            <li id="order6"><a href="<%=request.getContextPath()%>/cook/logoutAction" style="padding:15px;font-size:15px" class="logout"><i class="icon-remove"></i> 退&nbsp;&nbsp;&nbsp;&nbsp;出</a><li>
                        </ul>
          </div><!--/.well -->
        </div><!--/span-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="span3">

          <div id="sidebar" class="well sidebar-nav">
		  <ul class="nav nav-list">
                            <li id="order1"><a href="<%=request.getContextPath()%>/admin/index.jsp" class="index" style="padding:15px;font-size:15px" > <i class="icon-home" ></i>  首&nbsp;&nbsp;&nbsp;&nbsp;页</a><li>
                            <li id="order2"><a href="<%=request.getContextPath()%>/admin/AdminServers.jsp" style="padding:15px;font-size:15px" class="exam"><i class="icon-tasks"></i> 客服管理</a><li>
                            <li id="order3"><a href="<%=request.getContextPath()%>/admin/AdminUsers.jsp" class="personal" style="padding:15px;font-size:15px"><i class="icon-th"></i> 用户管理</a><li>
                            <li id="order30"><a href="<%=request.getContextPath()%>/admin/AdminCooks.jsp" class="personal" style="padding:15px;font-size:15px"><i class="icon-th-large"></i> 厨师管理</a><li>
                            <li id="order4"><a href="<%=request.getContextPath()%>/admin/AdminOrders.jsp" class="examlog" style="padding:15px;font-size:15px"><i class="icon-folder-open"></i> 订单管理</a><li>
                            <li id="order41"><a href="<%=request.getContextPath()%>/admin/ShowCountryOrder.jsp" class="examlog" style="padding:15px;font-size:15px"><i class="icon-list"></i>各地订单数量</a><li>
                            <li id="order42"><a href="<%=request.getContextPath()%>/admin/ShowCuisineOrder.jsp" class="examlog" style="padding:15px;font-size:15px"><i class="icon-align-justify"></i>各菜系订单数量</a><li>
 							<li id="order43"><a href="<%=request.getContextPath()%>/admin/ShowTotalMoney.jsp" class="examlog" style="padding:15px;font-size:15px"><i class="icon-calendar"></i>过去七天订单金额</a><li>                           
                            <li id="order5"><a href="<%=request.getContextPath()%>/admin/logoutAction" style="padding:15px;font-size:15px" class="logout"><i class="icon-off"></i> 退&nbsp;&nbsp;&nbsp;&nbsp;出</a><li>
                        </ul>
          </div><!--/.well -->
          
          
     
        </div><!--/span-->
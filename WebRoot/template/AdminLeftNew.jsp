<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <div class="col-sm-3 col-md-2 sidebar">
		  <ul class="nav nav-sidebar">
                            <li id="order1" ><a href="<%=request.getContextPath()%>/admin/index.jsp"  style="padding:15px;font-size:15px" >  <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>  首&nbsp;&nbsp;&nbsp;&nbsp;页</a><li>
                            <li id="order2"><a href="<%=request.getContextPath()%>/admin/AdminServers.jsp" style="padding:15px;font-size:15px" class="exam"><i class="icon-tasks"></i> 客服管理</a><li>
                            <li id="order3"><a href="<%=request.getContextPath()%>/admin/AdminUsers.jsp" class="personal" style="padding:15px;font-size:15px"><i class="icon-th"></i> 用户管理</a><li>
                            <li id="order30" class="active"><a href="<%=request.getContextPath()%>/admin/AdminCooks.jsp" class="personal" style="padding:15px;font-size:15px"><span class="sr-only">(current)</span>厨师管理</a><li>
                            <li id="order4"><a href="<%=request.getContextPath()%>/admin/AdminOrders.jsp" class="examlog" style="padding:15px;font-size:15px"><i class="icon-folder-open"></i> 订单管理</a><li>
                            <li id="order5"><a href="<%=request.getContextPath()%>/admin/logoutAction" style="padding:15px;font-size:15px" class="logout"><i class="icon-off"></i> 退&nbsp;&nbsp;&nbsp;&nbsp;出</a><li>
        </ul>
</div>
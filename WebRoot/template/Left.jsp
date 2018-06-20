<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="span3">
				<div class="well sidebar-nav">
					<ul class="nav nav-list" >
              <li id="order1"><a href="<%=request.getContextPath()%>/user/index.jsp"  id="mainpage"  class="optionbar" style="padding:15px;
      font-size:15px"> <i class="icon-home" ></i>  首&nbsp;&nbsp;&nbsp;&nbsp;页</a><li>
                            <li id="order2"><a href="<%=request.getContextPath()%>/user/CreateOrder.jsp"  class="optionbar" style="padding:15px;
      font-size:15px"><i class="icon-film"></i> 发布订单 </a><li>
                            <li id="order3"><a href="<%=request.getContextPath()%>/user/MyOrders.jsp" class="optionbar" style="padding:15px;
      font-size:15px" ><i class="icon-folder-open"></i> 我的未完成订单</a><li>
      						<li id="order32"><a href="<%=request.getContextPath()%>/user/MyScoringOrders.jsp" class="optionbar" style="padding:15px;
      font-size:15px" ><i class="icon-folder-open"></i> 待评分订单</a><li>
      						<li id="order31"><a href="<%=request.getContextPath()%>/user/MyHistroyOrders.jsp" class="optionbar" style="padding:15px;
      font-size:15px" ><i class="icon-folder-open"></i> 我的历史订单</a><li>
      						<li id="order4"><a href="#" class="optionbar" style="padding:15px;
      font-size:15px"><i class="icon-shopping-cart"></i> 购物车 </a><li>
                            <li id="order5"><a href="<%=request.getContextPath()%>/user/UpdateUser.jsp" class="optionbar" style="padding:15px;
      font-size:15px"> <i class="icon-user"></i> 我的信息</a><li>
                            <li id="order6"><a href="<%=request.getContextPath()%>/user/MyPurse.jsp" class="optionbar" style="padding:15px;
      font-size:15px"><i class=" icon-plus"></i> 我的钱包</a><li>
                            <li id="order7"><a href="<%=request.getContextPath()%>/user/MySysInfo.jsp" class="optionbar" style="padding:15px;
      font-size:15px"> <i class="icon-user"></i> 我的系统消息</a><li>
      						<li id="order8"><a href="#" class="optionbar" style="padding:15px;
      font-size:15px"> <i class="icon-user"></i> 系统论坛</a><li>
                            <li id="order9"><a href="<%=request.getContextPath()%>/user/logoutAction" class="optionbar" style="padding:15px;
      font-size:15px"><i class="icon-remove"></i> 退&nbsp;&nbsp;&nbsp;&nbsp;出 </a><li>
            </ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
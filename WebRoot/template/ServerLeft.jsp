<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="span3">

          <div class="well sidebar-nav">
		  <ul class="nav nav-list">
                            <li id="order1"><a href="<%=request.getContextPath()%>/server/index.jsp" class="index" style="padding:15px;font-size:15px" > <i class="icon-home" ></i>  首&nbsp;&nbsp;&nbsp;&nbsp;页</a><li>
                            <li id="order2"><a href="<%=request.getContextPath()%>/server/AduitCooks.jsp" style="padding:15px;font-size:15px" class="exam"><i class="icon-folder-open"></i> 厨师信息审核</a><li>
                            <li id="order3"><a href="<%=request.getContextPath()%>/server/ChangeOrderUser.jsp" class="personal" style="padding:15px;font-size:15px"><i class="icon-th-list"></i> 用户申请订单变更</a><li>
                            <li id="order30"><a href="<%=request.getContextPath()%>/server/ChangeOrderCook.jsp" class="personal" style="padding:15px;font-size:15px"><i class="icon-list"></i>厨师申请订单变更</a><li>
                            <li id="order4"><a href="<%=request.getContextPath()%>/server/CreateNotice.jsp" class="examlog" style="padding:15px;font-size:15px"><i class=" icon-edit"></i>通知信息发布</a><li>
                            <li id="myMessagePage"><a href="<%=request.getContextPath()%>/server/MyMessage.jsp" class="examlog" style="padding:15px;font-size:15px"><i class="icon-envelope"></i>我的消息</a><li>
                            <li id="order5"><a href="<%=request.getContextPath()%>/server/logoutAction" style="padding:15px;font-size:15px" class="logout"><i class="icon-off"></i> 退&nbsp;&nbsp;&nbsp;&nbsp;出</a><li>
                        </ul>
          </div><!--/.well -->
          
          
     
        </div><!--/span-->
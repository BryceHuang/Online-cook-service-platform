<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<!-- 顶部tab -->
	<div class="tab-header">
		<div class="inner">
			<div class="pull-left">
				<div class="pull-left">嗨，欢迎来到<span class="cr">家宴帮</span></div>
				<!-- <a href="#">食材代购</a>
				<a href="#">帮助中心</a> -->
			</div>
			<div class="pull-right">
				<a href="#">我的“家宴帮”</a>
				<a href="MyOrders.jsp">我的订单</a>
				<a href="<%=request.getContextPath()%>/cook/logoutAction">退出</a>
			</div>
		</div>
	</div>
	<!-- 顶部标题 -->
	<div class="bgf5 clearfix">
		<div class="top-user">
			<div class="inner">
				<a class="logo" href="index2.jsp"><img src="<%=request.getContextPath()%>/UI/simple/images/icons/logo.jpg" alt="家宴帮" class="cover"></a>
				<div class="title" id="logosidename">个人中心</div>
			</div>
		</div>
	</div>
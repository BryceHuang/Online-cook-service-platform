<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<section class="user-center inner clearfix">
			<div class="pull-left bgf">
				<a href="userindex2.jsp" class="title">欢迎页</a>
				<dl class="user-center__nav">
					<dt>帐户信息</dt>
					<a href="UpdateUser2.jsp"><dd id="personInfo" >个人资料</dd></a>
					<a href="MyPurse2.jsp"><dd id="myPurse">资金管理</dd></a>
					<a href="MyMoneyRecord.jsp"><dd id="myMoneyRecord">资金记录</dd></a>
					<!-- <a href="#"><dd>积分平台</dd></a>
					<a href="#"><dd>收货地址</dd></a>
					<a href="#"><dd>我的优惠券</dd></a>
					 -->					
					<a href="UpdateUserPassword.jsp"><dd id="updatePasswordPage">修改登录密码</dd></a>
				</dl>
				<dl class="user-center__nav">
					<dt>订单中心</dt>
					<a href="CreateOrder2.jsp"><dd id="createOrder">发布订单</dd></a>
					<a href="MyOrders2.jsp"><dd id="myOrders">我的订单</dd></a>
					<a href="MyChangingOrders.jsp"><dd id="myChangingOrders">订单变更</dd></a>
					<a href="MyHistroyOrders2.jsp"><dd id="myHistroyOrders">历史订单</dd></a>
					<!-- <a href="#"><dd>我的收藏</dd></a>
					<a href="#"><dd>退款/退货</dd></a> -->
				</dl>
				<dl class="user-center__nav">
					<dt>服务中心</dt>
					<a href="MyMessage.jsp"><dd id="myMessagePage">系统消息</dd></a>
					<!-- <a href="#"><dd>订单查询</dd></a>
					<a href="#"><dd>厨师查询</dd></a>
					<a href=""><dd>数据自助下载</dd></a>
					<a href="#"><dd>配送服务</dd></a> -->
					<a href="Info.jsp?page=service"><dd id="service">售后服务</dd></a>					
					<a href="Info.jsp?page=contract"><dd id="contract" >用户协议</dd></a>
					<a href="Info.jsp?page=problem"><dd id="problem">常见问题</dd></a>
				</dl>
				<dl class="user-center__nav">
					<dt>新手上路</dt>					
					<a href="Info.jsp?page=process"><dd id="process">订单流程</dd></a>
					<a href="Info.jsp?page=orderProblem"><dd id="orderProblem">订单常见问题</dd></a>					
				</dl>
				<dl class="user-center__nav">
					<dt>家宴帮</dt>
					<a href="Info.jsp?page=introduction"><dd id="introduction">网站简介</dd></a>
					<a href="Info.jsp?page=privacy"><dd id="privacy">隐私说明</dd></a>
				</dl>
			</div>
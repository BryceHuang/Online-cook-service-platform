<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<section class="user-center inner clearfix">
			<div class="pull-left bgf">
				<a href="index2.jsp" class="title">欢迎页</a>
				<dl class="user-center__nav">
					<dt>帐户信息</dt>
					<a href="UpdateCook.jsp"><dd id="personInfo" >个人资料</dd></a>
					<a href="MyPurse.jsp"><dd id="myPurse">资金管理</dd></a>
					<a href="MyMoneyRecord.jsp"><dd id="myMoneyRecordPage">资金记录</dd></a>
					<a href="UpdateCookPassword.jsp"><dd id="updatePasswordPage">修改登录密码</dd></a>
				</dl>
				<dl class="user-center__nav">
					<dt>订单中心</dt>
					<a href="OnlineOrder2.jsp"><dd id="OnlineOrders">在线订单</dd></a>
					<a href="MyOrders.jsp"><dd id="myOrders">我的订单</dd></a>
					<a href="MyChangingOrders.jsp"><dd id="myChangingOrders">订单变更</dd></a>
					<a href="MyHistoryOrder2.jsp"><dd id="myHistroyOrders">历史订单</dd></a>					
				</dl>
				<dl class="user-center__nav">
					<dt>服务中心</dt>
					<a href="MyMessage.jsp"><dd id="myMessagePage">系统消息</dd></a>
					<!-- <a href="#"><dd>订单查询</dd></a> -->
					<c:if test="${cook.is_aduit != 1}">
	                <a href="ApplyInfoAduit.jsp"><dd id="aduitPage">厨师认证</dd></a>		
	                </c:if>								
					<!-- <a href=""><dd>数据自助下载</dd></a> -->
					<a href="Info.jsp?page=contract"><dd id="contract">厨师协议</dd></a>
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
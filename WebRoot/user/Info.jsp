<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/UI/simple/favicon.ico">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/iconfont.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/global.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/swiper.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/styles.css">
	<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.1.12.4.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/bootstrap.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/swiper.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/global.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.DJMask.2.1.1.js" charset="UTF-8"></script>
	
	<title>家宴帮</title>
</head>
<body>
<%@include file="../UI/simple/template/UsualHeader.jsp" %>

	<div class="content clearfix bgf5">
	<%@include file="../UI/simple/template/UserWelcomeLeft.jsp" %>
	<div class="pull-right  bgf">
		<div class="user-center__content">
					<div class="head-box" id="right-titile"></div>
					<div class="html-code" id="right-content">						
					</div>
		</div>	
	</div>
	</div>
	
		<%@include file="../UI/simple/template/UserRight.jsp" %>
		<%@include file="../UI/simple/template/Footer.jsp" %>
	
	<script type="text/javascript">
$(function(){
	var pageId=GetRequest();
	console.log(pageId);
	$("#"+pageId.page).addClass("active");
	if(pageId.page=="contract")
	{
		$("#right-titile").html("服务中心-用户协议");
		$("#right-content").html("<h3>1、系统将会收集您在系统中的使用数据</h3><p style='text-indent: 2em'>您在系统中的服务数据将会用于对您的身份特征的分析，这些数据将只作为分析使用，不会开放给别的用户查看。</p><h3>2、关于服务的协议</h3><p style='text-indent: 2em'>您在发布订单之后将相当于签订了服务协议，如果您的服务在经过我们工作人员调查之后发现您的不合规定的行为，我们将降低您的系统评分。</p>");
	}
	if(pageId.page=="problem")
	{
		$("#right-titile").html("服务中心-常见问题");
		$("#right-content").html("<h3>1、我的订单将如何被接收？</h3><p style='text-indent: 2em'>订单发布之后，将会根据系统预设条件推送给系统的厨师进行抢单，订单在被接收之后您会受到系统的通知消息。</p><h3>2、用户的系统评分有何作用？</h3><p style='text-indent: 2em'>您的系统评分是系统对您的一个评价指标，如果您的评分较低，那么您可能无法享受订单的一些优惠服务。所以，珍惜好您每一次的与评分相关的操作机会。</p>");
	}
	if(pageId.page=="process")
	{
		$("#right-titile").html("新手上路-订单流程");
		$("#right-content").html(" <img src='../UI/img/process.png' style='height:70%;width:100%'>");
	}
	if(pageId.page=="introduction")
	{
		$("#right-titile").html("家宴帮-网站简介");
		$("#right-content").html("<h3  align='center'>在线私厨平台</h3><p style='text-indent: 2em'>为您提供优质的厨师上门服务,系统将为您提供资格认证的厨师，让您吃饱的同时更能吃好，在家就能享受星级酒店大厨级别的服务。</p>");
	}
	if(pageId.page=="privacy")
	{
		$("#right-titile").html("家宴帮-隐私简介");
		$("#right-content").html("<h3>1、关于用户身份信息？</h3><p style='text-indent: 2em'>系统将保存您的厨师认证信息用于对您的身份的认证，您的证件信息将只作为身份信息，不会对外公开。</p><h3>2、关于用户系统数据？</h3><p style='text-indent: 2em'>您的历史服务数据将用于系统的分析，帮助我们的系统设计，不会对其他用户公开。</p>");
	}
	if(pageId.page=="orderProblem")
	{
		$("#right-titile").html("新手上路-订单常见问题");
		$("#right-content").html("<h3>1、订单在接收之后还能不能退了？</h3><p style='text-indent: 2em'>您可以选择提交订单变更的申请，申请将由客服进行负责审核，如果审核通过订单就将退了。</p><h3>2、我能够自己选择厨师吗？</h3><p style='text-indent: 2em'>暂时还是厨师抢单的模式，根据用户的反馈，今后可能会开放预约厨师服务的功能。</p>");
	}
	if(pageId.page=="service")
	{
		$("#right-titile").html("订单中心-售后服务");
		$("#right-content").html("<h3>1、如果我对服务非常不满意怎么办？</h3><p style='text-indent: 2em'>您可以选择向客服提交订单反馈，我们将认真调查，给出相应的赔偿措施。</p><h3>2、我对厨师的评分有意义吗？</h3><p style='text-indent: 2em'>我们将根据您对厨师的评分来评估厨师的服务水平，并最终影响厨师的评分。</p>");
	}


});

function GetRequest() {   
   var url = location.search; //获取url中"?"符后的字串   
   var theRequest = new Object();   
   if (url.indexOf("?") != -1) {   
      var str = url.substr(1);   
      strs = str.split("&");   
      for(var i = 0; i < strs.length; i ++) {   
         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);   
      }   
   }   
   return theRequest;   
} 
	
	</script>
</body>
</html>
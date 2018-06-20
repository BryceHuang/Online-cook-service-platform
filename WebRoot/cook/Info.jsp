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
	
	<style>
	a:link{text-decoration:none ;}
	a:hover{color:#666;}
	</style>
	<title>家宴帮</title>
</head>
<body>
<%@include file="../UI/simple/template/CookHeader.jsp" %>

	<div class="content clearfix bgf5">
		<%@include file="../UI/simple/template/CookLeft.jsp" %>
		<div class="pull-right bgf">
				<div class="user-center__content">
					<div class="head-box" id="right-titile"></div>
					<div class="html-code" id="right-content">
						
					</div>
				</div>
		</div>
    </div>
	
		<%@include file="../UI/simple/template/UserRight.jsp" %>
		<%@include file="../UI/simple/template/Footer.jsp" %>
	
<script>
$(function(){
	var pageId=GetRequest();
	console.log(pageId);
	$("#"+pageId.page).addClass("active");
	if(pageId.page=="contract")
	{
		$("#right-titile").html("服务中心-厨师协议");
		$("#right-content").html("<h3>1、系统将会收集您在系统中的使用数据</h3><p style='text-indent: 2em'>您在系统中的服务数据将会用于对您的服务水平的分析，其中部分会开放给普通用户查看。</p><h3>2、关于服务的协议</h3><p style='text-indent: 2em'>您在接收订单之后有义务完成服务，如果您的服务在经过我们工作人员调查之后发现您的不合规定的行为，我们将降低您的系统评分。</p>");
	}
	if(pageId.page=="problem")
	{
		$("#right-titile").html("服务中心-常见问题");
		$("#right-content").html("<h3>1、厨师该如何进行认证？</h3><p style='text-indent: 2em'>厨师在厨师认证页面上传自己的身份证、厨师证、健康证并提交审核之后，客服会在2-3个工作日内完成审核操作。</p><h3>2、厨师评分有何作用？</h3><p style='text-indent: 2em'>由于订单发布时会根据当地厨师的评分进行降序排序分发订单，评分越高越容易获得订单，所以一定要珍惜每一次的订单服务好的机会。</p>");
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
		$("#right-content").html("<h3>1、关于厨师认证？</h3><p style='text-indent: 2em'>系统将保存您的厨师认证信息用于对您的身份的认证，您的证件信息将只作为身份信息，不会对外公开。</p><h3>2、关于系统数据？</h3><p style='text-indent: 2em'>您的历史服务数据将用于系统的分析，并且部分的历史数据将对普通用户开放。</p>");
	}
	if(pageId.page=="orderProblem")
	{
		$("#right-titile").html("新手上路-订单常见问题");
		$("#right-content").html("<h3>1、订单在接收之后还能不能退了？</h3><p style='text-indent: 2em'>您可以选择提交订单变更的申请，申请将由客服进行负责审核，如果审核通过订单就将退了。</p><h3>2、订单的服务费什么时候才会到厨师手中？</h3><p style='text-indent: 2em'>用户在支付订单之后，对订单进行评分，订单的金额就将作为服务费发送到您的系统账户中。</p>");
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
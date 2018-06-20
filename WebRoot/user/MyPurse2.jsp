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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/swiper.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/styles.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/css/bootstrap-table.css" rel="stylesheet" /> 
	<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.1.12.4.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/bootstrap.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/swiper.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/global.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.DJMask.2.1.1.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap-table.js"></script>	    
	<script src="<%=request.getContextPath()%>/js/bootstrap-table-zh-CN.js"></script>
	<style>
	a:link{text-decoration:none ;}
	a:hover{color:#666;}
	</style>
	<title>家宴帮</title>
</head>
<body>
<%@include file="../UI/simple/template/UsualHeader.jsp" %>

	<div class="content clearfix bgf5">
		<%@include file="../UI/simple/template/UserWelcomeLeft.jsp" %>
		<div class="pull-right">
		<div class="user-content__box clearfix bgf">
					<div class="title">账户信息-资金管理</div>
					<div class="assets-box">
						<samll class="c6">账户余额：</samll>
						<samll class="cr">¥</samll>
						<b class="fz16 cr" id="finalMoney">${user.user_money}</b>
						<ul class="nav pull-right" role="tablist">
							<li role="presentation" class="active"><a href="#add" aria-controls="add" role="tab" data-toggle="tab">充值</a></li>
							<li role="presentation" class=""><a href="#up" aria-controls="up" role="tab" data-toggle="tab">提现</a></li>
						</ul>
					</div>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane fade in active" id="add">
							<form id="addMoneyForm" class="user-setting__form" role="form">
							<input type="hidden" name="uid" value="${user.user_id}">
                    		<input type="hidden" name="username" value="${user.user_name}">
                    		<input type="hidden" name="money" value="${user.user_money}">
								<div class="form-group">
									<label for="add-money">充值金额</label>
									<input id="addmoney" name="addmoney" class="assets-control" placeholder="输入您要充值的金额" type="text">
								</div>
								<div class="form-group">
									<label for="add-note">备注（可不填）</label>
									<textarea id="add-note" class="assets-control" placeholder=""></textarea>
								</div>
								<div class="form-group">
									<label for="note">支付方式（手续费 <span id="tip">0</span>）</label>
									<div class="pay-method-box tags-box">
										<label><input checked="checked" type="radio" name="paykind" value="2"><i class="pay-method__img alipay"></i></label>
										<label><input type="radio" name="paykind" value="3"><i class="pay-method__img wechat"></i></label>
									</div>
								</div>
								<div class="user-form-group tags-box">
								<input  type="button" value="立即支付" onclick="addMoney()" class="btn">
								<input  style="margin-left:50px" type="reset" value="重置"  class="btn">
								</div>
							</form>
						</div>
						<div role="tabpanel" class="tab-pane fade" id="up">
							<form class="user-setting__form" id="cashMoneyForm" role="form">
							<input type="hidden" name="username" value="${user.user_name}">
							<input type="hidden" name="money" value="${user.user_money}">
								<div class="form-group">
									<label for="up-money">提现金额</label>
									<input id="up-money" name="cashmoney" class="assets-control" placeholder="输入您要提现的金额" type="text">
									<span class="help-block fz12"></span>
								</div>
								<div class="form-group">
									<label for="up-name">姓名</label>
									<input id="up-name" class="assets-control" placeholder="输入您的姓名" type="text">
								</div>
								<div class="form-group">
									<label for="up-bank">开户行</label>
									<input id="up-bank" class="assets-control" name="bankKind" placeholder="开户行" type="text">
								</div>
								<div class="form-group">
									<label for="up-number">银行账号</label>
									<input id="up-number" class="assets-control" name="cardId" placeholder="银行账号" type="text">
								</div>
								<div class="form-group">
									<label for="up-phone">手机号码</label>
									<input id="up-phone" class="assets-control" value="${user.user_tel}" placeholder="请输入您的联系号码" type="text">
								</div>
								<div class="form-group">
									<label for="up-note">备注（可不填）</label>
									<textarea id="up-note" class="assets-control" placeholder=""></textarea>
								</div>
								<div class="user-form-group tags-box">
								
								<input  type="button" value="申请提现" onclick="cashMoney()" class="btn">
								<input  style="margin-left:50px" type="reset" value="重置"  class="btn">
								</div>
							</form>
						</div>
						<div role="tabpanel" class="tab-pane fade" id="log">
							<div class="bs-example" data-example-id="hoverable-table">
								<table id="tb_moneyhistroy" class=""></table> 
								<table id="tb_onlineorder2" class=""></table> 
							</div>
						</div>
					</div>
				</div>
		</div>
    </div>
	
		<%@include file="../UI/simple/template/UserRight.jsp" %>
		<%@include file="../UI/simple/template/Footer.jsp" %>
<script type="text/javascript">

	$(function () {
/*	
		$("#footer1").css("margin-left","15px");
		$("#footer2").css("margin-left","10px");
		$("#footer3").css("margin-left","10px"); */
	  	$("#logosidename").html("订单中心");
	/*----------------------我的所有订单--------------------------------*/
	  	   $("#myPurse").addClass("active");
/*	//1.初始化Table
   		var oTable = new TableInit();
    	oTable.Init();

    //2.初始化Button的点击事件
  		var oButtonInit = new ButtonInit();
   	oButtonInit.Init(); */
   	$(".form-control").css("height","30px");
   	$(".dropdown-toggle").css("height","25px");
});




function addMoney()
	{
		 
 $.ajax({
					          url:"<%=request.getContextPath()%>/user/updateMoneyAction",
					                type:"post",
					                dataType:"json",
					                data:$("#addMoneyForm").serialize(),
					                aysnc:false,
					                success:function(data) {
					                
					                   $("#addMoneyForm")[0].reset();					                
									   var strArray=eval(data);
					                   var content=strArray.message_content;
					                   var title=strArray.message_title;
					                   $("#messageTitle").text(title);
					                   $("#messageContent").text(content);
									   $("#messagebox").slideDown("slow");
									   setTimeout("hideMessageBox()",4000); 									                   						                   
					                },
					                error:function(ajax) {
					                    alert("失败！");
					                }
					            }) ;    
	};
function cashMoney(){
		 
 $.ajax({
					          url:"<%=request.getContextPath()%>/user/cashMoneyAction",
					                type:"post",
					                dataType:"json",
					                data:$("#cashMoneyForm").serialize(),
					                aysnc:false,
					                success:function(data) {
					                 $("#cashMoneyForm")[0].reset();
									   var strArray=eval(data);
					                   var content=strArray.message_content;
					                   var title=strArray.message_title;
					                   $("#messageTitle").text(title);
					                   $("#messageContent").text(content);
									   $("#messagebox").slideDown("slow");
									   setTimeout("hideMessageBox()",4000); 
									   									                   						                   
					                },
					                error:function(ajax) {
					                    alert("失败！");
					                }
					            }) ;  

}



</script>
	
</body>
</html>
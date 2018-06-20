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
	<script>
    
	$(function() {
	$("#logosidename").html("订单支付");
		
       
	});
	
</script>
	<title>家宴帮</title>
</head>
<body>
<%@include file="../UI/simple/template/UsualHeader.jsp" %>
<%
	//获得取过来的参数
	Integer orderId=Integer.parseInt(request.getParameter("payOrderId"));
	String orderCustom=request.getParameter("payOrderCustom");
	String foodList=request.getParameter("payOrderFood");
	Double orderPrice=Double.parseDouble(request.getParameter("payOrderPrice"));
	String orderPlace=request.getParameter("payOrderPlace");
	String startTime=request.getParameter("payOrderStartTime");
	
 %>
	<div class="content clearfix bgf5">
		
		<!-- 顶部标题 -->
	
	<div class="content clearfix bgf5">
	<form id="payMoneyForm">
	<input type="hidden" name="price" value="<%=orderPrice %>">
	<input type="hidden" name="oid" value="<%=orderId %>">
	
		<section class="user-center inner clearfix">
			<div class="user-content__box clearfix bgf">
				<div class="title">订单中心-确认支付 </div>
				
					<div class="shop-title">订单信息</div>
					<div class="shop-order__detail">
						<table class="table">
							<thead>
								<tr>
									<th width="120">订单内容</th>
									<th width="300">订单价格</th>
									<th width="150">客户姓名</th>
									<th width="200">服务地点</th>
									<th width="80">总价</th>
								</tr>
							</thead>
							<tbody>
								<tr>								
									<td>
										<div class="name ep3"><%=foodList %></div>
										<!-- <div class="type c9">颜色分类：深棕色  尺码：均码</div> -->
									</td>
									<td>¥<%=orderPrice %></td>
									<td><%=orderCustom %></td>
									<td><%=orderPlace %></td>
									<td>¥<%=orderPrice %></td>
								</tr>
								
							</tbody>
						</table>
					</div>
					<div class="shop-cart__info clearfix">
						<div class="pull-left text-left">
							<div class="info-line text-nowrap">下单时间：<span class="c6"><%=startTime.replace('T', ' ') %></span></div>
							<div class="info-line text-nowrap">订单号：<span class="c6"><%=orderId %></span></div>
						</div>
						<div class="pull-right text-right">
							<div class="form-group">
								<label for="coupon" class="control-label">优惠券使用：</label>
								<select id="coupon" >
									<option value="-1" selected>- 请选择可使用的优惠券 -</option>
									<option value="1">【满￥20.0元减￥2.0】</option>
									<option value="2">【满￥30.0元减￥2.0】</option>
									<option value="3">【满￥25.0元减￥1.0】</option>
									<option value="4">【满￥10.0元减￥1.5】</option>
									<option value="5">【满￥15.0元减￥1.5】</option>
									<option value="6">【满￥20.0元减￥1.0】</option>
								</select>
							</div>
							<script>
								$('#coupon').bind('change',function() {
									console.log($(this).val());
								})
							</script>
							<div class="info-line">优惠活动：<span class="c6">无</span></div>
							<div class="info-line">已支付定金：<span class="c6">¥<%=orderPrice/2 %></span></div>
							<div class="info-line"><span class="favour-value">已优惠 ¥0.0</span>合计：<b class="fz18 cr">¥<%=orderPrice/2 %></b></div>
							<div class="info-line fz12 c9">（可获 <span class="c6">20</span> 积分）</div>
						</div>
					</div>
					<div class="shop-title">订单支付</div>
					<div class="pay-mode__box">
						<div class="radio-line radio-box">
							<label class="radio-label ep">
								<input name="paykind" value="1" autocomplete="off" type="radio"><i class="iconfont icon-radio"></i>
								<span class="fz16">余额支付</span><span class="fz14">（可用余额：¥${user.user_money}）</span>
							</label>
							<div class="pay-value">支付<b class="fz16 cr"><%=orderPrice/2 %></b>元</div>
						</div>
						<div class="radio-line radio-box">
							<label class="radio-label ep">
								<input name="paykind" value="2" autocomplete="off" type="radio"><i class="iconfont icon-radio"></i>
								<img src="<%=request.getContextPath()%>/UI/simple/images/icons/alipay.png" alt="支付宝支付">
							</label>
							<div class="pay-value">支付<b class="fz16 cr"><%=orderPrice/2 %></b>元</div>
						</div>
						<div class="radio-line radio-box">
							<label class="radio-label ep">
								<input name="paykind" value="3" autocomplete="off" type="radio"><i class="iconfont icon-radio"></i>
								<img src="<%=request.getContextPath()%>/UI/simple/images/icons/paywechat.png" alt="微信支付">
							</label>
							<div class="pay-value">支付<b class="fz16 cr"><%=orderPrice/2 %></b>元</div>
						</div>
					</div>
					<div class="user-form-group shopcart-submit">
						<input  type="button" value="继续支付" onclick="payMoney()" class="btn">
					</div>
					<script>
						$(document).ready(function(){
							$(this).on('change','input',function() {
								$(this).parents('.radio-box').addClass('active').siblings().removeClass('active');
							})
						});
					    function payMoney(){
					      $.ajax({
					          url:"<%=request.getContextPath()%>/user/payOrderAction",
					                type:"post",
					                dataType:"json",
					                data:$("#payMoneyForm").serialize(),
					                aysnc:true,
					                success:function(data) {
					                  // $("#myform")[0].reset();
					                   alert("支付成功！");
					                   window.location.href="<%=request.getContextPath()%>/user/MyOrders2.jsp";
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
				</form>
			</div>
		</section>
	</div>
    </div>
	
		<%@include file="../UI/simple/template/UserRight.jsp" %>
		<%@include file="../UI/simple/template/Footer.jsp" %>
	
	
</body>
</html>
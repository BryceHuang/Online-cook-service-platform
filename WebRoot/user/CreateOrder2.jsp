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
	<!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/bootstrap.min.css"> -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/swiper.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css2/styles.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css2/bootstrap.min.css">
    <link href="<%=request.getContextPath()%>/css/bootstrap-datetimepicker.min.css"  rel="stylesheet" media="screen" type="text/css" />
	<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.1.12.4.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/bootstrap.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/swiper.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/global.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.DJMask.2.1.1.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<style>
	a:link{text-decoration:none ;}
	a:hover{color:#666;}
	.tableLabelTd{width:120px;}
	</style>
	<title>家宴帮</title>
</head>
<body>
<%@include file="../UI/simple/template/UsualHeader.jsp" %>

	<div class="content clearfix bgf5">
		<%@include file="../UI/simple/template/UserWelcomeLeft.jsp" %>
		<div class="pull-right">
				<div class="user-content__box clearfix bgf">
				<form   name="myform" id="myform">
					<input type="hidden" id="price" name="price">
					<input type="hidden" id="leftprice" name="" val="${user.user_money}">
					<div class="title">订单中心-订单内容</div>
					<table  class="table">
					<tr>
						<td class="tableLabelTd">套餐档次：</td>
						<td>
							<select style="margin-left: 45px;" name="cuisinelevel" id="cuisinelevel" onchange="changelevel()">
							<option></option>
							<option value="高档">高档</option>
							<option value="中档">中档</option>
							</select>
						</td>
					</tr>
					<tr>
						<td  class="tableLabelTd">菜系：</td>
						<td>
							<select style="margin-left: 45px;" name="cuisinesystem" id="cuisinesystem" onchange="changesystem()">
							<option></option>
							<option value="鲁菜">鲁菜</option>
							<option value="川菜">川菜</option>
							<option value="粤菜">粤菜</option>
							<option value="苏菜">苏菜</option>
							<option value="闽菜">闽菜</option>
							<option value="徽菜">徽菜</option>
							<option value="湘菜">湘菜</option>
							<option value="浙菜">浙菜</option>
							</select>
						</td>
					</tr>					
					<tr>
                    				<td  class="tableLabelTd">套餐种类：</td>
                    				<td>
                    				    <select style="margin-left: 45px;" name="cuisinekind" id="cuisinekind"  onchange="changekind()">
                    				    	<option></option>
	                    				    <option value="四菜一汤" >四菜一汤</option>
	                    				    <option value="六菜一汤" >六菜一汤</option>
	                    				    <option value="八菜一汤">八菜一汤</option>
	                    				    <option value="十菜一汤" >十菜两汤</option>
	                    				    <option value="十二菜两汤" >十二菜两汤</option>
	                    				    <option value="十五菜两汤" >十五菜两汤</option>                   				         
                    				    </select>
                    					
                    				</td>
                    			</tr>
					
					
					</table>
					
					<div class="title">订单中心-订单信息</div>				
                    		<table class="table">
                    			
                    			<tr>
                    				<td  class="tableLabelTd">姓&nbsp;&nbsp;名：</td>
                    				<td>
                    					<input type="text" name="username" id="username" style="margin-left: 48px;">
                    				</td>
                    				<td>
                    					<span id="usernameInfo"><font color="red"></font></span>
                    				</td>
                    			</tr>
                    			<tr>
                    				<td  class="tableLabelTd">手机号码：</td>
                    				<td>
                    					<input type="text" name="tel" id="tel" style="margin-left: 48px;">
                    				</td>
                    				<td>
                    					<span id="telInfo"><font color="red"></font></span>
                    				</td>
                    			</tr>
                    			<tr>
                    				<td  class="tableLabelTd">用餐时间：</td>
                    				<td>
                    				   
               
                <div class="controls input-append date form_datetime"  data-link-field="dtp_input1">
                    <input  size="16" type="text" value="" readonly style="margin-left: 48px;">                   
					<span class="add-on"><i class="icon-th"></i></span>
                </div>
				<input type="hidden" id="dtp_input1" name="dtp_input1" value="" /><br/>
            
			

		                         	</td>
                    				
                    				<td>
                    					<span id="timeInfo"><font color="red"></font></span>
                    				</td>
                    				
                    			</tr>
                    			<tr style="height:100px;">
                    				<td  class="tableLabelTd">用餐地点：</td>
                    				<td>
                    				  <div  style="margin-left:45px;">
                    				  <select id="province" onchange="getCity()"></select>
									  <select id="city" onchange="getArea()"></select>
									  <select id="area" name="area"></select> 
									  </div>
                    				 <br>
                    					<input type="text" name="place" id="place" style="margin-left: 45px;width:80%" placeholder="建议您如实填写详细地址">
                    				</td>
                    				<td>
                    					<span id="placeInfo"><font color="red"></font></span>
                    				</td>
                    				
                    			</tr>
                    			<tr>
                    				<td  class="tableLabelTd">备注信息：</td>
                    				<td>
                    					<input type="text" name="message" id="message" style="margin-left: 48px;">
                    				</td>
                    				<td>
                    					<span id="messageInfo"><font color="red"></font></span>
                    				</td>
                    				
                    			</tr>
                    			<tr>
                    				<td  class="tableLabelTd">订金支付方式：</td>
                    				<td>
                    					<div class="pay-method-box tags-box" style="margin-left: 40px;display: -webkit-flex;display: flex;flex-flow: row nowrap;justify-content: space-between;">
										<label><input checked="checked" type="radio"  name="paykind" value="2" id="alipay"><i class="pay-method__img alipay"></i></label>
										<label><input type="radio" name="paykind"  value="3"><i class="pay-method__img wechat"></i></label>
										<label><input type="radio" name="paykind"  id="yue" value="1"><i class="pay-method__img yue"></i></label>
									</div>
                    				</td>
                    				<td>
                    					<span id="messageInfo"><font color="red"></font></span>
                    				</td>
                    				
                    			</tr>
                    			<tr>
                    				<td  class="tableLabelTd">订单价格：</td>
                    				<td>
                    					<b class="fz18 cr" id="showprice" style="margin-left:55px" >¥0.0</b>  <span class="favour-value" id="showbookedprice">需支付 ¥0.0定金</span>
                    				</td>
                    				<td>
                    					<span id="priceinfo"><font color="red"></font></span>
                    				</td>
                    				
                    			</tr>
                    			<tr>
                    				<td colspan="3" align="center">
                    					<input style="margin-left:150px" type="button" value="提交订单" onclick="check()" class="btn">
                    					<input  style="margin-left:50px" type="reset" value="重置"  class="btn">
                    				</td>
                    			</tr>
                    		</table>
                    	
						
					</form>
					<script src="<%=request.getContextPath()%>/UI/simple/js/zebra.datepicker.min.js"></script>
					<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/zebra.datepicker.css">
					<script>
						$('input.datepicker').Zebra_DatePicker({
							default_position: 'below',
							show_clear_date: false,
							show_select_today: false,
						});
					</script>
                 
					
					</div>
				</div>
    </div>

		<%@include file="../UI/simple/template/UserRight.jsp" %>
		<%@include file="../UI/simple/template/Footer.jsp" %>
		
	
	
		
	<script>
	var cuisineLevel;
	var cuisineSystem;
	var cuisineKind;
	var orderPrice=0.0;
	function changelevel(){
		cuisineLevel=$("#cuisinelevel").val();
	}
	function changesystem(){
		cuisineSystem=$("#cuisinesystem").val();
	}
	function changekind(){
		cuisineKind=$("#cuisinekind").val();
		if(cuisineLevel=="中档"){
			if(cuisineKind=="四菜一汤")
				orderPrice=150.0;
			if(cuisineKind=="六菜一汤")
				orderPrice=200.0;
			if(cuisineKind=="八菜一汤")
				orderPrice=280.0;
			if(cuisineKind=="十菜两汤")
				orderPrice=300.0;
			if(cuisineKind=="十二菜两汤")
				orderPrice=600.0;	
			if(cuisineKind=="十五菜两汤")
				orderPrice=800.0;			
		}
		if(cuisineLevel=="高档"){
			if(cuisineKind=="四菜一汤")
				orderPrice=350.0;
			if(cuisineKind=="六菜一汤")
				orderPrice=400.0;
			if(cuisineKind=="八菜一汤")
				orderPrice=580.0;
			if(cuisineKind=="十菜两汤")
				orderPrice=800.0;
			if(cuisineKind=="十二菜两汤")
				orderPrice=1000.0;	
			if(cuisineKind=="十五菜两汤")
				orderPrice=1200.0;		
		}
		var showbookprice=orderPrice/2;
		$("#showprice").html("￥"+orderPrice);
		$("#showbookedprice").html("需要支付￥"+showbookprice+"元订金");
		$("#price").val(orderPrice);
		
	}
	
	


						
	
	$(function() {
	    var mydate = new Date();
	    $("input").css("height","30px");
/*	    $("#footer1").css("margin-left","15px");
	    $("#footer2").css("margin-left","10px");
	    $("#footer3").css("margin-left","10px"); */ 
		$("#servetime").attr("value",mydate.toLocaleDateString());
        $("#createOrder").addClass("active");
        // showMessageBox();
          $.ajax({
         url:"<%=request.getContextPath()%>/publicAction/getProvinceAction",
               type:"post",
               dataType:"json",
               data:"",
               aysnc:true,
               success:function(data) {
               $("#province").empty();
               for(var i=0;i<data.length;i++)
               {
               	var code=data[i].provinceCode;
               	var name=data[i].provinceName;
               	$("#province").append("<option value="+code+">"+name+"</option>");
               	//console.log($("#province").val());              	
               }
               $("#province").val(data[0].provinceCode);	
			   getCity();	
			   						                   						                   
               },
               error:function(ajax) {
                   alert("获取省份信息失败！");
               }
           }) ; 
	});
	function getCity(){				
				     $.ajax({
				         	url:"<%=request.getContextPath()%>/publicAction/getCityAction",
				               type:"post",
				               dataType:"json",
				               data:{"type":0,"provinceCode":$("#province").val()},
				               aysnc:true,
				               success:function(data) {
				                $("#city").empty();
				               for(var i=0;i<data.length;i++)
				               {
				               	var cityCode=data[i].cityCode;
				               	var cityName=data[i].cityName;
				               	$("#city").append("<option value="+cityCode+">"+cityName+"</option>");
				               }
				                $("#city").val(data[0].cityCode);
				                getArea();													                   						                   
				               },
				               error:function(ajax) {
				                   alert("获取城市信息失败！");
				               }
				           }) ;		
	}
	function getArea(){
	     $.ajax({
         url:"<%=request.getContextPath()%>/publicAction/getAreaAction",
               type:"post",
               dataType:"json",
               data:{"type":0,"cityCode":$("#city").val()},
               aysnc:true,
               success:function(data) {
                 $("#area").empty();
               for(var i=0;i<data.length;i++)
               {
               	var areaCode=data[i].areaCode;
               	var areaName=data[i].areaName;
               	$("#area").append("<option value="+areaCode+">"+areaName+"</option>");
               }												                   						                   
               },
               error:function(ajax) {
                   alert("获取城市信息失败！");
               }
           }) ;
         
	}
	
	 $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	function check() {
        		var username = $("#username").val();
        		var tel = $("#tel").val();
        		var time=$("#dtp_input1").val();
        		var place=$("#place").val();       		  	
        		var haserror=false;
        		
        		if(username == "") {
        			$("#usernameInfo > font").text("请输入名字");
        			haserror=true;
        		}
        		else {
        			$("#usernameInfo > font").text("");
        		}
        		if(tel == "") {
        			$("#telInfo > font").text("请输入手机号码");
        			haserror=true;
        		}
        			else {
        				$("#telInfo > font").text("");
        			}
        		if(time == "") {
        			$("#timeInfo > font").text("请输入时间");
        			haserror=true;
        		}
        		else {
        			$("#timeInfo > font").text("");
        		}
        		if(place == "") {
        			$("#placeInfo > font").text("请输入地点");
        			haserror=true;
        		}
        			else {
        				$("#placeInfo > font").text("");
        			}
        		if(orderPrice/2>leftprice&&document.getElementById("yue").checked==true){
        			$("#priceInfo > font").text("余额不足！");
        			haserror=true;
        		}
        			else {
        				$("#priceInfo > font").text("");
        			}
        		
        		       		
        		if(!haserror)
        			{
        				  $.ajax({
					          url:"<%=request.getContextPath()%>/user/createOrderAction",
					                type:"post",
					                dataType:"json",
					                data:$("#myform").serialize(),
					                aysnc:true,
					                success:function(data) {
					                   //alert("成功！"); 
					                   //showMessageBox();
					                   //var strArray=eval(data);
					                   $("#myform")[0].reset();
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
        				//document.myform.submit();
        				//alert("do something");
        				//setTimeout("showMessageBox()",4000); 
        			}
        	 	else 
        	 		return;
        	 		
		
        
        	}
 

 
	</script>
</body>
</html>
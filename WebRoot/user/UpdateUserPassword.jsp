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
<div class="pull-right">
				<div class="user-content__box clearfix bgf">
					<div class="title">账户信息-更改密码</div>
					
					<form  class="user-setting__form"  name="myform"  id="myform" role="form">
						<div class="user-form-group">
							<label for="user-id">密码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<input  class="form-control" type="password" id="password1"  name="password1"  placeholder="请输入新的密码" >
                            	<span id="passwordInfo1"><font color="red"></font></span>						
						</div>
						<div class="user-form-group">
							<label for="user-id">再次输入密码：</label>
							<input  class="form-control" type="password" id="password2"  name="password2"  placeholder="请再次输入密码" >
							
							<span id="passwordInfo2"><font color="red"></font></span>
						</div>
						<div class="user-form-group" style="margin-top:10px;">
							<input  type="button" value="确认修改" onclick="check()" class="btn">
						<input  style="margin-left:50px" type="reset" value="重置"  class="btn">
						</div>
						
					</form>
					<b><font color="red" size="4"><s:actionerror /></font></b>
				</div>
			</div>
		</section>
	</div>
	
	
		<%@include file="../UI/simple/template/UserRight.jsp" %>
		<%@include file="../UI/simple/template/Footer.jsp" %>
	
	<script type="text/javascript">
	$(function(){
	$("#updatePasswordPage").addClass("active");
	});
	function check(){
	
	var password1=$("#password1").val();
	var password2=$("#password2").val();
	if(password1 == ""){
	$("#passwordInfo1 > font").text("请输入密码！");
	}
	else 
	$("#passwordInfo1 > font").text("");
	
	if(password2 == ""){
	$("#passwordInfo2 > font").text("请再次输入密码！");
	}
	else
	$("#passwordInfo2 > font").text("");
	
	if(password1 != password2){		
	$("#passwordInfo2 > font").text("两次输入的密码不一致！");
	}
	else
	$("#passwordInfo2 > font").text("");
	if(password1 != "" && password2 != "" && password1 == password2)
	{
	  $.ajax({
					          url:"<%=request.getContextPath()%>/user/changePassword",
					                type:"post",
					                dataType:"json",
					                data:$("#myform").serialize(),
					                aysnc:true,
					                success:function(data) {
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
					                    alert("更改密码失败！");
					                }
					            }) ; 
	}
	
	
	}
	
	
	</script>
</body>
</html>